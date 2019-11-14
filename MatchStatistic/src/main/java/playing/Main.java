package playing;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import beforeStarting.Schedule;
import beforeStarting.FutureMatch;
import beforeStarting.LigueLine;
import beforeStarting.TeamsToDrawing;
import model.MatchPlayedInfo;
import model.Team;
import statistics.GoalStatisticsPrinter;

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
    	
    	System.out.println("Played lines: " + schedule.getTotalLines() + "\n");
    	historyMatchesRepo.printHistoryGroupingByLigueLine();
    	
    	GoalStatisticsPrinter statisticsGenerator = new GoalStatisticsPrinter(historyMatchesRepo.getAllHistory());
//    	statisticsGenerator.printAllTeamsStatistics(teams.getAll());
    	
    	Team inspectedTeam = teams.getTeamById(2);
    	System.out.println("General team statistics: ");
    	statisticsGenerator.printTeamStatistics(inspectedTeam);
    	System.out.println("Player statistics for chosen team: ");
    	
		System.out.println("Goals: ");
    	IntStream.range(0, 11).forEach(i -> statisticsGenerator.printGoalsForPlayer(inspectedTeam, inspectedTeam.getPlayers().get(i)));
    	
    	//first get all teams 
    	//then get all players from team
    	
    }
	
	private static List<MatchPlayedInfo> playMatches(LigueLine ligueLine) {
		List<FutureMatch> matchesToPlay = ligueLine.getMatchesInCurrLine();
		List<MatchPlayedInfo> playedMatches = matchesToPlay.stream().map(Main::playMatch).collect(Collectors.toList());
		return playedMatches;
	}

	private static MatchPlayedInfo playMatch(FutureMatch futureMatch) {
		return new MatchPlayedInfo(futureMatch, 1200, DrawingEvents.getDrawedEvents(futureMatch));
	}

}
