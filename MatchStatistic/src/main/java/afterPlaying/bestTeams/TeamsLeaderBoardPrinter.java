package afterPlaying.bestTeams;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import beforePlaying.core.model.Team;
import playing.core.model.MatchPlayedInfo;

public class TeamsLeaderBoardPrinter {
	private final TeamsLeaderBoard teamsLeaderBoard;
	private final String desc;
	
	public TeamsLeaderBoardPrinter(TeamsLeaderBoard teamsLeaderBoard, String desc) {
		this.teamsLeaderBoard = teamsLeaderBoard;
		this.desc = desc;
	}

	public void printAll(List<Team> teams, List<MatchPlayedInfo> matches) {
		printLeaderboard(teamsLeaderBoard.createTotalLeaderBoard(teams, matches));
		System.out.println();
	}
	
	private void printLeaderboard(Multimap<Integer, Team> leaderboard) {
		System.out.println("-------------------------------------------------");
		System.out.println("Best teams by " + desc);
		System.out.println("-------------------------------------------------");
		
		Set<Integer> numberOfGoalsDesceding = new TreeSet<>(leaderboard.keySet()).descendingSet();
		numberOfGoalsDesceding.remove(0);
		
		for(Integer num : numberOfGoalsDesceding) {
			System.out.print(num + ": ");
			System.out.println(leaderboard.get(num).stream().map(String::valueOf).collect(Collectors.joining(",")));
		}
	}
}
