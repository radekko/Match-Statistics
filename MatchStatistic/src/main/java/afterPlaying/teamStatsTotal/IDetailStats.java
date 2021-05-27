package afterPlaying.teamStatsTotal;

import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.Event.EventSnapshot;


//For example
//
//Total home and away goals for Team id=2
//-------------------------------------------------
//2

public interface IDetailStats {
	int getTotalStat(Team team);
	Stream<EventSnapshot> getEvents(Team team);
}