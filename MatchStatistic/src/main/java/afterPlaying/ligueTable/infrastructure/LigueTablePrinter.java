package afterPlaying.ligueTable.infrastructure;

import java.util.List;

import afterPlaying.ligueTable.ILigueTable;
import afterPlaying.ligueTable.core.TeamRecord;
import beforePlaying.core.model.Team;

public class LigueTablePrinter {
	private final ILigueTable ligueTable;

	public LigueTablePrinter(ILigueTable ligueTable) {
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
