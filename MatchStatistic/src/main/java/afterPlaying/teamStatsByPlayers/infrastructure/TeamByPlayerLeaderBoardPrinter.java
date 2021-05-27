package afterPlaying.teamStatsByPlayers.infrastructure;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import afterPlaying.teamStatsByPlayers.ITeamByPlayerLeaderBoard;
import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class TeamByPlayerLeaderBoardPrinter {
	private final ITeamByPlayerLeaderBoard teamByPlayerLeaderBoard;
	private final String statDesc;

	public TeamByPlayerLeaderBoardPrinter(ITeamByPlayerLeaderBoard teamByPlayerLeaderBoard, String statDesc) {
		this.teamByPlayerLeaderBoard = teamByPlayerLeaderBoard;
		this.statDesc = statDesc;
	}

	public void printAll(Team team, List<MatchPlayedInfo> matches) {
		System.out.println("-------------------------------------------------");
		System.out.println("Players statistic for " + team);
    	System.out.println("-------------------------------------------------");
    	
    	System.out.println(statDesc);
    	System.out.println("*************************");
    	printPlayersLeaderboard(teamByPlayerLeaderBoard.createLeaderBoard(matches, team));
		System.out.println();
	}

	private void printPlayersLeaderboard(Multimap<Integer, Player> resultMap) {
		noStats(resultMap);
			
		Set<Integer> numberOfGoalsDesceding = new TreeSet<>(resultMap.keySet()).descendingSet();
		for(Integer num : numberOfGoalsDesceding) {
			System.out.print(num + ": ");
			System.out.println(resultMap.get(num).stream().map(String::valueOf).collect(Collectors.joining(", ")));
		}
	}

	private void noStats(Multimap<Integer, Player> resultMap) {
		if(resultMap.isEmpty()) {
			System.out.println("0 " + statDesc);
			return;
		}
	}
}
