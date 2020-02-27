package afterPlaying;

import java.util.List;

import beforePlaying.Team;

public class TeamStatisticsPrinter {
	private final TeamStats teamStats;

	public TeamStatisticsPrinter(TeamStats teamStats) {
		this.teamStats = teamStats;
	}
	
	public void printAllTeamsStatistics(List<Team> teams) {
		teams.stream().forEach(this::printAllTeamStatistics);
	}
	
	public void printAllTeamStatistics(Team team) {
    	System.out.println("General statistics for " + team);
    	System.out.println("-------------------------------------------------");
		printGoalsStatistics(team);
		System.out.println();
		System.out.println();
	}

	private void printGoalsStatistics(Team team) {
		System.out.println("Home goals: ");
		System.out.println(teamStats.getTotalHomeGoals(team));
		System.out.println("Away goals: ");
		System.out.println(teamStats.getTotalAwayGoals(team));
		System.out.println("Total goals: ");
		System.out.println(teamStats.getTotalGoals(team));
	}
	
}
