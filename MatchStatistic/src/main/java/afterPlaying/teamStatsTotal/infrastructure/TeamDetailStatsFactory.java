package afterPlaying.teamStatsTotal.infrastructure;

import java.util.List;

import afterPlaying.teamStatsTotal.IDetailStats;
import afterPlaying.teamStatsTotal.core.DetailStats;
import afterPlaying.teamStatsTotal.core.EventType;
import afterPlaying.teamStatsTotal.core.Localization;
import playing.core.model.Event;
import playing.core.model.Events;
import playing.core.model.MatchPlayedInfo;

public class TeamDetailStatsFactory {

	public static IDetailStats getInstance(EventType ev, Localization loc, List<MatchPlayedInfo> matches) {
		switch(ev) {
		case GOALS_GAINED:
			return new DetailStats(matches, loc.pred, Events::getEventsFromMatch, Event.isGoal());
		case GOALS_LOST:
			return new DetailStats(matches, loc.pred, Events::getEventsFromMatchForTheOpponent, Event.isGoal());
		case YELLOW_CARDS_GAINED:
			return new DetailStats(matches, loc.pred, Events::getEventsFromMatch, Event.isYellowCard());
		case YELLOW_CARDS_LOST:
			return new DetailStats(matches, loc.pred, Events::getEventsFromMatchForTheOpponent, Event.isYellowCard());
		}
		return null;
	}
	
}
