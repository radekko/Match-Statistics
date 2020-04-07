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
	
	public int getTotalEventsForChosenPlace(Team team, PlaceOfPlaying place, Predicate<Event> eventType) {
		return (int)getEventsStream(team, place, eventType)
				.count();
	}
	
	public List<EventSnapshot> getEvents(Team team, PlaceOfPlaying place, Predicate<Event> eventType) {
		return getEventsStream(team, place, eventType)
				.collect(Collectors.toList());
	}
	
	private Stream<EventSnapshot> getEventsStream(Team team, PlaceOfPlaying place, Predicate<Event> eventType){
		return matches.stream()
				.filter(place.chosenPlaceFilter(team))
				.flatMap(m -> m.findEventsForTeam(eventType,team));
	}
	
}
