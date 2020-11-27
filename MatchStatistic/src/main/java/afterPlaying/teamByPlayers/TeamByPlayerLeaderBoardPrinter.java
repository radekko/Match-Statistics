package afterPlaying.teamByPlayers;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import beforePlaying.Player;
import beforePlaying.Team;

public class TeamByPlayerLeaderBoardPrinter {
	private final TeamByPlayerLeaderBoard teamByPlayerLeaderBoard;
	private final String statDesc;

	public TeamByPlayerLeaderBoardPrinter(TeamByPlayerLeaderBoard teamByPlayerLeaderBoard) {
		this.teamByPlayerLeaderBoard = teamByPlayerLeaderBoard;
		this.statDesc = teamByPlayerLeaderBoard.statDescription();
	}
	
	public void printAll(Team team) {
		System.out.println("-------------------------------------------------");
		System.out.println("Players statistic for " + team);
    	System.out.println("-------------------------------------------------");
    	
    	System.out.println("Home " + statDesc);
    	System.out.println("*************************");
    	printPlayersLeaderboard(teamByPlayerLeaderBoard.createHomeLeaderBoard(team));
		System.out.println();
		
		System.out.println("Away " + statDesc);
		System.out.println("*************************");
		printPlayersLeaderboard(teamByPlayerLeaderBoard.createAwayLeaderBoard(team));
		System.out.println();
		
		System.out.println("Total " + statDesc);
		System.out.println("*************************");
		printPlayersLeaderboard(teamByPlayerLeaderBoard.createTotalLeaderBoard(team));
		System.out.println();
	}

	private void printPlayersLeaderboard(Multimap<Integer, Player> resultMap) {
		noStats(resultMap);
			
		Set<Integer> numberOfGoalsDesceding = new TreeSet<>(resultMap.keySet()).descendingSet();
		for(Integer num : numberOfGoalsDesceding) {
			System.out.print(num + ": ");
			System.out.println(resultMap.get(num).stream().map(String::valueOf).collect(Collectors.joining(", ")));
		}
	}

	private void noStats(Multimap<Integer, Player> resultMap) {
		if(resultMap.isEmpty()) {
			System.out.println("0 " + statDesc);
			return;
		}
	}
}
