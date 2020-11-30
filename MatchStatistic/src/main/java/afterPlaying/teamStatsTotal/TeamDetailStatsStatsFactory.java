package afterPlaying.teamStatsTotal;

import afterPlaying.filters.findEventsByLocalisation.EventLocalizationFilter;
import afterPlaying.filters.findEventsByLocalisation.TeamAwayMatchesEventFilter;
import afterPlaying.filters.findEventsByLocalisation.TeamHomeMatchesEventFilter;
import afterPlaying.filters.findEventsByLocalisation.TeamHomeOrAwayMatchesFilter;
import afterPlaying.filters.findEventsByLocalisation.VsTeamAwayMatchesEventFilter;
import afterPlaying.filters.findEventsByLocalisation.VsTeamHomeMatchesEventFilter;
import afterPlaying.filters.findEventsByLocalisation.VsTeamHomeOrAwayMatchesFilter;
import afterPlaying.filters.findEventsByTeam.GoalsFilter;
import afterPlaying.filters.findEventsByTeam.YellowCardsFilter;

public class TeamDetailStatsStatsFactory {

	public enum EventType{
		GOALS, YELLOW_CARDS
	}
	
	public enum Localization{
		HOME, AWAY, BOTH, VS_HOME, VS_AWAY, VS_BOTH
	}
	
	public static DetailStats getInstance(EventType ev, Localization loc) {
		switch(loc) {
		case HOME:
			return prepareAction(ev, new TeamHomeMatchesEventFilter());
		case AWAY:
			return prepareAction(ev, new TeamAwayMatchesEventFilter());
		case BOTH:
			return prepareAction(ev, new TeamHomeOrAwayMatchesFilter());
		case VS_HOME:
			return prepareAction(ev, new VsTeamHomeMatchesEventFilter());
		case VS_AWAY:
			return prepareAction(ev, new VsTeamAwayMatchesEventFilter());
		case VS_BOTH:
			return prepareAction(ev, new VsTeamHomeOrAwayMatchesFilter());	
		}
		return null;
	}

	private static DetailStats prepareAction(EventType ev, EventLocalizationFilter localizationFilter) {
		switch(ev) {
		case GOALS:
			return new TeamDetailStats(new GoalsFilter(localizationFilter));
		case YELLOW_CARDS:
			return new TeamDetailStats(new YellowCardsFilter(localizationFilter));
		}
		return null;
	}
	
}
