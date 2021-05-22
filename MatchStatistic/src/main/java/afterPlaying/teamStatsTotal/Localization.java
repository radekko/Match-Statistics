package afterPlaying.teamStatsTotal;

import playing.core.model.MatchPlayedInfo;

public enum Localization{
	HOME(MatchPlayedInfo::isTeamAsHomePred),
	AWAY(MatchPlayedInfo::isTeamAsAwayPred), 
	BOTH(MatchPlayedInfo::isTeamAsHomeOrAwayPred),
	VS_HOME(MatchPlayedInfo::isTeamAsHomePred), 
	VS_AWAY(MatchPlayedInfo::isTeamAsAwayPred);
	
	MatchPlayedInfoPredicate pred;
	
	private Localization(MatchPlayedInfoPredicate pred) {
		this.pred = pred;
	}
}