package afterPlaying.bestTeams;

import java.util.List;

import com.google.common.collect.Multimap;

import beforePlaying.core.model.Team;

public interface ITeamsLeaderBoard {
	Multimap<Integer, Team> createTotalLeaderBoard(List<Team> teams);
}