package playing.core.model;

import java.util.ArrayList;
import java.util.List;

import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;

public class EventsInMatch {
	private List<Event> events = new ArrayList<>();

	public void addNextEvent(Event e) {
		if(eventIsFormerThanLastlyAddedEvent(e))
			return;
		
		Player p = e.getPlayer();
		Team t = e.getTeam();
		
		if(ifOnlyZeroOrOneYellowCardForPlayerBefore(p) 
				&& ifNotRedCardForPlayerBefore(p) 
				&& ifLessThan7RedCardsForTeam(t))
			events.add(e);
	}
	
	public List<Event> getEvents() {
		return new ArrayList<Event>(events);
	}

	private boolean eventIsFormerThanLastlyAddedEvent(Event e) {
		return e.getMinute() < getMinuteOfLastEvent();
	}

	private boolean ifLessThan7RedCardsForTeam(Team t) {
		return getRedCards(t) < 7;
	}

	private boolean ifNotRedCardForPlayerBefore(Player p) {
		return getRedCards(p) == 0;
	}

	private boolean ifOnlyZeroOrOneYellowCardForPlayerBefore(Player p) {
		return getYellowCards(p) < 2;
	}
	
	private int getYellowCards(Player player) {
		return (int)events.stream()
			   .filter(Event.isYellowCard().and(Event.isForPlayer(player)))
			   .count();
	}
	
	private int getRedCards(Team team) {
		return (int)events.stream()
			   .filter(Event.isRedCard().and(Event.isForTeam(team)))
			   .count();
	}
	
	private int getRedCards(Player player) {
		return (int)events.stream()
			   .filter(Event.isRedCard().and(Event.isForPlayer(player)))
			   .count();
	}
	
	private int getMinuteOfLastEvent() {
		return events.stream()
			   .mapToInt(Event::getMinute)
			   .max().orElse(0);
	}
}