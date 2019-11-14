package model;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;


//TODO!!!!!!!!!!!!! probably goalsInMatch, yellowCardsInMatch, RedCardsInmatch
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

	/*public int getAmountOfEvents() {
		return events.size();
	}*/
	
	//get Events for team
	/*public List<Event> getEventsForTeam(Team team){
		return events.stream()
			   .filter(Event.isForTeam(team))
			   .collect(Collectors.toList());
	}*/
	
	//get events for player
	/*public List<Event> getEventsForPlayer(Player player){
		return events.stream()
			   .filter(Event.isForPlayer(player))
			   .collect(Collectors.toList());
	}*/
	
	
	//get home goal scorers -> List<PlayerId, minute>
	/*public List<Event> getGoalScorersForTeam(Team team){
		return events.stream()
			   .filter(Event.isGoal().and(Event.isForTeam(team)))
			   .collect(Collectors.toList());
	}*/
	//TODO: return List<Integers> minutes
	/*public List<Event> getGoalMinutesForPlayer(Player player){
		return events.stream()
			   .filter(Event.isGoal().and(Event.isForPlayer(player)))
			   .collect(Collectors.toList());
	}*/

	//TODO  return list with minute and player
	/*public List<Event> getYellowCardsWithDetails(Team team) {
		return events.stream()
			   .filter(Event.isYellowCard().and(Event.isForTeam(team)))
			   .collect(Collectors.toList());
	}*/
	//TODO return List<Integer> minutes
//	public List<Event> getYellowCards(Player player) {
//		return events.stream()
//			   .filter(Event.isYellowCard().and(Event.isForPlayer(player)))
//			   .collect(Collectors.toList());
//	}
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
	
	
	//TODO return List<Integer> minutes
	/*public List<Event> getRedCards(Team team) {
		return events.stream()
			   .filter(Event.isRedCard().and(Event.isForTeam(team)))
			   .collect(Collectors.toList());
	}*/
	//TODO: return Optional<Integer> minute
	/*public List<Event> getRedCards(Player player) {
		return events.stream()
			   .filter(Event.isRedCard().and(Event.isForPlayer(player)))
			   .collect(Collectors.toList());
	}*/

	/*public OptionalInt getMinuteOfGettingSecondYellowCards(Player player) {
		return events.stream()
			   .filter(e -> (e.getEventType() == EventType.YELLOW_CARD && e.getPlayer() == player))
			   .mapToInt(Event::getMinute)
			   .sorted()
			   .filter(n -> n % 1 == 0)
			   .findAny();
	}
	
	public OptionalInt getMinuteOfGettingRedCard(Player player) {
		return events.stream()
			   .filter(e -> (e.getEventType() == EventType.RED_CARD && e.getPlayer() == player))
			   .mapToInt(Event::getMinute)
			   .findAny();
	}*/
	
	private int getMinuteOfLastEvent() {
		return events.stream()
			   .mapToInt(Event::getMinute)
			   .max().orElse(0);
	}
}