package afterPlaying.teamStatsTotal;

import java.util.function.Predicate;

import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

@FunctionalInterface
interface MatchPlayedInfoPredicate {
	 Predicate<MatchPlayedInfo> localization(Team team);
}
