package afterPlaying;

import java.util.function.Predicate;

import beforePlaying.Team;
import playing.MatchPlayedInfo;

@FunctionalInterface
public interface PlaceOfPlayingMatchPredicate {
    Predicate<MatchPlayedInfo> isPlace(Team team);
}
