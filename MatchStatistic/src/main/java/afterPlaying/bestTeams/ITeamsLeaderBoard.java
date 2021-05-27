package afterPlaying.bestTeams;

import java.util.List;

import com.google.common.collect.Multimap;

import beforePlaying.core.model.Team;

// For example:
//
//-------------------------------------------------
//Best teams by goals total
//-------------------------------------------------
//5: Team id=3
//2: Team id=2,Team id=4


public interface ITeamsLeaderBoard {
	Multimap<Integer, Team> createTotalLeaderBoard(List<Team> teams);
}