package playing.core.playingMatches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import beforePlaying.core.model.FutureMatch;
import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.EventType;
import playing.core.model.EventsInMatch;

public class DrawingEvents {
	
	public EventsInMatch drawEvents(FutureMatch futureMatch) {
		int numberOfEvents = draw(1,5);
		List<Integer> minutes = drawMinutesWhenEventsTakePlace(numberOfEvents);
		List<Event> eventsInMatch = minutes.stream().map(m -> createRandomEventForChosenMinute(futureMatch,m)).collect(Collectors.toList());
		
		EventsInMatch ev = new EventsInMatch();
		for(Event e : eventsInMatch)
			ev.addNextEvent(e);
		
		return ev;
	}
	
	private List<Integer> drawMinutesWhenEventsTakePlace(int numberOfEvents){
		List<Integer> minutes = new ArrayList<>();
		for (int i = 0; i < numberOfEvents; i++)
			minutes.add(draw(1,90));
		
		Collections.sort(minutes);
		return minutes;
	}

	private Event createRandomEventForChosenMinute(FutureMatch futureMatch, int minute) {
		Team team = drawTeam(futureMatch);
		Player player = drawPlayer(team);
		EventType eventType = drawEvent();
		
		return new Event(team, player, minute, eventType);
	}

	private EventType drawEvent() {
		int eventsAmount = EventType.class.getEnumConstants().length;
		int whichEvent = ThreadLocalRandom.current().nextInt(0, eventsAmount);
		return EventType.class.getEnumConstants()[whichEvent];
	}

	private Player drawPlayer(Team team) {
		int whichPlayer = draw(0 , 10);
		return team.getPlayers().get(whichPlayer);
	}
	
	private Team drawTeam(FutureMatch futureMatch) {
		int whichTeam = draw(1, 2);
		return (whichTeam == 1 ? futureMatch.getHomeTeam() : futureMatch.getAwayTeam());
	}
	
	private int draw(int from, int to) {
		return ThreadLocalRandom.current().nextInt(from, to + 1);
	}
	
}
