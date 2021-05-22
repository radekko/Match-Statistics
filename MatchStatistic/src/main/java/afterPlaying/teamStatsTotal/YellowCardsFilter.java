package afterPlaying.teamStatsTotal;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.MatchPlayedInfo;

class YellowCardsFilter extends AbstractEventTypeFilter{

	public YellowCardsFilter(List<MatchPlayedInfo> matches, MatchPlayedInfoPredicate pred) {
		super(matches, pred);
	}

	@Override
	protected Predicate<Event> eventType() {
		return Event.isYellowCard();
	}
	
	@Override
	protected Stream<Event> teamEventFilter(MatchPlayedInfo m, Team team){
		return m.getEventsFromMatch(team);
	}
}
