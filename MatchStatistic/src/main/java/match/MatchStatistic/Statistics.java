package match.MatchStatistic;

import java.util.List;

import before.season.starting.Match;
import before.season.starting.Team;

interface Statistics{
	int countStatistic(List<Match> matches);
	int countStatistic(Team team);
	int countStatistic(Player player);
}

