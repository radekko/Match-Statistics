package beforePlaying.core.drawingSchedule;

import java.util.List;
import java.util.Map;

import beforePlaying.core.model.Team;

public interface ScheduleGenerator {
	Map<Integer, List<Pair>> drawSchedule(List<Team> teams);
}