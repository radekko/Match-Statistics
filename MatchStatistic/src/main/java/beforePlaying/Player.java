package beforePlaying;

import java.util.Objects;

public class Player{
	private int id;
	
	public Player(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	};
	
	@Override
	public String toString() {
		return "Player: " + id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}