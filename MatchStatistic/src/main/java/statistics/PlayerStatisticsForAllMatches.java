package statistics;

import java.util.List;
import java.util.stream.Collectors;

import model.MatchPlayedInfo;
import model.Player;
import model.Team;
import model.Event.EventSnapshot;

public class PlayerStatisticsForAllMatches {
	private List<MatchPlayedInfo> matches;

	public PlayerStatisticsForAllMatches(List<MatchPlayedInfo> matches) {
		this.matches = matches;
	}
	
	public int getTotalGoalsForPlayerWhenPlayInHome(Team team, Player player) {
		return (int)matches.stream()
						   .filter(m -> m.isHost(team))
						   .flatMap(m -> m.getHostGoals().stream())
						   .filter(e -> e.getPlayer() == player)
						   .count();
	}

	public int getTotalGoalsForPlayerWhenPlayAway(Team team, Player player) {
		return (int)matches.stream()
						   .filter(m -> m.isAway(team))
						   .flatMap(m -> m.getAwayGoals().stream())
						   .filter(e -> e.getPlayer() == player)
						   .count();
	}
	
	//get as List<Player, Minutes> and extract finding player
//	public int getTotalPlayerGoals(Player player) {
//		return matches.stream().map(m -> m.getTotalGoalsForPlayerInMatch(player)).reduce(0, Integer::sum);
//	}
	
}
