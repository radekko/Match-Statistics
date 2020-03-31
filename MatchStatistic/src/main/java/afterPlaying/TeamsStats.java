package afterPlaying;

import java.util.List;
import java.util.function.Function;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import beforePlaying.Team;
import beforePlaying.Teams;

public class TeamsStats {
	private final TeamStats teamStats;
	private final List<Team> allTeams;

	public TeamsStats(Teams teams, TeamStats teamStats) {
		this.teamStats = teamStats;
		this.allTeams = teams.getAll();
	}

	public Multimap<Integer, Team> groupByBestGoalScorersInHome() {
		return prepareLeaderBoard(teamStats::getTotalHomeGoals);
	}
	
	public Multimap<Integer, Team> groupByBestGoalScorersAway() {
		return prepareLeaderBoard(teamStats::getTotalAwayGoals);
	}
	
	public Multimap<Integer, Team> groupByBestGoalScorersTotal() {
		return prepareLeaderBoard(teamStats::getTotalGoals);
	}
	
	private Multimap<Integer, Team> prepareLeaderBoard(Function<Team, Integer> teamsStatsFunction) {
		Multimap<Integer, Team> leaderBoard = TreeMultimap.create();
		allTeams.stream().forEach(team -> leaderBoard.put(teamsStatsFunction.apply(team), team));
		return leaderBoard;
	}
	
}
