package afterPlaying.bestPlayers;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import beforePlaying.core.model.Player;
import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class PlayersLeaderBoardPrinter {
	private final PlayersLeaderBoard playersLeaderBoard;
	private final String statDesc;

	public PlayersLeaderBoardPrinter(PlayersLeaderBoard playersLeaderBoard, String statDesc) {
		this.playersLeaderBoard = playersLeaderBoard;
		this.statDesc = statDesc;
	}

	public void printAll(List<Team> teams, List<MatchPlayedInfo> matches) {
		System.out.println("-------------------------------------------------");
		System.out.println("Best players by " + statDesc);
		System.out.println("-------------------------------------------------");
		printLeaderBoard(playersLeaderBoard.createLeaderBoard(matches, teams));
	}
	
	private void printLeaderBoard(Multimap<Integer, Player> leaderboard) {
		Set<Integer> numberOfGoalsDesceding = new TreeSet<>(leaderboard.keySet()).descendingSet();
		
		for(Integer num : numberOfGoalsDesceding) {
			System.out.print(num + " " + statDesc + ": ");
			System.out.println(leaderboard.get(num).stream().map(String::valueOf).collect(Collectors.joining(", ")));
		}
	}
}
