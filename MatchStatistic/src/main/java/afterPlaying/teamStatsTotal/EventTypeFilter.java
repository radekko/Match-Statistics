package afterPlaying.teamStatsTotal;

import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.Event.EventSnapshot;

public interface EventTypeFilter {
	Stream<EventSnapshot> getStats(Team team);
}
