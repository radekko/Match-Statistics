package afterPlaying.bestTeams;

import java.util.List;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import afterPlaying.teamStatsTotal.DetailStats;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class TeamsLeaderBoard {
	private final DetailStats stats;

	public TeamsLeaderBoard(DetailStats stats) {
		this.stats = stats;
	}
	
	public Multimap<Integer, Team> createTotalLeaderBoard(List<Team> teams, List<MatchPlayedInfo> matches){
		Multimap<Integer, Team> leaderBoard = TreeMultimap.create();
		teams.stream().forEach(team -> leaderBoard.put(stats.getTotalStat(matches, team),team));
		return leaderBoard;
	}
}
