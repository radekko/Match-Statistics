package match.MatchStatistic;

import java.util.List;

import before.season.starting.FutureMatch;
import before.season.starting.Team;

public class MatchPlayedInfo {
	private FutureMatch match;
	private int crowd;
	EventsInMatch eventsInMatch;
	
	public MatchPlayedInfo(FutureMatch match, int crowd, EventsInMatch eventsInMatch) {
		this.match = match;
		this.crowd = crowd;
		this.eventsInMatch = eventsInMatch;
	}
	
	public void addEvent(Event event) {
		this.eventsInMatch.addEvent(event);
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

	@Override
	public String toString() {
		return "HomeGoals"+ getHomeGoals() + "AwayGoals"+ getAwayGoals() + "HomeGoalScorer" +getHomeGoalScorer() + "AwayGoalScorer"+ getAwayGoalScorer();

	}
	
}
