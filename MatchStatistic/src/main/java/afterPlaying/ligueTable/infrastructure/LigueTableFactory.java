package afterPlaying.ligueTable.infrastructure;

import java.util.List;

import afterPlaying.ligueTable.ILigueTable;
import afterPlaying.ligueTable.core.LigueTable;
import afterPlaying.teamStatsByResult.infrastructure.MainStatsFactory;
import afterPlaying.teamStatsByResult.infrastructure.MainStatsFactory.MainStatsType;
import afterPlaying.teamStatsTotal.core.EventType;
import afterPlaying.teamStatsTotal.core.Localization;
import afterPlaying.teamStatsTotal.infrastructure.TeamDetailStatsFactory;
import playing.core.model.MatchPlayedInfo;

public class LigueTableFactory {
	public enum LigueTableType{
		TOTAL, ONLY_HOME_MATCHES, ONLY_AWAY_MATCHES
	}
	
	public static ILigueTable getInstance(LigueTableType type, List<MatchPlayedInfo> matches) {
		switch(type) {
		case TOTAL:
			return create(matches,
					MainStatsType.HOME_AND_AWAY_TEAM_RESULTS,
					Localization.BOTH);
		case ONLY_HOME_MATCHES:
			return create(matches,
					MainStatsType.IN_HOME_TEAM_RESULTS,
					Localization.HOME);	
		case ONLY_AWAY_MATCHES:
			return create(matches,
					MainStatsType.AWAY_TEAM_RESULTS,
					Localization.AWAY);	
		}
		return null;
	}
	
	private static ILigueTable create(List<MatchPlayedInfo> matches, MainStatsType mainStatsType, Localization loc) {
		return new LigueTable(matches, 
							  MainStatsFactory.getMainStatsFactory(mainStatsType),
							  TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, loc, matches),
							  TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED.getOpposite(), loc, matches));
	}
}
