package afterPlaying;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import afterPlaying.teamStatsTotal.DetailStats;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory.EventType;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory.Localization;
import repos.MockedRepos;

public class TotalTeamStatsTest extends MockedRepos{
	// 1) team vs team2 : goals 3-1, yellow cards: 0-1
	// 2) team2 vs team: 1-2, yellow cards:2-0
	
	@Test
	public void homeGoals() throws Exception {
		DetailStats stats = TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.HOME);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team), 3);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team2), 1);
	}
	
	@Test
	public void awayGoals() throws Exception {
		DetailStats stats = TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.AWAY);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team), 2);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team2), 1);
	}
	
	@Test
	public void totalGoals() throws Exception {
		DetailStats stats = TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.BOTH);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team), 5);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team2), 2);
	}
	
	@Test
	public void homeYellowCards() throws Exception {
		DetailStats stats = TeamDetailStatsStatsFactory.getInstance(EventType.YELLOW_CARDS, Localization.HOME);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team), 0);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team2), 2);
	}
	
	@Test
	public void awayYellowCards() throws Exception {
		DetailStats stats = TeamDetailStatsStatsFactory.getInstance(EventType.YELLOW_CARDS, Localization.AWAY);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team), 0);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team2), 1);
	}
	
	@Test
	public void vsHomeGoals() throws Exception {
		DetailStats stats = TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.VS_HOME);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team), 1);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team2), 2);
	}
	
	@Test
	public void vsAwayGoals() throws Exception {
		DetailStats stats = TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.VS_AWAY);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team), 1);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team2), 3);
	}
	
	@Test
	public void vsTotalGoals() throws Exception {
		DetailStats stats = TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.VS_BOTH);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team), 2);
		assertEquals(stats.getTotalStat(historyMatchesRepo.getAllHistory(), team2), 5);
	}
}
