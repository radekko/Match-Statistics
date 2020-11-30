package afterPlaying.filters.findEventsByLocalisation;

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
	public Stream<EventSnapshot> getMatchesEventInChosenLocalization(List<MatchPlayedInfo> matches, Team team, Predicate<Event> eventType) {
		 Stream<EventSnapshot> homeEvents = homeMatchesEventFinder.getMatchesEventInChosenLocalization(matches, team, eventType);
		 Stream<EventSnapshot> awayEvents = awayMatchesEventFinder.getMatchesEventInChosenLocalization(matches, team, eventType);
		 return Stream.concat(homeEvents, awayEvents);
	}

}
