package afterPlaying.findMatchesInfoByLocation;

import java.util.List;
import java.util.stream.Stream;

import beforePlaying.Team;
import playing.MatchPlayedInfo;

public class AwayMatchesFinder implements EventByLocationFinder{
	private List<MatchPlayedInfo> matches;
	
	public AwayMatchesFinder(List<MatchPlayedInfo> matches) {
		this.matches = matches;
	}

	@Override
	public Stream<MatchPlayedInfo> getMatchPlayedInfo(Team team) {
		return matches.stream().filter(m -> m.isAway(team));
	}
}
