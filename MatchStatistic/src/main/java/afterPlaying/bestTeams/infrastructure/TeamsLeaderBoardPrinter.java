package afterPlaying.bestTeams.infrastructure;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import afterPlaying.bestTeams.ITeamsLeaderBoard;
import beforePlaying.core.model.Team;

public class TeamsLeaderBoardPrinter {
	private final ITeamsLeaderBoard teamsLeaderBoard;
	private final String desc;
	
	public TeamsLeaderBoardPrinter(ITeamsLeaderBoard teamsLeaderBoard, String desc) {
		this.teamsLeaderBoard = teamsLeaderBoard;
		this.desc = desc;
	}

	public void printAll(List<Team> teams) {
		printLeaderboard(teamsLeaderBoard.createTotalLeaderBoard(teams));
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
