package afterPlaying.bestTeams;

import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory.EventType;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory.Localization;

public class TeamsLeaderBoardFactory {
	
	public enum LeaderBoardType{
		GOALS_TOTAL, YELLOW_CARDS_TOTAL // Possible GOALS -HOME -AWAY, YELLOW CARDS -HOME -AWAY
	}
	
	public static TeamsLeaderBoard getTeamsLeaderBoard(LeaderBoardType lt) {
		switch(lt) {
		case GOALS_TOTAL:
			return new TeamsLeaderBoard(TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.BOTH));
		case YELLOW_CARDS_TOTAL:
			return new TeamsLeaderBoard(TeamDetailStatsStatsFactory.getInstance(EventType.YELLOW_CARDS, Localization.BOTH));
		}
		return null;
	}
}
