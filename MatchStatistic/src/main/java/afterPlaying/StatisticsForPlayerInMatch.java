package afterPlaying;

import java.util.List;
import java.util.stream.Collectors;

import beforePlaying.Player;
import playing.Event;

public class StatisticsForPlayerInMatch {

	private List<Event> events;

	public StatisticsForPlayerInMatch(List<Event> events) {
		this.events = events;
	}
	
	public List<Event> getGoalMinutesForPlayer(Player player){
		return events.stream()
			   .filter(Event.isGoal().and(Event.isForPlayer(player)))
			   .collect(Collectors.toList());
	}
	
	public List<Event> getYellowCards(Player player) {
		return events.stream()
			   .filter(Event.isYellowCard().and(Event.isForPlayer(player)))
			   .collect(Collectors.toList());
	}
	
	public List<Event> getRedCards(Player player) {
		return events.stream()
			   .filter(Event.isRedCard().and(Event.isForPlayer(player)))
			   .collect(Collectors.toList());
	}

}
