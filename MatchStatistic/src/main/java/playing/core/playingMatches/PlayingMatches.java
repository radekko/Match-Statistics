package playing.core.playingMatches;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import beforePlaying.core.ISchedule;
import beforePlaying.core.model.FutureMatch;
import beforePlaying.core.model.LigueLine;
import playing.core.IPlayingMatches;
import playing.core.model.MatchPlayedInfo;
import playing.infrastructure.HistoryMatchesRepo;

public class PlayingMatches implements IPlayingMatches {
	private ISchedule schedule;
	private List<MatchPlayedInfo> allPlayedMatches; 
	private final HistoryMatchesRepo historyMatchesRepo;
	private final DrawingEvents drawingEvents;
	
	public PlayingMatches(ISchedule schedule, HistoryMatchesRepo historyMatchesRepo, DrawingEvents drawingEvents) {
		this.schedule = schedule;
		this.drawingEvents = drawingEvents;
		this.historyMatchesRepo = historyMatchesRepo;
	}

	@Override
	public List<MatchPlayedInfo> getPlayedMatches() {
		this.allPlayedMatches = playAll();
		historyMatchesRepo.storeAllMatchPlayedInfo(allPlayedMatches);
		return allPlayedMatches;
	}

	@Override
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
	
	private List<MatchPlayedInfo> playMatches(LigueLine ligueLine) {
		List<FutureMatch> matchesToPlay = ligueLine.getMatchesInCurrLine();
		List<MatchPlayedInfo> playedMatches = matchesToPlay.stream().map(this::playMatch).collect(Collectors.toList());
		return playedMatches;
	}

	private MatchPlayedInfo playMatch(FutureMatch futureMatch) {
		return new MatchPlayedInfo(futureMatch, 1200, drawingEvents.drawEvents(futureMatch));
	}
}
