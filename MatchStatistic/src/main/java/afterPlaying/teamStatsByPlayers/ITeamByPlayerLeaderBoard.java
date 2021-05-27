package afterPlaying.teamStatsByPlayers;

import java.util.List;

import com.google.common.collect.Multimap;

import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;


//For example
//
//-------------------------------------------------
//Players statistic for Team id=1
//-------------------------------------------------
//total goals
//*************************
//1: Player: 3, Player: 4
public interface ITeamByPlayerLeaderBoard {
	Multimap<Integer, Player> createLeaderBoard(List<MatchPlayedInfo> matches, Team team);
}