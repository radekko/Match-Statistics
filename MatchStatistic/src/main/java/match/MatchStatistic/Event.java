package match.MatchStatistic;

import before.season.starting.Team;

public class Event {
	
	Team team;
	Player player;
	int minute;
	EventType eventType;
	
	public Event(Team team, Player player, int minute, EventType eventType) {
		this.team = team;
		this.player = player;
		this.minute = minute;
		this.eventType = eventType;
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
	
}
