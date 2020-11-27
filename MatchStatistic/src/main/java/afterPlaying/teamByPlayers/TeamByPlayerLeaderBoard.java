package afterPlaying.teamByPlayers;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import afterPlaying.findEventsByTeam.EventByTypeFinder;
import beforePlaying.Player;
import beforePlaying.Team;
import playing.Event.EventSnapshot;

public class TeamByPlayerLeaderBoard {
	private final EventByTypeFinder eventByTypeFinder;

	public TeamByPlayerLeaderBoard(EventByTypeFinder eventByTypeFinder) {
		this.eventByTypeFinder = eventByTypeFinder;
	}

	public Multimap<Integer, Player> createHomeLeaderBoard(Team examineTeam) {
		return createLeaderBoard(eventByTypeFinder.getHomeStat(examineTeam));
	}
	
	public Multimap<Integer, Player> createAwayLeaderBoard(Team examineTeam) {
		return createLeaderBoard(eventByTypeFinder.getAwayStat(examineTeam));
	}
	
	public Multimap<Integer, Player> createTotalLeaderBoard(Team examineTeam) {
		return createLeaderBoard(eventByTypeFinder.getHomeAndAwayStat(examineTeam));
	}
	
	public String statDescription() {
		return eventByTypeFinder.statDescription();
	}

	private Multimap<Integer, Player> createLeaderBoard(List<EventSnapshot> chosenEvents) {
		Multimap<Integer, Player> goalWithPlayers = TreeMultimap.create();

		Map<Player, Integer> playersWithNumberOfGoals = chosenEvents.stream().collect(Collectors.toMap(
				EventSnapshot::getPlayer,
				es -> 1, 
				Integer::sum,
				TreeMap::new)
				);

		playersWithNumberOfGoals.forEach((k, v) -> goalWithPlayers.put(v, k));
		return goalWithPlayers;
	}
}
