package afterPlaying;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Multimap;
import static org.assertj.guava.api.Assertions.assertThat;
import static org.assertj.guava.api.Assertions.entry;

import afterPlaying.bestPlayers.PlayersLeaderBoard;
import afterPlaying.bestPlayers.PlayersLeaderBoardFactory;
import afterPlaying.bestPlayers.PlayersLeaderBoardFactory.PlayerLeaderBoardType;
import beforePlaying.core.model.Player;
import repos.MockedRepos;

public class PlayersTotalGoalsLeaderBoardTest extends MockedRepos{
	private PlayersLeaderBoard playersLeaderBoard;
	// 2 MATCHES
		// 1) team vs team2 : goals 3-1
		// a) goals: p:2, p2:1 - p4:1

		
		// 2) team2 vs team: 1-2
		// a) goals: p4:1 - p:1, p2:1

	@Before
	public void setUp() {
		super.setUp();
		this.playersLeaderBoard = PlayersLeaderBoardFactory.getInstance(PlayerLeaderBoardType.PLAYER_BOTH_GOALS);
	}
	
	@Test
	public void checkPlayerGoalsLeaderBoard() throws Exception {
		Multimap<Integer, Player> playersTotalGoalsLeaderBoard = playersLeaderBoard.createLeaderBoard(historyMatchesRepo.getAllHistory(), teamRepo.getAllTeams());

		assertThat(playersTotalGoalsLeaderBoard).hasSize(3);
	    assertThat(playersTotalGoalsLeaderBoard).containsKeys(2,3);
	    assertThat(playersTotalGoalsLeaderBoard).contains(entry(2, p2), entry(2, p4), entry(3, p));
	}
}
