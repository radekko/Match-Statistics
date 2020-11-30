package playing.infrastructure;

import java.util.List;

import playing.core.model.MatchPlayedInfo;

public class HistoryMatchesRepo {
	private final HistoryMatchesDatabase historyMatchesDatabase;

	public HistoryMatchesRepo(HistoryMatchesDatabase historyMatchesDatabase) {
		this.historyMatchesDatabase = historyMatchesDatabase;
	}
	
	public List<MatchPlayedInfo> getAllMatchPlayedInfo(){
		return historyMatchesDatabase.getAllHistory();
	}
	
	public void storeAllMatchPlayedInfo(List<MatchPlayedInfo> m) {
		historyMatchesDatabase.storeAllMatchesInHistory(m);
	}
	
}
