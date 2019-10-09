package match.MatchStatistic;

import java.util.ArrayList;
import java.util.List;

public class HistoryMatches {
	List<MatchPlayedInfo> matches;

	public HistoryMatches() {
		this.matches = new ArrayList<>();
	}
	
	public void storeMatchInHistory(MatchPlayedInfo matchPlayedInfo) {
		matches.add(matchPlayedInfo);
	}
	
	public void printHistory() {
		matches.stream().forEach(System.out::println);
	}

	
}
