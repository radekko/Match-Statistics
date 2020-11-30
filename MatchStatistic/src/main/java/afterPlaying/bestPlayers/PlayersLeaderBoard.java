package afterPlaying.bestPlayers;

import java.util.List;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import afterPlaying.teamStatsByPlayers.TeamByPlayerLeaderBoard;
import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class PlayersLeaderBoard {
	private final TeamByPlayerLeaderBoard teamByPlayerLeaderBoard;

	public PlayersLeaderBoard(TeamByPlayerLeaderBoard teamByPlayerLeaderBoard) {
		this.teamByPlayerLeaderBoard = teamByPlayerLeaderBoard;
	}

	public Multimap<Integer, Player> createLeaderBoard(List<MatchPlayedInfo> matches, List<Team> teams){
		Multimap<Integer, Player> leaderboardSum = TreeMultimap.create();
		teams.stream().forEach(team -> leaderboardSum.putAll(createLeaderBoardForTeam(matches, team)));
		return leaderboardSum;
	}

	private Multimap<Integer, Player> createLeaderBoardForTeam(List<MatchPlayedInfo> matches, Team t) {
		return teamByPlayerLeaderBoard.createLeaderBoard(matches, t);
	}
	
}
