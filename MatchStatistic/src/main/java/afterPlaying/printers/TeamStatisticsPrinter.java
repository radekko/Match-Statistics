package afterPlaying.printers;

import java.util.List;

import afterPlaying.TeamStats;
import beforePlaying.Team;
import playing.Event;
import playing.PlaceOfPlaying;

public class TeamStatisticsPrinter {
	private final TeamStats teamStats;

	public TeamStatisticsPrinter(TeamStats teamStats) {
		this.teamStats = teamStats;
	}
	
	public void printAllTeamsStatistics(List<Team> teams) {
		teams.stream().forEach(this::printAllTeamStatistics);
		System.out.println();
	}
	
	public void printAllTeamStatistics(Team team) {
    	System.out.println("General statistics for " + team);
    	System.out.println("-------------------------------------------------");
		printGoalsStatistics(team);
		System.out.println();
	}

	private void printGoalsStatistics(Team team) {
		System.out.println("Home goals: ");
		System.out.println(teamStats.getTotalEventsForChosenPlace(team, PlaceOfPlaying.HOME, Event.isGoal()));
		System.out.println("Away goals: ");
		System.out.println(teamStats.getTotalEventsForChosenPlace(team, PlaceOfPlaying.AWAY, Event.isGoal()));
		System.out.println("Total goals: ");
		System.out.println(teamStats.getTotalEventsForChosenPlace(team, PlaceOfPlaying.HOME_OR_AWAY, Event.isGoal()));
	}
	
}
