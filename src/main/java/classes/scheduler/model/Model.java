package main.java.classes.scheduler.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;

public class Model implements Serializable {
	
	public ArrayList<Task> tasksArrayList;
	public ArrayList<Person> personArrayList;
	public ArrayList<Assignment> assignmentsArrayList;
	public ObservableList<Task> tasksObservableList;
	public ObservableList<Person> personObservableList;
	public ObservableList<Assignment> assignmentsObservableList;
	
	public static final String TASKS_FILE_PATH = "./tasks.ser";
	public static final String PEOPLE_FILE_PATH = "./people.ser";
	public static final String ASSIGNMENTS_FILE_PATH = "./assignments.ser";

	/**
	 * This constructs a new model for the scheduler application
	 */
	public Model() {

		tasksArrayList = new ArrayList<>();
		personArrayList = new ArrayList<>();
		assignmentsArrayList = new ArrayList<>();

		tasksObservableList =  FXCollections.observableArrayList();
		personObservableList = FXCollections.observableArrayList();
		assignmentsObservableList = FXCollections.observableArrayList();
	}
	
	/**
	 * This creates and adds a new task to the Take list and observable list
	 * @param name - The name of the new task
	 * @param cat - The category of the new task
	 * @param diff - The difficulty of the new task
	 * @param prerequisites - The arraylist of prerequisites for the new task
	 */
	public void addNewTask(String name, String cat, int diff, ArrayList<Task> prerequisites) {
		Task newTask = new Task(name,diff,cat,new ArrayList<Task>(prerequisites));
		tasksArrayList.add(newTask);
		tasksObservableList.addAll(newTask);
	}

	/**
	 * This creates and adds a new Person to the list and observable lists
	 * @param name - The name of the person
	 * @param skillSet - The arraylist of skills the person will have
	 */
	public void addNewPerson(String name, ArrayList<String> skillSet)
	{
		Person newPerson = new Person(name,new ArrayList<String>(skillSet));
		personArrayList.add(newPerson);
		personObservableList.add(newPerson);
		
//		for( Person p: personObservableList) {
//			System.out.println(p);
//		}
	}

	/**
	 * This craetes a new scheduler, schedules the tasks and stores the schedule in the scheduler arrayList
	 */
	public void assign() {

		Scheduler scheduler = new Scheduler(tasksArrayList, personArrayList);
		scheduler.generateSchedule();
		assignmentsArrayList = scheduler.getSchedule();
		assignmentsObservableList.addAll(assignmentsArrayList);

		
	}
	/**
	 * This deletes a given Person from the Person array list and observable list
	 * @param person - The person to be deleted from the list
	 */
	public void removeFromPersonLists(Person person) {
		personArrayList.remove(person);
		personObservableList.remove(person);


	}
	/**
	 * This deletes a given Task from the Person array list and observable list
	 * @param task - The person to be deleted from the list
	 */
	public void removeFromTaskLists(Task task) {
		tasksArrayList.remove(task);
		tasksObservableList.remove(task);

	

	}

	public ObservableList<Task> getTasksObservableList()
	{
		return tasksObservableList;
	}

	public ObservableList<Person> getPersonObservableList()
	{
		return personObservableList;
	}
	

	public ObservableList<Assignment> getAssignmentObservableList()
	{
		return assignmentsObservableList;
	}
	
	public void serializeModel(){
		try {
			FileOutputStream fileOut = new FileOutputStream(TASKS_FILE_PATH);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(tasksArrayList);
			out.close();
			fileOut.close();
			System.out.println("Task list serialized into: " + TASKS_FILE_PATH);
			
			fileOut = new FileOutputStream(PEOPLE_FILE_PATH);
			out = new ObjectOutputStream(fileOut);
			
			out.writeObject(personArrayList);
			out.close();
			fileOut.close();
			System.out.println("Task list serialized into: " + PEOPLE_FILE_PATH);
			
			fileOut = new FileOutputStream(ASSIGNMENTS_FILE_PATH);
			out = new ObjectOutputStream(fileOut);
			
			out.writeObject(assignmentsArrayList);
			out.close();
			fileOut.close();
			System.out.println("Task list serialized into: " + ASSIGNMENTS_FILE_PATH);
			
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public void loadLists(ArrayList<Task> tasks, ArrayList<Person> people, ArrayList<Assignment> assignments){
		this.tasksArrayList.addAll(tasks);
		this.personArrayList.addAll(people);
		this.assignmentsArrayList.addAll(assignments);
		this.tasksObservableList.addAll(tasks);
		this.personObservableList.addAll(people);
		this.assignmentsObservableList.addAll(assignments);
	}
	
	public void deserializeModel(){
		ArrayList<Task> deserializedTasks = null;
		ArrayList<Person> deserializedPeople = null;
		ArrayList<Assignment> deserializedAssignments = null;
		
		try {
			FileInputStream fileIn = new FileInputStream(TASKS_FILE_PATH);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			deserializedTasks = (ArrayList<Task>) in.readObject();
			in.close();
			fileIn.close();
			
			fileIn = new FileInputStream(PEOPLE_FILE_PATH);
			in = new ObjectInputStream(fileIn);
			
			deserializedPeople = (ArrayList<Person>) in.readObject();
			in.close();
			fileIn.close();
			
			fileIn = new FileInputStream(ASSIGNMENTS_FILE_PATH);
			in = new ObjectInputStream(fileIn);
			
			deserializedAssignments = (ArrayList<Assignment>) in.readObject();
			in.close();
			fileIn.close();
			
		} catch (IOException i) {
			System.out.println("Could not load Model.");
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
		
		loadLists(deserializedTasks, deserializedPeople, deserializedAssignments);
	}

}
