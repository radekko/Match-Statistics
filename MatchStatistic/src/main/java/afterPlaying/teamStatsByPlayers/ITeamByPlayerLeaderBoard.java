package afterPlaying.teamStatsByPlayers;

import java.util.List;

import com.google.common.collect.Multimap;

import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public interface ITeamByPlayerLeaderBoard {
	Multimap<Integer, Player> createLeaderBoard(List<MatchPlayedInfo> matches, Team team);
}