package beforePlaying;

import java.util.List;


public class Team{
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
}