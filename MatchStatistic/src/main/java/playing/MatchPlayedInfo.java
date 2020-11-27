package playing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import beforePlaying.FutureMatch;
import beforePlaying.Team;
import playing.Event.EventSnapshot;

public class MatchPlayedInfo {
	private Team homeTeam;
	private Team awayTeam;
	private int ligueLine;
	private int crowd;
	private List<Event> events = new ArrayList<>();
	
	public MatchPlayedInfo(FutureMatch match, int crowd, EventsInMatch eventsInMatch) {
		this.homeTeam = match.getHomeTeam();
		this.awayTeam = match.getAwayTeam();
		this.ligueLine = match.getLigueLine();
		this.crowd = crowd;
		this.events = eventsInMatch.getEvents();
	}
	
	public boolean isHost(Team team){
		return team.equals(homeTeam) ? true : false; 
	}
	
	public boolean isAway(Team team){
		return team.equals(awayTeam) ? true : false; 
	}
	
	public boolean isHomeOrAway(Team team){
		return
				team.equals(homeTeam) ? true : false ||
				team.equals(awayTeam) ? true : false; 
	}
	
	public Stream<Team> getTeams(){
		return Stream.of(homeTeam, awayTeam);
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
	
	public Stream<Event> getHomeEvents(){
		return events.stream()
				   .filter(Event.isForTeam(homeTeam));
	}
	
	public Stream<Event> getAwayEvents(){
		return events.stream()
				   .filter(Event.isForTeam(awayTeam));
	}
	
	@Override
	public String toString() {
		String match = homeTeam + " vs " + awayTeam + "  ";
		String eventsToPrint = events.stream().map(Event::toString).collect(Collectors.joining("\n"));
		return match + "\n" + eventsToPrint;
	}
}