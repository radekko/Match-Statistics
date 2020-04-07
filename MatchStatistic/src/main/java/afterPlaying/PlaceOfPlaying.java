package afterPlaying;

import java.util.function.Predicate;

import beforePlaying.Team;
import playing.MatchPlayedInfo;

public enum PlaceOfPlaying {

	HOME(homePlay(), "home"), AWAY(awayPlay(), "away"), HOME_OR_AWAY(homeOrAwayPlay(), "home or away");
	
	private PlaceOfPlayingMatchPredicate pred;
	private String description;
	
	private PlaceOfPlaying(PlaceOfPlayingMatchPredicate pred, String description) {
		this.pred = pred;
		this.description = description;
	}

	public Predicate<MatchPlayedInfo> chosenPlaceFilter(Team team){
		return pred.isPlace(team);
	}
	
	public String getDescription() {
		return description;
	}

	public static PlaceOfPlayingMatchPredicate homePlay() {
		return (Team team) -> {
			return (MatchPlayedInfo m) -> m.isHost(team);	
		};
	    /*return new PlaceOfPlayingMatchPredicate() {
			@Override
			public Predicate<MatchPlayedInfo> isPlace(Team team) {
				return m -> m.isHost(team);
			}
	    };*/
	}

	public static PlaceOfPlayingMatchPredicate awayPlay() {
		return (Team team) -> {
			return (MatchPlayedInfo m) -> m.isAway(team);	
		};
	}
	
	public static PlaceOfPlayingMatchPredicate homeOrAwayPlay() {
		return (Team team) -> {
			return (MatchPlayedInfo m) -> m.isHomeOrAway(team);	
		};
	}
	
}
