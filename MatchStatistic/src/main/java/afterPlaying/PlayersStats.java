package afterPlaying;

import java.util.function.Predicate;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import beforePlaying.Player;
import beforePlaying.Teams;
import playing.Event;
import playing.PlaceOfPlaying;

public class PlayersStats {
	private final TeamStatsByPlayers teamStatsByPlayers;
	private final Teams teams;

	public PlayersStats(Teams teams, TeamStatsByPlayers teamStatsByPlayers) {
		this.teams = teams;
		this.teamStatsByPlayers = teamStatsByPlayers;
	}
	
	public Multimap<Integer, Player> createPlayerLeaderboard(PlaceOfPlaying place, Predicate<Event> eventType){
		Multimap<Integer, Player> playersLeaderboard = TreeMultimap.create();
		teams.getAll().stream()
			.map(
					team -> teamStatsByPlayers.getPlayersStatisticsInChosenTeam(team, place, eventType)
				)
			.forEach(playersLeaderboard :: putAll);
		
		return playersLeaderboard;
	}

}
