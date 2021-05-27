package playing.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.Event.EventSnapshot;

public class Events {
	private List<Event> events = new ArrayList<>();
	private Supplier<Stream<Event>> streamSupplier;

	public Events(EventsInMatch eventsInMatch) {
		this.events = eventsInMatch.getEvents();
		this.streamSupplier = () -> events.stream();
	}

	private Stream<Event> getStream() {
		return streamSupplier.get();
	}

	public Stream<Event> getEventsFromMatch(Team team){
		return getStream().filter(Event.isForTeam(team));
	}
	
	public Stream<Event> getEventsFromMatchForTheOpponent(Team team){
		return getStream().filter(Event.isForTeam(team).negate());
	}
	
	public Stream<EventSnapshot> findEventsForTeam(Predicate<Event> predEventType, Team team){
		return getStream()
				   .filter(predEventType.and(Event.isForTeam(team)))
				   .map(Event::prepareSnapshot);
	}
	
	@Override
	public String toString() {
		return getStream().map(Event::toString).collect(Collectors.joining("\n"));
	}

}
