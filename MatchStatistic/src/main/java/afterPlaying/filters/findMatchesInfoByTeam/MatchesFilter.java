package afterPlaying.filters.findMatchesInfoByTeam;

import java.util.List;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public interface MatchesFilter {
	Stream<MatchPlayedInfo> getMatchesInChosenLocalization(List<MatchPlayedInfo> matches, Team team);
}