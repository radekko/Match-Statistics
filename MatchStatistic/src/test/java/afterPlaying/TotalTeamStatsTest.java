package afterPlaying;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import afterPlaying.findEventsByTeam.EventByTypeFinder;
import afterPlaying.findEventsByTeam.GoalsFinder;
import afterPlaying.findEventsByTeam.YellowCardsFinder;
import afterPlaying.teamTotal.TotalTeamStats;
import repos.MockedRepos;

public class TotalTeamStatsTest extends MockedRepos{
	// 1) team vs team2 : goals 3-1, yellow cards: 0-1
	// 2) team2 vs team: 1-2, yellow cards:2-0
	
	@Test
	public void goalsForTeam() throws Exception {
		EventByTypeFinder eventByTypeFinder = new GoalsFinder(historyMatchesRepo);
		TotalTeamStats totalTeamStats = new TotalTeamStats(eventByTypeFinder);
		
		assertEquals(totalTeamStats.getHomeStat(team), 3);
		assertEquals(totalTeamStats.getAwayStat(team), 2);
		assertEquals(totalTeamStats.getHomeAndAwayStat(team), 5);
	}
	
	@Test
	public void goalsForTeam2() throws Exception {
		EventByTypeFinder eventByTypeFinder = new GoalsFinder(historyMatchesRepo);
		TotalTeamStats totalTeamStats = new TotalTeamStats(eventByTypeFinder);
		
		assertEquals(totalTeamStats.getHomeStat(team2), 1);
		assertEquals(totalTeamStats.getAwayStat(team2), 1);
		assertEquals(totalTeamStats.getHomeAndAwayStat(team2), 2);
	}
	
	@Test
	public void yellowCardsForTeam() throws Exception {
		EventByTypeFinder eventByTypeFinder = new YellowCardsFinder(historyMatchesRepo);
		TotalTeamStats totalTeamStats = new TotalTeamStats(eventByTypeFinder);
		
		assertEquals(totalTeamStats.getHomeStat(team), 0);
		assertEquals(totalTeamStats.getAwayStat(team), 0);
		assertEquals(totalTeamStats.getHomeAndAwayStat(team), 0);
	}
	
	@Test
	public void yellowCardsForTeam2() throws Exception {
		EventByTypeFinder eventByTypeFinder = new YellowCardsFinder(historyMatchesRepo);
		TotalTeamStats totalTeamStats = new TotalTeamStats(eventByTypeFinder);
		
		assertEquals(totalTeamStats.getHomeStat(team2), 2);
		assertEquals(totalTeamStats.getAwayStat(team2), 1);
		assertEquals(totalTeamStats.getHomeAndAwayStat(team2), 3);
	}
}
