package afterPlaying.teamStatsByResult.core;

import java.util.List;

import afterPlaying.teamStatsByResult.IMainStats;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class HomeAndAwayTeamResults implements IMainStats{
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
