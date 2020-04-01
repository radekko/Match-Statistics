package afterPlaying;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import beforePlaying.Player;

public class PlayerStatisticsPrinter {
	private final PlayersStats playersStats;

	public PlayerStatisticsPrinter(PlayersStats playersStats) {
		this.playersStats = playersStats;
	}
	
	public void printAll() {
		System.out.println("-------------------------------------------------");
		System.out.println("Best players by goals");
		System.out.println("-------------------------------------------------");
		printBestGoalScorers();
		
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Best players by goals in home");
		System.out.println("-------------------------------------------------");
		printBestGoalScorersInHome();
		
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Best players by goals away");
		System.out.println("-------------------------------------------------");
		printBestGoalScorersAway();
	}
	
	public void printBestGoalScorers() {
		printLeaderBoard(playersStats.getBestPlayersByTotalGoals());
	}
	
	public void printBestGoalScorersInHome() {
		printLeaderBoard(playersStats.getBestPlayersByHomeGoals());
	}
	
	public void printBestGoalScorersAway() {
		printLeaderBoard(playersStats.getBestPlayersByAwayGoals());
	}
	
	private void printLeaderBoard(Multimap<Integer, Player> leaderboard) {
		Set<Integer> numberOfGoalsDesceding = new TreeSet<>(leaderboard.keySet()).descendingSet();
		
		for(Integer num : numberOfGoalsDesceding) {
			System.out.print(num + " goals: ");
			System.out.println(leaderboard.get(num).stream().map(String::valueOf).collect(Collectors.joining(", ")));
		}
	}
	
}
