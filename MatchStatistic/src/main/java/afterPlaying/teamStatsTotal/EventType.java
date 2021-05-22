package afterPlaying.teamStatsTotal;

public enum EventType{
	GOALS_GAINED, 
	GOALS_LOST, 
	YELLOW_CARDS_GAINED,
	YELLOW_CARDS_LOST;
	
	static {
		GOALS_GAINED.opposite = GOALS_LOST;
		GOALS_LOST.opposite = GOALS_GAINED;
		YELLOW_CARDS_GAINED.opposite = YELLOW_CARDS_LOST;
		YELLOW_CARDS_LOST.opposite = YELLOW_CARDS_GAINED;
	}
	 
	private EventType opposite;

	public EventType getOpposite() {
		return opposite;
	}
}