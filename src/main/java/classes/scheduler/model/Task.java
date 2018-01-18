package main.java.classes.scheduler.model;

import java.util.ArrayList;
import java.io.Serializable;

/**
* @author Mateusz Przewlocki
*/
public class Task implements Serializable {

  private String name;
  private int effort_estimate; // duration
  private String skill_requirements; // the skill required to do this task
  private ArrayList<Task> prerequisites; // the Tasks that need to be done before this one

  /**
  * Construct a task with no prerequisites.
  * Takes: String name, int effort_estimate, String skill_requirements
  */
  public Task(String name, int effort_estimate, String skill_requirements){
    this.name = name;
    this.effort_estimate = effort_estimate;
    this.skill_requirements = skill_requirements;
    this.prerequisites = new ArrayList<Task>();
  }

  /**
  * Construct a task with prerequisites.
  * Takes: String name, int effort_estimate, String skill_requirements, ArrayList of Tasks prerequisites.
  */
  public Task(String name, int effort_estimate, String skill_requirements, ArrayList<Task> prerequisites){
    this.name = name;
    this.effort_estimate = effort_estimate;
    this.skill_requirements = skill_requirements;
    this.prerequisites = prerequisites;
  }

  // Standard getters and setters
  public String getName(){
    return name;
  }

  public int getEffortEstimate(){
    return effort_estimate;
  }

  public String getSkillRequirements(){
    return skill_requirements;
  }

  public ArrayList<Task> prerequisites(){
    return prerequisites;
  }

  public void setName(String new_name){
    name = new_name;
  }

  public void setEffortEstimate(int new_effort_estimate){
    effort_estimate = new_effort_estimate;
  }

  public void setSkillRequirements(String new_skill_requirements){
    skill_requirements = new_skill_requirements;
  }

  public void setPrerequisites(ArrayList<Task> new_prerequisites){
    prerequisites = new_prerequisites;
  }

  
  public String toString() {
	  return "Name: " + name + " Difficulty: " + effort_estimate + " Skill requirements: " + skill_requirements + " Prerequisites: " + prerequisites;
  }



}
