package afterPlaying.filters.findMatchesInfoByTeam;

import java.util.List;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class HomeMatchesFilter implements MatchesFilter {
	@Override
	public Stream<MatchPlayedInfo> getMatchesInChosenLocalization(List<MatchPlayedInfo> matches, Team team) {
		return matches.stream().filter(m -> m.isHost(team));
	}
}
