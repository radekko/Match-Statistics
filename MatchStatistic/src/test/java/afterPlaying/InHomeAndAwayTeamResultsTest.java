package afterPlaying;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import afterPlaying.teamStatsByResult.MainStatsFactory;
import afterPlaying.teamStatsByResult.MainStatsFactory.MainStatsType;
import repos.MockedRepos;

public class InHomeAndAwayTeamResultsTest extends MockedRepos{
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
	}
	
	@Test
	public void numberOfTotalWins() throws Exception {
		int firstTeamWins = 
				MainStatsFactory.getMainStatsFactory(MainStatsType.HOME_AND_AWAY_TEAM_RESULTS).getWins(historyMatchesRepo.getAllHistory(), team);
		int secondTeamWins = 
				MainStatsFactory.getMainStatsFactory(MainStatsType.HOME_AND_AWAY_TEAM_RESULTS).getWins(historyMatchesRepo.getAllHistory(), team2);
		
		assertEquals(firstTeamWins, 2);
		assertEquals(secondTeamWins, 0);
	}
	
	@Test
	public void numberOfTotalDraws() throws Exception {
		int firstTeamDraws =
				MainStatsFactory.getMainStatsFactory(MainStatsType.HOME_AND_AWAY_TEAM_RESULTS).getDraws(historyMatchesRepo.getAllHistory(), team);
		int secondTeamDraws = 
				MainStatsFactory.getMainStatsFactory(MainStatsType.HOME_AND_AWAY_TEAM_RESULTS).getDraws(historyMatchesRepo.getAllHistory(), team2);
		
		assertEquals(firstTeamDraws, 0);
		assertEquals(secondTeamDraws, 0);
	}
	
	@Test
	public void numberOfTotalLooses() throws Exception {
		int firstTeamLooses = 
				MainStatsFactory.getMainStatsFactory(MainStatsType.HOME_AND_AWAY_TEAM_RESULTS).getLooses(historyMatchesRepo.getAllHistory(), team);

		int secondTeamLooses = 
				MainStatsFactory.getMainStatsFactory(MainStatsType.HOME_AND_AWAY_TEAM_RESULTS).getLooses(historyMatchesRepo.getAllHistory(), team2);
		
		assertEquals(firstTeamLooses, 0);
		assertEquals(secondTeamLooses, 2);
	}
	
}
