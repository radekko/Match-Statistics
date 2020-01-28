package beforePlaying;

import java.util.ArrayList;
import java.util.List;

public class Teams{
	private List<Team> teams;

	public Teams(TeamRepo repo) {
		this.teams = repo.getAllTeams();
	}
	
	public Team getTeamById(long id) {
		return teams.stream().filter(p -> p.getId() == id).findFirst().<RuntimeException>orElseThrow(() -> new RuntimeException("Team not exist"));
	}

	public List<Team> getAll() {
		return new ArrayList<>(teams);
	}

}