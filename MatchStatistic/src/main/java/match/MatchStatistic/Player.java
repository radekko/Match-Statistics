package match.MatchStatistic;
public class Player{
	private int goals;
	private int yellowCards;
	private int redCards;
	private int matchesPlayed;
	
	int getGoalsScored() {
		return goals;
	};
	int getYellowCards() {
		return yellowCards;
	}
	int getRedCards() {
		return redCards;
	}
	void incrementGoals(int num) {goals += num;}
	void incrementYellowCards() {yellowCards++;}
	void incrementRedCards() {redCards++;}
}