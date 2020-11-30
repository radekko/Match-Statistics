package afterPlaying;

import static org.assertj.guava.api.Assertions.assertThat;
import static org.assertj.guava.api.Assertions.entry;

import org.junit.Test;

import com.google.common.collect.Multimap;

import afterPlaying.bestTeams.TeamsLeaderBoard;
import afterPlaying.bestTeams.TeamsLeaderBoardFactory;
import afterPlaying.bestTeams.TeamsLeaderBoardFactory.LeaderBoardType;
import beforePlaying.core.model.Team;
import repos.MockedRepos;

public class TeamsLeaderBoardTest extends MockedRepos{
	// 2 MATCHES
	// 1) team vs team2 : goals 3-1, yellow cards: 0-1
	// a) goals: p:2, p2:1 - p4:1
	// b) yellow cards: 0 - p4:1
	
	// 2) team2 vs team: 1-2, yellow cards:2-0
	// a) goals: p4:1 - p:1, p2:1
	// b) yellow cards: p3:1, p4:1 - 0
	@Test
	public void goalsBothLeaderBoard() throws Exception {
		TeamsLeaderBoard teamsGoalsLeaderBoard = TeamsLeaderBoardFactory.getTeamsLeaderBoard(LeaderBoardType.GOALS_TOTAL);
		Multimap<Integer, Team> totalGoalsLeaderBoard = teamsGoalsLeaderBoard.createTotalLeaderBoard(teamRepo.getAllTeams(), historyMatchesRepo.getAllHistory());
		
		assertThat(totalGoalsLeaderBoard).hasSize(2);
	    assertThat(totalGoalsLeaderBoard).containsKeys(2,5);
	    assertThat(totalGoalsLeaderBoard).contains(entry(5, team), entry(2, team2));
	}
	
	@Test
	public void yellowCardsBothLeaderBoard() throws Exception {
		TeamsLeaderBoard teamsGoalsLeaderBoard = TeamsLeaderBoardFactory.getTeamsLeaderBoard(LeaderBoardType.YELLOW_CARDS_TOTAL);
		Multimap<Integer, Team> totalGoalsLeaderBoard = teamsGoalsLeaderBoard.createTotalLeaderBoard(teamRepo.getAllTeams(), historyMatchesRepo.getAllHistory());
		
		assertThat(totalGoalsLeaderBoard).hasSize(2);
	    assertThat(totalGoalsLeaderBoard).containsKeys(0,3);
	    assertThat(totalGoalsLeaderBoard).contains(entry(0, team), entry(3, team2));
	}
}
