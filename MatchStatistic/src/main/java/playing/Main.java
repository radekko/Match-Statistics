package playing;

import java.util.List;
import java.util.stream.Collectors;

import beforeStarting.FutureMatch;
import beforeStarting.LigueLine;
import beforeStarting.Schedule;
import beforeStarting.TeamsToDrawing;
import model.MatchPlayedInfo;
import model.Team;
import statistics.PlayerStatisticsPrinter;
import statistics.PlayerStats;
import statistics.TeamStatisticsPrinter;
import statistics.TeamStats;

public class Main 
{
	public static void main(String[] args)
    {
    	TeamsToDrawing teams = new TeamsToDrawing();
    	Schedule schedule = new Schedule(teams);
    	
    	HistoryMatchesRepo historyMatchesRepo = new HistoryMatchesRepo();
    	
    	for(int ligueLine = 1; ligueLine < schedule.getTotalLines() + 1; ligueLine++) {
    		List<MatchPlayedInfo> playedMatches = playMatches(schedule.getChosenLigueLine(ligueLine));
    		historyMatchesRepo.storeAllMatchesInHistory(playedMatches);
    	}
    	
    	System.out.println("Played ligue lines: " + schedule.getTotalLines() + "\n");
    	System.out.println("FINAL RESULTATS:"+ "\n");
    	historyMatchesRepo.printHistoryGroupingByLigueLine();
    	
    	List<MatchPlayedInfo> historicalMatches = historyMatchesRepo.getAllHistory();
    	TeamStats teamStats = new TeamStats(historicalMatches); 
    	TeamStatisticsPrinter statisticsGenerator = new TeamStatisticsPrinter(teamStats);
    	
    	Team inspectedTeam = teams.getTeamById(2);
    	statisticsGenerator.printAllTeamStatistics(inspectedTeam);
    	System.out.println();
    	
//    	historicalMatches = historyMatchesRepo.getAllHistory();
    	PlayerStats playerStats = new PlayerStats(teamStats);
    	PlayerStatisticsPrinter pl = new PlayerStatisticsPrinter(playerStats);
    	pl.print(inspectedTeam, inspectedTeam.getPlayers());
    	/*statisticsGenerator.printTeamStatistics(inspectedTeam);
    	System.out.println("Player statistics for chosen team: ");
    	
		System.out.println("Goals: ");
    	IntStream.range(0, 11).forEach(i -> statisticsGenerator.printGoalsForPlayer(inspectedTeam, inspectedTeam.getPlayers().get(i)));*/
    	
    	//first get all teams 
    	//then get all players from team
    	
    }

	/*private static List<IPrintableStatistics> preparewhiChStatisticsPrint(List<MatchPlayedInfo> historicalMatches) {
		List<IPrintableStatistics> list = new ArrayList<>();
		IPrintableStatistics goalsHomeTeam = new GoalsHomeTeam(historicalMatches);
		IPrintableStatistics goalsAwayTeam = new GoalsAwayTeam(historicalMatches);
		IPrintableStatistics goalsTotal = new HomeAndAwayTotal(goalsHomeTeam,goalsAwayTeam);
		
		IPrintableStatistics yellowCardsHomeTeam = new YellowCardsHomeTeam(historicalMatches);
		IPrintableStatistics yellowCardsAwayTeam = new YellowCardsAwayTeam(historicalMatches);
		IPrintableStatistics yellowCardsTotal = new HomeAndAwayTotal(yellowCardsHomeTeam, yellowCardsAwayTeam);
		
		list.add(goalsHomeTeam);
		list.add(goalsAwayTeam);
		list.add(goalsTotal);
		
		list.add(yellowCardsHomeTeam);
		list.add(yellowCardsAwayTeam);
		list.add(yellowCardsTotal);
    	
    	return list;
	}*/
	
	private static List<MatchPlayedInfo> playMatches(LigueLine ligueLine) {
		List<FutureMatch> matchesToPlay = ligueLine.getMatchesInCurrLine();
		List<MatchPlayedInfo> playedMatches = matchesToPlay.stream().map(Main::playMatch).collect(Collectors.toList());
		return playedMatches;
	}

	private static MatchPlayedInfo playMatch(FutureMatch futureMatch) {
		return new MatchPlayedInfo(futureMatch, 1200, DrawingEvents.getDrawedEvents(futureMatch));
	}

}
