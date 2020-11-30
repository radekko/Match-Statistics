package afterPlaying.teamStatsTotal;

import java.util.List;
import java.util.stream.Stream;

import afterPlaying.filters.findEventsByTeam.EventTypeFilter;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public class TeamDetailStats implements DetailStats{
	
	private final EventTypeFilter eventTypeFinder;
	
	public TeamDetailStats(EventTypeFilter eventTypeFinder) {
		this.eventTypeFinder = eventTypeFinder;
	}

	@Override
	public int getTotalStat(List<MatchPlayedInfo> matches, Team team) {
		return (int) eventTypeFinder.getStats(matches, team).count();
	}

	@Override
	public Stream<EventSnapshot> getEvents(List<MatchPlayedInfo> matches, Team team) {
		return eventTypeFinder.getStats(matches, team);
	}
}
