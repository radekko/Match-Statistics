package afterPlaying.filters.findEventsByTeamAndLocalization;

import java.util.function.Predicate;

import afterPlaying.filters.findEventsByLocalization.EventLocalizationFilter;
import playing.core.model.Event;

public class YellowCardsFilter extends AbstractEventTypeFilter{
	public YellowCardsFilter(EventLocalizationFilter matchesEventByLocalizationFinder) {
		super(matchesEventByLocalizationFinder);
	}

	@Override
	protected Predicate<Event> eventType() {
		return Event.isYellowCard();
	}
}
