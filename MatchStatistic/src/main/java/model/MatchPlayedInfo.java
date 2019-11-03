package model;

import java.util.List;

import beforeStarting.FutureMatch;

public class MatchPlayedInfo {
	private FutureMatch match;
	private int crowd;
	private EventsInMatch eventsInMatch;
	
	public MatchPlayedInfo(FutureMatch match, int crowd, EventsInMatch eventsInMatch) {
		this.match = match;
		this.crowd = crowd;
		this.eventsInMatch = eventsInMatch;
	}
	
	public int getLigueLine() {
		return match.getLigueLine();
	}

	public int getCrowd() {
		return crowd;
	}

	public Team getHomeTeam() {
		return match.getHomeTeam();
	}
	
	public Team getAwayTeam() {
		return match.getAwayTeam();
	}
	
	public int getHomeGoals() {
		return eventsInMatch.getGoals(match.getHomeTeam());
	}
	
	public int getAwayGoals() {
		return eventsInMatch.getGoals(match.getAwayTeam());
	}
	
	public int getHomeYellowCards() {
		return eventsInMatch.getYellowCards(match.getHomeTeam());
	}
	
	public int getAwayYellowCards() {
		return eventsInMatch.getYellowCards(match.getAwayTeam());
	}
	
	public List<Event> getHomeGoalScorer(){
		return eventsInMatch.getGoalScorer(match.getHomeTeam());
	}
	
	public List<Event> getAwayGoalScorer(){
		return eventsInMatch.getGoalScorer(match.getAwayTeam());
	}
	
	public List<Event> getHomeYellowCardsWithDetails(){
		return eventsInMatch.getYellowCardsWithDetails(match.getHomeTeam());
	}
	
	public List<Event> getAwayYellowCardsWithDetails(){
		return eventsInMatch.getYellowCardsWithDetails(match.getAwayTeam());
	}
	
	public List<Event> getHomeRedCards(){
		return eventsInMatch.getRedCards(match.getHomeTeam());
	}
	
	public List<Event> getAwayRedCards(){
		return eventsInMatch.getRedCards(match.getAwayTeam());
	}
	
	public int getTotalGoalsForPlayerInMatch(Player player) {
		return eventsInMatch.getEventScoreForPlayer(player).size();
	}

	@Override
	public String toString() {
		String match = getHomeTeam() + " vs " + getAwayTeam() + "  ";
		String result = getHomeGoals() + ":" + getAwayGoals()+ "\n";
		String homeGoalScorer = (getHomeGoalScorer().isEmpty() ? "" : "HomeGoalScorer: " + "\n" + getHomeGoalScorer()+ "\n");
		String homeYellowCards = (getHomeYellowCardsWithDetails().isEmpty() ? "" : "HomeYellowCards: " + "\n" + getHomeYellowCardsWithDetails()+ "\n");
		String homeRedCards = (getHomeRedCards().isEmpty() ? "" : "HomeRedCards: " + "\n" + getHomeRedCards()+ "\n");
		String awayGoalScorer = (getAwayGoalScorer().isEmpty() ? "" : "AwayGoalScorer: " + "\n" + getAwayGoalScorer()+ "\n");
		String awayYellowCards = (getHomeYellowCardsWithDetails().isEmpty() ? "" : "HomeYellowCards: " + "\n" + getHomeYellowCardsWithDetails()+ "\n");
		String awayRedCards = (getAwayRedCards().isEmpty() ? "" : "AwayRedCards: " + "\n" + getAwayRedCards()+ "\n");
				 
		return match + 
			   result  +
			   homeGoalScorer +
			   homeYellowCards +
			   homeRedCards +
			   awayGoalScorer +
			   awayYellowCards +
			   awayRedCards;
	}
	
}
