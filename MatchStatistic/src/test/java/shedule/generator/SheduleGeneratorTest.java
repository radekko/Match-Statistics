package shedule.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import beforeStarting.Pair;
import beforeStarting.ScheduleDrawingMachine;
import junit.framework.TestCase;
import model.Team;

public class SheduleGeneratorTest extends TestCase {

	private List<Team> teams;

	protected void setUp() throws Exception {
		teams = new ArrayList<Team>();
		
		for (int i = 0; i < 4; i++) {
			teams.add(new Team(i));
		}
		
		List<Pair> list = new ArrayList<>();
		list.add(new Pair(1,2));
		list.add(new Pair(2,1));
		list.add(new Pair(1,3));
		list.add(new Pair(3,1));
		list.add(new Pair(2,4));
		list.add(new Pair(4,2));
		list.add(new Pair(4,1));
		list.add(new Pair(1,4));
		list.add(new Pair(3,2));
		list.add(new Pair(2,3));
		list.add(new Pair(3,4));
		list.add(new Pair(4,3));
		
		
	}
	
	@Test
	public void testName() throws Exception {
		Map<Integer, List<Pair>> prepareShedule = ScheduleDrawingMachine.drawSchedule(teams);
		System.out.println(prepareShedule.size());
		
	}

}
