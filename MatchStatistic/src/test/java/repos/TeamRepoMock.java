package repos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;
import beforePlaying.infrastructure.TeamDatabase;

public class TeamRepoMock extends TeamDatabase{
	private static TeamRepoMock teamRepoMock;
	private List<Team> teams = new ArrayList<>();
	
	public static TeamRepoMock getInstance() {
		if (teamRepoMock != null)
			return teamRepoMock;

		return new TeamRepoMock();
	}

	@Override
	public List<Team> getAllTeams() {
		return teams;
	}

	private TeamRepoMock() {
		Player p = new Player(1);
		Player p2 = new Player(2);
		Player p3 = new Player(3);
		Player p4 = new Player(4);
		Player p5 = new Player(5);
		Player p6 = new Player(6);

		List<Player> players = new ArrayList<>(Arrays.asList(p, p2, p3));
		List<Player> players2 = new ArrayList<>(Arrays.asList(p4, p5, p6));

		teams.add(new Team(1, players));
		teams.add(new Team(2, players2));
	}

}
