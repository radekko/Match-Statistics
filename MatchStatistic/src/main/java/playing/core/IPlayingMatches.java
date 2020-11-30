package playing.core;

import java.util.List;

import playing.core.model.MatchPlayedInfo;

public interface IPlayingMatches {
	List<MatchPlayedInfo> getPlayedMatches();
	void printPlayedMatchesGroupingByLigueLine();
}