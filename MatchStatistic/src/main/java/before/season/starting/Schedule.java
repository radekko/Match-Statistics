package before.season.starting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import shedule.generator.ScheduleDrawingMachine;

public class Schedule {
	private final List<LigueLine> ligueLines;
	private final Teams teams;
	
	public Schedule(Teams teams) {
		this.teams = teams;
		this.ligueLines = createLigueLines();
	}
	
	public List<LigueLine> getMatchesInChosenLigueLine(int ligueLine) {
		return ligueLines.stream().filter(p -> p.getNumber() == ligueLine).collect(Collectors.toList());
	}
	
	public int getTotalLines() {
		return ligueLines.size();
	}
	
	public LigueLine getLigueLine(int line) {
		return ligueLines.stream().filter(p -> p.getNumber() == line).findFirst().<RuntimeException>orElseThrow(() -> new RuntimeException("Ligue line not exist"));
	}
	private List<LigueLine> createLigueLines() {
		List<LigueLine> ligueLines = new ArrayList<>();
		Map<Integer, List<Pair>> drawSchedule = ScheduleDrawingMachine.drawSchedule(teams.getAll());
		
		for (Integer key : drawSchedule.keySet()) {
			List<Pair> list = drawSchedule.get(key);
			List<Match> matches = list.stream().map(p -> createListOfMatches(p)).collect(Collectors.toList());
			ligueLines.add(new LigueLine(key, matches));
		}
		
		return ligueLines;
	}

	private Match createListOfMatches(Pair p) {
		Team homeTeam = teams.getTeamById(p.getFirstTeamId());
		Team awayTeam = teams.getTeamById(p.getSecondTeamId());
		return new Match(homeTeam, awayTeam);
	}

}
