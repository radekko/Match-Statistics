package core;

import java.util.List;

import afterPlaying.HistoryMatchesRepo;
import afterPlaying.PlayerStatisticsPrinter;
import afterPlaying.SinglePlayerStats;
import afterPlaying.SingleTeamStats;
import afterPlaying.TeamStatisticsPrinter;
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
    	System.out.println("Played ligue lines: " + drawingMatches.getTotalLines() + "\n");
    	System.out.println("FINAL RESULTATS:"+ "\n");
    	historyMatchesRepo.printPlayedMatchesGroupingByLigueLine();
    	
    	//GET ALL HISTORY
    	List<MatchPlayedInfo> historicalMatches = historyMatchesRepo.getAllHistory();

    	//PREPARE TO PRINTING TEAM STATISTICS
    	SingleTeamStats teamStats = new SingleTeamStats(historicalMatches); 
    	TeamStatisticsPrinter statisticsGenerator = new TeamStatisticsPrinter(teamStats);

    	//PRINT CHOOSEN TEAM STATS
    	Team inspectedTeam = allTeams.getTeamById(2);
    	statisticsGenerator.printAllTeamStatistics(inspectedTeam);
    	
    	//PRINT ANOTHER TEAM STATS
    	Team inspectedTeam2 = allTeams.getTeamById(3);
    	statisticsGenerator.printAllTeamStatistics(inspectedTeam2);
    	
    	//PREPARE TO PRINTING TEAM STATS
    	SinglePlayerStats playerStats = new SinglePlayerStats(teamStats);
    	PlayerStatisticsPrinter pl = new PlayerStatisticsPrinter(playerStats);
    	
    	//PRINT STATS FOR ALL PLAYER FROM TEAM ID=2
    	pl.printAll(inspectedTeam, inspectedTeam.getPlayers());
    	
    	/*statisticsGenerator.printTeamStatistics(inspectedTeam);
    	System.out.println("Player statistics for chosen team: ");
    	
		System.out.println("Goals: ");
    	IntStream.range(0, 11).forEach(i -> statisticsGenerator.printGoalsForPlayer(inspectedTeam, inspectedTeam.getPlayers().get(i)));*/
    	
    	//first get all teams 
    	//then get all players from team
    	
    }

}

