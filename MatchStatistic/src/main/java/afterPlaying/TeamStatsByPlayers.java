package afterPlaying;

import java.util.List;

import beforePlaying.Player;
import beforePlaying.Team;
import playing.Event.EventSnapshot;

public class TeamStatsByPlayers{
	private final TeamStats teamStats;

	public TeamStatsByPlayers(TeamStats teamStats) {
		this.teamStats = teamStats;
	}
	
	public int getPlayerHomeGoals(Player player, Team team) {
		List<EventSnapshot> teamGoalsInHome = teamStats.getGoalsInHome(team);
		return countForPlayer(teamGoalsInHome, player);
	}
	
	public int getPlayerAwayGoals(Player player, Team team) {
		List<EventSnapshot> teamGoalsAway = teamStats.getGoalsAway(team);
		return countForPlayer(teamGoalsAway, player);
	}
	
	public int getPlayerTotalGoals(Player player, Team team) {
		List<EventSnapshot> teamGoalsTotal = teamStats.getGoals(team);
		return countForPlayer(teamGoalsTotal, player);
	}
	
	private int countForPlayer(List<EventSnapshot> events, Player player) {
		return (int)events.stream()
				 .filter(e -> e.getPlayer() == player)
				 .count();
	}

}
