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
	
	public TeamResult homeTeamResult() {
		return checkTeamResult(homeTeam);
	}
	
	public TeamResult awayTeamResult() {
		return checkTeamResult(awayTeam);
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
	
	public Stream<Event> getEventsFromMatch(Team team){
		return events.stream().filter(Event.isForTeam(team));
	}
	
	public Stream<Event> getEventsFromMatchForOpponent(Team team){
		return events.stream().filter(Event.isForTeam(team).negate());
	}
	
	public int getLigueLine() {
		return ligueLine;
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
	
	private Stream<EventSnapshot> findEventsForTeam(Predicate<Event> predEventType, Team team){
		return events.stream()
				   .filter(predEventType.and(Event.isForTeam(team)))
				   .map(Event::prepareSnapshot);
	}
	
	private TeamResult checkTeamResult(Team team) {
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
	
	private MatchResult computeResult() {
		long homeGoals = findEventsForTeam(Event.isGoal(), homeTeam).count();
		long awayGoals = findEventsForTeam(Event.isGoal(), awayTeam).count();
		
		if(homeGoals == awayGoals)
			return MatchResult.DRAW;
		
		return homeGoals > awayGoals ?  MatchResult.HOME_WIN : MatchResult.AWAY_WIN;
	}

	@Override
	public String toString() {
		String match = homeTeam + " vs " + awayTeam + "  ";
		String eventsToPrint = events.stream().map(Event::toString).collect(Collectors.joining("\n"));
		return match + "\n" + eventsToPrint;
	}
}