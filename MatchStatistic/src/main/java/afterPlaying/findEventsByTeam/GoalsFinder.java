package afterPlaying.findEventsByTeam;

import java.util.function.Predicate;

import playing.Event;
import playing.HistoryMatchesRepo;

public class GoalsFinder extends AbstractEventTypeFinder{
	public GoalsFinder(HistoryMatchesRepo historyMatchesRepo) {
		super(historyMatchesRepo);
	}

	@Override
	Predicate<Event> whichStat() {
		return Event.isGoal();
	}

	@Override
	public String statDescription() {
		return "goals";
	}

}
