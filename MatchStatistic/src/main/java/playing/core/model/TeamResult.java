package playing.core.model;

public enum TeamResult {
	WIN, DRAW, LOOSE;
	
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
