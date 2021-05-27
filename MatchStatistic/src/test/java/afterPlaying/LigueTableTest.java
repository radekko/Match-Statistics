package afterPlaying;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import afterPlaying.ligueTable.ILigueTable;
import afterPlaying.ligueTable.core.TeamRecord;
import afterPlaying.ligueTable.infrastructure.LigueTableFactory;
import afterPlaying.ligueTable.infrastructure.LigueTableFactory.LigueTableType;
import repos.MockedRepos;

public class LigueTableTest extends MockedRepos{
	// 1) team vs team2 : goals 3-1, yellow cards: 0-1
	// 2) team2 vs team: 1-2, yellow cards:2-0
	// Table:
	// 1) team 6p. 5-2
	// 2) team2 0p. 2-5
	private ILigueTable ligueTable;
	
	@Before
	public void setUp() {
		super.setUp();
		this.ligueTable = LigueTableFactory.getInstance(LigueTableType.TOTAL, historyMatchesRepo.getAllHistory());
	}
	
	@Test
	public void tableRecordForFirstTeamInTable() throws Exception {
		List<TeamRecord> table = ligueTable.prepareTable(teamRepo.getAllTeams());
		assertEquals(table.get(0).getTeam(), team);
		assertEquals(table.get(0).getPoints(), 6);
		assertEquals(table.get(0).getGoalBalance(), 3);
		assertEquals(table.get(0).getScored(), 5);
		assertEquals(table.get(0).getLost(), 2);
	}
	
	@Test
	public void tableRecordForSecondTeamInTable() throws Exception {
		List<TeamRecord> table = ligueTable.prepareTable(teamRepo.getAllTeams());
		assertEquals(table.get(1).getTeam(), team2);
		assertEquals(table.get(1).getPoints(), 0);
		assertEquals(table.get(1).getGoalBalance(), -3);
		assertEquals(table.get(1).getScored(), 2);
		assertEquals(table.get(1).getLost(), 5);
	}
	
}
