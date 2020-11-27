package afterPlaying;

import static org.assertj.guava.api.Assertions.assertThat;
import static org.assertj.guava.api.Assertions.entry;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Multimap;

import afterPlaying.findEventsByTeam.EventByTypeFinder;
import afterPlaying.findEventsByTeam.GoalsFinder;
import afterPlaying.teamByPlayers.TeamByPlayerLeaderBoard;
import beforePlaying.Player;
import repos.MockedRepos;

public class TeamGoalsByPlayerLeaderBoardTest extends MockedRepos{
	// 2 MATCHES
	// 1) team vs team2 : goals 3-1, yellow cards: 0-1
	// a) goals: p:2, p2:1 - p4:1
	// b) yellow cards: 0 - p4:1
	
	// 2) team2 vs team: 1-2, yellow cards:2-0
	// a) goals: p4:1 - p:1, p2:1
	// b) yellow cards: p3:1, p4:1 - 0
	private EventByTypeFinder eventByTypeFinder;
	private TeamByPlayerLeaderBoard teamByPlayerLeaderBoard;
	
	@Before
	public void setUp() {
		super.setUp();
		eventByTypeFinder = new GoalsFinder(historyMatchesRepo);
		teamByPlayerLeaderBoard = new TeamByPlayerLeaderBoard(eventByTypeFinder);
	}
	
	@Test
	public void homeGoalLeaderBoardForTeam() {
		Multimap<Integer, Player> homeLeaderBoard = teamByPlayerLeaderBoard.createHomeLeaderBoard(team);
		
		assertThat(homeLeaderBoard).hasSize(2);
	    assertThat(homeLeaderBoard).containsKeys(1, 2);
	    assertThat(homeLeaderBoard).contains(entry(2, p), entry(1, p2));
	}
	
	@Test
	public void homeGoalLeaderBoardForTeam2() {
		Multimap<Integer, Player> homeLeaderBoard = teamByPlayerLeaderBoard.createHomeLeaderBoard(team2);
		
		assertThat(homeLeaderBoard).hasSize(1);
	    assertThat(homeLeaderBoard).containsKeys(1);
	    assertThat(homeLeaderBoard).contains(entry(1, p4));
	}
	
	@Test
	public void awayGoalLeaderBoardForTeam() {
		Multimap<Integer, Player> awayLeaderBoard = teamByPlayerLeaderBoard.createAwayLeaderBoard(team);
		
		assertThat(awayLeaderBoard).hasSize(2);
	    assertThat(awayLeaderBoard).containsKeys(1);
	    assertThat(awayLeaderBoard).contains(entry(1, p), entry(1, p2));
	}
	
	@Test
	public void awayGoalLeaderBoardForTeam2() {
		Multimap<Integer, Player> awayLeaderBoard = teamByPlayerLeaderBoard.createAwayLeaderBoard(team2);
		
		assertThat(awayLeaderBoard).hasSize(1);
	    assertThat(awayLeaderBoard).containsKeys(1);
	    assertThat(awayLeaderBoard).contains(entry(1, p4));
	}
	
	@Test
	public void totalGoalLeaderBoardForTeam() {
		Multimap<Integer, Player> totalLeaderBoard = teamByPlayerLeaderBoard.createTotalLeaderBoard(team);
		
		assertThat(totalLeaderBoard).hasSize(2);
	    assertThat(totalLeaderBoard).containsKeys(2,3);
	    assertThat(totalLeaderBoard).contains(entry(3, p), entry(2, p2));
	}
	
	@Test
	public void totalGoalLeaderBoardForTeam2() {
		Multimap<Integer, Player> totalLeaderBoard = teamByPlayerLeaderBoard.createTotalLeaderBoard(team2);
		
		assertThat(totalLeaderBoard).hasSize(1);
	    assertThat(totalLeaderBoard).containsKeys(2);
	    assertThat(totalLeaderBoard).contains(entry(2, p4));
	}
}
