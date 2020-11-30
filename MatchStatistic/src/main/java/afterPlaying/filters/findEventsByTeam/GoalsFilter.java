package afterPlaying.filters.findEventsByTeam;

import java.util.List;
import java.util.stream.Stream;

import afterPlaying.filters.findEventsByLocalisation.EventLocalizationFilter;
import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.MatchPlayedInfo;
import playing.core.model.Event.EventSnapshot;

public class GoalsFilter implements EventTypeFilter{
	private final EventLocalizationFilter matchesEventByLocalizationFinder;
	
	public GoalsFilter(EventLocalizationFilter matchesEventByLocalizationFinder) {
		this.matchesEventByLocalizationFinder = matchesEventByLocalizationFinder;
	}
	
	@Override
	public Stream<EventSnapshot> getStats(List<MatchPlayedInfo> matches,Team team){
		return matchesEventByLocalizationFinder.getMatchesEventInChosenLocalization(matches, team, Event.isGoal());
	}
}
