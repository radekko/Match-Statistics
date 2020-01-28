package afterPlaying;

import java.util.List;

import beforePlaying.Team;

public class TeamStatisticsPrinter {
	private final SingleTeamStats teamStats;

	public TeamStatisticsPrinter(SingleTeamStats teamStats) {
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
	
	/*public void printAllTeamsStatistics(List<Team> teams) {
		teams.stream().forEach(this::printAllTeamStatistics);
	}
	
	public void printAllTeamStatistics(Team team) {
    	System.out.println("General team statistics for " + team);
    	System.out.println("-------------------------------------------------");
		printGoalsStatistics(team);
		System.out.println();
		printYellowCardsStatistics(team);
		System.out.println();
		printRedCardsStatistics(team);
	}

	public void printGoalsStatistics(Team team) {
		System.out.println("Goals");
		System.out.println("*************************");
		printOneType(team, PlaceOfPlaying.HOME, MatchPlayedInfo::getHomeGoals);
		printOneType(team, PlaceOfPlaying.AWAY, MatchPlayedInfo::getAwayGoals);
		printTotal(team, MatchPlayedInfo::getHomeGoals, MatchPlayedInfo::getAwayGoals);
	}
	
	public void printYellowCardsStatistics(Team team) {
		System.out.println("Yellow cards");
		System.out.println("*************************");
		printOneType(team, PlaceOfPlaying.HOME, MatchPlayedInfo::getHostYellowCards);
		printOneType(team, PlaceOfPlaying.AWAY, MatchPlayedInfo::getAwayGoals);
		printTotal(team, MatchPlayedInfo::getHostYellowCards, MatchPlayedInfo::getAwayYellowCards);
	}
	
	public void printRedCardsStatistics(Team team) {
		System.out.println("Red cards");
		System.out.println("*************************");
		printOneType(team, PlaceOfPlaying.HOME, MatchPlayedInfo::getHostRedCards);
		printOneType(team, PlaceOfPlaying.AWAY, MatchPlayedInfo::getAwayRedCards);
		printTotal(team, MatchPlayedInfo::getHostRedCards, MatchPlayedInfo::getAwayRedCards);
	}
	
	private void printOneType(Team team, PlaceOfPlaying place, Function<MatchPlayedInfo, List<EventSnapshot>> whichEvents) {
		System.out.println(place.getDescription());
		System.out.println(teamStats.getHomeOrAwayEvents(team, place, whichEvents).size());
	}
	
	private void printTotal(Team team, Function<MatchPlayedInfo, 
			List<EventSnapshot>> whichEvents, 
			Function<MatchPlayedInfo, List<EventSnapshot>> whichEvents2) {
		System.out.println("Total");
		System.out.println(teamStats.getSumOfEvents(team, whichEvents, whichEvents2).size());
	}*/
	
	/*public void printGoalsForPlayer(Team team, Player player) {
		System.out.println("player id: " + player.getId());
		System.out.println("--------------------------------------------");
		
		System.out.print("Home: ");
		int goalsInHome = playerStatisticsForAllMatches.getTotalGoalsForPlayerWhenPlayInHome(team, player);
		System.out.print(goalsInHome);
		
		System.out.print(", Away: ");
		int goalsAway = playerStatisticsForAllMatches.getTotalGoalsForPlayerWhenPlayAway(team, player);
		System.out.print(goalsAway);

		System.out.print(", Total: ");
		System.out.println(goalsInHome + goalsAway);
		
		System.out.println();
	}
	
	public void printLigueStatistics() {}*/
	
}
