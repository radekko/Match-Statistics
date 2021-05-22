package afterPlaying;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import afterPlaying.teamStatsTotal.DetailStats;
import afterPlaying.teamStatsTotal.EventType;
import afterPlaying.teamStatsTotal.Localization;
import afterPlaying.teamStatsTotal.TeamDetailStatsFactory;
import repos.MockedRepos;

public class TotalTeamStatsTest extends MockedRepos{
	// 1) team vs team2 : goals 3-1, yellow cards: 0-1
	// 2) team2 vs team: 1-2, yellow cards:2-0
	
	@Test
	public void homeGoals() throws Exception {
		DetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.HOME, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 3);
		assertEquals(stats.getTotalStat(team2), 1);
	}
	
	@Test
	public void awayGoals() throws Exception {
		DetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.AWAY, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 2);
		assertEquals(stats.getTotalStat(team2), 1);
	}
	
	@Test
	public void totalGoals() throws Exception {
		DetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.BOTH, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 5);
		assertEquals(stats.getTotalStat(team2), 2);
	}
	
	@Test
	public void homeYellowCards() throws Exception {
		DetailStats stats = TeamDetailStatsFactory.getInstance(EventType.YELLOW_CARDS_GAINED, Localization.HOME, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 0);
		assertEquals(stats.getTotalStat(team2), 2);
	}
	
	@Test
	public void awayYellowCards() throws Exception {
		DetailStats stats = TeamDetailStatsFactory.getInstance(EventType.YELLOW_CARDS_GAINED, Localization.AWAY, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 0);
		assertEquals(stats.getTotalStat(team2), 1);
	}
	
	@Test
	public void vsHomeGoals() throws Exception {
		DetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_LOST, Localization.VS_HOME, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 1);
		assertEquals(stats.getTotalStat(team2), 2);
	}
	
	@Test
	public void vsAwayGoals() throws Exception {
		DetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_LOST, Localization.VS_AWAY, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 1);
		assertEquals(stats.getTotalStat(team2), 3);
	}
	
	@Test
	public void vsTotalGoals() throws Exception {
		DetailStats stats = TeamDetailStatsFactory.getInstance(EventType.GOALS_LOST, Localization.BOTH, historyMatchesRepo.getAllHistory());
		assertEquals(stats.getTotalStat(team), 2);
		assertEquals(stats.getTotalStat(team2), 5);
	}
}
