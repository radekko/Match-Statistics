package afterPlaying.bestPlayers.core;

import java.util.List;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import afterPlaying.bestPlayers.IPlayersLeaderBoard;
import afterPlaying.teamStatsByPlayers.ITeamByPlayerLeaderBoard;
import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class PlayersLeaderBoard implements IPlayersLeaderBoard {
	private final ITeamByPlayerLeaderBoard teamByPlayerLeaderBoard;

	public PlayersLeaderBoard(ITeamByPlayerLeaderBoard teamByPlayerLeaderBoard) {
		this.teamByPlayerLeaderBoard = teamByPlayerLeaderBoard;
	}

	@Override
	public Multimap<Integer, Player> createLeaderBoard(List<MatchPlayedInfo> matches, List<Team> teams){
		Multimap<Integer, Player> leaderboardSum = TreeMultimap.create();
		teams.stream().forEach(team -> leaderboardSum.putAll(createLeaderBoardForTeam(matches, team)));
		return leaderboardSum;
	}

	private Multimap<Integer, Player> createLeaderBoardForTeam(List<MatchPlayedInfo> matches, Team t) {
		return teamByPlayerLeaderBoard.createLeaderBoard(matches, t);
	}
	
}
