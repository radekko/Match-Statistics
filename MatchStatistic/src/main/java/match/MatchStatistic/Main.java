package match.MatchStatistic;

import java.util.List;

import before.season.starting.FutureLigueLines;
import before.season.starting.FutureMatch;
import before.season.starting.MatchesInCurrentLine;
import before.season.starting.TeamsToDrawing;

public class Main 
{
	public static void main( String[] args )
    {
    	TeamsToDrawing teams = new TeamsToDrawing();
    	FutureLigueLines schedule = new FutureLigueLines(teams);
    	HistoryMatches historyMatches = new HistoryMatches();
    	
    	for(int i = 0; i < schedule.getTotalLines(); i++) {
    		MatchesInCurrentLine ligueLineBeforeStart = schedule.getLigueLine(++i);
    		List<FutureMatch> matchesToPlay = ligueLineBeforeStart.getMatchesInCurrLine();
    		
    		matchesToPlay.stream().map(Main::zz).forEach(z -> store(z,historyMatches));
    		i--;
    	}
    	
    	System.out.println(schedule.getTotalLines());
    	historyMatches.printHistory();

    }

	private static void store(MatchPlayedInfo z, HistoryMatches historyMatches) {
		historyMatches.storeMatchInHistory(z);
	}

	private static MatchPlayedInfo zz(FutureMatch f) {
		PlayingArea pa = new PlayingArea(f,1200);
		return pa.playMatch();
	}

}
