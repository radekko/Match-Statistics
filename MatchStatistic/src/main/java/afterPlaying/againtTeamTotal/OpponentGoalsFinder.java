package afterPlaying.againtTeamTotal;

import java.util.function.Predicate;

import playing.Event;
import playing.HistoryMatchesRepo;

public class OpponentGoalsFinder extends AbstractOppositeTeamEventTypeFinder{
	public OpponentGoalsFinder(HistoryMatchesRepo historyMatchesRepo) {
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
