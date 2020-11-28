package afterPlaying.againtTeamTotal;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import afterPlaying.findMatchesInfoByLocation.AwayMatchesFinder;
import afterPlaying.findMatchesInfoByLocation.EventByLocationFinder;
import afterPlaying.findMatchesInfoByLocation.HomeMatchesFinder;
import afterPlaying.findMatchesInfoByLocation.HomeOrAwayMatchesFinder;
import beforePlaying.Team;
import playing.Event;
import playing.Event.EventSnapshot;
import playing.HistoryMatchesRepo;
import playing.MatchPlayedInfo;

public abstract class AbstractOppositeTeamEventTypeFinder implements VsTeamEventByTypeFinder{
	private final List<MatchPlayedInfo> matches;

	public AbstractOppositeTeamEventTypeFinder(HistoryMatchesRepo historyMatchesRepo) {
		this.matches = historyMatchesRepo.getAllHistory();
	}
	
	@Override
	public List<EventSnapshot> getVsHomeStat(Team team){
		return getStats(team, new HomeMatchesFinder(matches));
	}
	
	@Override
	public List<EventSnapshot> getVsAwayStat(Team team){
		return getStats(team, new AwayMatchesFinder(matches));
	}
	
	@Override
	public List<EventSnapshot> getVsHomeAndAwayStat(Team team){
		return getStats(team, new HomeOrAwayMatchesFinder(matches));
	}
	
	List<EventSnapshot> getStats(Team team, EventByLocationFinder EventByLocationFinder){
		return EventByLocationFinder
						.getMatchPlayedInfo(team)
						.flatMap(m -> m.findEventsForOppositeTeam(whichStat(),team))
						.collect(Collectors.toList());
	}

	abstract Predicate<Event> whichStat();
}
