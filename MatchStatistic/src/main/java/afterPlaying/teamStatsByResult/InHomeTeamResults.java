package afterPlaying.teamStatsByResult;

import java.util.List;

import afterPlaying.filters.findMatchesInfoByTeam.HomeMatchesFilter;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class InHomeTeamResults implements MainStats{
	
	@Override
	public int getWins(List<MatchPlayedInfo> matches, Team team) { 
		return (int) new HomeMatchesFilter().getMatchesInChosenLocalization(matches, team).filter(MatchPlayedInfo::isHomeWinner).count();
	}
	
	@Override
	public int getDraws(List<MatchPlayedInfo> matches, Team team) { 
		return (int) new HomeMatchesFilter().getMatchesInChosenLocalization(matches, team).filter(MatchPlayedInfo::isDraw).count();
	}

	@Override
	public int getLooses(List<MatchPlayedInfo> matches, Team team) {
		return (int) new HomeMatchesFilter().getMatchesInChosenLocalization(matches, team).filter(MatchPlayedInfo::isAwayWinner).count();
	}
}
