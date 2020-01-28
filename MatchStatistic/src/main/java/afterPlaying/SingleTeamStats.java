package afterPlaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import beforePlaying.Player;
import beforePlaying.Team;
import playing.Event;
import playing.MatchPlayedInfo;
import playing.Event.EventSnapshot;

public class SingleTeamStats{
	private final List<MatchPlayedInfo> matches;

	//next 2 classes: set of statistics for allTeams and for all players
	
	public SingleTeamStats(List<MatchPlayedInfo> matches) {
		this.matches = matches;
	}
	
	public int getTotalHomeGoals(Team team) {
		return getTotalEvents(team, PlaceOfPlaying.HOME, Event.isGoal());
	}
	
	public int getTotalAwayGoals(Team team) {
		return getTotalEvents(team, PlaceOfPlaying.AWAY, Event.isGoal());
	}
	
	public int getTotalGoals(Team team) {
		return getTotalHomeGoals(team) + getTotalAwayGoals(team);
	}
	
	public List<EventSnapshot> getGoalsInHome(Team team){
		return getEvents(team, PlaceOfPlaying.HOME, Event.isGoal());
	}
	
	public List<EventSnapshot> getGoalsAway(Team team){
		return getEvents(team, PlaceOfPlaying.AWAY, Event.isGoal());
	}
	
	public List<EventSnapshot> getGoals(Team team){
		return Stream.of(
					getEvents(team, PlaceOfPlaying.HOME, Event.isGoal()),
					getEvents(team, PlaceOfPlaying.AWAY, Event.isGoal())
				)
				.flatMap(x -> x.stream())
				.collect(Collectors.toList());
	}
	
	private int getTotalEvents(Team team, PlaceOfPlaying place, Predicate<Event> eventType) {
		return (int)getEventsStream(team, place, eventType)
				.count();
	}
	
	private List<EventSnapshot> getEvents(Team team, PlaceOfPlaying place, Predicate<Event> eventType) {
		return getEventsStream(team, place, eventType)
				.collect(Collectors.toList());
	}
	
	private Stream<EventSnapshot> getEventsStream(Team team, PlaceOfPlaying place, Predicate<Event> eventType){
		return matches.stream()
				.filter(place.chosenPlaceFilter(team))
				.flatMap(m -> m.findEventsForTeam(eventType,team));
	}
	
	/*public List<EventSnapshot> getHomeOrAwayEvents(Team team, PlaceOfPlaying pred, Function<MatchPlayedInfo, List<EventSnapshot>> whichStats) {
		return matches.stream()
				.filter(pred.chosenPlaceFilter(team))
				.map(whichStats)
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}
	
	public List<EventSnapshot> getSumOfEvents(Team team, 
			Function<MatchPlayedInfo, List<EventSnapshot>> homeEvents, 
			Function<MatchPlayedInfo, List<EventSnapshot>> awayEvents) {
		List<EventSnapshot> result = new ArrayList<>();
		result.addAll(getHomeOrAwayEvents(team, PlaceOfPlaying.HOME, homeEvents));
		result.addAll(getHomeOrAwayEvents(team, PlaceOfPlaying.AWAY, awayEvents));
		return result;
	}*/
	
}
