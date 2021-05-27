package afterPlaying.ligueTable;

import java.util.List;

import afterPlaying.ligueTable.core.TeamRecord;
import beforePlaying.core.model.Team;

public interface ILigueTable {
	List<TeamRecord> prepareTable(List<Team> whcichTeams);
}