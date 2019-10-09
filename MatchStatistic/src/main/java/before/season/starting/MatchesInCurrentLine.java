package before.season.starting;

import java.util.ArrayList;
import java.util.List;

public class MatchesInCurrentLine {
	private List<FutureMatch> matchesInCurrLine;
	
	public MatchesInCurrentLine(List<FutureMatch> matchesInCurrLine) {
		this.matchesInCurrLine = matchesInCurrLine;
	}

	public List<FutureMatch> getMatchesInCurrLine() {
		return new ArrayList<>(matchesInCurrLine);
	}
	
	public void addAll(List<FutureMatch> list) {
		this.matchesInCurrLine.addAll(list);
	}

	@Override
	public String toString() {
		return "Matches [matchesInCurrLine=" + matchesInCurrLine + "]";
	}
	
	

}
