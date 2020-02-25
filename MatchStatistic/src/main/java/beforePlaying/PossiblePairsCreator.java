package beforePlaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class PossiblePairsCreator {

	public static List<Pair> getAllPairs(List<Team> teams) {
		List<Pair> allPairs = new ArrayList<Pair>();
		
		for (Team team : teams) {
			List<Team> coppiedTeams = new ArrayList<>(teams);
			coppiedTeams.remove(team);
			
			List<Pair> possiblePairsForCurrTeam = prepareListOfAllMatchesForCurrentTeam(team, coppiedTeams);
			allPairs.addAll(possiblePairsForCurrTeam);
		}
	
		return allPairs;
	}

	private static List<Pair> prepareListOfAllMatchesForCurrentTeam(Team choosenTeam, List<Team> remainsTeams) {
		return remainsTeams.stream()
				.mapToLong(p -> p.getId())
				.mapToObj(id -> new Pair(choosenTeam.getId(), id))
				.collect(Collectors.toList());
	}

}
