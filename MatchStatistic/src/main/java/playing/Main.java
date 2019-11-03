package playing;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import beforeStarting.FutureLigueLines;
import beforeStarting.FutureMatch;
import beforeStarting.MatchesInCurrentLine;
import beforeStarting.TeamsToDrawing;
import model.Event;
import model.EventType;
import model.EventsInMatch;
import model.MatchPlayedInfo;
import model.Player;
import model.Team;
import statistics.StatisticsPrinter;

public class Main 
{
	public static void main( String[] args )
    {
    	TeamsToDrawing teams = new TeamsToDrawing();
    	FutureLigueLines schedule = new FutureLigueLines(teams);
    	
    	HistoryMatchesRepo historyMatches = new HistoryMatchesRepo();
    	
    	for(int i = 0; i < schedule.getTotalLines(); i++) {
    		MatchesInCurrentLine matchesInChosenLigueLine = schedule.getLigueLine(++i);
    		List<FutureMatch> matchesToPlay = matchesInChosenLigueLine.getMatchesInCurrLine();
    		
    		List<MatchPlayedInfo> playedMatches = matchesToPlay.stream().map(Main::playMatch).collect(Collectors.toList());
    		historyMatches.storeAllMatchesInHistory(playedMatches);
    		
    		i--;
    	}
    	
    	System.out.println(schedule.getTotalLines());
    	historyMatches.printHistoryGroupingByLigueLine();
    	
    	StatisticsPrinter statisticsGenerator = new StatisticsPrinter(historyMatches.getAllHistory());
    	
    	statisticsGenerator.printTeamStatistics(teams.getTeamById(1));
    	statisticsGenerator.printPlayerStatistics(teams.getTeamById(1).getPlayers().get(0));
    }

	private static MatchPlayedInfo playMatch(FutureMatch futureMatch) {
		return new MatchPlayedInfo(futureMatch, 1200, createRandomEvents(futureMatch));
	}
	
	private static EventsInMatch createRandomEvents(FutureMatch futureMatch){
		EventsInMatch events = new EventsInMatch();
		int numberOfEvents = draw(1, 5);
		
		for(int i = 0; i < numberOfEvents; i++) 
			events.addEvent(createRandomEvent(futureMatch));
		
		return events;
	}

	private static Event createRandomEvent(FutureMatch futureMatch) {
		int whichTeam = draw(1, 2);
		Team team = (whichTeam == 1 ? futureMatch.getHomeTeam() : futureMatch.getAwayTeam());

		int whichPlayer = draw(0 , 10);
		Player player = team.getPlayers().get(whichPlayer);
		
		int eventsAmount = EventType.class.getEnumConstants().length;
		int whichEvent = ThreadLocalRandom.current().nextInt(0, eventsAmount);
		EventType eventType = EventType.class.getEnumConstants()[whichEvent];
		
		int minute = draw(1,90);
		
		return new Event(team, player, minute, eventType);
	}
	
	private static int draw(int from, int to) {
		return ThreadLocalRandom.current().nextInt(from, to + 1);
	}

}
