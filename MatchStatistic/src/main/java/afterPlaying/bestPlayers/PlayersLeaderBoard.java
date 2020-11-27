package afterPlaying.bestPlayers;

import java.util.List;
import java.util.function.Function;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import afterPlaying.teamByPlayers.TeamByPlayerLeaderBoard;
import beforePlaying.Player;
import beforePlaying.Team;

public class PlayersLeaderBoard {
	private final TeamByPlayerLeaderBoard teamByPlayerLeaderBoard;
	private final List<Team> allTeams;

	public PlayersLeaderBoard(TeamByPlayerLeaderBoard teamByPlayerLeaderBoard, List<Team> allTeams) {
		this.teamByPlayerLeaderBoard = teamByPlayerLeaderBoard;
		this.allTeams = allTeams;
	}

	public Multimap<Integer, Player> createHomeLeaderBoard() {
		return createLeaderBoard(teamByPlayerLeaderBoard::createHomeLeaderBoard);
	}
	
	public Multimap<Integer, Player> createAwayLeaderBoard() {
		return createLeaderBoard(teamByPlayerLeaderBoard::createAwayLeaderBoard);
	}
	
	public Multimap<Integer, Player> createTotalLeaderBoard() {
		return createLeaderBoard(teamByPlayerLeaderBoard::createTotalLeaderBoard);
	}
	
	private Multimap<Integer, Player> createLeaderBoard(Function<Team, Multimap<Integer, Player>> funExtractingTeamLeaderBoard) {
		Multimap<Integer, Player> leaderboardSum = TreeMultimap.create();
		allTeams.stream().forEach(team -> leaderboardSum.putAll(funExtractingTeamLeaderBoard.apply(team)));
		return leaderboardSum;
	}

	public String statDescription() {
		return teamByPlayerLeaderBoard.statDescription();
	}
}
