package afterPlaying.teamStatsTotal;

import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.Event.EventSnapshot;

public class TeamDetailStats implements DetailStats{
	
	private final EventTypeFilter eventTypeFinder;
	
	public TeamDetailStats(EventTypeFilter eventTypeFinder) {
		this.eventTypeFinder = eventTypeFinder;
	}

	@Override
	public int getTotalStat(Team team) {
		return (int) getEv(team).count();
	}

	@Override
	public Stream<EventSnapshot> getEvents(Team team) {
		return getEv(team);
	}
	
	private Stream<EventSnapshot> getEv(Team team){
		return eventTypeFinder.getStats(team);
	}
}
