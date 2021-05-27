package afterPlaying.teamStatsByResult.infrastructure;

import afterPlaying.teamStatsByResult.IMainStats;
import afterPlaying.teamStatsByResult.core.AwayTeamResults;
import afterPlaying.teamStatsByResult.core.HomeAndAwayTeamResults;
import afterPlaying.teamStatsByResult.core.InHomeTeamResults;

public class MainStatsFactory {

	public enum MainStatsType{
		IN_HOME_TEAM_RESULTS, AWAY_TEAM_RESULTS, HOME_AND_AWAY_TEAM_RESULTS;
	}
	
	public static IMainStats getMainStatsFactory(MainStatsType type) {
		switch(type) {
		case IN_HOME_TEAM_RESULTS:
			return new InHomeTeamResults();
		case AWAY_TEAM_RESULTS:
			return new AwayTeamResults();
		case HOME_AND_AWAY_TEAM_RESULTS:
			return new HomeAndAwayTeamResults();
		}
		return null;
	}
}
