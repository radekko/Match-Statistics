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
		"Statistics for team: " + team + "\n" +
		"Home goals: " + teamStatistics.getTotalGoalsWhenPlayInHome(team) + "\n" +
		"Away goals: " + teamStatistics.getTotalGoalsWhenPlayInAway(team) + "\n" +
		"All goals: " + teamStatistics.getTotalTeamGoals(team) + "\n" +
		"Yellow cards in home: " + teamStatistics.getTotalYellowCardsInHome(team) + "\n" +
		"Yellow cards away: " + teamStatistics.getTotalYellowCardsAway(team) + "\n" +
		"Yellow cards total: " + teamStatistics.getTotalYellowCards(team) + "\n"
		
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
