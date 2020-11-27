package afterPlaying.bestTeams;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import beforePlaying.Team;

public class TeamsLeaderBoardPrinter {
	private final TeamsLeaderBoard teamsLeaderBoard;

	public TeamsLeaderBoardPrinter(TeamsLeaderBoard teamsLeaderBoard) {
		this.teamsLeaderBoard = teamsLeaderBoard;
	}
	
	public void printAll() {
		printTotal();
		printHomeHome();
		printAway();
		System.out.println();
	}

	private void printTotal() {
		printLeaderboard(teamsLeaderBoard.createTotalLeaderBoard(),"total ");
	}
	
	private void printHomeHome() {
		printLeaderboard(teamsLeaderBoard.createHomeLeaderBoard(),"home ");
	}
	
	private void printAway() {
		printLeaderboard(teamsLeaderBoard.createAwayLeaderBoard(),"away ");
	}
	
	private void printLeaderboard(Multimap<Integer, Team> leaderboard, String desc) {
		System.out.println("-------------------------------------------------");
		System.out.println("Best teams by " + desc + teamsLeaderBoard.statsDescription());
		System.out.println("-------------------------------------------------");
		
		Set<Integer> numberOfGoalsDesceding = new TreeSet<>(leaderboard.keySet()).descendingSet();
		numberOfGoalsDesceding.remove(0);
		
		for(Integer num : numberOfGoalsDesceding) {
			System.out.print(num + ": ");
			System.out.println(leaderboard.get(num).stream().map(String::valueOf).collect(Collectors.joining(",")));
		}
	}
}
