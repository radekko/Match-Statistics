package afterPlaying;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import beforePlaying.Team;
import playing.Event;
import playing.Event.EventSnapshot;
import playing.MatchPlayedInfo;

public class TeamStats{
	private final List<MatchPlayedInfo> matches;

	public TeamStats(HistoryMatchesRepo historyMatchesRepo) {
		this.matches = historyMatchesRepo.getAllHistory();
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
	
}
