package afterPlaying.teamTotal;

import beforePlaying.Team;

public class TeamTotalStatisticsPrinterNew {
	private final TotalTeamStats totalteamStats;
	private final String statDescription;

	public TeamTotalStatisticsPrinterNew(TotalTeamStats totalteamStats) {
		this.totalteamStats = totalteamStats;
		this.statDescription = totalteamStats.statsDescription();
	}
	
	public void printAllTeamStatistics(Team team) {
    	System.out.println("Total " + statDescription +  " statistics for " + team);
    	System.out.println("-------------------------------------------------");
		printTotalStatistics(team);
		System.out.println();
	}

	private void printTotalStatistics(Team team) {
		System.out.println("Home " + statDescription);
		System.out.println(totalteamStats.getHomeStat(team));
		System.out.println("Away " + statDescription);
		System.out.println(totalteamStats.getAwayStat(team));
		System.out.println("Total " + statDescription);
		System.out.println(totalteamStats.getHomeAndAwayStat(team));
	}
}
