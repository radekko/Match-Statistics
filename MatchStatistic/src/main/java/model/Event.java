package model;

import java.util.function.Predicate;

public class Event {
	private Team team;
	private Player player;
	private int minute;
	private EventType eventType;
	
	public Event(Team team, Player player, int minute, EventType eventType) {
		this.team = team;
		this.player = player;
		this.minute = minute;
		this.eventType = eventType;
	}
	
	public EventSnapshot prepareSnapshot() {
		return new EventSnapshot(player, minute);
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	public static Predicate<Event> isGoal() {
	    return e -> e.getEventType() == EventType.GOAL;
	}
	
	public static Predicate<Event> isYellowCard() {
	    return e -> e.getEventType() == EventType.YELLOW_CARD;
	}
	
	public static Predicate<Event> isRedCard() {
	    return e -> e.getEventType() == EventType.RED_CARD;
	}
	
	public static Predicate<Event> isForPlayer(Player player) {
	    return e ->  e.getPlayer() == player;
	}
	
	public static Predicate<Event> isForTeam(Team team) {
	    return e ->  e.getTeam() == team;
	}
	
	public class EventSnapshot{
		private Player player;
		private int minute;
		
		public EventSnapshot(Player player, int minute) {
			this.player = player;
			this.minute = minute;
		}
		
		public Player getPlayer() {
			return player;
		}
		
		public int getMinute() {
			return minute;
		}
		
		@Override
		public String toString() {
			return player + " - minute: " + minute;
		}
		
	}
}
