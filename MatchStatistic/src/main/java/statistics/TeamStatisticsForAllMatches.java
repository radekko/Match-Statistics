package statistics;

import java.util.List;

import model.MatchPlayedInfo;
import model.Team;

public class TeamStatisticsForAllMatches {
	private List<MatchPlayedInfo> matches;

	public TeamStatisticsForAllMatches(List<MatchPlayedInfo> matches) {
		this.matches = matches;
	}
	
	public int getTotalGoalsWhenPlayInHome(Team team) {
		return matches.stream().filter(p -> p.getHomeTeam() == team).map(MatchPlayedInfo::getHomeGoals).reduce(0, Integer::sum);
	}
	
	public int getTotalGoalsWhenPlayInAway(Team team) {
		return matches.stream().filter(p -> p.getAwayTeam() == team).map(MatchPlayedInfo::getAwayGoals).reduce(0, Integer::sum);
	}
	
	public int getTotalTeamGoals(Team team) {
		return getTotalGoalsWhenPlayInHome(team) + getTotalGoalsWhenPlayInAway(team);
	}
	
	
}
