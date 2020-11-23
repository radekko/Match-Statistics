package playing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import beforePlaying.FutureMatch;
import beforePlaying.LigueLine;
import beforePlaying.Schedule;

public class PlayingMatches {
	private Schedule schedule;
	private List<MatchPlayedInfo> allPlayedMatches; 
	private HistoryMatchesRepo historyMatchesRepo;

	public PlayingMatches(Schedule schedule, HistoryMatchesRepo historyMatchesRepo) {
		this.schedule = schedule;
		this.allPlayedMatches = playAll();
		this.historyMatchesRepo = historyMatchesRepo;
	}
	
	public HistoryMatchesRepo getAllPlayedMatches() {
		historyMatchesRepo.storeAllMatchesInHistory(allPlayedMatches);
		return historyMatchesRepo;
	}

	public void printPlayedMatchesGroupingByLigueLine() {
		Map<Integer, List<MatchPlayedInfo>> byLigueLine = allPlayedMatches.stream().collect(Collectors.groupingBy(MatchPlayedInfo::getLigueLine));
		Iterator<Integer> it = byLigueLine.keySet().iterator();
		
		while (it.hasNext()) {
			Integer line = it.next();
			System.out.println("Line: " + line);
			System.out.println("-------------------------------------------------");
			byLigueLine.get(line).stream().forEach(this::printSingle);
			
			if(it.hasNext())
				System.out.println("-------------------------------------------------");
		}
	}
	
	private void printSingle(MatchPlayedInfo info) {
		 System.out.println(info + "\n");
	}
	
	private List<MatchPlayedInfo> playAll() {
		List<MatchPlayedInfo> allPlayedMatches = new ArrayList<>();
		for(int ligueLine = 1; ligueLine < schedule.getTotalLines() + 1; ligueLine++) {
    		List<MatchPlayedInfo> matchesPlayedInOneLigueLine = playMatches(schedule.getChosenLigueLine(ligueLine));
    		allPlayedMatches.addAll(matchesPlayedInOneLigueLine);
    	}
		return allPlayedMatches;
	}
	
	private static List<MatchPlayedInfo> playMatches(LigueLine ligueLine) {
		List<FutureMatch> matchesToPlay = ligueLine.getMatchesInCurrLine();
		List<MatchPlayedInfo> playedMatches = matchesToPlay.stream().map(PlayingMatches::playMatch).collect(Collectors.toList());
		return playedMatches;
	}

	private static MatchPlayedInfo playMatch(FutureMatch futureMatch) {
		return new MatchPlayedInfo(futureMatch, 1200, DrawingEvents.getDrawedEvents(futureMatch));
	}
}
