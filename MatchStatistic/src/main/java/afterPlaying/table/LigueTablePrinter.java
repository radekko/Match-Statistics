package afterPlaying.table;

import java.util.List;

public class LigueTablePrinter {
	private final List<TeamRecord> table;

	public LigueTablePrinter(LigueTable ligueTable) {
		this.table = ligueTable.prepareTable();
	}
	
	public void print() {
		int pos = 1;
		for (TeamRecord r : table) {
			System.out.println(pos + "." + r.getTeam() + " " + r.getPoints() + " " + r.getScored() +"-" + r.getLost());
			++pos;
		}
	}
}
