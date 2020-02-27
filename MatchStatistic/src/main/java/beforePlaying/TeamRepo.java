package beforePlaying;

import java.util.ArrayList;
import java.util.List;

public class TeamRepo {
	private List<Team> teams = new ArrayList<>();
	
	public TeamRepo() {
		for (int i = 1; i < 5; i++) {
    		List<Player> players = new ArrayList<>();
    		for (int j = startIndex(i); j < startIndex(i) + 11; j++) {
    			players.add(new Player(j));
    		}
    		
    		Team team = new Team(i, players);
			teams.add(team);
		}
		
	}

	private int startIndex(int i) {
		return 10 * i - 10 + i;
	}

	public List<Team> getAllTeams(){
		return teams;
	}
}
