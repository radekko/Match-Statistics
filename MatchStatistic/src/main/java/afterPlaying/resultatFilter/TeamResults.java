package afterPlaying.resultatFilter;

import java.util.function.Predicate;

import afterPlaying.findMatchesInfoByLocation.EventByLocationFinder;
import beforePlaying.Team;

public class TeamResults {
	private final EventByLocationFinder eventByLocationFinder;

	public TeamResults(EventByLocationFinder eventByLocationFinder) {
		this.eventByLocationFinder = eventByLocationFinder;
	}

	public int calculateTotalWins(Team team){
		return create(team, TeamResult::isWin);
	}
	
	public int calculateTotalDraws(Team team){
		return create(team, TeamResult::isDraw);
	}
	
	public int calculateTotalLooses(Team team){
		return create(team, TeamResult::isLoose);
	}
	
	private int create(Team team, Predicate<TeamResult> resultFiltr) {
		return (int) eventByLocationFinder.getMatchPlayedInfo(team)
				.map(m -> m.checkTeamResult(team))
				.filter(resultFiltr)
				.count();
	}
}
