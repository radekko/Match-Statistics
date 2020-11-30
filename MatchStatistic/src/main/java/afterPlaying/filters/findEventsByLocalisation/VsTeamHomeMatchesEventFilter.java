package afterPlaying.filters.findEventsByLocalisation;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import afterPlaying.filters.findMatchesInfoByTeam.HomeMatchesFilter;
import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public class VsTeamHomeMatchesEventFilter implements EventLocalizationFilter{
	@Override
	public Stream<EventSnapshot> getMatchesEventInChosenLocalization(List<MatchPlayedInfo> matches, Team team, Predicate<Event> eventType) {
		return new HomeMatchesFilter().getMatchesInChosenLocalization(matches, team)
				.flatMap(m -> m.findEventsForAwayTeam(eventType));
	}
}
