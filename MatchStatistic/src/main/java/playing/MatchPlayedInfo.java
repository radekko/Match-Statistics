package playing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import afterPlaying.resultatFilter.TeamResult;
import beforePlaying.FutureMatch;
import beforePlaying.Team;
import playing.Event.EventSnapshot;

public class MatchPlayedInfo {
	private Team homeTeam;
	private Team awayTeam;
	private int ligueLine;
	private int crowd;
	private Result result;
	private List<Event> events = new ArrayList<>();
	
	public MatchPlayedInfo(FutureMatch match, int crowd, EventsInMatch eventsInMatch) {
		this.homeTeam = match.getHomeTeam();
		this.awayTeam = match.getAwayTeam();
		this.ligueLine = match.getLigueLine();
		this.crowd = crowd;
		this.events = eventsInMatch.getEvents();
		this.result = computeResult();
	}
	
	private Result computeResult() {
		long homeGoals = findEventsForTeam(Event.isGoal(),homeTeam).count();
		long awayGoals = findEventsForTeam(Event.isGoal(),awayTeam).count();
		
		if(homeGoals == awayGoals)
			return Result.DRAW;
		
		return homeGoals > awayGoals ?  Result.HOME_WIN : Result.AWAY_WIN;
	}
	
	public TeamResult checkTeamResult(Team team) {
		if(result == Result.DRAW)
			return TeamResult.DRAW;
		
		if(team.equals(homeTeam)) {
			if(result == Result.HOME_WIN)
				return TeamResult.WIN;
			else
				return TeamResult.LOOSE;
		}
		
		if(result == Result.HOME_WIN)
			return TeamResult.LOOSE;
		
		return TeamResult.WIN;
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

	public Stream<EventSnapshot> findEventsForTeam(Predicate<Event> predEventType, Team team){
		return events.stream()
				   .filter(predEventType.and(Event.isForTeam(team)))
				   .map(Event::prepareSnapshot);
	}
	
	public Stream<EventSnapshot> findEventsForOppositeTeam(Predicate<Event> predEventType, Team team){
		return events.stream()
				   .filter(predEventType.and(Event.isForTeam(getOppositeTeam(team))))
				   .map(Event::prepareSnapshot);
	}
	
	private Team getOppositeTeam(Team team) {
		if(team.equals(homeTeam))
			return awayTeam;
		else
			return homeTeam;
	}
	
	@Override
	public String toString() {
		String match = homeTeam + " vs " + awayTeam + "  ";
		String eventsToPrint = events.stream().map(Event::toString).collect(Collectors.joining("\n"));
		return match + "\n" + eventsToPrint;
	}
}