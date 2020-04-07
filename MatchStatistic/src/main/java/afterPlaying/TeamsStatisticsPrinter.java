package afterPlaying;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import beforePlaying.Team;
import playing.Event;

public class TeamsStatisticsPrinter {
	private final TeamsStats teamsStats;

	public TeamsStatisticsPrinter(TeamsStats teamsStats) {
		this.teamsStats = teamsStats;
	}
	
	public void printAll() {
		printTeamsByGoals();
		printTeamsByGoalsInHome();
		printTeamsByGoalsAway();
		System.out.println();
	}

	private void printTeamsByGoals() {
		printLeaderboard(teamsStats.createLeaderboardForAllTeams(PlaceOfPlaying.HOME_OR_AWAY, Event.isGoal()),"total");
	}
	
	private void printTeamsByGoalsInHome() {
		printLeaderboard(teamsStats.createLeaderboardForAllTeams(PlaceOfPlaying.HOME, Event.isGoal()),"in home");
	}
	
	private void printTeamsByGoalsAway() {
		printLeaderboard(teamsStats.createLeaderboardForAllTeams(PlaceOfPlaying.AWAY, Event.isGoal()),"away");
	}
	
	private void printLeaderboard(Multimap<Integer, Team> leaderboard, String desc) {
		System.out.println("-------------------------------------------------");
		System.out.println("Best teams by goals " + desc);
		System.out.println("-------------------------------------------------");
		
		Set<Integer> numberOfGoalsDesceding = new TreeSet<>(leaderboard.keySet()).descendingSet();
		numberOfGoalsDesceding.remove(0);
		
		for(Integer num : numberOfGoalsDesceding) {
			System.out.print(num + " goals: ");
			System.out.println(leaderboard.get(num).stream().map(String::valueOf).collect(Collectors.joining(",")));
		}
	}
	
}
