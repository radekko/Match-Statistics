package afterPlaying;

import java.util.List;

import beforePlaying.Player;
import beforePlaying.Team;
import playing.Event.EventSnapshot;

public class SinglePlayerStats{
	private final SingleTeamStats teamStats;

	public SinglePlayerStats(SingleTeamStats teamStats) {
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

//	public List<EventSnapshot> getHomeOrAwayEvents(Team team, Player player, PlaceOfPlaying pred, Function<MatchPlayedInfo, List<EventSnapshot>> whichStats) {
		/*List<EventSnapshot> statsForTeam = teamStats.getHomeOrAwayEvents(team, pred, whichStats);
		
		return statsForTeam.stream()
				.filter(e -> e.getPlayer() == player)
				.collect(Collectors.toList());*/
		/*return matches.stream()
						   .filter(pred.execute(team))
						   .map(whichStats)
						   .flatMap(List::stream)
						   .filter(e -> e.getPlayer() == player)
							.collect(Collectors.toList());*/
//	}
	
//	public List<EventSnapshot> getSumOfEvents(Team team, Player player,
//			Function<MatchPlayedInfo, List<EventSnapshot>> homeEvents, 
//			Function<MatchPlayedInfo, List<EventSnapshot>> awayEvents) {
		/*List<EventSnapshot> sumOfEvents = teamStats.getSumOfEvents(team, homeEvents, awayEvents);

		return sumOfEvents.stream()
				.filter(e -> e.getPlayer() == player)
				.collect(Collectors.toList()); */
//	}
}
