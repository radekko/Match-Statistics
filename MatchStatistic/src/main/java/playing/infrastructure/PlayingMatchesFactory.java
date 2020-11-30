package playing.infrastructure;

import beforePlaying.core.ISchedule;
import playing.core.IPlayingMatches;
import playing.core.playingMatches.DrawingEvents;
import playing.core.playingMatches.PlayingMatches;

public class PlayingMatchesFactory {

	public static IPlayingMatches getPlayingMatchesInstance(ISchedule schedule) {
		return new PlayingMatches(schedule, new HistoryMatchesRepo(new HistoryMatchesDatabase()), new DrawingEvents());
	}

}
