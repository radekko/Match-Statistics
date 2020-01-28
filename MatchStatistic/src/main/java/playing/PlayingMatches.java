package playing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import beforePlaying.FutureMatch;
import beforePlaying.LigueLine;
import beforePlaying.Schedule;

public class PlayingMatches {
	private Schedule schedule;

	public PlayingMatches(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public List<MatchPlayedInfo> playAll() {
		List<MatchPlayedInfo> allPlayedMatches = new ArrayList<>();
		for(int ligueLine = 1; ligueLine < schedule.getTotalLines() + 1; ligueLine++) {
    		List<MatchPlayedInfo> matchesPlayedInOneLigueLine = playMatches(schedule.getChosenLigueLine(ligueLine));
    		allPlayedMatches.addAll(matchesPlayedInOneLigueLine);
    	}
		return allPlayedMatches;
	}
	
	public int getTotalLines() {
		return schedule.getTotalLines();
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
