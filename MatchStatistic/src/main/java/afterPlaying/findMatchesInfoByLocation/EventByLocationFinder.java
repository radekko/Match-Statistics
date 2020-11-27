package afterPlaying.findMatchesInfoByLocation;

import java.util.stream.Stream;

import beforePlaying.Team;
import playing.MatchPlayedInfo;

public interface EventByLocationFinder {
	public Stream<MatchPlayedInfo> getMatchPlayedInfo(Team team);
}
