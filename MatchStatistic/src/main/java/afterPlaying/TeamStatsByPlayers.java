package afterPlaying;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import beforePlaying.Player;
import beforePlaying.Team;
import playing.Event;
import playing.PlaceOfPlaying;
import playing.Event.EventSnapshot;

public class TeamStatsByPlayers{
	private final TeamStats teamStats;

	public TeamStatsByPlayers(TeamStats teamStats) {
		this.teamStats = teamStats;
	}
	
	public Multimap<Integer, Player> getPlayersStatisticsInChosenTeam(Team team, PlaceOfPlaying place, Predicate<Event> eventType){
		return prepareLeaderboardoard(teamStats.getEvents(team, place, eventType));
	}
	
	private Multimap<Integer, Player> prepareLeaderboardoard(List<EventSnapshot> events){
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