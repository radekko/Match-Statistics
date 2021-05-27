package afterPlaying.teamStatsByResult;

import java.util.List;

import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public interface IMainStats {
	int getWins(List<MatchPlayedInfo> matches, Team team);
	int getDraws(List<MatchPlayedInfo> matches, Team team); 
	int getLooses(List<MatchPlayedInfo> matches, Team team); 
}
