package afterPlaying.teamStatsTotal;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.Event.EventSnapshot;
import playing.core.model.MatchPlayedInfo;

abstract class AbstractEventTypeFilter implements EventTypeFilter{
	private final MatchPlayedInfoPredicate pred;
	private final List<MatchPlayedInfo> matches;
	
	public AbstractEventTypeFilter(List<MatchPlayedInfo> matches, MatchPlayedInfoPredicate pred) {
		this.pred = pred;
		this.matches = matches;
	}

	@Override
	public Stream<EventSnapshot> getStats(Team team){
		return matches.stream()
				.filter(pred.localization(team))
				.flatMap(m -> teamEventFilter(m,team))
				.filter(eventType())
				.map(Event::prepareSnapshot);
	}
	
	protected abstract Predicate<Event> eventType();
	protected abstract Stream<Event> teamEventFilter(MatchPlayedInfo m, Team team);
}
