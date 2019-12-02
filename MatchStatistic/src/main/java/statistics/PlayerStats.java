package statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import model.MatchPlayedInfo;
import model.Player;
import model.Team;
import model.Event.EventSnapshot;

//TODO: maybe here team stats and filer this by player?
public class PlayerStats {
	private final TeamStats teamStats;

	public PlayerStats(TeamStats teamStats) {
		this.teamStats = teamStats;
	}

	public List<EventSnapshot> getHomeOrAwayEvents(Team team, Player player, PlaceOfPlaying pred, Function<MatchPlayedInfo, List<EventSnapshot>> whichStats) {
		List<EventSnapshot> statsForTeam = teamStats.getHomeOrAwayEvents(team, pred, whichStats);
		
		return statsForTeam.stream()
				.filter(e -> e.getPlayer() == player)
				.collect(Collectors.toList());
		/*return matches.stream()
						   .filter(pred.execute(team))
						   .map(whichStats)
						   .flatMap(List::stream)
						   .filter(e -> e.getPlayer() == player)
							.collect(Collectors.toList());*/
	}
	
	public List<EventSnapshot> getSumOfEvents(Team team, Player player,
			Function<MatchPlayedInfo, List<EventSnapshot>> homeEvents, 
			Function<MatchPlayedInfo, List<EventSnapshot>> awayEvents) {
		List<EventSnapshot> sumOfEvents = teamStats.getSumOfEvents(team, homeEvents, awayEvents);

		return sumOfEvents.stream()
				.filter(e -> e.getPlayer() == player)
				.collect(Collectors.toList()); 
	}
}
