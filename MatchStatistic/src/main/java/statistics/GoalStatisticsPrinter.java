package statistics;

import java.util.List;

import model.MatchPlayedInfo;
import model.Player;
import model.Team;

public class GoalStatisticsPrinter {
	private TeamStatisticsForAllMatches teamStatistics;
	private PlayerStatisticsForAllMatches playerStatisticsForAllMatches;

	public GoalStatisticsPrinter(List<MatchPlayedInfo> matches) {
		this.teamStatistics = new TeamStatisticsForAllMatches(matches);
		this.playerStatisticsForAllMatches = new PlayerStatisticsForAllMatches(matches);
	}
	
	public void printTeamStatistics(Team team) {
		System.out.println(
		"Statistics for team: " + team + "\n" +
		"Home goals: " + teamStatistics.getTotalGoalsWhenPlayInHome(team) + "\n" +
		"Away goals: " + teamStatistics.getTotalGoalsWhenPlayInAway(team) + "\n" +
		"All goals: " + teamStatistics.getTotalTeamGoals(team) + "\n" /*+
		"Yellow cards in home: " + teamStatistics.getTotalYellowCardsInHome(team) + "\n" +
		"Yellow cards away: " + teamStatistics.getTotalYellowCardsAway(team) + "\n" +
		"Yellow cards total: " + teamStatistics.getTotalYellowCards(team) + "\n" */
		
				);
	}
	
	public void printAllTeamsStatistics(List<Team> teams) {
		teams.stream().forEach(this::printTeamStatistics);
	}
	
	public void printGoalsForPlayer(Team team, Player player) {
		System.out.println("player id: " + player.getId());
		System.out.println("--------------------------------------------");
		
		System.out.print("Home: ");
		int goalsInHome = playerStatisticsForAllMatches.getTotalGoalsForPlayerWhenPlayInHome(team, player);
		System.out.print(goalsInHome);
		
		System.out.print(", Away: ");
		int goalsAway = playerStatisticsForAllMatches.getTotalGoalsForPlayerWhenPlayAway(team, player);
		System.out.print(goalsAway);

		System.out.print(", Total: ");
		System.out.println(goalsInHome + goalsAway);
		
		System.out.println();
	}
	
	public void printLigueStatistics() {}
	
	//get total goals for player
	//get total goals for home teams
	//statistics for player, team, match
	

}
