package match.MatchStatistic;

import java.util.ArrayList;
import java.util.List;

import before.season.starting.Team;

public class TeamRepo {
	List<Team> teams = new ArrayList<>();
	
	public TeamRepo() {
		for (int i = 1; i < 11; i++) {
    		
    		List<Player> players = new ArrayList<>();
    		for (int j = 1; j < 12; j++) {
    			players.add(new Player(j));
    		}
    		
    		Team team = new Team(i, players);
			teams.add(team);
		}
	}

	public List<Team> getAllTeams(){
		return teams;
	}
}
