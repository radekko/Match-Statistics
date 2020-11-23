package core;

import afterPlaying.PlayersStats;
import afterPlaying.TeamStats;
import afterPlaying.TeamStatsByPlayers;
import afterPlaying.TeamsStats;
import afterPlaying.printers.PlayerStatisticsPrinter;
import afterPlaying.printers.PlayerStatisticsWithinTeamsPrinter;
import afterPlaying.printers.TeamStatisticsPrinter;
import afterPlaying.printers.TeamsStatisticsPrinter;
import beforePlaying.Schedule;
import beforePlaying.Team;
import beforePlaying.TeamRepo;
import beforePlaying.Teams;
import playing.HistoryMatchesRepo;
import playing.PlayingMatches;

public class Main 
{
	public static void main(String[] args)
    {
		//CREATE TEAMS FOR LIGUE
		Teams allTeams = new Teams(new TeamRepo());
		
		//PREPARE SCHEDULE FOR CHOSEN TEAMS
		Schedule schedule = new Schedule(allTeams);
		
		//PLAY MATCHES FROM SCHEDULE
    	PlayingMatches playingMatches = new PlayingMatches(schedule, new HistoryMatchesRepo());
    	
    	//PRINT INFO ABOUT LIGUE AND PLAYED MATCHES DETAILS
    	printHeader(1, "AFTER PLAYING");
		System.out.println("Played ligue lines: " + schedule.getTotalLines() + "\n");
    	System.out.println("FINAL RESULTATS:"+ "\n");
    	playingMatches.printPlayedMatchesGroupingByLigueLine();
    	
    	//PREPARE TO PRINTING TEAM STATISTICS
    	TeamStats teamStats = new TeamStats(playingMatches.getAllPlayedMatches()); 

    	//PRINT CHOOSEN TEAM STATS
    	printHeader(2, "TEAM STATS");
    	Team inspectedTeam = allTeams.getTeamById(2);
    	printStatsForChosenTeam(teamStats, inspectedTeam);
    	
    	//PRINT ANOTHER TEAM STATS
    	Team inspectedTeam2 = allTeams.getTeamById(3);
    	printStatsForChosenTeam(teamStats, inspectedTeam2);
    	
    	//PREPARE TO PRINTING PLAYER STATS
    	printHeader(3, "PLAYER STATS WITHIN TEAM");
    	TeamStatsByPlayers playerStats = new TeamStatsByPlayers(teamStats);
    	PlayerStatisticsWithinTeamsPrinter pl = new PlayerStatisticsWithinTeamsPrinter(playerStats);
    	
    	//PRINT STATS FOR ALL PLAYER FOR TEAM ID=2
    	pl.printAll(inspectedTeam);
    	
    	//STATS BY TEAMS
    	printHeader(4, "GENERAL STATS GROUPING BY TEAMS");
    	TeamsStats teamsStats = new TeamsStats(allTeams, teamStats); 
    	TeamsStatisticsPrinter teamsStatisticsPrinter = new TeamsStatisticsPrinter(teamsStats);
    	teamsStatisticsPrinter.printAll();
    	
    	//STATS BY PLAYERS
    	printHeader(5, "GENERAL STATS GROUPING BY PLAYERS");
    	PlayersStats playersStats = new PlayersStats(allTeams, playerStats);
    	PlayerStatisticsPrinter playerStatisticsPrinter = new PlayerStatisticsPrinter(playersStats);
    	playerStatisticsPrinter.printAll();
    }

	private static void printStatsForChosenTeam(TeamStats teamStats, Team inspectedTeam) {
    	TeamStatisticsPrinter statisticsGenerator = new TeamStatisticsPrinter(teamStats);
		statisticsGenerator.printAllTeamStatistics(inspectedTeam);
	}
	
	private static void printHeader(int moduleNumber, String message) {
		System.out.print("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.print("MODULE: " + moduleNumber + " - " + message);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println();
	}
}