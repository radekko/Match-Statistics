package afterPlaying.filters.findEventsByLocalization;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public class TeamHomeOrAwayMatchesFilter implements EventLocalizationFilter{
	private TeamHomeMatchesEventFilter homeMatchesEventFinder;
	private TeamAwayMatchesEventFilter awayMatchesEventFinder;
	
	public TeamHomeOrAwayMatchesFilter() {
		this.homeMatchesEventFinder = new TeamHomeMatchesEventFilter();
		this.awayMatchesEventFinder = new TeamAwayMatchesEventFilter();
	}

	@Override
	public Stream<EventSnapshot> getMatchesInChosenLocalization(List<MatchPlayedInfo> matches, Team team, Predicate<Event> eventType) {
		 Stream<EventSnapshot> homeEvents = homeMatchesEventFinder.getMatchesInChosenLocalization(matches, team, eventType);
		 Stream<EventSnapshot> awayEvents = awayMatchesEventFinder.getMatchesInChosenLocalization(matches, team, eventType);
		 return Stream.concat(homeEvents, awayEvents);
	}

}
