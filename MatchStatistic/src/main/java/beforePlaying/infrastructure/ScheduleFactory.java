package beforePlaying.infrastructure;

import beforePlaying.core.ISchedule;
import beforePlaying.core.drawingSchedule.SimplifiedScheduleGenerator;
import beforePlaying.core.model.Schedule;

public class ScheduleFactory {
	public static ISchedule getInstance(TeamsRepo teams) {
		return new Schedule(teams, new SimplifiedScheduleGenerator());
	}
}
