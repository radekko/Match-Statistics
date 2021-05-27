package afterPlaying.bestTeams.core;

import java.util.List;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import afterPlaying.bestTeams.ITeamsLeaderBoard;
import afterPlaying.teamStatsTotal.IDetailStats;
import beforePlaying.core.model.Team;

public class TeamsLeaderBoard implements ITeamsLeaderBoard {
	private final IDetailStats stats;

	public TeamsLeaderBoard(IDetailStats stats) {
		this.stats = stats;
	}
	
	@Override
	public Multimap<Integer, Team> createTotalLeaderBoard(List<Team> teams){
		Multimap<Integer, Team> leaderBoard = TreeMultimap.create();
		teams.stream().forEach(team -> leaderBoard.put(stats.getTotalStat(team),team));
		return leaderBoard;
	}
}
