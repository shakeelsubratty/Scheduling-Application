package main.java.classes.scheduler.model;

import java.util.ArrayList;
import java.io.Serializable;

public class Person implements Serializable {
  private String name;
  // A list of skills that this person has
  private ArrayList<String> skillset;

  /**
   * Constructor that initialises the person without any current skills
   */
  public Person(String name) {
    this.name = name;
    skillset = new ArrayList<>();

    skillset.add("");
  }

  /**
   * Constructor that initialises the person with a list of existing skills
   */
  public Person(String name, ArrayList<String> skillset) {
    this.name = name;
    this.skillset = skillset;
    
    skillset.add("");
  }

  public String getName() {
    return name;
  }

  public void addSkill(String skill) {
    skillset.add(skill);
  }

  public ArrayList<String> getSkillset() {
    return skillset;
  }

  public boolean hasSkill(String skill) {
    return skillset.contains(skill);
  }

  /**
  * Function to get an ArrayList of Strings given a String.
  * To be used to get the skills from a String
  */
  public static String[] parseSkills(String skills){
    String[] skills_array = skills.split(",");

    for(String s : skills_array){
      s = s.trim();
    }

    return skills_array;
  }

  

  public String toString(){
    return name + " Skillset: " + skillset;

  }
}
