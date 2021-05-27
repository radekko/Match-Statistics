package afterPlaying.bestTeams.infrastructure;

import java.util.List;

import afterPlaying.bestTeams.ITeamsLeaderBoard;
import afterPlaying.bestTeams.core.TeamsLeaderBoard;
import afterPlaying.teamStatsTotal.core.EventType;
import afterPlaying.teamStatsTotal.core.Localization;
import afterPlaying.teamStatsTotal.infrastructure.TeamDetailStatsFactory;
import playing.core.model.MatchPlayedInfo;

public class TeamsLeaderBoardFactory {
	
	public enum LeaderBoardType{
		GOALS_TOTAL, YELLOW_CARDS_TOTAL // Possible GOALS -HOME -AWAY, YELLOW CARDS -HOME -AWAY
	}
	
	public static ITeamsLeaderBoard getTeamsLeaderBoard(LeaderBoardType type, List<MatchPlayedInfo> matches) {
		switch(type) {
		case GOALS_TOTAL:
			return new TeamsLeaderBoard(TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.BOTH, matches));
		case YELLOW_CARDS_TOTAL:
			return new TeamsLeaderBoard(TeamDetailStatsFactory.getInstance(EventType.YELLOW_CARDS_GAINED, Localization.BOTH, matches));
		}
		return null;
	}
}
