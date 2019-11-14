package statistics;

import java.util.List;
import java.util.stream.Collectors;

import model.Event;
import model.Event.EventSnapshot;
import model.MatchPlayedInfo;
import model.Team;

public class TeamStatisticsForAllMatches {
	private List<MatchPlayedInfo> matches;

	public TeamStatisticsForAllMatches(List<MatchPlayedInfo> matches) {
		this.matches = matches;
	}

	public int getTotalGoalsWhenPlayInHome(Team team) {
		return matches.stream()
				.filter(m -> m.isHost(team))
				.map(MatchPlayedInfo::getHostGoals)
				.map(List::size)
				.reduce(0, Integer::sum);
	}
	
	public int getTotalGoalsWhenPlayInAway(Team team) {
		return matches.stream()
				.filter(m -> m.isAway(team))
				.map(MatchPlayedInfo::getAwayGoals)
				.map(List::size)
				.reduce(0, Integer::sum);
	}
	
	public int getTotalTeamGoals(Team team) {
		return getTotalGoalsWhenPlayInHome(team) + getTotalGoalsWhenPlayInAway(team);
	}
	
	/*public int getTotalYellowCardsInHome(Team team) {
		return matches.stream().filter(p -> p.getHomeTeam() == team).map(MatchPlayedInfo::getHomeYellowCards).reduce(0, Integer::sum);
	}
	
	public int getTotalYellowCardsAway(Team team) {
		return matches.stream().filter(p -> p.getAwayTeam() == team).map(MatchPlayedInfo::getAwayYellowCards).reduce(0, Integer::sum);
	}

	public int getTotalYellowCards(Team team) {
		return getTotalYellowCardsInHome(team) + getTotalYellowCardsAway(team);
	}*/
	
	
	
/*	public int getTotalRedCardsInHome(Team team) {
		return matches.stream().filter(p -> p.getHomeTeam() == team).map(MatchPlayedInfo::getHomeRedCards).reduce(0, Integer::sum);
	}
	
	public int getTotalRedCardsAway(Team team) {
		return matches.stream().filter(p -> p.getAwayTeam() == team).map(MatchPlayedInfo::getAwayRedCards).reduce(0, Integer::sum);
	}

	public int getTotalRedCards(Team team) {
		return getTotalRedCardsInHome(team) + getTotalRedCardsAway(team);
	}*/
	
}
