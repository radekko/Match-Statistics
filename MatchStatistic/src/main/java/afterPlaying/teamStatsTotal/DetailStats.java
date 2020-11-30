package afterPlaying.teamStatsTotal;

import java.util.List;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public interface DetailStats {
	int getTotalStat(List<MatchPlayedInfo> matches, Team team);
	Stream<EventSnapshot> getEvents(List<MatchPlayedInfo> matches, Team team);
}