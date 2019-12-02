package statistics;

import java.util.function.Predicate;

import model.MatchPlayedInfo;
import model.Team;

@FunctionalInterface
public interface PlaceOfPlayingMatchPredicate {
    Predicate<MatchPlayedInfo> isHome(Team team);
}
