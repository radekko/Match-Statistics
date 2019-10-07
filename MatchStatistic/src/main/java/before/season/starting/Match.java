package before.season.starting;

public class Match{
	private Team homeTeam;
	private Team awayTeam;
	
	public Match(Team homeTeam, Team awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	@Override
	public String toString() {
		return "homeTeam= " + homeTeam + ", awayTeam=" + awayTeam;
	}
	
}