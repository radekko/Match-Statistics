package afterPlaying.table;

import beforePlaying.Team;

public class TeamRecord implements Comparable<TeamRecord>{
	private Team team;
	private int points;
	private int goalBalance;
	private int scored;
	private int lost;
	
	public TeamRecord(Team team, int wins, int draws, int scored, int lost){
		this.team = team;
		this.points = wins * 3 + draws;
		this.goalBalance = scored - lost;
		this.scored = scored;
		this.lost = lost;
	}

	public int getPoints() {
		return points;
	}

	public int getGoalBalance() {
		return goalBalance;
	}

	public int getScored() {
		return scored;
	}

	public int getLost() {
		return lost;
	}

	public Team getTeam() {
		return team;
	}

	@Override
	public int compareTo(TeamRecord t) {
		 int result = Integer.compare(points, t.points);
	        if (result == 0) {
	            result = Integer.compare(goalBalance, t.goalBalance);
	            if (result == 0)
	                result = Integer.compare(scored, t.scored);
	            	if (result == 0)
	            		result = Integer.compare(lost, t.lost);
	        }			
	    return result;
	}
}
