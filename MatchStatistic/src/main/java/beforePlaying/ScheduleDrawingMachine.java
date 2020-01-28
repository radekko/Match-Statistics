package beforePlaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Working only for small number of teams, around 10
// More number of teams need better algorithm
public class ScheduleDrawingMachine {

	public static Map<Integer, List<Pair>> drawSchedule(List<Team> teams) {
		Map<Integer, List<Pair>> firstHalfLines = makeDrawing(teams);
		Map<Integer, List<Pair>> result = createSecondHalfLines(firstHalfLines);
		
		return result;
	}
	
	// Sometimes can not continue drawing for example:
	//	3-1 6-4 2-5
	//	4-3 6-1    -- can not draw 2-5 or 5-2 because already drawed
	// so in that case there is repeating of drawing  
	private static Map<Integer, List<Pair>> makeDrawing(List<Team> teams) {
		Map<Integer, List<Pair>> firstHalfLines;
		
		do {
			firstHalfLines  = createFirstHalfLines(teams);
		}
		while(firstHalfLines.size() == 0);
		
		return firstHalfLines;
	}
	
	
	private static Map<Integer, List<Pair>> createFirstHalfLines(List<Team> teams) {
		Map<Integer, List<Pair>> firstHalfLines = new TreeMap<>();
		List<Pair> allPairs = PossiblePairsCreator.getAllPairs(teams);
		
		int totalLines = teams.size() - 1;
		int matchesInSingleLine = teams.size() / 2;

		int line = 1;
		while(line <= totalLines) {
			List<Pair> oneLigueLine = createOneLigueLine(matchesInSingleLine,allPairs);
			
			if(oneLigueLine.size() != matchesInSingleLine) { // lack of good drawing option, undo drawing
//				System.out.println("no good option.");
				return new TreeMap<>();
			}
			else {
				firstHalfLines.put(line, oneLigueLine);
				oneLigueLine.stream().forEach(p -> removeAlreadyDrawed(p,allPairs));
				line++;
			}
		}
		return firstHalfLines;
	}

	private static Map<Integer, List<Pair>> createSecondHalfLines(Map<Integer, List<Pair>> firstHalfLines) {
			Map<Integer, List<Pair>> result = new TreeMap<>(firstHalfLines);
			int size = firstHalfLines.size();
			for(int keyInsert = size + 1, keyGet = size; keyInsert <= size * 2; keyInsert++, keyGet--) {
				List<Pair> toReversePairs = firstHalfLines.get(keyGet);
				List<Pair> reversedPairs = toReversePairs.stream().map(Pair::reverse).collect(Collectors.toList());
				result.put(keyInsert, reversedPairs);
			}
			return result;
	}
	
	private static void removeAlreadyDrawed(Pair pair, List<Pair> copyOfPairs) {
		copyOfPairs.removeIf(p -> p.getFirstTeamId() == pair.getFirstTeamId() && p.getSecondTeamId() == pair.getSecondTeamId() ||
				p.getFirstTeamId() == pair.getSecondTeamId() && p.getSecondTeamId() == pair.getFirstTeamId());
	}

	private static List<Pair> createOneLigueLine(int matchesInSingleLine, List<Pair> allPairs) {
		List<Pair> copyOfPairs = new ArrayList<>(allPairs);
		List<Pair> currentLigueLine = new ArrayList<>();
		
		while(copyOfPairs.size() > 0) {
			Pair drawedPair = getRandomPair(copyOfPairs);
			currentLigueLine.add(drawedPair);
			copyOfPairs.removeIf(removeBothDrawedTeams(drawedPair));
		}
		return currentLigueLine;
	}

	private static Predicate<Pair> removeBothDrawedTeams(Pair randomPair) {
		return p -> p.getFirstTeamId() == randomPair.getFirstTeamId() 
				|| p.getSecondTeamId() == randomPair.getSecondTeamId()
				|| p.getFirstTeamId() == randomPair.getSecondTeamId() 
				|| p.getSecondTeamId() == randomPair.getFirstTeamId();
	}

	private static Pair getRandomPair(List<Pair> copyListOfPair) {
		Random randomGenerator = new Random(); 
		int index = randomGenerator.nextInt(copyListOfPair.size());
		return copyListOfPair.get(index);
	}

}
