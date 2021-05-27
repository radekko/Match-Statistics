package afterPlaying.teamStatsTotal;

import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.Event.EventSnapshot;

public interface IDetailStats {
	int getTotalStat(Team team);
	Stream<EventSnapshot> getEvents(Team team);
}