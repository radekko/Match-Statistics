package afterPlaying.teamStatsTotal.core;

import java.util.function.Predicate;

import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

@FunctionalInterface
interface MatchPlayedInfoLocalizationPredicate {
	 Predicate<MatchPlayedInfo> localization(Team team);
}
