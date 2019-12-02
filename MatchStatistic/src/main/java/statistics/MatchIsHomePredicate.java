package statistics;

import java.util.function.Predicate;

import model.MatchPlayedInfo;
import model.Team;

public class MatchIsHomePredicate implements PlaceOfPlayingMatchPredicate{

	@Override
	public Predicate<MatchPlayedInfo> isHome(Team team) {
		return m -> m.isHost(team);
	}

}
