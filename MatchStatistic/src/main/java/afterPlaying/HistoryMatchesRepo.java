package afterPlaying;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import playing.MatchPlayedInfo;

public class HistoryMatchesRepo {
	private List<MatchPlayedInfo> matches;

	public HistoryMatchesRepo() {
		this.matches = new ArrayList<>();
	}
	
	public void storeMatchInHistory(MatchPlayedInfo matchPlayedInfo) {
		matches.add(matchPlayedInfo);
	}
	
	public void storeAllMatchesInHistory(List<MatchPlayedInfo> matchesPlayedInfo) {
		matches.addAll(matchesPlayedInfo);
	}
	
	public List<MatchPlayedInfo> getAllHistory(){
		return Collections.unmodifiableList(matches);
	}
	
	public void printHistory() {
		matches.stream().forEach(this::printSingle);
	}
	
	public void printPlayedMatchesGroupingByLigueLine() {
		Map<Integer, List<MatchPlayedInfo>> byLigueLine = matches.stream().collect(Collectors.groupingBy(MatchPlayedInfo::getLigueLine));
		
		for (Integer line : byLigueLine.keySet()) {
			System.out.println("Line: " + line);
			System.out.println("-------------------------------------------------");
			byLigueLine.get(line).stream().forEach(this::printSingle);
			System.out.println("-------------------------------------------------");
		}
	}

	private void printSingle(MatchPlayedInfo info) {
		 System.out.println(info + "\n");
	}
}