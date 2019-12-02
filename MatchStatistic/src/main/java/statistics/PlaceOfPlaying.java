package statistics;

import java.util.function.Predicate;

import model.MatchPlayedInfo;
import model.Team;

public enum PlaceOfPlaying {

	HOME(homePlay(),"home"), AWAY(awayPlay(),"away");
	
	private PlaceOfPlayingMatchPredicate pred;
	private String description;
	
	private PlaceOfPlaying(PlaceOfPlayingMatchPredicate pred, String description) {
		this.pred = pred;
		this.description = description;
	}

	public Predicate<MatchPlayedInfo> execute(Team team){
		return pred.isHome(team);
	}
	
	public String getDescription() {
		return description;
	}

	public static PlaceOfPlayingMatchPredicate homePlay() {
	    return new PlaceOfPlayingMatchPredicate() {
			@Override
			public Predicate<MatchPlayedInfo> isHome(Team team) {
				return m -> m.isHost(team);
			}
	    };
	}

	public static PlaceOfPlayingMatchPredicate awayPlay() {
	    return new PlaceOfPlayingMatchPredicate() {
			@Override
			public Predicate<MatchPlayedInfo> isHome(Team team) {
				return m -> m.isAway(team);
			}
	    };
	}
	
	
}
