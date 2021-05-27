package afterPlaying.teamStatsTotal.core;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import afterPlaying.teamStatsTotal.IDetailStats;
import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.Event.EventSnapshot;
import playing.core.model.Events;
import playing.core.model.MatchPlayedInfo;

public class DetailStats implements IDetailStats{
	private final MatchPlayedInfoLocalizationPredicate pred;
	private final List<MatchPlayedInfo> matches;
	private final BiFunction<Events, Team, Stream<Event>> chosenTeamEventsExtractor;
	private final Predicate<Event> eventFilter;

	public DetailStats(List<MatchPlayedInfo> matches, MatchPlayedInfoLocalizationPredicate pred,
			BiFunction<Events, Team, Stream<Event>> chosenTeamEventsExtractor, Predicate<Event> eventFilter) {
		this.pred = pred;
		this.matches = matches;
		this.chosenTeamEventsExtractor = chosenTeamEventsExtractor;
		this.eventFilter = eventFilter;
	}

	@Override
	public Stream<EventSnapshot> getEvents(Team team) {
		return matches.stream()
				.filter(pred.localization(team))
				.map(MatchPlayedInfo::getEvents)
				.flatMap(e -> chosenTeamEventsExtractor.apply(e, team))
				.filter(eventFilter)
				.map(Event::prepareSnapshot);
	}
	
	@Override
	public int getTotalStat(Team team) {
		return (int) getEvents(team).count();
	}

}
