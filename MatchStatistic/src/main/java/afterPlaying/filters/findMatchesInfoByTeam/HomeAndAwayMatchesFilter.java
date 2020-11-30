package afterPlaying.filters.findMatchesInfoByTeam;

import java.util.List;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class HomeAndAwayMatchesFilter implements MatchesFilter {//nie potrzebne?
	@Override
	public Stream<MatchPlayedInfo> getMatchesInChosenLocalization(List<MatchPlayedInfo> matches, Team team) {
		HomeMatchesFilter homeMatchesFilter = new HomeMatchesFilter();
		AwayMatchesFilter awayMatchesFilter = new AwayMatchesFilter();
		Stream<MatchPlayedInfo> s = homeMatchesFilter.getMatchesInChosenLocalization(matches, team);
		Stream<MatchPlayedInfo> s2 = awayMatchesFilter.getMatchesInChosenLocalization(matches, team);
		return Stream.concat(s, s2);
	}
}
