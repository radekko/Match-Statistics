package afterPlaying.table;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import afterPlaying.againtTeamTotal.VsTeamEventByTypeFinder;
import afterPlaying.findEventsByTeam.EventByTypeFinder;
import afterPlaying.resultatFilter.TeamResults;
import beforePlaying.Team;

public class LigueTable {
	private final TeamResults teamResults;
	private final List<Team> teams;
	private final EventByTypeFinder eventByTypeFinder;
	private final VsTeamEventByTypeFinder vsEventByTypeFinder;

	public LigueTable(List<Team> teams, TeamResults teamResults, EventByTypeFinder eventByTypeFinder,VsTeamEventByTypeFinder vsEventByTypeFinder) {
		this.teamResults = teamResults;
		this.teams = teams;
		this.eventByTypeFinder = eventByTypeFinder;
		this.vsEventByTypeFinder = vsEventByTypeFinder;
	}
	
	public List<TeamRecord> prepareTable() {
		return teams.stream().map(this::prepare).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	}
	
	private TeamRecord prepare(Team team){
		int wins = teamResults.calculateTotalWins(team);
		int draws = teamResults.calculateTotalDraws(team);
		int homeGoals = eventByTypeFinder.getHomeAndAwayStat(team).size();
		int awayGoals = vsEventByTypeFinder.getVsHomeAndAwayStat(team).size();
		return new TeamRecord(team, wins, draws, homeGoals, awayGoals);
	}
}
