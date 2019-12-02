package statistics;

import java.util.List;
import java.util.function.Function;

import model.Event.EventSnapshot;
import model.MatchPlayedInfo;
import model.Team;

public interface IStatistics {
//	int getTotalEvents(Team team);
//	List<EventSnapshot> getEvents(Team team);
//	List<EventSnapshot> getEvents(Team team, PlaceOfPlaying place);
//	int getTotalEvents(Team team, PlaceOfPlaying place);
	List<EventSnapshot> getEvents(Team team, PlaceOfPlaying pred, Function<MatchPlayedInfo, List<EventSnapshot>> f);

	int getTotalEvents(Team team, PlaceOfPlaying pred, Function<MatchPlayedInfo, List<EventSnapshot>> f);

}
