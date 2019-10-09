package match.MatchStatistic;

import before.season.starting.FutureMatch;

public class PlayingArea {

	FutureMatch f;
	int crowd;
	MatchPlayedInfo matchPlayedInfo;
	
	public PlayingArea(FutureMatch f, int crowd) {
		this.f = f;
		this.crowd = crowd;
//		this.matchPlayedInfo = new MatchPlayedInfo(f, crowd, matchEvents);
	}

//	public MatchPlayedInfo getMatchPlayedInfo() {
//		return matchPlayedInfo;
//	}
	
	//probably here inject events
	public MatchPlayedInfo playMatch() {
		
		EventsInMatch matchEvents = new EventsInMatch();
		
		Event event = new Event(f.getHomeTeam(), f.getHomeTeam().getPlayers().get(4), 12, EventType.GOAL);
		Event event2 = new Event(f.getHomeTeam(), f.getHomeTeam().getPlayers().get(8), 80, EventType.GOAL);
		matchEvents.addEvent(event);
		matchEvents.addEvent(event2);
		
		MatchPlayedInfo pa = new MatchPlayedInfo(f,
										 1000,
										 matchEvents);
		
//		historyMatches.storeMatchInHistory(pa);
		return pa;
	}

}
