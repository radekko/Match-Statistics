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

	public FutureMatch getMatch() {
		return match;
	}
	
	public int getLigueLine() {
		return match.getLigueLine();
	}

	public int getCrowd() {
		return crowd;
	}

	public EventsInMatch getEventsInMatch() {
		return eventsInMatch;
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
	
	public List<Score> getHomeGoalScorer(){
		return eventsInMatch.getGoalScorer(match.getHomeTeam());
	}
	
	public List<Score> getAwayGoalScorer(){
		return eventsInMatch.getGoalScorer(match.getAwayTeam());
	}
	
	public int getTotalGoalsForPlayerInMatch(Player player) {
		return eventsInMatch.getEventScoreForPlayer(player).size();
	}

	@Override
	public String toString() {
		String line = "Ligue line: " + getLigueLine() + "\n";
		String match = getHomeTeam() + " vs " + getAwayTeam() + "   ";
		String result = getHomeGoals() + ":" + getAwayGoals()+ "\n";
		String homeGoalScorer = (getHomeGoalScorer().isEmpty() ? "" : "HomeGoalScorer: " + "\n" + getHomeGoalScorer()+ "\n");	
		String awayGoalScorer = (getAwayGoalScorer().isEmpty() ? "" : "AwayGoalScorer: " + "\n" + getAwayGoalScorer()+ "\n");	
				 
		return line +
			   match + 
			   result  +
			   homeGoalScorer +
			   awayGoalScorer;
				
	}
	
}
