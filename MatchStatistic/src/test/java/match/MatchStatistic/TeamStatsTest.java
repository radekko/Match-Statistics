package match.MatchStatistic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import beforeStarting.FutureMatch;
import model.Event;
import model.Event.EventSnapshot;
import model.EventType;
import model.EventsInMatch;
import model.MatchPlayedInfo;
import model.Player;
import model.Team;
import statistics.PlaceOfPlaying;
import statistics.PlayerStats;
import statistics.TeamStats;

public class TeamStatsTest {

	private List<MatchPlayedInfo> matches;
	private TeamStats teamStats;
	private Team team;
	private Team team2;
	private PlayerStats playerStats;

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
		MatchPlayedInfo m = prepareMatchPlayedInfo(team, team2,p,p4);
		MatchPlayedInfo m2 = prepareMatchPlayedInfo(team2, team,p4,p);
		
		matches.add(m);
		matches.add(m2);
		
		teamStats = new TeamStats(matches);
		playerStats = new PlayerStats(teamStats);
		
		System.out.println("home");
		System.out.println(playerStats.getHomeOrAwayEvents(team, p, PlaceOfPlaying.HOME, MatchPlayedInfo::getHomeGoals));
		System.out.println("away");
		System.out.println(playerStats.getHomeOrAwayEvents(team, p, PlaceOfPlaying.AWAY, MatchPlayedInfo::getAwayGoals));
		System.out.println("total");
		System.out.println(playerStats.getSumOfEvents(team, p, MatchPlayedInfo::getHomeGoals, MatchPlayedInfo::getAwayGoals));
	}
	
	@Test
	public void testName() throws Exception {
		List<EventSnapshot> homeOrAwayEvents = teamStats.getHomeOrAwayEvents(team, PlaceOfPlaying.HOME, MatchPlayedInfo::getHomeGoals);
		assertEquals(homeOrAwayEvents.size(), 2);
	}
	
//	@Test
//	public void testName2() throws Exception {
//		List<EventSnapshot> homeOrAwayEvents = teamStats.getHomeOrAwayEvents(team, PlaceOfPlaying.HOME, MatchPlayedInfo::getHomeGoals);
//		assertEquals(homeOrAwayEvents.size(), 2);
//	}

	private MatchPlayedInfo prepareMatchPlayedInfo(Team team, Team team2, Player playerFromTeam, Player playerFromTeam2) {

		FutureMatch matchToPlay = prepareFutureMatch(team, team2);

		EventsInMatch eventsInMatch = new EventsInMatch();
		Event ev = new Event(team, playerFromTeam, 1, EventType.GOAL);
		Event ev2 = new Event(team, playerFromTeam, 12, EventType.GOAL);
		Event ev3 = new Event(team2, playerFromTeam2, 60, EventType.GOAL);
		eventsInMatch.addNextEvent(ev);
		eventsInMatch.addNextEvent(ev2);
		eventsInMatch.addNextEvent(ev3);

		return new MatchPlayedInfo(matchToPlay, 1000, eventsInMatch);
	}

	private FutureMatch prepareFutureMatch(Team team, Team team2) {
		return new FutureMatch(team, team2, 1);
	}

	private Team prepareTeam(int teamId, int playerId, int player2Id, int player3Id) {
		List<Player> players = new ArrayList<>();
		players.add(new Player(playerId));
		players.add(new Player(player2Id));
		players.add(new Player(player3Id));

		return new Team(teamId, players);
	}

}
