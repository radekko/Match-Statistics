package afterPlaying.filters.findEventsByLocalization;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public interface EventLocalizationFilter {
	Stream<EventSnapshot> getMatchesInChosenLocalization(List<MatchPlayedInfo> matches, Team team,Predicate<Event> eventType);
}