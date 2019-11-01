package statistics;

import java.util.List;

import model.MatchPlayedInfo;
import model.Player;

public class PlayerStatisticsForAllMatches {
	private List<MatchPlayedInfo> matches;

	public PlayerStatisticsForAllMatches(List<MatchPlayedInfo> matches) {
		this.matches = matches;
	}
	
	public int getTotalPlayerGoals(Player player) {
		return matches.stream().map(m -> m.getTotalGoalsForPlayerInMatch(player)).reduce(0, Integer::sum);
	}
	
	
}
