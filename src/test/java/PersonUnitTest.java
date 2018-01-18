package test.java;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.java.classes.scheduler.model.Person;
import java.util.ArrayList;

public class PersonUnitTest {
  @Test
  public void checkPersonCreation() {
  	Person p = new Person("Person A");
    ArrayList<String> skills = p.getSkillset();
    assertEquals(1,skills.size());
  }

  @Test
  public void checkAddSkill() {
    Person p = new Person("Person A");
    p.addSkill("SomeSkill");
    boolean result = p.hasSkill("SomeSkill");
    assertEquals(true,result);
  }
}
