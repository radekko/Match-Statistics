package afterPlaying.bestPlayers;

import java.util.List;

import com.google.common.collect.Multimap;

import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

//For example
//
//-------------------------------------------------
//Best players by total goals
//-------------------------------------------------
//3 total goals: Player: 31
//1 total goals: Player: 3, Player: 4, Player: 24, Player: 26, Player: 42, Player: 43, Player: 44

public interface IPlayersLeaderBoard {
	Multimap<Integer, Player> createLeaderBoard(List<MatchPlayedInfo> matches, List<Team> teams);
}