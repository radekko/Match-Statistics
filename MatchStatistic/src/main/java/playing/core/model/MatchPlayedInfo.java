package playing.core.model;

import java.util.function.Predicate;

import beforePlaying.core.model.FutureMatch;
import beforePlaying.core.model.Team;

public class MatchPlayedInfo {
	private Team homeTeam;
	private Team awayTeam;
	private int ligueLine;
	private int crowd;
	private MatchResult result;
	private Events events;
	
	public MatchPlayedInfo(FutureMatch match, int crowd, EventsInMatch eventsInMatch) {
		this.homeTeam = match.getHomeTeam();
		this.awayTeam = match.getAwayTeam();
		this.ligueLine = match.getLigueLine();
		this.crowd = crowd;
		this.events = new Events(eventsInMatch);
		this.result = computeResult();
	}
	
	public boolean isHomeWinner() {
		return result == MatchResult.HOME_WIN ? true : false;
	}
	
	public boolean isAwayWinner() {
		return result == MatchResult.AWAY_WIN ? true : false;
	}
	
	public boolean isDraw() {
		return result == MatchResult.DRAW ? true : false;
	}

	public static Predicate<MatchPlayedInfo> isTeamAsHomePred(Team team){
		return m -> m.isTeamAsHome(team);
	}
	
	public static Predicate<MatchPlayedInfo> isTeamAsAwayPred(Team team){
		return m -> m.isTeamAsAway(team);
	}
	
	public static Predicate<MatchPlayedInfo> isTeamAsHomeOrAwayPred(Team team){
		return isTeamAsHomePred(team).or(isTeamAsAwayPred(team));
	}
	
	public int getLigueLine() {
		return ligueLine;
	}

	public Events getEvents() {
		return events;
	}

	public int getCrowd() {
		return crowd;
	}
	
	private boolean isTeamAsHome(Team team){
		return team.equals(homeTeam) ? true : false; 
	}
	
	private boolean isTeamAsAway(Team team){
		return team.equals(awayTeam) ? true : false; 
	}
	
	private MatchResult computeResult() {
		long homeGoals = events.findEventsForTeam(Event.isGoal(), homeTeam).count();
		long awayGoals = events.findEventsForTeam(Event.isGoal(), awayTeam).count();
		
		if(homeGoals == awayGoals)
			return MatchResult.DRAW;
		
		return homeGoals > awayGoals ?  MatchResult.HOME_WIN : MatchResult.AWAY_WIN;
	}

	@Override
	public String toString() {
		String match = homeTeam + " vs " + awayTeam + "  ";
		String eventsToPrint = events.toString();
		return match + "\n" + eventsToPrint;
	}
}