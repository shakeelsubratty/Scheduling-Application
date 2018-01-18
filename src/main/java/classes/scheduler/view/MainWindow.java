package main.java.classes.scheduler.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.classes.scheduler.model.Person;
import main.java.classes.scheduler.model.Task;
import main.java.classes.scheduler.model.Assignment;
import main.java.classes.scheduler.controller.Controller;
import main.java.classes.scheduler.model.Model;

public class MainWindow extends Application{
	
	private Stage stage;
	private Scene startScene;
	private Controller controller;
	private Model model;
	private GridPane gridPane;
	private ListView<Person> peopleListView;
	private ListView<Task> taskListView;
	private ListView<Assignment> assignmentListView;
	private FlowPane leftpane;
	private FlowPane rightpane;
	private BorderPane root;
	private BorderPane root2;
	private BorderPane root3;
	private Scene addTask;
	private Scene addPerson;
	private Button btnNewTask;
	private Button btnAssign;
	private Button btnNewPerson;
	
	
	/**
	 * This is the main method that launches the scheduler application
	 * @param args
	 */
	 public static void main(String[] args) {
	        launch(args);
	        
	    }

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		model = new Model();
		controller  = new Controller(model, this);
		
		//The three scene layouts
		root = new BorderPane();
		root2 = new BorderPane();
		root3 = new BorderPane();
		
		stage = primaryStage;
		
		initWindow();
		
		stage.setTitle("Scheduler");
		
		stage.show();
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
			public void handle(WindowEvent we){
				controller.serializeModel();
			}
		});
	}
	
	/**
	 * This initialises all the widgets and builds the main window of the GUI
	 */
	public void initWindow() {
		
		//Setting the start scene
		startScene = new Scene(root, 800, 600);
		stage.setScene(startScene);
		
		addTask = new NewTask(root2, this, controller, model);
		addPerson = new NewPerson(root3, this, controller);
		
	
		controller.setPerson(addPerson);
		controller.setTask(addTask);
		
		leftpane = new FlowPane(Orientation.VERTICAL);
		rightpane = new FlowPane(Orientation.VERTICAL);
		
		//All buttons and their controllers
		btnNewTask = new Button("Add New Task");
		btnNewTask.setOnAction(e -> stage.setScene(addTask));
		btnNewTask.setId("NewTaskButton");
		btnNewPerson = new Button("Add New Person");
		btnNewPerson.setOnAction(e -> stage.setScene(addPerson));
		btnNewPerson.setId("NewPersonButton");
		btnAssign = new Button("Assign");
		btnAssign.setOnAction(controller::handleAssignButton);
		btnAssign.setId("AssignButton");
		
		//Add the buttons to window
		leftpane.getChildren().add(btnNewTask);
		leftpane.getChildren().add(btnAssign);
		rightpane.getChildren().add(btnNewPerson);

		//The central gridpane with the three viewing lists
        gridPane = new GridPane();
        peopleListView = new ListView<>(model.getPersonObservableList());
        peopleListView.setId("PeopleListView");
        taskListView = new ListView<>(model.getTasksObservableList());
        taskListView.setId("TaskListView");
        assignmentListView = new ListView<>(model.getAssignmentObservableList());
        assignmentListView.setId("AssignmentListView");
        
		peopleListView.setOnMouseClicked(controller::handleDeletePerson);
		taskListView.setOnMouseClicked(controller::handleDeleteTask);
        
        peopleListView.setPrefWidth(600); 
        taskListView.setPrefWidth(600); 
        assignmentListView.setPrefWidth(600); 
       
        gridPane.add(taskListView, 0  ,0);
        gridPane.add(assignmentListView, 0, 2);
        gridPane.add(peopleListView,0,1);
		
        
		root.setLeft(leftpane);
		root.setRight(rightpane);
		root.setCenter(gridPane);
		
		controller.deserializeModel();

	}
	/**
	 * This is for setting the current scene on the stage
	 * @param scene - The scene that is to be set
	 */
	public void switchScene(Scene scene) {
		stage.setScene(scene);
	}
	
	/**
	 * This is to allow access to the start Scene
	 * @return Returns the start scene
	 */
	public Scene getScene() {
		return startScene;
	}

}
