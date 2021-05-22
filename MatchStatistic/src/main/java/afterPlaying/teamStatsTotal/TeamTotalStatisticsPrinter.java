package afterPlaying.teamStatsTotal;

import beforePlaying.core.model.Team;

public class TeamTotalStatisticsPrinter {
	private final DetailStats teamStats;
	private final String statDescription;

	public TeamTotalStatisticsPrinter(DetailStats teamStats, String statDescription) {
		this.teamStats = teamStats;
		this.statDescription = statDescription;
	}

	public void print(Team team) {
    	System.out.println("Total " + statDescription +  " for " + team);
    	System.out.println("-------------------------------------------------");
    	System.out.println(teamStats.getTotalStat(team));
		System.out.println();
	}
}
