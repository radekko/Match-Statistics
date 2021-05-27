package afterPlaying.teamStatsByPlayers.core;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import afterPlaying.teamStatsByPlayers.ITeamByPlayerLeaderBoard;
import afterPlaying.teamStatsTotal.IDetailStats;
import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public class TeamByPlayerLeaderBoard implements ITeamByPlayerLeaderBoard {
	private final IDetailStats detailStats;

	public TeamByPlayerLeaderBoard(IDetailStats detailStats) {
		this.detailStats = detailStats;
	}

	@Override
	public Multimap<Integer, Player> createLeaderBoard(List<MatchPlayedInfo> matches, Team team){
		Multimap<Integer, Player> teamStatsGroupedByPlayers = TreeMultimap.create();
		
		Map<Player, Integer> playersByStats =
				detailStats.getEvents(team)
				.collect(Collectors.toMap(
					EventSnapshot::getPlayer,
					es -> 1, 
					Integer::sum,
					TreeMap::new)
				);

		playersByStats.forEach((k, v) -> teamStatsGroupedByPlayers.put(v, k));
		return teamStatsGroupedByPlayers;
	}
}
