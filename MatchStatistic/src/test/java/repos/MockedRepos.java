package repos;

import org.junit.Before;

import beforePlaying.Player;
import beforePlaying.Team;
import playing.HistoryMatchesRepo;

public abstract class MockedRepos {
	protected HistoryMatchesRepo historyMatchesRepo;
	protected TeamRepoMock teamRepo;
	protected Team team;
	protected Team team2;
	protected Player p; 
	protected Player p2;
	protected Player p3;
	protected Player p4;
	
	@Before
	public void setUp() {
		teamRepo = TeamRepoMock.getInstance();
		historyMatchesRepo = HistoryMatchesRepoMock.getIntance();
		team = teamRepo.getAllTeams().get(0);
		team2 = teamRepo.getAllTeams().get(1);
		p = team.getPlayers().get(0);
		p2 = team.getPlayers().get(1);
		p3 = team2.getPlayers().get(0);
		p4 = team2.getPlayers().get(1);
	}
}
