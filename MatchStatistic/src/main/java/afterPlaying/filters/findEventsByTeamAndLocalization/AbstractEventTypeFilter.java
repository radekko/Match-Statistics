package afterPlaying.filters.findEventsByTeamAndLocalization;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import afterPlaying.filters.findEventsByLocalization.EventLocalizationFilter;
import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public abstract class AbstractEventTypeFilter implements EventTypeFilter{
	private final EventLocalizationFilter matchesEventByLocalizationFinder;
	
	public AbstractEventTypeFilter(EventLocalizationFilter matchesEventByLocalizationFinder) {
		this.matchesEventByLocalizationFinder = matchesEventByLocalizationFinder;
	}
	
	@Override
	public Stream<EventSnapshot> getStats(List<MatchPlayedInfo> matches, Team team){
		return matchesEventByLocalizationFinder.getMatchesInChosenLocalization(matches, team, eventType());
	}
	
	protected abstract Predicate<Event> eventType();
}
