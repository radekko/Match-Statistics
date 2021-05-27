package afterPlaying.bestPlayers;

import java.util.List;

import com.google.common.collect.Multimap;

import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public interface IPlayersLeaderBoard {
	Multimap<Integer, Player> createLeaderBoard(List<MatchPlayedInfo> matches, List<Team> teams);
}