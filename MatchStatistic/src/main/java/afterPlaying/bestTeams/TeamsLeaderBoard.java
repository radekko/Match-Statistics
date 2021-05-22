package afterPlaying.bestTeams;

import java.util.List;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import afterPlaying.teamStatsTotal.DetailStats;
import beforePlaying.core.model.Team;

public class TeamsLeaderBoard {
	private final DetailStats stats;

	public TeamsLeaderBoard(DetailStats stats) {
		this.stats = stats;
	}
	
	public Multimap<Integer, Team> createTotalLeaderBoard(List<Team> teams){
		Multimap<Integer, Team> leaderBoard = TreeMultimap.create();
		teams.stream().forEach(team -> leaderBoard.put(stats.getTotalStat(team),team));
		return leaderBoard;
	}
}
