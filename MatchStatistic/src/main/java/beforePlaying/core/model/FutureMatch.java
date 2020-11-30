package beforePlaying.core.model;

public class FutureMatch{
	private Team homeTeam;
	private Team awayTeam;
	private int ligueLine;
	
	public FutureMatch(Team homeTeam, Team awayTeam, int ligueLine) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.ligueLine = ligueLine;
	}
	
	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}
	
	public int getLigueLine() {
		return ligueLine;
	}

	@Override
	public String toString() {
		return "homeTeam= " + homeTeam + ", awayTeam=" + awayTeam;
	}
	
}