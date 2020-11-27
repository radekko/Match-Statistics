package repos;

import java.util.ArrayList;
import java.util.List;

import beforePlaying.FutureMatch;
import beforePlaying.Player;
import beforePlaying.Team;
import playing.Event;
import playing.EventType;
import playing.EventsInMatch;
import playing.HistoryMatchesRepo;
import playing.MatchPlayedInfo;

public class HistoryMatchesRepoMock {
	
	private static HistoryMatchesRepo historyMatchesRepo;
	private static TeamRepoMock teamRepo;
	
	private HistoryMatchesRepoMock() {
	}
	
	public static HistoryMatchesRepo getIntance() {
		if(historyMatchesRepo != null)
			return historyMatchesRepo;
		
		teamRepo = TeamRepoMock.getInstance();
		HistoryMatchesRepo historyMatchesRepo = new HistoryMatchesRepo();
		historyMatchesRepo.storeAllMatchesInHistory(
				prepareMatches(
						teamRepo.getAllTeams().get(0),
						teamRepo.getAllTeams().get(1)
						));
		return historyMatchesRepo;
	}

	// 2 MATCHES
	// 1) team vs team2 : goals 3-1, yellow cards: 0-1
	// a) goals: p:2, p2:1 - p4:1
	// b) yellow cards: 0 - p4:1
	
	// 2) team2 vs team: 1-2, yellow cards:2-0
	// a) goals: p4:1 - p:1, p2:1
	// b) yellow cards: p3:1, p4:1 - 0
	private static List<MatchPlayedInfo> prepareMatches(Team team, Team team2) {
		List<MatchPlayedInfo> matches = new ArrayList<>();

		Player p = team.getPlayers().get(0);
		Player p2 = team.getPlayers().get(1);
		Player p3 = team2.getPlayers().get(0);
		Player p4 = team2.getPlayers().get(1);

		matches.add(prepareMatchPlayedInfo(
				team, 
				team2, 
				new Event(team, p, 1, EventType.GOAL), 
				new Event(team, p, 10, EventType.GOAL), 
				new Event(team, p2, 12, EventType.GOAL),
				new Event(team2, p4, 61, EventType.GOAL),
				new Event(team2, p4, 70, EventType.YELLOW_CARD)
				));
		
		matches.add(prepareMatchPlayedInfo(
				team2, 
				team, 
				new Event(team, p, 11, EventType.GOAL), 
				new Event(team, p2, 21, EventType.GOAL),
				new Event(team2, p4, 60, EventType.GOAL),
				new Event(team2, p4, 71, EventType.YELLOW_CARD),
				new Event(team2, p3, 72, EventType.YELLOW_CARD)
				));
		
		return matches;
	}
	
	private static MatchPlayedInfo prepareMatchPlayedInfo(Team homeTeam, Team awayTeam, Event... events) {
		FutureMatch matchToPlay = prepareFutureMatch(homeTeam, awayTeam);
		EventsInMatch eventsInMatch = new EventsInMatch();
		for(Event event : events)
			eventsInMatch.addNextEvent(event);
		
		MatchPlayedInfo temp = new MatchPlayedInfo(matchToPlay, 1000, eventsInMatch);
		return temp;
	}
	
	private static FutureMatch prepareFutureMatch(Team team, Team team2) {
		return new FutureMatch(team, team2, 1);
	}
}
