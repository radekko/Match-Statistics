package playing.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import beforePlaying.core.model.FutureMatch;
import beforePlaying.core.model.Team;
import playing.core.model.Event.EventSnapshot;

public class MatchPlayedInfo {
	private Team homeTeam;
	private Team awayTeam;
	private int ligueLine;
	private int crowd;
	private MatchResult result;
	private List<Event> events = new ArrayList<>();
	
	public MatchPlayedInfo(FutureMatch match, int crowd, EventsInMatch eventsInMatch) {
		this.homeTeam = match.getHomeTeam();
		this.awayTeam = match.getAwayTeam();
		this.ligueLine = match.getLigueLine();
		this.crowd = crowd;
		this.events = eventsInMatch.getEvents();
		this.result = computeResult();
	}
	
	private MatchResult computeResult() {
		long homeGoals = findEventsForHomeTeam(Event.isGoal()).count();
		long awayGoals = findEventsForAwayTeam(Event.isGoal()).count();
		
		if(homeGoals == awayGoals)
			return MatchResult.DRAW;
		
		return homeGoals > awayGoals ?  MatchResult.HOME_WIN : MatchResult.AWAY_WIN;
	}
	
	public TeamResult homeTeamResult() {
		return checkTeamResult(homeTeam);
	}
	
	public TeamResult awayTeamResult() {
		return checkTeamResult(homeTeam);
	}
	
	public TeamResult checkTeamResult(Team team) {
		if(result == MatchResult.DRAW)
			return TeamResult.DRAW;
		
		if(team.equals(homeTeam)) {
			if(result == MatchResult.HOME_WIN)
				return TeamResult.WIN;
			else
				return TeamResult.LOOSE;
		}
		
		if(result == MatchResult.HOME_WIN)
			return TeamResult.LOOSE;
		
		return TeamResult.WIN;
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

	public boolean isHost(Team team){
		return team.equals(homeTeam) ? true : false; 
	}
	
	public boolean isAway(Team team){
		return team.equals(awayTeam) ? true : false; 
	}
	
	public boolean isHomeOrAway(Team team){
		return team.equals(homeTeam) ? true : false ||
				team.equals(awayTeam) ? true : false; 
	}
	
	public int getLigueLine() {
		return ligueLine;
	}

	public int getCrowd() {
		return crowd;
	}
	
	public MatchResult getResult() {
		return result;
	}

	public Stream<EventSnapshot> findEventsForHomeTeam(Predicate<Event> predEventType){
		return events.stream()
				   .filter(predEventType.and(Event.isForTeam(homeTeam)))
				   .map(Event::prepareSnapshot);
	}
	
	public Stream<EventSnapshot> findEventsForAwayTeam(Predicate<Event> predEventType){
		return events.stream()
				   .filter(predEventType.and(Event.isForTeam(awayTeam)))
				   .map(Event::prepareSnapshot);
	}

	@Override
	public String toString() {
		String match = homeTeam + " vs " + awayTeam + "  ";
		String eventsToPrint = events.stream().map(Event::toString).collect(Collectors.joining("\n"));
		return match + "\n" + eventsToPrint;
	}
}