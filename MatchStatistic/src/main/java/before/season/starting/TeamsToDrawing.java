package before.season.starting;

import java.util.ArrayList;
import java.util.List;

import match.MatchStatistic.TeamRepo;

public class TeamsToDrawing{
	TeamRepo repo = new TeamRepo();
	List<Team> teams;

	public TeamsToDrawing() {
		this.teams = repo.getAllTeams();
	}
	
	public void addTeam(Team team) {
		this.teams.add(team);
	}
	
	public Team getTeamById(long id) {
		return teams.stream().filter(p -> p.getId() == id).findFirst().<RuntimeException>orElseThrow(() -> new RuntimeException("Team not exist"));
	}

	public List<Team> getAll() {
		return new ArrayList<>(teams);
	}

}