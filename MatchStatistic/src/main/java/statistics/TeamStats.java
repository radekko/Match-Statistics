package statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import model.Event.EventSnapshot;
import model.MatchPlayedInfo;
import model.Team;

public class TeamStats{
	private final List<MatchPlayedInfo> matches;

	public TeamStats(List<MatchPlayedInfo> matches) {
		this.matches = matches;
	}
	
	public List<EventSnapshot> getHomeOrAwayEvents(Team team, PlaceOfPlaying pred, Function<MatchPlayedInfo, List<EventSnapshot>> whichStats) {
		return matches.stream()
				.filter(pred.execute(team))
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
	}
	
}
