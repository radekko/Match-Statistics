package afterPlaying;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import beforePlaying.Team;

public class TeamsStatisticsPrinter {
	private final TeamsStats teamsStats;

	public TeamsStatisticsPrinter(TeamsStats teamsStats) {
		this.teamsStats = teamsStats;
	}
	
	public void printAll() {
		printTeamsByGoals();
		printTeamsByGoalsInHome();
		printTeamsByGoalsAway();
	}

	private void printTeamsByGoals() {
		printLeaderBoard(teamsStats.groupByBestGoalScorersTotal(),"total");
	}
	
	private void printTeamsByGoalsInHome() {
		printLeaderBoard(teamsStats.groupByBestGoalScorersInHome(),"in home");
	}
	
	private void printTeamsByGoalsAway() {
		printLeaderBoard(teamsStats.groupByBestGoalScorersAway(),"away");
	}
	
	private void printLeaderBoard(Multimap<Integer, Team> leaderboard, String desc) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Best teams by goals " + desc);
		System.out.println("-------------------------------------------------");
		
		Set<Integer> numberOfGoalsDesceding = new TreeSet<>(leaderboard.keySet()).descendingSet();
		
		for(Integer num : numberOfGoalsDesceding) {
			System.out.print(num + " goals: ");
			System.out.println(leaderboard.get(num).stream().map(String::valueOf).collect(Collectors.joining(",")));
		}
	}
	
}
