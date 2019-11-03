package model;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

public class EventsInMatch {
	private List<Event> events = new ArrayList<>();

	public void addEvent(Event e) {
		Player p = e.getPlayer();
		
		if(getYellowCards(p).size() < 2 && getRedCards(p).size() == 0) {
			events.add(e);
		}
		else {
			OptionalInt redCardMinute = getMinuteOfGettingRedCard(p);
			OptionalInt secondYellowCardMinute = getMinuteOfGettingSecondYellowCards(p);
			
			redCardMinute.ifPresent(addToEventsIfCurrEventIsFormer(e));
			secondYellowCardMinute.ifPresent(addToEventsIfCurrEventIsFormer(e));
		}
	}

	public void addEvents(EventsInMatch tempEvents) {
		events.addAll(tempEvents.events);
	}
	
	public int getGoals(Team team) {
		return (int)events.stream().filter(e -> (e.getEventType() == EventType.GOAL && e.getTeam() == team)).count();
	}
	
	public List<Event> getGoalScorer(Team team){
		return events.stream()
			   .filter(e -> (e.getEventType() == EventType.GOAL && e.getTeam() == team))
			   .collect(Collectors.toList());
	}
	
	public List<Event> getEventScoreForPlayer(Player player){
		return events.stream()
			   .filter(e -> (e.getEventType() == EventType.GOAL && e.getPlayer() == player))
			   .collect(Collectors.toList());
	}

	public List<Event> getYellowCards(Team team) {
		return events.stream()
			   .filter(e -> (e.getEventType() == EventType.YELLOW_CARD && e.getTeam() == team))
			   .collect(Collectors.toList());
	}
	
	public List<Event> getYellowCards(Player player) {
		return events.stream()
			   .filter(e -> (e.getEventType() == EventType.YELLOW_CARD && e.getPlayer() == player))
			   .collect(Collectors.toList());
	}
	
	public OptionalInt getMinuteOfGettingSecondYellowCards(Player player) {
		return events.stream()
			   .filter(e -> (e.getEventType() == EventType.YELLOW_CARD && e.getPlayer() == player))
			   .mapToInt(Event::getMinute)
			   .sorted()
			   .filter(n -> n % 1 == 0)
			   .findAny();
	}
	
	public List<Event> getRedCards(Team team) {
		return events.stream()
			   .filter(e -> (e.getEventType() == EventType.RED_CARD && e.getTeam() == team))
			   .collect(Collectors.toList());
	}

	public List<Event> getRedCards(Player player) {
		return events.stream()
			   .filter(e -> (e.getEventType() == EventType.RED_CARD && e.getPlayer() == player))
			   .collect(Collectors.toList());
	}
	
	public OptionalInt getMinuteOfGettingRedCard(Player player) {
		return events.stream()
			   .filter(e -> (e.getEventType() == EventType.RED_CARD && e.getPlayer() == player))
			   .mapToInt(Event::getMinute)
			   .findAny();
	}
	
	private IntConsumer addToEventsIfCurrEventIsFormer(Event e) {
		return m -> {
			if(m>e.getMinute())
				events.add(e);
		};
	}

}
