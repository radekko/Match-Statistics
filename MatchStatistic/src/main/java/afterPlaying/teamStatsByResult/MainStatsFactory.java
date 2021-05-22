package afterPlaying.teamStatsByResult;

public class MainStatsFactory {

	public enum MainStatsType{
		IN_HOME_TEAM_RESULTS, AWAY_TEAM_RESULTS, HOME_AND_AWAY_TEAM_RESULTS;
	}
	
	public static MainStats getMainStatsFactory(MainStatsType type) {
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
