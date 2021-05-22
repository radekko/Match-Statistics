package afterPlaying.teamStatsTotal;

import java.util.List;

import playing.core.model.MatchPlayedInfo;

public class TeamDetailStatsFactory {

	public static DetailStats getInstance(EventType ev, Localization loc, List<MatchPlayedInfo> matches) {
		switch(ev) {
		case GOALS_GAINED:
			return new TeamDetailStats(new GoalsFilter(matches, loc.pred));
		case GOALS_LOST:
			return new TeamDetailStats(new LostGoalsFilter(matches, loc.pred));
		case YELLOW_CARDS_GAINED:
			return new TeamDetailStats(new YellowCardsFilter(matches, loc.pred));
		case YELLOW_CARDS_LOST:
			return new TeamDetailStats(new LostYellowCardsFilter(matches, loc.pred));
		}
		return null;
	}
	
}
