package afterPlaying.filters.findEventsByTeamAndLocalization;

import java.util.function.Predicate;

import afterPlaying.filters.findEventsByLocalization.EventLocalizationFilter;
import playing.core.model.Event;

public class GoalsFilter extends AbstractEventTypeFilter{
	public GoalsFilter(EventLocalizationFilter matchesEventByLocalizationFinder) {
		super(matchesEventByLocalizationFinder);
	}

	@Override
	protected Predicate<Event> eventType() {
		return Event.isGoal();
	}
}
