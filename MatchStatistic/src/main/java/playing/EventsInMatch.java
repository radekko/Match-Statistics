package playing;

import java.util.ArrayList;
import java.util.List;

import beforePlaying.Player;
import beforePlaying.Team;

public class EventsInMatch {
	private List<Event> events = new ArrayList<>();

	public void addNextEvent(Event e) {
		//not next
		if(e.getMinute() < getMinuteOfLastEvent())
			return;
		
		Player p = e.getPlayer();
		Team t = e.getTeam();
		
		if(getYellowCards(p) < 2 && getRedCards(p) == 0 && getRedCards(t) < 7)
			events.add(e);
	}
	
	public List<Event> getEvents() {
		return new ArrayList<Event>(events);
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