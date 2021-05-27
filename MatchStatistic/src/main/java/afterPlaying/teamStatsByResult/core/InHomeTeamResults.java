package afterPlaying.teamStatsByResult.core;

import java.util.List;
import java.util.function.Predicate;

import afterPlaying.teamStatsByResult.IMainStats;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class InHomeTeamResults implements IMainStats{
	
	@Override
	public int getWins(List<MatchPlayedInfo> matches, Team team) { 
		return getNumberOfResults(matches, team, MatchPlayedInfo::isHomeWinner);
	}
	
	@Override
	public int getDraws(List<MatchPlayedInfo> matches, Team team) {
		return getNumberOfResults(matches, team, MatchPlayedInfo::isDraw);
	}

	@Override
	public int getLooses(List<MatchPlayedInfo> matches, Team team) {
		return getNumberOfResults(matches, team, MatchPlayedInfo::isAwayWinner);
	}
	
	private int getNumberOfResults(List<MatchPlayedInfo> matches, Team team, Predicate<MatchPlayedInfo> statChecker) {
		return (int) matches.stream().filter(MatchPlayedInfo.isTeamAsHomePred(team)).filter(statChecker).count();
	}
}
