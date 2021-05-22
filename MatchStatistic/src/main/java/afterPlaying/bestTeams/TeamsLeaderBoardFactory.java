package afterPlaying.bestTeams;

import java.util.List;

import afterPlaying.teamStatsTotal.EventType;
import afterPlaying.teamStatsTotal.Localization;
import afterPlaying.teamStatsTotal.TeamDetailStatsFactory;
import playing.core.model.MatchPlayedInfo;

public class TeamsLeaderBoardFactory {
	
	public enum LeaderBoardType{
		GOALS_TOTAL, YELLOW_CARDS_TOTAL // Possible GOALS -HOME -AWAY, YELLOW CARDS -HOME -AWAY
	}
	
	public static TeamsLeaderBoard getTeamsLeaderBoard(LeaderBoardType type, List<MatchPlayedInfo> matches) {
		switch(type) {
		case GOALS_TOTAL:
			return new TeamsLeaderBoard(TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.BOTH, matches));
		case YELLOW_CARDS_TOTAL:
			return new TeamsLeaderBoard(TeamDetailStatsFactory.getInstance(EventType.YELLOW_CARDS_GAINED, Localization.BOTH, matches));
		}
		return null;
	}
}
