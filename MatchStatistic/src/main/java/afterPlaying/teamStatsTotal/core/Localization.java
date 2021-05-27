package afterPlaying.teamStatsTotal.core;

import playing.core.model.MatchPlayedInfo;

public enum Localization{
	HOME(MatchPlayedInfo::isTeamAsHomePred),
	AWAY(MatchPlayedInfo::isTeamAsAwayPred), 
	BOTH(MatchPlayedInfo::isTeamAsHomeOrAwayPred),
	VS_HOME(MatchPlayedInfo::isTeamAsHomePred), 
	VS_AWAY(MatchPlayedInfo::isTeamAsAwayPred);
	
	public MatchPlayedInfoLocalizationPredicate pred;
	
	private Localization(MatchPlayedInfoLocalizationPredicate pred) {
		this.pred = pred;
	}
}