package core;

import java.util.List;

import afterPlaying.HistoryMatchesRepo;
import afterPlaying.PlayerStatisticsPrinter;
import afterPlaying.PlayerStatisticsWithinTeamsPrinter;
import afterPlaying.PlayersStats;
import afterPlaying.TeamStatisticsPrinter;
import afterPlaying.TeamStats;
import afterPlaying.TeamStatsByPlayers;
import afterPlaying.TeamsStatisticsPrinter;
import afterPlaying.TeamsStats;
import beforePlaying.Schedule;
import beforePlaying.Team;
import beforePlaying.TeamRepo;
import beforePlaying.Teams;
import playing.MatchPlayedInfo;
import playing.PlayingMatches;

public class Main 
{
	public static void main(String[] args)
    {
		//CREATE TEAMS FOR LIGUE
		Teams allTeams = new Teams(new TeamRepo());
		
		//PREPARE MATCHES WHICH WILL BE PLAY
    	PlayingMatches drawingMatches = new PlayingMatches(new Schedule(allTeams));
    	
    	//PLAY MATCHES
    	List<MatchPlayedInfo> matchesPlayed = drawingMatches.playAll();

    	//STORE PLAYED MATCHES
    	HistoryMatchesRepo historyMatchesRepo = new HistoryMatchesRepo();
		historyMatchesRepo.storeAllMatchesInHistory(matchesPlayed);
    	
    	//PRINT INFO ABOUT LIGUE AND PLAYED MATCHES DETAILS
		printHeader(1, "AFTER PLAYING");
		System.out.println("Played ligue lines: " + drawingMatches.getTotalLines() + "\n");
    	System.out.println("FINAL RESULTATS:"+ "\n");
    	historyMatchesRepo.printPlayedMatchesGroupingByLigueLine();
    	
    	//PREPARE TO PRINTING TEAM STATISTICS
    	TeamStats teamStats = new TeamStats(historyMatchesRepo); 
    	TeamStatisticsPrinter statisticsGenerator = new TeamStatisticsPrinter(teamStats);

    	//PRINT CHOOSEN TEAM STATS
    	printHeader(2, "TEAM STATS");
    	Team inspectedTeam = allTeams.getTeamById(2);
    	statisticsGenerator.printAllTeamStatistics(inspectedTeam);
    	
    	//PRINT ANOTHER TEAM STATS
    	Team inspectedTeam2 = allTeams.getTeamById(3);
    	statisticsGenerator.printAllTeamStatistics(inspectedTeam2);
    	
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
	
	private static void printHeader(int moduleNumber, String message) {
		System.out.print("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.print("MODULE: " + moduleNumber + " - " + message);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println();
	}
}