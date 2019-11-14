package match.MatchStatistic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import model.Event;
import model.EventType;
import model.EventsInMatch;
import model.Player;
import model.Team;

public class EventInMatchTest {
	
	private static Player player;
	private static Player player2;
	private static Team team;
	private EventsInMatch eventsInMatch;

	@BeforeClass
	public static void setUp() {
		player = new Player(1);
		player2 = new Player(2);
		team = new Team(1l);
	}
	
	@Before
	public void setUp2() {
		eventsInMatch = new EventsInMatch();
	}
	
	/*@Test
	public void cannotAddFormerEvent() throws Exception {
		Event event = prepareEvent(team, player, 81, EventType.YELLOW_CARD);
		Event event2 = prepareEvent(team, player, 80, EventType.YELLOW_CARD);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		Assert.assertEquals(eventsInMatch.getAmountOfEvents(), 1);
		Assert.assertEquals(eventsInMatch.getYellowCards(player).get(0).getMinute(), 81);
	}
	
	@Test
	public void addTwoYellowCards() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.YELLOW_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.YELLOW_CARD);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		Assert.assertEquals(eventsInMatch.getYellowCards(player).size(), 2);
		Assert.assertEquals(eventsInMatch.getYellowCards(player).get(0).getMinute(), 80);
		Assert.assertEquals(eventsInMatch.getYellowCards(player).get(1).getMinute(), 81);
	}
	
	@Test
	public void addThreeYellowCards() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.YELLOW_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.YELLOW_CARD);
		Event event3 = prepareEvent(team, player, 82, EventType.YELLOW_CARD);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		eventsInMatch.addNextEvent(event3);
		
		Assert.assertEquals(eventsInMatch.getYellowCards(player).size(), 2);
		Assert.assertEquals(eventsInMatch.getYellowCards(player).get(0).getMinute(), 80);
		Assert.assertEquals(eventsInMatch.getYellowCards(player).get(1).getMinute(), 81);
	}
	
	@Test
	public void addGoalAfterYellowCard() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.YELLOW_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.GOAL);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		Assert.assertEquals(eventsInMatch.getYellowCards(player).size(), 1);
		Assert.assertEquals(eventsInMatch.getYellowCards(player).get(0).getMinute(), 80);
		Assert.assertEquals(eventsInMatch.getGoalMinutesForPlayer(player).size(), 1);
	}
	
	@Test
	public void addGoalAfterTwoYellowCards() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.YELLOW_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.YELLOW_CARD);
		Event event3 = prepareEvent(team, player, 82, EventType.GOAL);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		eventsInMatch.addNextEvent(event3);
		
		Assert.assertEquals(eventsInMatch.getYellowCards(player).size(), 2);
		Assert.assertEquals(eventsInMatch.getYellowCards(player).get(0).getMinute(), 80);
		Assert.assertEquals(eventsInMatch.getYellowCards(player).get(1).getMinute(), 81);
		Assert.assertEquals(eventsInMatch.getGoalMinutesForPlayer(player).size(), 0);
	}
	
	@Test
	public void addTwoRedCards() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.RED_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.RED_CARD);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		Assert.assertEquals(eventsInMatch.getRedCards(player).size(), 1);
		Assert.assertEquals(eventsInMatch.getRedCards(player).get(0).getMinute(), 80);
	}
	
	@Test
	public void addGoalAfterRedCard() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.RED_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.GOAL);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		Assert.assertEquals(eventsInMatch.getRedCards(player).size(),1);
		Assert.assertEquals(eventsInMatch.getRedCards(player).get(0).getMinute(),80);
		Assert.assertEquals(eventsInMatch.getGoalMinutesForPlayer(player).size(),0);
	}
	
	@Test
	public void addTwoYellowCardsToTheSameTeamAndCheckAmount() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.YELLOW_CARD);
		Event event2 = prepareEvent(team, player2, 81, EventType.YELLOW_CARD);

		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		Assert.assertEquals(eventsInMatch.getTotalYellowCards(team),2);
	}
	
	@Test
	public void addTwoGoalsToTheSameTeamAndCheckAmount() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.GOAL);
		Event event2 = prepareEvent(team, player2, 81, EventType.GOAL);

		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		Assert.assertEquals(eventsInMatch.getTotalGoalsInMatchForTeam(team),2);
	}
	
	private Event prepareEvent(Team team, Player player, int minute, EventType eventType) {
		return new Event(team, player, minute, eventType);
	}*/
}
