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
    	historyMatches.printHistory();
    	
    	StatisticsPrinter statisticsGenerator = new StatisticsPrinter(historyMatches.getAllHistory());
    	
    	statisticsGenerator.printTeamStatistics(teams.getTeamById(1));
    	statisticsGenerator.printPlayerStatistics(teams.getTeamById(1).getPlayers().get(0));
    	
    	
    }

	private static MatchPlayedInfo playMatch(FutureMatch futureMatch) {
		return new MatchPlayedInfo(futureMatch, 1200, prepareRandomEvents(futureMatch));
	}
	
	private static EventsInMatch prepareRandomEvents(FutureMatch futureMatch){
		EventsInMatch events = new EventsInMatch();
		//random beetwen 1=5, this will be amount of events
		int amount = draw(1, 5);
		
		for(int i = 0; i < 5; i++) {
		
		//first random beetwen 1-2 and choose team
		int whichTeam = draw(1, 2);
		Team team = (whichTeam == 1 ? futureMatch.getHomeTeam() : futureMatch.getAwayTeam());
		//random beetwen 1-11 and this will be player from previous team
		int whichPlayer = draw(0 , 10);
		Player player = team.getPlayers().get(whichPlayer);
		
		//random beetwen 1-3, will be event type
		int eventsAmount = EventType.class.getEnumConstants().length;
		int whichEvent = ThreadLocalRandom.current().nextInt(0, eventsAmount);
		EventType eventType = EventType.class.getEnumConstants()[whichEvent];
		
		int minute = draw(1,90);
		Event event = new Event(team, player, minute, eventType);
		events.addEvent(event);
		}
		
		return events;
		
	}
	
	private static int draw(int from, int to) {
		return ThreadLocalRandom.current().nextInt(from, to + 1);
	}

}
