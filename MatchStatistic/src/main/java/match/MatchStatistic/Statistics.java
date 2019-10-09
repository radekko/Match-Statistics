package match.MatchStatistic;

import java.util.List;

import before.season.starting.FutureMatch;
import before.season.starting.Team;

interface Statistics{
	int countStatistic(List<FutureMatch> matches);
	int countStatistic(Team team);
	int countStatistic(Player player);
}

