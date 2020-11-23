package playing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryMatchesRepo {
	private List<MatchPlayedInfo> matches;

	public HistoryMatchesRepo() {
		this.matches = new ArrayList<>();
	}
	
	public void storeAllMatchesInHistory(List<MatchPlayedInfo> matchesPlayedInfo) {
		matches.addAll(matchesPlayedInfo);
	}
	
	public List<MatchPlayedInfo> getAllHistory(){
		return Collections.unmodifiableList(matches);
	}

}