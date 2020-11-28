package afterPlaying.againtTeamTotal;

import java.util.List;

import beforePlaying.Team;
import playing.Event.EventSnapshot;

public interface VsTeamEventByTypeFinder {
	List<EventSnapshot> getVsHomeStat(Team team);
	List<EventSnapshot> getVsAwayStat(Team team);
	List<EventSnapshot> getVsHomeAndAwayStat(Team team);
	String statDescription();
}
