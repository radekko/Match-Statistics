package model;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticsForTeamInMatch {
	private List<Event> eventsForTeam;
	private Team team;
	private boolean isHome;

	public StatisticsForTeamInMatch(List<Event> eventsForTeam, Team team, boolean isHome) {
		this.eventsForTeam = eventsForTeam;
		this.team = team;
		this.isHome = isHome;
	}

	public List<Event> getGoalScorersForTeam(){
		return eventsForTeam.stream()
			   .filter(Event.isGoal())
			   .collect(Collectors.toList());
	}
	
	public List<Event> getYellowCardsWithDetails() {
		return eventsForTeam.stream()
			   .filter(Event.isYellowCard())
			   .collect(Collectors.toList());
	}
	
	public List<Event> getRedCards(Team team) {
		return eventsForTeam.stream()
			   .filter(Event.isRedCard())
			   .collect(Collectors.toList());
	}
	
}
