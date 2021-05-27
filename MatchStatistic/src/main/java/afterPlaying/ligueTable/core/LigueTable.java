package afterPlaying.ligueTable.core;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import afterPlaying.ligueTable.ILigueTable;
import afterPlaying.teamStatsByResult.IMainStats;
import afterPlaying.teamStatsTotal.IDetailStats;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class LigueTable implements ILigueTable {
	private final List<MatchPlayedInfo> matches;
	private final IMainStats mainStats; //total home wins, total draws
	private final IDetailStats teamStats; //goals scored
	private final IDetailStats vsTeamStats; //goals lost
	
	public LigueTable(List<MatchPlayedInfo> matches , IMainStats mainStats, IDetailStats statsGained,
			IDetailStats statsLost) {
		this.matches = matches;
		this.mainStats = mainStats;
		this.teamStats = statsGained;
		this.vsTeamStats = statsLost;
	}

	@Override
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
