package afterPlaying.teamStatsByPlayers;

import java.util.List;

import afterPlaying.teamStatsTotal.EventType;
import afterPlaying.teamStatsTotal.Localization;
import afterPlaying.teamStatsTotal.TeamDetailStatsFactory;
import playing.core.model.MatchPlayedInfo;

public class TeamByPlayerLeaderBoardFactory {
	
	public enum TeamByPlayerLeaderBoardType{
		HOME_GOALS, AWAY_GOALS, BOTH_GOALS //POSSIBLE YELLOW_CARDS_HOME ...
	}
	
	public static TeamByPlayerLeaderBoard getInstance(TeamByPlayerLeaderBoardType t, List<MatchPlayedInfo> matches) {
		switch(t) {
		case BOTH_GOALS:
			return new TeamByPlayerLeaderBoard(TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.BOTH, matches));
		case HOME_GOALS:
			return new TeamByPlayerLeaderBoard(TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.HOME, matches));
		case AWAY_GOALS:
			return new TeamByPlayerLeaderBoard(TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.AWAY, matches));
		}
		return null;
	}
}
