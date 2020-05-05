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

import afterPlaying.TeamStats;
import beforePlaying.FutureMatch;
import beforePlaying.Player;
import beforePlaying.Team;
import playing.Event;
import playing.Event.EventSnapshot;
import playing.EventType;
import playing.EventsInMatch;
import playing.HistoryMatchesRepo;
import playing.MatchPlayedInfo;
import playing.PlaceOfPlaying;

public class TeamStatsTest {

	private List<MatchPlayedInfo> matches;
	private HistoryMatchesRepo historyMatchesRepo;
	private TeamStats teamStats;
	private Team team;
	private Team team2;
	private Player p; 
	private Player p2;
	private Player p3;
	private Player p4;
	private Player p5;
	private Player p6;
	private Event pScoreForTeamIn1MinuteFirstMatch;
	private Event p2ScoreForTeamIn12MinuteFirstMatch; 
	private Event p4ScoreForTeam2In61MinuteFirstMatch;
	private Event p4ScoreForTeam2In60MinuteSecondMatch;
	private Event pScoreForTeamIn11MinuteSecondMatch;
	private Event p2ScoreForTeamIn21MinuteSecondMatch;

	/*
	 	Prepare two matches:
	 	1. team vs team2 2:1
	 	2. team2 vs team 1:2
	 */
	@Before
	public void setUp() {
		matches = new ArrayList<>();

		p = new Player(1);
		p2 = new Player(2);
		p3 = new Player(3);
		p4 = new Player(4);
		p5 = new Player(5);
		p6 = new Player(6);

		List<Player> players = new ArrayList<>(Arrays.asList(p, p2, p3));
		List<Player> players2 = new ArrayList<>(Arrays.asList(p4, p5, p6));

		team = new Team(1, players);
		team2 = new Team(2, players2);
		
		pScoreForTeamIn1MinuteFirstMatch = new Event(team, p, 1, EventType.GOAL);
		p2ScoreForTeamIn12MinuteFirstMatch = new Event(team, p2, 12, EventType.GOAL);
		p4ScoreForTeam2In61MinuteFirstMatch = new Event(team2, p4, 61, EventType.GOAL);
		MatchPlayedInfo m = prepareMatchPlayedInfo(
				team, team2, pScoreForTeamIn1MinuteFirstMatch, p2ScoreForTeamIn12MinuteFirstMatch, p4ScoreForTeam2In61MinuteFirstMatch);
		matches.add(m);
		
		pScoreForTeamIn11MinuteSecondMatch = new Event(team, p, 11, EventType.GOAL);
		p2ScoreForTeamIn21MinuteSecondMatch = new Event(team, p2, 21, EventType.GOAL);
		p4ScoreForTeam2In60MinuteSecondMatch = new Event(team2, p4, 60, EventType.GOAL);
		MatchPlayedInfo m2 = prepareMatchPlayedInfo(
				team2, team, pScoreForTeamIn11MinuteSecondMatch, p2ScoreForTeamIn21MinuteSecondMatch, p4ScoreForTeam2In60MinuteSecondMatch);
		matches.add(m2);
		
		historyMatchesRepo = new HistoryMatchesRepo();
		historyMatchesRepo.storeAllMatchesInHistory(matches);
		
		teamStats = new TeamStats(historyMatchesRepo);
	}
	
	@Test
	public void testNumberOfGoalsForTeam() throws Exception {
		assertEquals(teamStats.getTotalEventsForChosenPlace(team, PlaceOfPlaying.HOME, Event.isGoal()), 2);
		assertEquals(teamStats.getTotalEventsForChosenPlace(team, PlaceOfPlaying.AWAY, Event.isGoal()), 2);
		assertEquals(teamStats.getTotalEventsForChosenPlace(team, PlaceOfPlaying.HOME_OR_AWAY, Event.isGoal()), 4);
	}
	
	@Test
	public void testNumberOfGoalsForTeam2() throws Exception {
		assertEquals(teamStats.getTotalEventsForChosenPlace(team, PlaceOfPlaying.HOME, Event.isGoal()), 2);
		assertEquals(teamStats.getTotalEventsForChosenPlace(team, PlaceOfPlaying.AWAY, Event.isGoal()), 2);
		assertEquals(teamStats.getTotalEventsForChosenPlace(team, PlaceOfPlaying.HOME_OR_AWAY, Event.isGoal()), 4);
	}
	
	@Test
	public void testScorersInHomeForTeam() throws Exception {
		assertThat(teamStats.getEvents(team, PlaceOfPlaying.HOME, Event.isGoal()), 
			hasItems(
						toEventSnapshot(pScoreForTeamIn1MinuteFirstMatch), 
						toEventSnapshot(p2ScoreForTeamIn12MinuteFirstMatch) 
					)
			);
	}
	
	@Test
	public void testScorersAwayForTeam() throws Exception {
		assertThat(teamStats.getEvents(team, PlaceOfPlaying.AWAY, Event.isGoal()), 
			hasItems(
						toEventSnapshot(pScoreForTeamIn11MinuteSecondMatch),
						toEventSnapshot(p2ScoreForTeamIn21MinuteSecondMatch)
					)
			);
	}
	
	@Test
	public void testScorersInHomeForTeam2() throws Exception {
		assertThat(teamStats.getEvents(team2, PlaceOfPlaying.HOME, Event.isGoal()), 
			hasItems(
					toEventSnapshot(p4ScoreForTeam2In60MinuteSecondMatch)
					)
			);
	}
	
	@Test
	public void testScorersAwayForTeam2() throws Exception {
		assertThat(teamStats.getEvents(team2, PlaceOfPlaying.AWAY, Event.isGoal()), 
			hasItems(
						toEventSnapshot(p4ScoreForTeam2In61MinuteFirstMatch)
					)
			);
	}
	
	@Test
	public void testScorersTotalForTeam() throws Exception {
		assertThat(teamStats.getEvents(team, PlaceOfPlaying.HOME_OR_AWAY, Event.isGoal()), hasSize(4)); 
		assertThat(teamStats.getEvents(team, PlaceOfPlaying.HOME_OR_AWAY, Event.isGoal()), 
			hasItems(
						toEventSnapshot(pScoreForTeamIn1MinuteFirstMatch),
						toEventSnapshot(p2ScoreForTeamIn12MinuteFirstMatch),
						toEventSnapshot(pScoreForTeamIn11MinuteSecondMatch),
						toEventSnapshot(p2ScoreForTeamIn21MinuteSecondMatch)
					)
			);
	}
	
	@Test
	public void testScorersTotalForTeam2() throws Exception {
		assertThat(teamStats.getEvents(team2, PlaceOfPlaying.HOME_OR_AWAY, Event.isGoal()), hasSize(2)); 
		assertThat(teamStats.getEvents(team2, PlaceOfPlaying.HOME_OR_AWAY, Event.isGoal()), 
			hasItems(
						toEventSnapshot(p4ScoreForTeam2In61MinuteFirstMatch),
						toEventSnapshot(p4ScoreForTeam2In60MinuteSecondMatch)
					)
			);
	}
	
	private EventSnapshot toEventSnapshot(Event event){
		return event.prepareSnapshot();
	}

	private MatchPlayedInfo prepareMatchPlayedInfo(Team homeTeam, Team awayTeam, Event... events) {
		FutureMatch matchToPlay = prepareFutureMatch(homeTeam, awayTeam);
		EventsInMatch eventsInMatch = new EventsInMatch();
		for(Event event : events)
			eventsInMatch.addNextEvent(event);
		
		MatchPlayedInfo temp = new MatchPlayedInfo(matchToPlay, 1000, eventsInMatch);
		return temp;
	}
	

	private FutureMatch prepareFutureMatch(Team team, Team team2) {
		return new FutureMatch(team, team2, 1);
	}

}
