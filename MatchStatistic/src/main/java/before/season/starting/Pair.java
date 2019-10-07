package before.season.starting;

import java.util.Objects;

public class Pair{
	final long firstTeamId;
	final long secondTeamId;
	
	public Pair(long firstTeamId, long secondTeamId) {
		this.firstTeamId = firstTeamId;
		this.secondTeamId = secondTeamId;
	}
	public Pair(Pair next) {
		this.firstTeamId = next.firstTeamId;
		this.secondTeamId = next.secondTeamId;
	}
	
	public static Pair reverse(Pair p) {
		return new Pair(p.secondTeamId, p.firstTeamId);
	}
	
	public long getFirstTeamId() {
		return firstTeamId;
	}
	
	public long getSecondTeamId() {
		return secondTeamId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(firstTeamId,secondTeamId);

	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (firstTeamId != other.firstTeamId)
			return false;
		if (secondTeamId != other.secondTeamId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return firstTeamId + "-" + secondTeamId;
	}
	
}