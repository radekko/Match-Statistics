package afterPlaying.findMatchesInfoByLocation;

import java.util.List;
import java.util.stream.Stream;

import beforePlaying.Team;
import playing.MatchPlayedInfo;

public  class HomeOrAwayMatchesFinder implements EventByLocationFinder{
	private List<MatchPlayedInfo> matches;

	public HomeOrAwayMatchesFinder(List<MatchPlayedInfo> matches) {
		this.matches = matches;
	}
	
	public Stream<MatchPlayedInfo> getMatchPlayedInfo(Team team) {
		return matches.stream().filter(m -> m.isHomeOrAway(team));
	}

}
