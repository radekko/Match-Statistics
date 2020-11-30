package afterPlaying.teamStatsByPlayers;

import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory.EventType;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory.Localization;

public class TeamByPlayerLeaderBoardFactory {
	
	public enum TeamByPlayerLeaderBoardType{
		HOME_GOALS, AWAY_GOALS, BOTH_GOALS //POSSIBLE YELLOW_CARDS_HOME ...
	}
	
	public static TeamByPlayerLeaderBoard getInstance(TeamByPlayerLeaderBoardType t) {
		switch(t) {
		case BOTH_GOALS:
			return new TeamByPlayerLeaderBoard(TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.BOTH));
		case HOME_GOALS:
			return new TeamByPlayerLeaderBoard(TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.HOME));
		case AWAY_GOALS:
			return new TeamByPlayerLeaderBoard(TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.AWAY));
		}
		return null;
	}
}
