package beforePlaying.infrastructure;

import java.util.ArrayList;
import java.util.List;

import beforePlaying.core.model.Team;

public class TeamsRepo{
	private List<Team> teams;

	public TeamsRepo(TeamDatabase database) {
		this.teams = database.getAllTeams();
	}
	
	public Team getTeamById(long id) {
		return teams.stream().filter(p -> p.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("Team not exist"));
	}

	public List<Team> getAll() {
		return new ArrayList<>(teams);
	}

}