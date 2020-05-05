package playing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	
	public void printPlayedMatchesGroupingByLigueLine() {
		Map<Integer, List<MatchPlayedInfo>> byLigueLine = matches.stream().collect(Collectors.groupingBy(MatchPlayedInfo::getLigueLine));
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
}