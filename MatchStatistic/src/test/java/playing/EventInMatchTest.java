package playing;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import beforePlaying.Player;
import beforePlaying.Team;
import playing.Event;
import playing.EventType;
import playing.EventsInMatch;

public class EventInMatchTest {
	private static Player player;
	private static Team team;
	private EventsInMatch eventsInMatch;

	@BeforeClass
	public static void setUp() {
		player = new Player(1);
		team = new Team(1l);
	}
	
	@Before
	public void setUp2() {
		eventsInMatch = new EventsInMatch();
	}
	
	@Test
	public void cannotAddFormerEvent() throws Exception {
		Event event = prepareEvent(team, player, 81, EventType.YELLOW_CARD);
		Event event2 = prepareEvent(team, player, 80, EventType.YELLOW_CARD);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		assertEquals(eventsInMatch.getEvents().size(), 1);
		assertThat(eventsInMatch.getEvents(), hasItem(event));
	}
	
	@Test
	public void addTwoYellowCards() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.YELLOW_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.YELLOW_CARD);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		assertEquals(eventsInMatch.getEvents().size(), 2);
		assertThat(eventsInMatch.getEvents(), hasItems(event, event2));
	}
	
	@Test
	public void addThreeYellowCards() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.YELLOW_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.YELLOW_CARD);
		Event event3 = prepareEvent(team, player, 82, EventType.YELLOW_CARD);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		eventsInMatch.addNextEvent(event3);
		
		assertEquals(eventsInMatch.getEvents().size(), 2);
		assertThat(eventsInMatch.getEvents(), hasItems(event, event2));
	}
	
	@Test
	public void addGoalAfterYellowCard() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.YELLOW_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.GOAL);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		assertEquals(eventsInMatch.getEvents().size(), 2);
		assertThat(eventsInMatch.getEvents(), hasItems(event, event2));
	}
	
	@Test
	public void addGoalAfterTwoYellowCards() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.YELLOW_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.YELLOW_CARD);
		Event event3 = prepareEvent(team, player, 82, EventType.GOAL);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		eventsInMatch.addNextEvent(event3);
		
		assertEquals(eventsInMatch.getEvents().size(), 2);
		assertThat(eventsInMatch.getEvents(), hasItems(event, event2));
	}
	
	@Test
	public void addTwoRedCards() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.RED_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.RED_CARD);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		assertEquals(eventsInMatch.getEvents().size(), 1);
		assertThat(eventsInMatch.getEvents(), hasItems(event));
	}
	
	@Test
	public void addGoalAfterRedCard() throws Exception {
		Event event = prepareEvent(team, player, 80, EventType.RED_CARD);
		Event event2 = prepareEvent(team, player, 81, EventType.GOAL);
		
		eventsInMatch.addNextEvent(event);
		eventsInMatch.addNextEvent(event2);
		
		assertEquals(eventsInMatch.getEvents().size(), 1);
		assertThat(eventsInMatch.getEvents(), hasItems(event));
	}
	
	private Event prepareEvent(Team team, Player player, int minute, EventType eventType) {
		return new Event(team, player, minute, eventType);
	}
}
