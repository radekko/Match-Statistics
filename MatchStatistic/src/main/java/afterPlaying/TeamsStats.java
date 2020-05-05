package afterPlaying;

import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import beforePlaying.Team;
import beforePlaying.Teams;
import playing.Event;
import playing.PlaceOfPlaying;

public class TeamsStats {
	private final TeamStats teamStats;
	private final List<Team> allTeams;

	public TeamsStats(Teams teams, TeamStats teamStats) {
		this.teamStats = teamStats;
		this.allTeams = teams.getAll();
	}

	public Multimap<Integer, Team> createLeaderboardForAllTeams(PlaceOfPlaying place, Predicate<Event> eventType){
		Multimap<Integer, Team> leaderBoard = TreeMultimap.create();
		allTeams.stream().forEach(team -> leaderBoard.put(teamStats.getTotalEventsForChosenPlace(team, place, eventType), team));
		return leaderBoard;
	}
	
}
