package afterPlaying.findEventsByTeam;

import java.util.List;

import beforePlaying.Team;
import playing.Event.EventSnapshot;

public interface EventByTypeFinder {
	public List<EventSnapshot> getHomeStat(Team team);
	public List<EventSnapshot> getAwayStat(Team team);
	public List<EventSnapshot> getHomeAndAwayStat(Team team);
	public String statDescription();
}
