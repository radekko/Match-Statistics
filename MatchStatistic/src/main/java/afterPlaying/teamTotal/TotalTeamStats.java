package afterPlaying.teamTotal;

import afterPlaying.findEventsByTeam.EventByTypeFinder;
import beforePlaying.Team;

public class TotalTeamStats {
	private final EventByTypeFinder eventByTypeFinder;

	public TotalTeamStats(EventByTypeFinder eventByTypeFinder) {
		this.eventByTypeFinder = eventByTypeFinder;
	}
	
	public int getHomeStat(Team team) {
		return eventByTypeFinder.getHomeStat(team).size();
	}
	
	public int getAwayStat(Team team) {
		return eventByTypeFinder.getAwayStat(team).size();
	}
	
	public int getHomeAndAwayStat(Team team) {
		return eventByTypeFinder.getHomeAndAwayStat(team).size();
	}
	
	public String statsDescription() {
		return eventByTypeFinder.statDescription();
	}
	
}
