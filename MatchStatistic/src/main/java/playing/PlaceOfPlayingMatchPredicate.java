package playing;

import java.util.function.Predicate;

import beforePlaying.Team;

@FunctionalInterface
public interface PlaceOfPlayingMatchPredicate {
    Predicate<MatchPlayedInfo> isPlace(Team team);
}
