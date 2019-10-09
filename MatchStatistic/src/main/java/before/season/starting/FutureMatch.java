package before.season.starting;

public class FutureMatch{
	private Team homeTeam;
	private Team awayTeam;
	
	public FutureMatch(Team homeTeam, Team awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	@Override
	public String toString() {
		return "homeTeam= " + homeTeam + ", awayTeam=" + awayTeam;
	}
	
}