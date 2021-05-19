package afterPlaying.filters.findEventsByTeamAndLocalization;

import java.util.List;
import java.util.stream.Stream;

import afterPlaying.filters.findEventsByLocalization.EventLocalizationFilter;
import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public class YellowCardsFilter implements EventTypeFilter{
	private final EventLocalizationFilter matchesEventByLocalizationFinder;
	
	public YellowCardsFilter(EventLocalizationFilter matchesEventByLocalizationFinder) {
		this.matchesEventByLocalizationFinder = matchesEventByLocalizationFinder;
	}
	
	@Override
	public Stream<EventSnapshot> getStats(List<MatchPlayedInfo> matches, Team team){
		return matchesEventByLocalizationFinder.getMatchesInChosenLocalization(matches, team, Event.isYellowCard());
	}
}
