package afterPlaying.bestPlayers;

import java.util.List;

import afterPlaying.teamStatsByPlayers.TeamByPlayerLeaderBoardFactory;
import afterPlaying.teamStatsByPlayers.TeamByPlayerLeaderBoardFactory.TeamByPlayerLeaderBoardType;
import playing.core.model.MatchPlayedInfo;

public class PlayersLeaderBoardFactory {
	public enum PlayerLeaderBoardType{
		PLAYER_HOME_GOALS, PLAYER_AWAY_GOALS, PLAYER_BOTH_GOALS //POSSIBLE PLAYER_YELLOW_CARDS_HOME ...
	}
	
	public static PlayersLeaderBoard getInstance(PlayerLeaderBoardType t, List<MatchPlayedInfo> matches) {
		switch(t) {
		case PLAYER_BOTH_GOALS:
			return new PlayersLeaderBoard(TeamByPlayerLeaderBoardFactory.getInstance(TeamByPlayerLeaderBoardType.BOTH_GOALS, matches));
		case PLAYER_AWAY_GOALS:
			return new PlayersLeaderBoard(TeamByPlayerLeaderBoardFactory.getInstance(TeamByPlayerLeaderBoardType.AWAY_GOALS, matches));
		case PLAYER_HOME_GOALS:
			return new PlayersLeaderBoard(TeamByPlayerLeaderBoardFactory.getInstance(TeamByPlayerLeaderBoardType.HOME_GOALS, matches));
		}
		return null;
	}
}
