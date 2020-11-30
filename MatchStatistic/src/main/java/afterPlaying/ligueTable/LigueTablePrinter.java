package afterPlaying.ligueTable;

import java.util.List;

import beforePlaying.core.model.Team;

public class LigueTablePrinter {
	private final LigueTable ligueTable;

	public LigueTablePrinter(LigueTable ligueTable) {
		this.ligueTable = ligueTable;
	}
	
	public void print(List<Team> whcichTeams) {
		int pos = 1;
		for (TeamRecord r : ligueTable.prepareTable(whcichTeams)) {
			System.out.println(pos + "." + r.getTeam() + " " + r.getPoints() + " " + r.getScored() +"-" + r.getLost());
			++pos;
		}
		System.out.println();
	}

}
