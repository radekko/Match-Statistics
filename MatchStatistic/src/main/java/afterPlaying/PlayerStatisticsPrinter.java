package afterPlaying;

import java.util.List;

import beforePlaying.Player;
import beforePlaying.Team;

public class PlayerStatisticsPrinter {
	private final SinglePlayerStats playerStats;

	public PlayerStatisticsPrinter(SinglePlayerStats playerStats) {
		this.playerStats = playerStats;
	}
	
	public void printAll(Team team, List<Player> player) {
		System.out.println("-------------------------------------------------");
		System.out.println("Players statistic for " + team);
    	System.out.println("-------------------------------------------------");
    	
    	System.out.println("Home goals");
    	System.out.println("*************************");
		player.stream().forEach(p -> printPlayerHomeGoals(team, p));
		System.out.println();
		System.out.println("Away goals");
		System.out.println("*************************");
		player.stream().forEach(p -> printPlayerAwayGoals(team, p));
		System.out.println();
		System.out.println("Total goals");
		System.out.println("*************************");
		player.stream().forEach(p -> printPlayerAllGoals(team, p));
		
	}

	private void printPlayerAllGoals(Team team, Player p) {
		System.out.println("Player id: " + p.getId() + " - " + playerStats.getPlayerTotalGoals(p, team));
	}

	private void printPlayerAwayGoals(Team team, Player p) {
		System.out.println("Player id: " + p.getId() + " - " + playerStats.getPlayerAwayGoals(p, team));
	}

	private void printPlayerHomeGoals(Team team, Player p) {
		System.out.println("Player id: " + p.getId() + " - " + playerStats.getPlayerHomeGoals(p, team));
	}

}

