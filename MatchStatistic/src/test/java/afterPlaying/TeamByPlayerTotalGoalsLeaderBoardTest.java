package afterPlaying;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Multimap;

import afterPlaying.teamStatsByPlayers.ITeamByPlayerLeaderBoard;
import afterPlaying.teamStatsByPlayers.infrastructure.TeamByPlayerLeaderBoardFactory;
import afterPlaying.teamStatsByPlayers.infrastructure.TeamByPlayerLeaderBoardFactory.TeamByPlayerLeaderBoardType;
import beforePlaying.core.model.Player;

import static org.assertj.guava.api.Assertions.assertThat;
import static org.assertj.guava.api.Assertions.entry;

import repos.MockedRepos;

public class TeamByPlayerTotalGoalsLeaderBoardTest extends MockedRepos{
	private ITeamByPlayerLeaderBoard teamByPlayerLeaderBoard;
	// 2 MATCHES
		// 1) team vs team2 : goals 3-1, yellow cards: 0-1
		// a) goals: p:2, p2:1 - p4:1
		// b) yellow cards: 0 - p4:1
		
		// 2) team2 vs team: 1-2, yellow cards:2-0
		// a) goals: p4:1 - p:1, p2:1
		// b) yellow cards: p3:1, p4:1 - 0
	@Before
	public void setUp() {
		super.setUp();
		this.teamByPlayerLeaderBoard = TeamByPlayerLeaderBoardFactory.getInstance(TeamByPlayerLeaderBoardType.BOTH_GOALS, historyMatchesRepo.getAllHistory());
	}
	
	@Test
	public void checkTeamGoalsLeaderBoard() throws Exception {
		Multimap<Integer, Player> byPlayerLeaderBoard = teamByPlayerLeaderBoard.createLeaderBoard(historyMatchesRepo.getAllHistory(), team);
		
		assertThat(byPlayerLeaderBoard).hasSize(2);
	    assertThat(byPlayerLeaderBoard).containsKeys(2,3);
	    assertThat(byPlayerLeaderBoard).contains(entry(2, p2), entry(3, p));
	}
	
	@Test
	public void checkTeam2GoalsLeaderBoard() throws Exception {
		Multimap<Integer, Player> byPlayerLeaderBoard = teamByPlayerLeaderBoard.createLeaderBoard(historyMatchesRepo.getAllHistory(), team2);
		
		assertThat(byPlayerLeaderBoard).hasSize(1);
	    assertThat(byPlayerLeaderBoard).containsKeys(2);
	    assertThat(byPlayerLeaderBoard).contains(entry(2, p4));
	}
	
}
