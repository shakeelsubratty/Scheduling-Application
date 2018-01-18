package test.java;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.java.classes.scheduler.model.Person;
import main.java.classes.scheduler.model.Task;
import main.java.classes.scheduler.model.Assignment;
import main.java.classes.scheduler.model.Scheduler;
import java.util.ArrayList;

public class SchedulerTest {
	// Test 1: Assigmnent with 1 person and 1 task
	@Test
	public void test1() {
		Person p = new Person("Person A");
		ArrayList<Person> personList = new ArrayList<>();
		personList.add(p);
		Task t = new Task("Task A", 7, "Java");
		ArrayList<Task> taskList = new ArrayList<>();
		taskList.add(t);

		Scheduler s = new Scheduler(taskList, personList);
		s.generateSchedule();
		ArrayList<Assignment> schedule = s.getSchedule();
	}
}
