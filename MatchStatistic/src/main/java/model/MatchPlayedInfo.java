package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import beforeStarting.FutureMatch;
import model.Event.EventSnapshot;

public class MatchPlayedInfo {
	private Team homeTeam;
	private Team awayTeam;
	private int ligueLine;
	private int crowd;
	private List<Event> events = new ArrayList<>();
	
	private List<EventSnapshot> homeGoals = new ArrayList<>();
	private List<EventSnapshot> awayGoals = new ArrayList<>();
	private List<EventSnapshot> homeYellowCards = new ArrayList<>();
	private List<EventSnapshot> awayYellowCards = new ArrayList<>();
	private List<EventSnapshot> homeRedCards = new ArrayList<>();
	private List<EventSnapshot> awayRedCards = new ArrayList<>();
	
	public MatchPlayedInfo(FutureMatch match, int crowd, EventsInMatch eventsInMatch) {
		this.homeTeam = match.getHomeTeam();
		this.awayTeam = match.getAwayTeam();
		this.ligueLine = match.getLigueLine();
		this.crowd = crowd;
		this.events = eventsInMatch.getEvents();
		
		this.homeGoals = prepareGoalsList(homeTeam);
		this.awayGoals = prepareGoalsList(awayTeam);
		
		this.homeYellowCards = prepareYellowCardsList(homeTeam);
		this.awayYellowCards = prepareYellowCardsList(awayTeam);
		
		this.homeRedCards = prepareRedCardsList(homeTeam);
		this.awayRedCards = prepareRedCardsList(awayTeam);
	}
	
	public boolean isHost(Team team){
		return  team == homeTeam ? true : false; 
	}
	
	public boolean isAway(Team team){
		return  team == awayTeam ? true : false; 
	}
	
	public int getLigueLine() {
		return ligueLine;
	}

	public int getCrowd() {
		return crowd;
	}
	
	public List<EventSnapshot> getHomeGoals() {
		return homeGoals;
	}

	public List<EventSnapshot> getAwayGoals() {
		return awayGoals;
	}

	public List<EventSnapshot> getHostYellowCards() {
		return homeYellowCards;
	}

	public List<EventSnapshot> getAwayYellowCards() {
		return awayYellowCards;
	}

	public List<EventSnapshot> getHostRedCards() {
		return homeRedCards;
	}

	public List<EventSnapshot> getAwayRedCards() {
		return awayRedCards;
	}

	private List<EventSnapshot> prepareGoalsList(Team team){
		return findEventsForTeam(Event.isGoal(), team);
	}
	
	private List<EventSnapshot> prepareYellowCardsList(Team team){
		return findEventsForTeam(Event.isYellowCard(), team);
	}
	
	private List<EventSnapshot> prepareRedCardsList(Team team){
		return findEventsForTeam(Event.isRedCard(), team);
	}
	
	private List<EventSnapshot> findEventsForTeam(Predicate<Event> predEventType, Team team){
		return events.stream()
				   .filter(predEventType.and(Event.isForTeam(team)))
				   .map(Event::prepareSnapshot)
				   .collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		String match = homeTeam + " vs " + awayTeam + "  ";
		String result = homeGoals.size() + ":" + awayGoals.size() + "\n";
		String homeGoalScorer2 = (homeGoals.isEmpty() ? "" : "HomeGoalScorer: " + "\n" + homeGoals+ "\n");
		String homeYellowCards2 = (homeYellowCards.isEmpty() ? "" : "HomeYellowCards: " + "\n" + homeYellowCards+ "\n");
		String homeRedCards2 = (homeRedCards.isEmpty() ? "" : "HomeRedCards: " + "\n" + homeRedCards+ "\n");
		String awayGoalScorer2 = (awayGoals.isEmpty() ? "" : "AwayGoalScorer: " + "\n" + awayGoals+ "\n");
		String awayYellowCards2 = (awayYellowCards.isEmpty() ? "" : "AwayYellowCards: " + "\n" + awayYellowCards+ "\n");
		String awayRedCards2 = (awayRedCards.isEmpty() ? "" : "AwayRedCards: " + "\n" + awayRedCards+ "\n");
				 
		return match + 
			   result  +
			   homeGoalScorer2 +
			   awayGoalScorer2 +
			   homeYellowCards2 +
			   awayYellowCards2 +
			   homeRedCards2 +
			   awayRedCards2;
	}
}