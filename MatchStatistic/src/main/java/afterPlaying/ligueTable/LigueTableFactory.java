package afterPlaying.ligueTable;

import java.util.List;

import afterPlaying.teamStatsByResult.AwayTeamResults;
import afterPlaying.teamStatsByResult.HomeAndAwayTeamResults;
import afterPlaying.teamStatsByResult.InHomeTeamResults;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory.EventType;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory.Localization;
import playing.core.model.MatchPlayedInfo;

public class LigueTableFactory {
	public enum LigueTableType{
		TOTAL, ONLY_HOME_MATCHES, ONLY_AWAY_MATCHES
	}
	
	public static LigueTable getInstance(LigueTableType t, List<MatchPlayedInfo> matches) {
		switch(t) {
		case TOTAL:
			return new LigueTable(matches,
					new HomeAndAwayTeamResults(),
					TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.BOTH),
					TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.VS_BOTH));
		case ONLY_HOME_MATCHES:
			return new LigueTable(matches,
					new InHomeTeamResults(),
					TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.HOME),
					TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.VS_HOME));
		case ONLY_AWAY_MATCHES:
			return new LigueTable(matches,
					new AwayTeamResults(),
					TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.AWAY),
					TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.VS_AWAY));
		}
		return null;
	}
}
