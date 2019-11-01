package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventsInMatch {
	List<Event> events = new ArrayList<>();

	public void addEvent(Event e) {
		events.add(e);
	}
	
	public int getGoals(Team team) {
		return (int)events.stream().filter(e -> (e.getEventType() == EventType.GOAL && e.getTeam() == team)).count();
	}
	
	public List<Score> getGoalScorer(Team team){
		return events.stream().filter(
					e -> (e.getEventType() == EventType.GOAL && e.getTeam() == team)
				)
				.map(
						e -> new Score(e.getPlayer(),e.getMinute())
				)
				.collect(Collectors.toList());
	}
	
	public List<Score> getEventScoreForPlayer(Player player){
		return events.stream().filter(
				e -> (e.getEventType() == EventType.GOAL && e.getPlayer() == player)
			)
			.map(
					e -> new Score(e.getPlayer(),e.getMinute())
			)
			.collect(Collectors.toList());
	}
	
	//here get all scorers
	

}
