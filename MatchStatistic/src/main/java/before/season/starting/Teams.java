package before.season.starting;

import java.util.ArrayList;
import java.util.List;

public class Teams{
	List<Team> teams = new ArrayList<>();

	public Teams() {
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