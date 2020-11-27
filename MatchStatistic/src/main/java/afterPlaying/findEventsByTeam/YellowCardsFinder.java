package afterPlaying.findEventsByTeam;

import java.util.function.Predicate;

import playing.Event;
import playing.HistoryMatchesRepo;

public class YellowCardsFinder extends AbstractEventTypeFinder {

	public YellowCardsFinder(HistoryMatchesRepo historyMatchesRepo) {
		super(historyMatchesRepo);
	}

	@Override
	Predicate<Event> whichStat() {
		return Event.isYellowCard();
	}
	
	@Override
	public String statDescription() {
		return "yellow cards";
	}
}
