package model;

public class Score {
	Player player;
	int minute;

	public Score(Player player, int minute) {
		this.player = player;
		this.minute = minute;
	}

	public Player getPlayer() {
		return player;
	}

	public int getMinute() {
		return minute;
	}

	@Override
	public String toString() {
		return player + " - minute: " + minute;
	}
	
	
}
