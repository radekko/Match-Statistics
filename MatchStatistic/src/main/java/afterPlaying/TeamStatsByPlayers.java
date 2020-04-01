package afterPlaying;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import beforePlaying.Player;
import beforePlaying.Team;
import playing.Event.EventSnapshot;

public class TeamStatsByPlayers{
	private final TeamStats teamStats;

	public TeamStatsByPlayers(TeamStats teamStats) {
		this.teamStats = teamStats;
	}
	
	public Multimap<Integer, Player> getPlayerHomeGoals(Team team) {
		return prepareBoard(teamStats.getGoalsInHome(team));
	}
	
	public Multimap<Integer, Player> getPlayerAwayGoals(Team team) {
		return prepareBoard(teamStats.getGoalsAway(team));
	}
	
	public Multimap<Integer, Player> getPlayerTotalGoals(Team team) {
		return prepareBoard(teamStats.getGoals(team));
	}
	
	private Multimap<Integer, Player> prepareBoard(List<EventSnapshot> events){
		Multimap<Integer, Player> goalWithPlayers = TreeMultimap.create();
		
		Map<Player, Integer> playersWithNumberOfGoals = events.stream().collect(Collectors.toMap(
				EventSnapshot::getPlayer,
				es -> 1, 
				Integer::sum,
				TreeMap::new)
				);
		
		playersWithNumberOfGoals.forEach((k,v) -> goalWithPlayers.put(v, k));
		return goalWithPlayers;
	}
}