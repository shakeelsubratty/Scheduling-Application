package main.java.classes.scheduler.model;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.io.Serializable;

/**
* @author Mateusz Przewlocki
*/
public class Assignment implements Serializable {

  private Task task;
  private ArrayList<Person> people;
  private double real_time; // A prediction of the real time that it will take to complete the task
  private double start_time;
  private double end_time;

  /**
  * Construct an Assignment that has a Task and an ArrayList of Person objects
  */
  public Assignment(Task task, ArrayList<Person> people){
    this.task = task;
    this.people = people;
  }

  // Standard getters and setters
  public Task getTask(){
    return task;
  }

  public ArrayList<Person> getPeople(){
    return people;
  }
  
  public double getRealTime()
  {
	  return real_time;
  }

  public void setTask(Task new_task){
    task = new_task;
    calculateRealTime();
  }

  public void setPeople(ArrayList<Person> new_people){
    people = new_people;
    calculateRealTime(); // Recalculate
  }
  
  public void addPerson(Person person)
  {
	  people.add(person);
	  calculateRealTime();
  }
  
  public void setStartTime(double start_time)
  {
	  this.start_time = start_time;
  }
  
  public void calculateEndTime()
  {
	  this.end_time = start_time + real_time;
  }
  
  public double getStartTime()
  {
	  return start_time;
  }
  
  public double getEndTime()
  {
	  return end_time;
  }

  /**
  * Function to predict how much time it would actually take to perform the Task this Assignment holds.
  */
  public void calculateRealTime(){
    if(people.size() == 0){ throw new IllegalArgumentException("An assignment must have at least one person working on a task."); }
    real_time = ((double)task.getEffortEstimate() / (Math.log10(people.size()) + 1));
  }

@Override
public String toString() {
	return "Assignment [task=" + task.getName() + ", people=" + people + ", real_time="
			+ real_time + ", start_time=" + start_time + ", end_time="
			+ end_time + "]";
}
  
  
}
