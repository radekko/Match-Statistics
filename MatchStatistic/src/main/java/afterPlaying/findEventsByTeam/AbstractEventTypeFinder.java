package afterPlaying.findEventsByTeam;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import afterPlaying.findMatchesInfoByLocation.AwayMatchesFinder;
import afterPlaying.findMatchesInfoByLocation.EventByLocationFinder;
import afterPlaying.findMatchesInfoByLocation.HomeMatchesFinder;
import afterPlaying.findMatchesInfoByLocation.HomeOrAwayMatchesFinder;
import beforePlaying.Team;
import playing.Event;
import playing.HistoryMatchesRepo;
import playing.MatchPlayedInfo;
import playing.Event.EventSnapshot;

public abstract class AbstractEventTypeFinder implements EventByTypeFinder{
	private final List<MatchPlayedInfo> matches;

	public AbstractEventTypeFinder(HistoryMatchesRepo historyMatchesRepo) {
		this.matches = historyMatchesRepo.getAllHistory();
	}
	
	@Override
	public List<EventSnapshot> getHomeStat(Team team){
		return getStats(team, new HomeMatchesFinder(matches));
	}
	
	@Override
	public List<EventSnapshot> getAwayStat(Team team){
		return getStats(team, new AwayMatchesFinder(matches));
	}
	
	@Override
	public List<EventSnapshot> getHomeAndAwayStat(Team team){
		return getStats(team, new HomeOrAwayMatchesFinder(matches));
	}
	
	List<EventSnapshot> getStats(Team team, EventByLocationFinder eventFinder){
		return eventFinder
						.getMatchPlayedInfo(team)
						.flatMap(m -> m.findEventsForTeam(whichStat(),team))
						.collect(Collectors.toList());
	}
	
	abstract Predicate<Event> whichStat();
}
