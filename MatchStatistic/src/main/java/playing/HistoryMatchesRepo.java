package playing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.MatchPlayedInfo;

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

	private void printSingle(MatchPlayedInfo info) {
		 System.out.println(info + "\n");
	}
	
}
