package test.java;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItemInArray;


;

import org.junit.Test;
import org.hamcrest.Matcher;
import org.junit.Before;
import com.athaydes.automaton.FXer;


import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import com.athaydes.automaton.FXApp;

import main.java.classes.scheduler.view.*;
import main.java.classes.scheduler.model.*;

public class GuiTester {

  FXer fxer;
  Button btnSubmit;
  ListView peopleLvSkillSet;
  ListView mainWindowLvPeople;
  ListView mainWindowLvTask;
  ListView mainWindowLvAssignments;

  @Before
  public void setUp() throws Exception{
    FXApp.startApp(new MainWindow());
    Thread.sleep( 500 );
    fxer = FXer.getUserWith( FXApp.getScene().getRoot() );
  //  fxerPerson.getUserWith( FXApp. );
    fxer.pause(2000);

  }

  @Test
  public void addPeopleTest(){

	fxer.pause(2000)
	   	.clickOn("#NewPersonButton")
        .pause(1000)
        .clickOn("#NameTextField")
        .type("Seb")
        .clickOn("#SkillListTextField")
        .type("Java")
        .clickOn("#AddSkillButton")
        .pause(1000);

    peopleLvSkillSet = (ListView) fxer.getAt("#SkillSetListView");
    //Checks the correct skill is input
    assertThat(peopleLvSkillSet.getItems().get(0), equalTo("Java"));

      fxer.clickOn("#SubmitButton")
          .pause(1000);
    //Checks one person in list name is correct
      mainWindowLvPeople = (ListView) fxer.getAt("#PeopleListView");
      assertThat(((Person) (mainWindowLvPeople.getItems().get(0))).getName(), equalTo("Seb"));

      fxer.clickOn("#NewPersonButton")
          .pause(1000)
          .clickOn("#NameTextField")
          .type("Stefan")
          .clickOn("#SkillListTextField")
          .type("cpp")
          .clickOn("#AddSkillButton")
          .pause(1000);


       assertThat(peopleLvSkillSet.getItems().get(0), equalTo("cpp"));

       fxer.clickOn("#SubmitButton")
           .pause(1000);


       assertThat(((Person) (mainWindowLvPeople.getItems().get(1))).getName(), equalTo("Stefan"));

     }

  @Test
  public void addTasksTest(){
    fxer.pause(2000)
        .clickOn("#NewTaskButton")
        .pause(1000)
        .clickOn("#NameTextField")
        .type("TaskOne")
        .clickOn("#DifficultyTextField")
        .type("5")
        .clickOn("#CategoryTextField")
        .type("Java")
        .clickOn("#SubmitButton")
        .pause(1000);

    mainWindowLvTask = (ListView) fxer.getAt("#TaskListView");
    assertThat(((Task) (mainWindowLvTask.getItems().get(0))).getName(), equalTo("TaskOne"));
    assertThat(((Task)(mainWindowLvTask.getItems().get(0))).getSkillRequirements(), equalTo("Java"));

}
  @Test
  public void getAnAssignment(){
    fxer.pause(2000)
        .clickOn("#NewTaskButton")
        .pause(1000)
        .clickOn("#NameTextField")
        .type("TaskOne")
        .clickOn("#DifficultyTextField")
        .type("5")
        .clickOn("#CategoryTextField")
        .type("Java")
        .clickOn("#SubmitButton")
        .pause(1000)
      	.clickOn("#NewPersonButton")
        .pause(1000)
        .clickOn("#NameTextField")
        .type("Seb")
        .clickOn("#SkillListTextField")
        .type("Java")
        .clickOn("#AddSkillButton")
        .pause(500)
    		.clickOn("#SubmitButton")
    		.pause(500)
    		.clickOn("AssignButton")
    		.pause(500);



    mainWindowLvAssignments = (ListView) fxer.getAt("#AssignmentListView");
    assertThat(((Task)(((Assignment)(mainWindowLvAssignments.getItems().get(0))).getTask())).getName(), equalTo("TaskOne"));


  }


}
