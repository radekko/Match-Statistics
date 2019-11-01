package statistics;

import java.util.List;

import model.MatchPlayedInfo;
import model.Player;
import model.Team;

public class StatisticsPrinter {
	private List<MatchPlayedInfo> matches;
	private TeamStatisticsForAllMatches teamStatistics;
	private PlayerStatisticsForAllMatches playerStatisticsForAllMatches;

	public StatisticsPrinter(List<MatchPlayedInfo> matches) {
		this.matches = matches;
		this.teamStatistics = new TeamStatisticsForAllMatches(matches);
		this.playerStatisticsForAllMatches = new PlayerStatisticsForAllMatches(matches);
	}
	
	public void printTeamStatistics(Team team) {
		System.out.println(
		"Home goals: " + teamStatistics.getTotalGoalsWhenPlayInHome(team) +
		"Away goals: " + teamStatistics.getTotalGoalsWhenPlayInAway(team) +
		"All goals: " + teamStatistics.getTotalTeamGoals(team)
				);
	}
	public void printPlayerStatistics(Player player) {
		System.out.println(
		"Total goals by player id=" + player.getId() + " " + playerStatisticsForAllMatches.getTotalPlayerGoals(player)
		);
	}
	public void printLigueStatistics() {}
	
	//get total goals for player
	//get total goals for home teams
	//statistics for player, team, match
	

}
