package afterPlaying.filters.findEventsByLocalization;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import afterPlaying.filters.findMatchesInfoByTeam.AwayMatchesFilter;
import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public class TeamAwayMatchesEventFilter implements EventLocalizationFilter{

	@Override
	public Stream<EventSnapshot> getMatchesInChosenLocalization(List<MatchPlayedInfo> matches, Team team, Predicate<Event> eventType) {
		return new AwayMatchesFilter().getMatchesInChosenLocalization(matches, team)
				.flatMap(m -> m.findEventsForAwayTeam(eventType));
	}
	
}
