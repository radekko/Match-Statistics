package beforePlaying.core.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import beforePlaying.core.ISchedule;
import beforePlaying.core.drawingSchedule.Pair;
import beforePlaying.core.drawingSchedule.ScheduleGenerator;
import beforePlaying.infrastructure.TeamsRepo;

public class Schedule implements ISchedule {
	private final TeamsRepo teams;
	private final ScheduleGenerator scheduleGenerator;
	private Map<Integer, LigueLine> ligueLines;
	
	public Schedule(TeamsRepo teams, ScheduleGenerator scheduleGenerator) {
		this.teams = teams;
		this.scheduleGenerator = scheduleGenerator;
		this.ligueLines = createLigueLines();
	}

	@Override
	public int getTotalLines() {
		return ligueLines.size();
	}

	@Override
	public int getNumberOfMatchesInLine() {
		return ligueLines.get(0).getMatchesInCurrLine().size();
	}
	
	@Override
	public LigueLine getChosenLigueLine(int line) {
		return ligueLines.get(line);
	}
	
	private Map<Integer, LigueLine> createLigueLines() {
		Map<Integer, LigueLine> ligueLines = new HashMap<>();
		Map<Integer, List<Pair>> drawSchedule = scheduleGenerator.drawSchedule(teams.getAll());
		
		for (Integer key : drawSchedule.keySet()) {
			List<Pair> list = drawSchedule.get(key);
			LigueLine matches = new LigueLine(list.stream().map(p -> createListOfMatches(p,key)).collect(Collectors.toList()));
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
