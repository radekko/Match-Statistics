package match.MatchStatistic;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import afterPlaying.SingleTeamStats;
import beforePlaying.FutureMatch;
import beforePlaying.Player;
import beforePlaying.Team;
import playing.Event;
import playing.EventType;
import playing.EventsInMatch;
import playing.MatchPlayedInfo;

public class TeamStatsTest {

	private List<MatchPlayedInfo> matches;
	private SingleTeamStats teamStats;
	private Team team;
	private Team team2;

	/*
	 	Prepare two matches:
	 	1. team vs team2 2:1
	 	2. team2 vs team 1:2
	 */
	@Before
	public void setUp() {
		matches = new ArrayList<>();

		Player p = new Player(1);
		Player p2 = new Player(2);
		Player p3 = new Player(3);
		Player p4 = new Player(4);
		Player p5 = new Player(5);
		Player p6 = new Player(6);

		List<Player> players = new ArrayList<>(Arrays.asList(p, p2, p3));
		List<Player> players2 = new ArrayList<>(Arrays.asList(p4, p5, p6));

		team = new Team(1, players);
		team2 = new Team(2, players2);
		
		Event ev = new Event(team, p, 1, EventType.GOAL);
		Event ev2 = new Event(team, p2, 12, EventType.GOAL);
		Event ev3 = new Event(team2, p4, 61, EventType.GOAL);
		MatchPlayedInfo m = prepareMatchPlayedInfo(team, team2, ev, ev2, ev3);
		matches.add(m);
		
		Event ev6 = new Event(team2, p4, 60, EventType.GOAL);
		Event ev4 = new Event(team, p, 11, EventType.GOAL);
		Event ev5 = new Event(team, p2, 21, EventType.GOAL);
		MatchPlayedInfo m2 = prepareMatchPlayedInfo(team2, team, ev4, ev5, ev6);
		matches.add(m2);
		
		teamStats = new SingleTeamStats(matches);
	}
	
	@Test
	public void testNumberOfGoalsForTeam() throws Exception {
		assertEquals(teamStats.getTotalHomeGoals(team), 2);
		assertEquals(teamStats.getTotalAwayGoals(team), 2);
		assertEquals(teamStats.getTotalGoals(team), 4);
	}
	
	@Test
	public void testNumberOfGoalsForTeam2() throws Exception {
		assertEquals(teamStats.getTotalHomeGoals(team2), 1);
		assertEquals(teamStats.getTotalAwayGoals(team2), 1);
		assertEquals(teamStats.getTotalGoals(team2), 2);
	}
	
	@Test
	public void testScorersInHomeForTeam() throws Exception {
		assertThat(teamStats.getGoalsInHome(team), 
			hasItems(
						new Event(team, new Player(1), 1, EventType.GOAL).prepareSnapshot(), 
						new Event(team, new Player(2), 12, EventType.GOAL).prepareSnapshot()
					)
			);
	}
	
	@Test
	public void testScorersAwayForTeam() throws Exception {
		assertThat(teamStats.getGoalsAway(team), 
			hasItems(
						new Event(team, new Player(1), 11, EventType.GOAL).prepareSnapshot(), 
						new Event(team, new Player(2), 21, EventType.GOAL).prepareSnapshot()
					)
			);
	}
	
	@Test
	public void testScorersInHomeForTeam2() throws Exception {
		assertThat(teamStats.getGoalsInHome(team2), 
			hasItems(
						new Event(team2, new Player(4), 60, EventType.GOAL).prepareSnapshot()
					)
			);
	}
	
	@Test
	public void testScorersAwayForTeam2() throws Exception {
		assertThat(teamStats.getGoalsAway(team2), 
			hasItems(
						new Event(team2, new Player(4), 61, EventType.GOAL).prepareSnapshot()
					)
			);
	}
	
	@Test
	public void testScorersTotalForTeam() throws Exception {
		assertThat(teamStats.getGoals(team), hasSize(4)); 
		assertThat(teamStats.getGoals(team), 
			hasItems(
						new Event(team, new Player(1), 11, EventType.GOAL).prepareSnapshot(), 
						new Event(team, new Player(2), 21, EventType.GOAL).prepareSnapshot(),
						new Event(team, new Player(1), 11, EventType.GOAL).prepareSnapshot(), 
						new Event(team, new Player(2), 21, EventType.GOAL).prepareSnapshot()
					)
			);
	}
	
	@Test
	public void testScorersTotalForTeam2() throws Exception {
		assertThat(teamStats.getGoals(team2), hasSize(2)); 
		assertThat(teamStats.getGoals(team2), 
			hasItems(
						new Event(team2, new Player(4), 60, EventType.GOAL).prepareSnapshot(), 
						new Event(team2, new Player(4), 61, EventType.GOAL).prepareSnapshot()
					)
			);
	}

	private MatchPlayedInfo prepareMatchPlayedInfo(Team homeTeam, Team awayTeam, Event... events) {
		FutureMatch matchToPlay = prepareFutureMatch(homeTeam, awayTeam);
		EventsInMatch eventsInMatch = new EventsInMatch();
		
		for(Event event : events)
			eventsInMatch.addNextEvent(event);

		return new MatchPlayedInfo(matchToPlay, 1000, eventsInMatch);
	}
	

	private FutureMatch prepareFutureMatch(Team team, Team team2) {
		return new FutureMatch(team, team2, 1);
	}

}
