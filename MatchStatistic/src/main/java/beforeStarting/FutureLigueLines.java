package beforeStarting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Team;

public class FutureLigueLines {
	private final TeamsToDrawing teams;
	private Map<Integer, MatchesInCurrentLine> ligueLines;
	
	public FutureLigueLines(TeamsToDrawing teams) {
		this.teams = teams;
		this.ligueLines = createLigueLines();
	}
	
	public int getTotalLines() {
		return ligueLines.size();
	}
	
	public int getNumberOfMatchesInLine() {
		return ligueLines.get(0).getMatchesInCurrLine().size();
	}
	
	public MatchesInCurrentLine getLigueLine(int line) {
		return ligueLines.get(line);
	}
	
	private Map<Integer, MatchesInCurrentLine> createLigueLines() {
		Map<Integer, MatchesInCurrentLine> ligueLines = new HashMap<>();
		Map<Integer, List<Pair>> drawSchedule = ScheduleDrawingMachine.drawSchedule(teams.getAll());
		
		for (Integer key : drawSchedule.keySet()) {
			List<Pair> list = drawSchedule.get(key);
			MatchesInCurrentLine matches = new MatchesInCurrentLine(list.stream().map(p -> createListOfMatches(p,key)).collect(Collectors.toList()));
			ligueLines.put(key,matches);
		}
		
		return ligueLines;
	}

	private FutureMatch createListOfMatches(Pair p, int ligueLine) {
		Team homeTeam = teams.getTeamById(p.getFirstTeamId());
		Team awayTeam = teams.getTeamById(p.getSecondTeamId());
		return new FutureMatch(homeTeam, awayTeam, ligueLine);
	}

	@Override
	public String toString() {
		return "FutureLigueLines [teams=" + teams + ", ligueLines=" + ligueLines + "]";
	}

}
