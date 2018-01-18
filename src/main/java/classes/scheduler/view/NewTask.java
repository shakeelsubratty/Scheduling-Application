package main.java.classes.scheduler.view;


import javafx.event.EventHandler;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import main.java.classes.scheduler.controller.Controller;
import main.java.classes.scheduler.model.Task;
import main.java.classes.scheduler.model.Model;

import java.util.ArrayList;

public class NewTask extends Scene {
	
	private BorderPane root;
	private MainWindow mainWindow;
	private Button btnSubmit;
	private Button btnCancel;
	private TextField tfDifficulty;
	private TextField tfTaskName;
	private TextField tfCategory;
	private Label lbDifficulty;
	private Label lbTaskName;
	private Label lbPrerequisites;
	private Label lbCategory;
	private GridPane gpBottomLayout;
	private GridPane gpTopLayout;
	private GridPane gpMiddleLayout;
	private ComboBox cbPrerequisiteList;
	private ListView<Task> lvPrerequisiteList;
	private Controller controller;
	private ArrayList<Task> prereqTasks;
	private Model model;
	private Alert warning; 
	
	/**
	 * This constructs a new Scene for adding Tasks
	 * @param root - This is the root layout of the scene
	 * @param window - This is the MainWindow GUI
	 * @param controller -This is the main controller
	 * @param model - This is the Model of the application
	 */
	public NewTask(Parent root, MainWindow window, Controller controller, Model model) {
		super(root, 200, 400);
		this.root = (BorderPane) root;
		this.controller = controller;
		mainWindow = window;
		this.model = model;
		initScene();
		listActionEvents();
		
	}
	/**
	 * This is to initialise the NewTask Window and it's widgets
	 */
	public void initScene() {
		
		//Buttons and their controllers
		btnSubmit = new Button("Submit");
		btnCancel = new Button("Cancel");	
		btnSubmit.setOnAction(controller::handleTaskSubmit);
		btnCancel.setOnAction(controller::handleTaskCancel);
		btnSubmit.setId("SubmitButton");
		btnCancel.setId("CancelButton");
		
		//The bottom layout
		gpBottomLayout = new GridPane();
		gpBottomLayout.add(btnSubmit, 0, 0);
		gpBottomLayout.add(btnCancel, 1, 0);
		root.setBottom(gpBottomLayout);
		
		//Labels
		lbDifficulty = new Label("Difficulty");
		lbTaskName = new Label("Task Name");
		lbCategory = new Label("Category");
		//Input fields
		tfDifficulty = new TextField();
		tfDifficulty.setId("DifficultyTextField");
		tfTaskName = new TextField();
		tfTaskName.setId("NameTextField");
		tfCategory = new TextField();
		tfCategory.setId("CategoryTextField");
		
		//The top layout
		gpTopLayout = new GridPane();
		gpTopLayout.add(lbDifficulty, 0, 2);
		gpTopLayout.add(lbTaskName, 0, 0);
		gpTopLayout.add(tfDifficulty, 0, 3);
		gpTopLayout.add(tfTaskName, 0, 1);
		gpTopLayout.add(lbCategory, 0, 4);
		gpTopLayout.add(tfCategory, 0, 5);
		root.setTop(gpTopLayout);
		
		//The prerequisite combobox and lsits
		lbPrerequisites = new Label("Choose Prerequisite Tasks");
		cbPrerequisiteList = new ComboBox(model.getTasksObservableList());
		cbPrerequisiteList.setId("PrerequisiteComboBox");
		lvPrerequisiteList = new ListView<Task>();
		lvPrerequisiteList.setId("PrerequisiteList");
		prereqTasks = new ArrayList<>();

		//The middle layout
		gpMiddleLayout = new GridPane();
		gpMiddleLayout.add(lbPrerequisites, 0, 0);
		gpMiddleLayout.add(cbPrerequisiteList, 0, 1);
		gpMiddleLayout.add(lvPrerequisiteList, 0, 2);
		root.setCenter(gpMiddleLayout);
		
	}
	
	/**
	 * This handles the Events attached to the Combobox and Listview
	 */
	private void listActionEvents() {
		
		//The combobox event
		cbPrerequisiteList.setOnAction(e -> {
			
			if(!(prereqTasks.contains(((Task)cbPrerequisiteList.getValue())))) {
				
			
            prereqTasks.add((Task)cbPrerequisiteList.getValue());
            lvPrerequisiteList.getItems().clear();
            lvPrerequisiteList.getItems().addAll(prereqTasks);
			}
		});

		//The remove from listview on double mouse click
		lvPrerequisiteList.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		          
		            		prereqTasks.remove(((Task)lvPrerequisiteList.getSelectionModel().getSelectedItem()));
		            		lvPrerequisiteList.getItems().clear();
		            		lvPrerequisiteList.getItems().addAll(prereqTasks);
		            }
		        }
		    }
		});
	}
	
	/**
	 * This displays a warning for any invalid data inputs
	 */
	public void dataValidationWarning() {
		warning = new Alert(AlertType.ERROR);
		warning.setTitle("Error");
		warning.setContentText("Please ensure fields are complete and the difficulty is a value");
		warning.show();
	}

	/**
	 * This is for access to the prerequisite task arraylist
	 * @return The list of prerequisites
	 */
	public ArrayList<Task> getPrereqTasks() {return prereqTasks;}
	
	/**
	 * This is for access to the Difficulty text field
	 * @return The text field for difficulty
	 */
	public TextField getDiff() {
		return tfDifficulty;
	}
	/**
	 * This is for access to the prerequisite list view
	 * @return the ListView for prerequisites
	 */
	public ListView<Task> getPre() {
		return lvPrerequisiteList;
	}
	/**
	 * This is for access to the category input
	 * @return the text field for category
	 */ 
	public TextField getCat() {
		return tfCategory;
	}
	/**
	 * This is for access to the name input
	 * @return The name text field
	 */
	public TextField getName() {
		return tfTaskName;
	}

	
	/**
	 * This is for clearing all of the Input fields
	 */
	public void clearInputFields() {
		tfTaskName.clear();
		tfCategory.clear();
		lvPrerequisiteList.getItems().clear();
		tfDifficulty.clear();
		prereqTasks.clear();
	}
}
