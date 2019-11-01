package model;
public class Player{
	private int id;
//	private int goals;
//	private int yellowCards;
//	private int redCards;
//	private int matchesPlayed;
	
	public Player(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	};
	/*int getGoalsScored() {
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
	void incrementRedCards() {redCards++;}*/
	
	@Override
	public String toString() {
		return "Player: " + id;
	}
	
	
}