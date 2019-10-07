package before.season.starting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import match.MatchStatistic.Player;


public class Team{
	final long id;
	//not needed here
	List<Player> players;
	int won;
	int draw;
	int loose;

	public Team(long id) {
		this.id = id;
	}
	
	public Team(long id, Player ...p) {
		this.id = id;
		players = new ArrayList<>(Arrays.asList(p));
	}

	List<Player> getPlayers(){
		return players;
	}
	
	public long getId() {
		return id;
	}

	void incrementWon() {won++;}
	void incrementDraw() {draw++;}
	void incrementLoose() {loose++;}

	@Override
	public String toString() {
		return "Team id=" + id;
	}
}
