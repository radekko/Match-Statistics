package afterPlaying;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import afterPlaying.teamStatsTotal.IDetailStats;
import afterPlaying.teamStatsTotal.core.EventType;
import afterPlaying.teamStatsTotal.core.Localization;
import afterPlaying.teamStatsTotal.infrastructure.TeamDetailStatsFactory;
import repos.MockedRepos;

public class TotalTeamStatsTest extends MockedRepos{
	// 1) team vs team2 : goals 3-1, yellow cards: 0-1
	// 2) team2 vs team: 1-2, yellow cards:2-0
	
	@Test
	public void homeGoals() throws Exception {
		IDetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.HOME, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 3);
		assertEquals(stats.getTotalStat(team2), 1);
	}
	
	@Test
	public void awayGoals() throws Exception {
		IDetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.AWAY, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 2);
		assertEquals(stats.getTotalStat(team2), 1);
	}
	
	@Test
	public void totalGoals() throws Exception {
		IDetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.BOTH, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 5);
		assertEquals(stats.getTotalStat(team2), 2);
	}
	
	@Test
	public void homeYellowCards() throws Exception {
		IDetailStats stats = TeamDetailStatsFactory.getInstance(EventType.YELLOW_CARDS_GAINED, Localization.HOME, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 0);
		assertEquals(stats.getTotalStat(team2), 2);
	}
	
	@Test
	public void awayYellowCards() throws Exception {
		IDetailStats stats = TeamDetailStatsFactory.getInstance(EventType.YELLOW_CARDS_GAINED, Localization.AWAY, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 0);
		assertEquals(stats.getTotalStat(team2), 1);
	}
	
	@Test
	public void vsHomeGoals() throws Exception {
		IDetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_LOST, Localization.VS_HOME, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 1);
		assertEquals(stats.getTotalStat(team2), 2);
	}
	
	@Test
	public void vsAwayGoals() throws Exception {
		IDetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_LOST, Localization.VS_AWAY, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 1);
		assertEquals(stats.getTotalStat(team2), 3);
	}
	
	@Test
	public void vsTotalGoals() throws Exception {
		IDetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_LOST, Localization.BOTH, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 2);
		assertEquals(stats.getTotalStat(team2), 5);
	}
}
