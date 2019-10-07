package before.season.starting;

import java.util.ArrayList;
import java.util.List;

public class LigueLine {
	private int number;
	private List<Match> matchesInCurrLine;
	
	public LigueLine(int number, List<Match> matchesInCurrLine) {
		this.number = number;
		this.matchesInCurrLine = matchesInCurrLine;
	}

	public int getNumber() {
		return number;
	}

	public List<Match> getMatchesInCurrLine() {
		return new ArrayList<>(matchesInCurrLine);
	}

	@Override
	public String toString() {
		return "LigueLine [number=" + number + ", matchesInCurrLine=" + matchesInCurrLine + "]";
	}
}
