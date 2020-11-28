package afterPlaying.findEventsByTeam;

import java.util.List;

import beforePlaying.Team;
import playing.Event.EventSnapshot;

public interface EventByTypeFinder {
	List<EventSnapshot> getHomeStat(Team team);
	List<EventSnapshot> getAwayStat(Team team);
	List<EventSnapshot> getHomeAndAwayStat(Team team);
	String statDescription();
}
