package main.java.classes.scheduler.controller;

import javax.swing.JOptionPane;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import main.java.classes.scheduler.model.Model;
import main.java.classes.scheduler.model.Person;
import main.java.classes.scheduler.model.Task;
import main.java.classes.scheduler.view.MainWindow;
import main.java.classes.scheduler.view.NewPerson;
import main.java.classes.scheduler.view.NewTask;

public class Controller {

	private Model model;
	private MainWindow mainW;
	private Scene task;
	private Scene person;
	
	/**
	 * This constructs the controller for communication between the model and view 
	 * @param model This is the model of the application	
	 * @param main This is the Main view window of the application
	 */
	public Controller(Model model, MainWindow main) {
		this.model = model;
		this.mainW = main;
	}
	
	/**
	* This serializes the model to save it.
	*/
	public void serializeModel(){
		this.model.serializeModel();
	}
	
	/**
	* This deserializes the model to make it readable
	*/
	public void deserializeModel(){
		this.model.deserializeModel();
	}
	
	/**
	 * This is for setting a NewTask in the controller
	 * @param task - A new Task scene
	 */
	public void setTask(Scene task) {
		this.task = task;	
	}
	/**
	 * This is for setting a NewPerson window in the controller 
	 * @param person a NewPerson scene
	 */
	public void setPerson(Scene person) {
		this.person = person;	
		}
	/**
	 * This handles all of the events necessary when the NewTask window submit button is activated. 
	 * It activates methods in the model.
	 * @param event The action event
	 */
	public void handleTaskSubmit(Event event) {
		
		NewTask newTask = ((NewTask) task);
		
		if (!(newTask.getName().getText().equals("")) && !(newTask.getCat().getText().equals(""))){
        	
        try {
        		
        		model.addNewTask(newTask.getName().getText(),newTask.getCat().getText(),Integer.parseInt(newTask.getDiff().getText()),newTask.getPrereqTasks());
        		newTask.clearInputFields();
        		mainW.switchScene(mainW.getScene());
        		
        	} catch (NumberFormatException n) {
        		newTask.dataValidationWarning();
        	}
        }else {
        		newTask.dataValidationWarning();
        	}
	}
	
	/**
	 * This the actions to take place when the NewTask Window Cancel button is activated
	 * @param e The action 
	 */
	public void handleTaskCancel(Event e) {
		mainW.switchScene(mainW.getScene());
		((NewTask) task).clearInputFields();

	}
	/**
	 * This initiates the events to take places when the NewPerson submit button is activated
	 * @param event The action
	 */
	public void handlePersonSubmit(Event event) {
		NewPerson newPerson = ((NewPerson) person);
		if(!(newPerson.getSkillView().getItems().isEmpty()) && !(newPerson.getNameField().getText().equals(""))) {
		
		mainW.switchScene(mainW.getScene());

		System.out.println(newPerson.getSkills());
		
		model.addNewPerson(newPerson.getNameField().getText(),newPerson.getSkills());
		newPerson.clearInputFields();

		}else {
			newPerson.dataValidationWarning();
		}
		
		}
	/**
	 * This initates all actions to be called when the newPerson cancel button is activated
	 * @param e The action
	 */
	public void handlePersonCancel(Event e) {
		NewPerson newPerson = ((NewPerson) person);
		mainW.switchScene(mainW.getScene());
		newPerson.clearInputFields();
	}
	
	/**
	 * This initiates all events to be activated when the MainWindow assign button is pressed
	 * @param e
	 */
	public void handleAssignButton(Event e) {
		System.out.println("Button to contoller");
		model.assign();
		
	}
	/**
	 * This initiates events to be called when a Person in the Listview is double clicked
	 * @param mouseEvent - The mouse click action
	 */
	public void handleDeletePerson(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
            		ListView<Person> lv = (ListView<Person>) mouseEvent.getSource();
            		model.removeFromPersonLists(lv.getSelectionModel().getSelectedItem());
            }
        }
    }
	/**
	 * This initiates events to be called when a Task in the Listview is double clicked
	 * @param mouseEvent - The mouse click action
	 */
	public void handleDeleteTask(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
            		ListView<Task> lv = (ListView<Task>) mouseEvent.getSource();
            		model.removeFromTaskLists(lv.getSelectionModel().getSelectedItem());
            }
        }
    }

}
