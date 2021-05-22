package afterPlaying.teamStatsByResult;

import java.util.List;

import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

class HomeAndAwayTeamResults implements MainStats{
	private InHomeTeamResults teamHomeResults;
	private AwayTeamResults teamAwayResults;
			
	public HomeAndAwayTeamResults(){
		teamHomeResults = new InHomeTeamResults();
		teamAwayResults = new AwayTeamResults();
	}
	
	@Override
	public int getWins(List<MatchPlayedInfo> matches, Team team) { 
		return teamHomeResults.getWins(matches, team) + teamAwayResults.getWins(matches, team);
	}

	@Override
	public int getDraws(List<MatchPlayedInfo> matches, Team team) {
		return teamHomeResults.getDraws(matches, team) + teamAwayResults.getDraws(matches, team);
	}

	@Override
	public int getLooses(List<MatchPlayedInfo> matches, Team team) {
		return teamHomeResults.getLooses(matches, team) + teamAwayResults.getLooses(matches, team);
	}
}
