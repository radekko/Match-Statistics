package afterPlaying;

import java.util.function.Function;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import beforePlaying.Player;
import beforePlaying.Team;
import beforePlaying.Teams;

public class PlayersStats {
	private final TeamStatsByPlayers teamStatsByPlayers;
	private final Teams teams;

	public PlayersStats(Teams teams, TeamStatsByPlayers teamStatsByPlayers) {
		this.teams = teams;
		this.teamStatsByPlayers = teamStatsByPlayers;
	}
	
	public Multimap<Integer, Player> getBestPlayersByTotalGoals() {
		return createPlayerLeaderboard(teamStatsByPlayers :: getPlayerTotalGoals);
	}
	
	public Multimap<Integer, Player> getBestPlayersByHomeGoals() {
		return createPlayerLeaderboard(teamStatsByPlayers :: getPlayerHomeGoals);
	}
	
	public Multimap<Integer, Player> getBestPlayersByAwayGoals() {
		return createPlayerLeaderboard(teamStatsByPlayers :: getPlayerAwayGoals);
	}
	
	private Multimap<Integer, Player> createPlayerLeaderboard(Function<Team, Multimap<Integer, Player>> extractingPlayers){
		Multimap<Integer, Player> playersLeaderboard = TreeMultimap.create();
		teams.getAll().stream().map(extractingPlayers::apply).forEach(playersLeaderboard :: putAll);
		
		return playersLeaderboard;
	}
}
