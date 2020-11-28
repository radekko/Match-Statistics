package afterPlaying.resultatFilter;

public enum TeamResult {
	WIN(3), DRAW(1), LOOSE(0);
	
	private int points;

	private TeamResult(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}
	
	public static boolean isWin(TeamResult teamResult) {
		return teamResult == WIN ? true : false;
	}
	
	public static boolean isLoose(TeamResult teamResult) {
		return teamResult == LOOSE ? true : false;
	}
	
	public static boolean isDraw(TeamResult teamResult) {
		return teamResult == DRAW ? true : false;
	}
	
}
