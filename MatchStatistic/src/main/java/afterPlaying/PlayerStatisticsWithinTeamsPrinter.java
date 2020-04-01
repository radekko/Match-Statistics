package afterPlaying;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import beforePlaying.Player;
import beforePlaying.Team;

public class PlayerStatisticsWithinTeamsPrinter {
	private final TeamStatsByPlayers playerStats;

	public PlayerStatisticsWithinTeamsPrinter(TeamStatsByPlayers playerStats) {
		this.playerStats = playerStats;
	}
	
	public void printAll(Team team) {
		System.out.println("-------------------------------------------------");
		System.out.println("Players statistic for " + team);
    	System.out.println("-------------------------------------------------");
    	
    	System.out.println("Home goals");
    	System.out.println("*************************");
    	printPlayersLeaderboard(playerStats.getPlayerHomeGoals(team));
		System.out.println();
		
		System.out.println("Away goals");
		System.out.println("*************************");
		printPlayersLeaderboard(playerStats.getPlayerAwayGoals(team));
		System.out.println();
		
		System.out.println("Total goals");
		System.out.println("*************************");
		printPlayersLeaderboard(playerStats.getPlayerTotalGoals(team));
		System.out.println();
	}

	private void printPlayersLeaderboard(Multimap<Integer, Player> resultMap) {
		noGoals(resultMap);
			
		Set<Integer> numberOfGoalsDesceding = new TreeSet<>(resultMap.keySet()).descendingSet();
		for(Integer num : numberOfGoalsDesceding) {
			System.out.print(num + " goals: ");
			System.out.println(resultMap.get(num).stream().map(String::valueOf).collect(Collectors.joining(", ")));
		}
	}

	private void noGoals(Multimap<Integer, Player> resultMap) {
		if(resultMap.isEmpty()) {
			System.out.println("0 goals");
			return;
		}
	}

}