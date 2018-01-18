package test.java;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.java.classes.scheduler.model.Task;

public class TaskUnitTest {
  @Test
  public void basicTaskTest() {
  	Task t = new Task("Task A", 3, "Java");
  	assertEquals("Task A", t.getName());
  	assertEquals(3, t.getEffortEstimate());
  	assertEquals("Java", t.getSkillRequirements());
  }
}
