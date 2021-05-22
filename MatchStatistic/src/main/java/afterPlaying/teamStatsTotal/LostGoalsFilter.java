package afterPlaying.teamStatsTotal;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import beforePlaying.core.model.Team;
import playing.core.model.Event;
import playing.core.model.MatchPlayedInfo;

class LostGoalsFilter extends AbstractEventTypeFilter{

	public LostGoalsFilter(List<MatchPlayedInfo> matches, MatchPlayedInfoPredicate pred) {
		super(matches, pred);
	}

	@Override
	protected Predicate<Event> eventType() {
		return Event.isGoal();
	}

	@Override
	protected Stream<Event> teamEventFilter(MatchPlayedInfo m, Team team){
		return m.getEventsFromMatchForOpponent(team);
	}
}
