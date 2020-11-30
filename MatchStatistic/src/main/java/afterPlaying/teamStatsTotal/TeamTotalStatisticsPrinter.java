package afterPlaying.teamStatsTotal;

import java.util.List;

import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class TeamTotalStatisticsPrinter {
	private final DetailStats teamStats;
	private final String statDescription;
	private final List<MatchPlayedInfo> matches;

	public TeamTotalStatisticsPrinter(DetailStats teamStats, List<MatchPlayedInfo> matches, String statDescription) {
		this.teamStats = teamStats;
		this.matches = matches;
		this.statDescription = statDescription;
	}

	public void print(Team team) {
    	System.out.println("Total " + statDescription +  " for " + team);
    	System.out.println("-------------------------------------------------");
    	System.out.println(teamStats.getTotalStat(matches, team));
		System.out.println();
	}
}
