package afterPlaying.filters.findEventsByLocalisation;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public class VsTeamHomeOrAwayMatchesFilter implements EventLocalizationFilter{
	private VsTeamHomeMatchesEventFilter vsHomeMatchesEventFinder;
	private VsTeamAwayMatchesEventFilter vsAwayMatchesEventFinder;
	
	public VsTeamHomeOrAwayMatchesFilter() {
		this.vsHomeMatchesEventFinder = new VsTeamHomeMatchesEventFilter();
		this.vsAwayMatchesEventFinder = new VsTeamAwayMatchesEventFilter();
	}

	@Override
	public Stream<EventSnapshot> getMatchesEventInChosenLocalization(List<MatchPlayedInfo> matches, Team team, Predicate<Event> eventType) {
		 Stream<EventSnapshot> homeEvents = vsHomeMatchesEventFinder.getMatchesEventInChosenLocalization(matches, team, eventType);
		 Stream<EventSnapshot> awayEvents = vsAwayMatchesEventFinder.getMatchesEventInChosenLocalization(matches, team, eventType);
		 return Stream.concat(homeEvents, awayEvents);
	}
}
