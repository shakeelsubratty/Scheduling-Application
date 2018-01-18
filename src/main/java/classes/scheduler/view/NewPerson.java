package main.java.classes.scheduler.view;

import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import main.java.classes.scheduler.controller.Controller;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class NewPerson extends Scene {
	
	private BorderPane root;
	private MainWindow mainWindow;
	private Button btnSubmit;
	private Button btnCancel;
	private Button btnAddSkill;
	private Label lbSkills;
	private Label lbName;
	private TextField tfName;
	private GridPane gpBottomLayout;
	private GridPane gpTop;
	private GridPane gpCentre;
	private TextField tfSkillList;
	private ScrollPane spSkillList;
	private ListView<String> lvSkillSet;
	private Controller controller;
	private ArrayList<String> skillList;
	private Alert warning;

	/**
	 * his is for constructing a NewPerson Window
	 * @param root - The root layout of this scene
	 * @param window - The Mainwindow GUI
	 * @param controller - The main controller
	 */
	public NewPerson(Parent root, MainWindow window,Controller controller) {
		super(root, 200, 400);
		this.root = (BorderPane) root;
		this.controller = controller;
		mainWindow = window;
		initScene();
		actionEvents();
	}
	
	/**
	 * This initialises the NewPerson Window and necessary widgets
	 */
	public void initScene() {
		
		//Buttons and their controllers
		btnSubmit = new Button("Submit");
		btnSubmit.setId("SubmitButton");
		btnCancel = new Button("Cancel");
		btnCancel.setId("CancelButton");
		btnSubmit.setOnAction(controller::handlePersonSubmit);
		btnCancel.setOnAction(controller::handlePersonCancel);
		btnAddSkill = new Button("Add Skill");
		btnAddSkill.setId("AddSkillButton");
		
		//Bottom layout
		gpBottomLayout = new GridPane();
		gpBottomLayout.add(btnSubmit, 0, 0);
		gpBottomLayout.add(btnCancel, 1, 0);
		root.setBottom(gpBottomLayout);
		
		//Labels and test fields
		lbSkills = new Label("Please enter skills");
		lbName = new Label("Name");
		tfName = new TextField();
		tfName.setId("NameTextField");
		tfSkillList = new TextField();
		tfSkillList.setId("SkillListTextField");
		
		
		//Top layout
		gpTop = new GridPane();
		gpTop.add(lbName, 0, 0);
		gpTop.add(tfName, 0, 1);
		root.setTop(gpTop);
		
		//skill selection
		lvSkillSet = new ListView<String>(); 
		lvSkillSet.setId("SkillSetListView");
		skillList = new ArrayList<String>();
		
		
		//centre layout
		gpCentre = new GridPane();
		gpCentre.add(lbSkills, 0, 0);
		gpCentre.add(tfSkillList, 0, 1);
		gpCentre.add(btnAddSkill, 0, 2);
		gpCentre.add(lvSkillSet, 0, 3);
		root.setCenter(gpCentre);
		
		
	
	}
	
	/**
	 * This is the action lsisteners for the NewPerson widgets
	 */
	private void actionEvents() {
		btnAddSkill.setOnAction(e -> {skillList.add(tfSkillList.getText());
		tfSkillList.clear();
		lvSkillSet.getItems().clear();
		lvSkillSet.getItems().addAll(skillList);
		});

	}
	/**
	 * This displays a warning for any invalid data inputs
	 */
	public void dataValidationWarning() {
		warning= new Alert(AlertType.ERROR);
		warning.setTitle("Error");
		warning.setContentText("Please ensure a name and at least one skill is entered");
		warning.show();
	}
	/**
	 * This is for access to the skills list
	 * @return The skill arraylist
	 */
	public ArrayList getSkills() {
		return skillList;
	}
	/**
	 * This is for access to the skills view
	 * @return The listview of skills
	 */
	public ListView<String> getSkillView(){
		return lvSkillSet;
	}
	/**
	 * This is for access to the name input
	 * @return The name text field
	 */ 
	public TextField getNameField() {
		return tfName;
	}
	/**
	 * This is for access to the the skills input
	 * @return the skills text field
	 */
	public TextField getSkillsField() {
		return tfSkillList;
	}
	/**
	 * This is for clearing all of the Input fields
	 */
	public void clearInputFields() {
		tfName.clear();
		tfSkillList.clear();
		lvSkillSet.getItems().clear();
		skillList.clear();
	}
	
	
	
	
}