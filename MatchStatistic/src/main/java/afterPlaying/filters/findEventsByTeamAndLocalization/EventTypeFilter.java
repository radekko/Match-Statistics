package afterPlaying.filters.findEventsByTeamAndLocalization;

import java.util.List;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public interface EventTypeFilter {
	Stream<EventSnapshot> getStats(List<MatchPlayedInfo> matches, Team team);
}
