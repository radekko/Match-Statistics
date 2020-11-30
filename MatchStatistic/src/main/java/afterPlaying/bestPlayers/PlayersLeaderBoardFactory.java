package afterPlaying.bestPlayers;

import afterPlaying.teamStatsByPlayers.TeamByPlayerLeaderBoardFactory;
import afterPlaying.teamStatsByPlayers.TeamByPlayerLeaderBoardFactory.TeamByPlayerLeaderBoardType;

public class PlayersLeaderBoardFactory {
	public enum PlayerLeaderBoardType{
		PLAYER_HOME_GOALS, PLAYER_AWAY_GOALS, PLAYER_BOTH_GOALS //POSSIBLE PLAYER_YELLOW_CARDS_HOME ...
	}
	
	public static PlayersLeaderBoard getInstance(PlayerLeaderBoardType t) {
		switch(t) {
		case PLAYER_BOTH_GOALS:
			return new PlayersLeaderBoard(TeamByPlayerLeaderBoardFactory.getInstance(TeamByPlayerLeaderBoardType.BOTH_GOALS));
		case PLAYER_AWAY_GOALS:
			return new PlayersLeaderBoard(TeamByPlayerLeaderBoardFactory.getInstance(TeamByPlayerLeaderBoardType.AWAY_GOALS));
		case PLAYER_HOME_GOALS:
			return new PlayersLeaderBoard(TeamByPlayerLeaderBoardFactory.getInstance(TeamByPlayerLeaderBoardType.HOME_GOALS));
		}
		return null;
	}
}
