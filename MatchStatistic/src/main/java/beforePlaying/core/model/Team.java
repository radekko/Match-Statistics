package beforePlaying.core.model;

import java.util.List;
import java.util.Objects;


public class Team implements Comparable<Team> {
	private final long id;
	private List<Player> players;

	public Team(long id) {
		this.id = id;
	}
	
	public Team(long id, List<Player> players) {
		this.id = id;
		this.players = players;
	}

	public List<Player> getPlayers(){
		return players;
	}
	
	public void addPlayers(List<Player> players) {
		players.addAll(players);
	}
	
	public long getId() {
		return id;
	}
	
	public void printPlayers() {
		players.stream().forEach(System.out::println);
	}

	@Override
	public String toString() {
		return "Team id=" + id;
	}

	@Override
	public int compareTo(Team o) {
		return Long.compare(id, o.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		Team other = (Team) obj;
		if (id != other.id)
			return false;
		
		return true;
	}
	
}
