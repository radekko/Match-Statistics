package beforePlaying.core;

import beforePlaying.core.model.LigueLine;

public interface ISchedule {
	int getTotalLines();
	int getNumberOfMatchesInLine();
	LigueLine getChosenLigueLine(int line);

}