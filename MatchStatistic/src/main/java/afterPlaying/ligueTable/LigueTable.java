package afterPlaying.ligueTable;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import afterPlaying.teamStatsByResult.MainStats;
import afterPlaying.teamStatsTotal.DetailStats;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class LigueTable {
	private final List<MatchPlayedInfo> matches;
	private final MainStats mainStats; //total home wins, total draws
	private final DetailStats teamStats; //goals scored
	private final DetailStats vsTeamStats; //goals lost
	
	public LigueTable(List<MatchPlayedInfo> matches , MainStats mainStats, DetailStats statsGained,
			DetailStats statsLost) {
		this.matches = matches;
		this.mainStats = mainStats;
		this.teamStats = statsGained;
		this.vsTeamStats = statsLost;
	}

	public List<TeamRecord> prepareTable(List<Team> whcichTeams) {
		return whcichTeams.stream().map(this::prepare).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	}
	
	private TeamRecord prepare(Team team){
		int wins = mainStats.getWins(matches, team);
		int draws = mainStats.getDraws(matches, team);
		int homeGoals = teamStats.getTotalStat(team);
		int awayGoals = vsTeamStats.getTotalStat(team);
		return new TeamRecord(team, wins, draws, homeGoals, awayGoals);
	}
}
