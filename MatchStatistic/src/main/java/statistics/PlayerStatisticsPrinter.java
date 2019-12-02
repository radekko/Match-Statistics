package statistics;

import java.util.List;
import java.util.function.Function;

import model.MatchPlayedInfo;
import model.Player;
import model.Team;
import model.Event.EventSnapshot;

public class PlayerStatisticsPrinter {
	private final PlayerStats playerStats;

	public PlayerStatisticsPrinter(PlayerStats playerStats) {
		this.playerStats = playerStats;
	}
	
	public void print(Team team, List<Player> player) {
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
		System.out.println("Total goals");
		System.out.println("*************************");
		player.stream().forEach(p -> printPlayerAllGoals(team, p));
		
	}
	
	private void printPlayerAllGoals(Team team, Player player) {
		List<EventSnapshot> sumOfEvents = playerStats.getSumOfEvents(team, player, MatchPlayedInfo::getHomeGoals, MatchPlayedInfo::getAwayGoals);
		if(!sumOfEvents.isEmpty())
			System.out.println(sumOfEvents);
	}

	private void printPlayerHomeGoals(Team team, Player player) {
		printOne(team, player, PlaceOfPlaying.HOME, MatchPlayedInfo::getHomeGoals);
	}
	
	private void printPlayerAwayGoals(Team team, Player player) {
		printOne(team, player, PlaceOfPlaying.AWAY, MatchPlayedInfo::getAwayGoals);
	}

	public void printOne(Team team, Player player, PlaceOfPlaying place, Function<MatchPlayedInfo, List<EventSnapshot>> whichStats) {
		List<EventSnapshot> stats = playerStats.getHomeOrAwayEvents(team, player, place, whichStats);
		if(!stats.isEmpty()) {
			System.out.println("Goals for player: " + player.getId());
			System.out.println(stats);
		}
	}

}
