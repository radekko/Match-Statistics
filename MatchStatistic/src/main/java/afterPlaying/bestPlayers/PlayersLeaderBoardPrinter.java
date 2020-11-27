package afterPlaying.bestPlayers;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import beforePlaying.Player;

public class PlayersLeaderBoardPrinter {
	private final PlayersLeaderBoard playersLeaderBoard;
	private final String statDesc;

	public PlayersLeaderBoardPrinter(PlayersLeaderBoard playersLeaderBoard) {
		this.playersLeaderBoard = playersLeaderBoard;
		this.statDesc = playersLeaderBoard.statDescription();
	}
	
	public void printAll() {
		System.out.println("-------------------------------------------------");
		System.out.println("Best players by " + statDesc);
		System.out.println("-------------------------------------------------");
		printBestTotal();
		
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Best players by " + statDesc + " in home");
		System.out.println("-------------------------------------------------");
		printBestInHome();
		
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Best players by " + statDesc + " away");
		System.out.println("-------------------------------------------------");
		printBestAway();
	}
	
	public void printBestTotal() {
		printLeaderBoard(playersLeaderBoard.createTotalLeaderBoard());
	}
	
	public void printBestInHome() {
		printLeaderBoard(playersLeaderBoard.createHomeLeaderBoard());
	}
	
	public void printBestAway() {
		printLeaderBoard(playersLeaderBoard.createAwayLeaderBoard());
	}
	
	private void printLeaderBoard(Multimap<Integer, Player> leaderboard) {
		Set<Integer> numberOfGoalsDesceding = new TreeSet<>(leaderboard.keySet()).descendingSet();
		
		for(Integer num : numberOfGoalsDesceding) {
			System.out.print(num + " " + statDesc + ": ");
			System.out.println(leaderboard.get(num).stream().map(String::valueOf).collect(Collectors.joining(", ")));
		}
	}
}
