package afterPlaying.bestTeams;

import java.util.List;
import java.util.function.Function;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import afterPlaying.teamTotal.TotalTeamStats;
import beforePlaying.Team;

public class TeamsLeaderBoard {
	private final TotalTeamStats totalTeamStats;
	private final List<Team> teams;

	public TeamsLeaderBoard(TotalTeamStats totalTeamStats, List<Team> teams) {
		this.totalTeamStats = totalTeamStats;
		this.teams = teams;
	}
	
	public Multimap<Integer, Team> createTotalLeaderBoard(){
		return createLeaderBoard(totalTeamStats :: getHomeAndAwayStat);
	}
	
	public Multimap<Integer, Team> createHomeLeaderBoard(){
		return createLeaderBoard(totalTeamStats :: getHomeStat);
	}
	
	public Multimap<Integer, Team> createAwayLeaderBoard(){
		return createLeaderBoard(totalTeamStats :: getAwayStat);
	}

	private Multimap<Integer, Team> createLeaderBoard(Function<Team, Integer> findTotal) {
		Multimap<Integer, Team> leaderBoard = TreeMultimap.create();
		teams.stream().forEach(team -> leaderBoard.put(findTotal.apply(team), team));
		return leaderBoard;
	}
	
	public String statsDescription() {
		return totalTeamStats.statsDescription();
	}
}
