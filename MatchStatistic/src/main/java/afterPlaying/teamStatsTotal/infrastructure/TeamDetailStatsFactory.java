package afterPlaying.teamStatsTotal.infrastructure;

import java.util.List;

import afterPlaying.teamStatsTotal.IDetailStats;
import afterPlaying.teamStatsTotal.core.DetailStats;
import afterPlaying.teamStatsTotal.core.EventType;
import afterPlaying.teamStatsTotal.core.Localization;
import playing.core.model.Event;
import playing.core.model.MatchPlayedInfo;

public class TeamDetailStatsFactory {

	public static IDetailStats getInstance(EventType ev, Localization loc, List<MatchPlayedInfo> matches) {
		switch(ev) {
		case GOALS_GAINED:
			return new DetailStats(matches, loc.pred, (e,t) -> e.getEventsFromMatch(t), Event.isGoal());
		case GOALS_LOST:
			return new DetailStats(matches, loc.pred, (e,t) -> e.getEventsFromMatchForOpponent(t), Event.isGoal());
		case YELLOW_CARDS_GAINED:
			return new DetailStats(matches, loc.pred, (e,t) -> e.getEventsFromMatch(t), Event.isYellowCard());
		case YELLOW_CARDS_LOST:
			return new DetailStats(matches, loc.pred, (e,t) -> e.getEventsFromMatchForOpponent(t), Event.isYellowCard());
		}
		return null;
	}
	
}
