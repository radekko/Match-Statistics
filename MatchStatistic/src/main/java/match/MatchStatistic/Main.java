package match.MatchStatistic;

import before.season.starting.LigueLine;
import before.season.starting.Schedule;
import before.season.starting.Team;
import before.season.starting.Teams;

public class Main 
{
    public static void main( String[] args )
    {
    	Player p1 = new Player();
    	Player p2 = new Player();
    	Player p3 = new Player();
    	Player p4 = new Player();
    	
    	/*Team team1 = new Team(1,p1, p2);
    	Team team2 = new Team(2,p3, p4);
    	Team team3 = new Team(3,p3, p4);
    	Team team4 = new Team(4,p3, p4);
    	Team team5 = new Team(5,p3, p4);
    	Team team6 = new Team(6,p3, p4);
    	Team team7 = new Team(7,p3, p4);
    	Team team8 = new Team(8,p3, p4);
    	
    	Teams teams = new Teams();
    	teams.addTeam(team1);
    	teams.addTeam(team2);
    	teams.addTeam(team3);
    	teams.addTeam(team4);
    	teams.addTeam(team5);
    	teams.addTeam(team6);
    	teams.addTeam(team7);
    	teams.addTeam(team8);*/
    	Teams teams = new Teams();
    	
    	for (int i = 1; i < 11; i++) 
    		teams.addTeam(new Team(i,p1,p2));
		
    	
    	Schedule schedule = new Schedule(teams);
    	
    	for(int i = 0; i < schedule.getTotalLines(); i++) {
    		LigueLine ligueLineBeforeStart = schedule.getLigueLine(++i);
    		System.out.println(ligueLineBeforeStart);
    		i--;
    	}
    	
    	System.out.println(schedule.getTotalLines());

		/*List<Match> matches = new ArrayList<>();
		List<Match> matches2 = new ArrayList<>();
		
		List<LigueLine> ligueLines = new ArrayList<>();
		LigueLine l = new LigueLine(1, matches);
		LigueLine l2 = new LigueLine(2, matches2);
		ligueLines.add(l);
		ligueLines.add(l2);
		
		Schedule s = new Schedule(ligueLines);*/
    }
}
