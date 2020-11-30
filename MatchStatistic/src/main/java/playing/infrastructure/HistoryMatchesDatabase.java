package playing.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import playing.core.model.MatchPlayedInfo;

public class HistoryMatchesDatabase {
	private List<MatchPlayedInfo> matches;

	public HistoryMatchesDatabase() {
		this.matches = new ArrayList<>();
	}
	
	public void storeAllMatchesInHistory(List<MatchPlayedInfo> matchesPlayedInfo) {
		matches.addAll(matchesPlayedInfo);
	}
	
	public List<MatchPlayedInfo> getAllHistory(){
		return Collections.unmodifiableList(matches);
	}

}