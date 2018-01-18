package main.java.classes.scheduler.model;

import java.util.ArrayList;

public class Scheduler 
{
	private ArrayList<Task> tasks;	//List of Tasks
	private ArrayList<Person> people;	//List of People
	private ArrayList<Assignment> schedule;	//List of Assignments
	private boolean partialAssignment;	//Flag for determining whether a full assignment was generated
	
	public Scheduler(ArrayList<Task> tasks, ArrayList<Person> people)	//Constructor
	{
		this.tasks = tasks;
		this.people = people;
	}

	public ArrayList<Task> getTasks()	//Getter for Tasks
	{
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks)	//Setter for Tasks
	{
		this.tasks = tasks;
	}

	public ArrayList<Person> getPeople()	//Getter for People
	{
		return people;
	}

	public void setPeople(ArrayList<Person> people)	//Setter for People
	{
		this.people = people;
	}
	
	public ArrayList<Assignment> getSchedule()	//Getter for Schedule
	{
		return schedule;
	}
	
	public boolean isPartialAssignment()	//Getter for Partial Assignment Flag
	{
		return partialAssignment;
	}
	


	


	public void generateSchedule()	//Method to generate a schedule from the list of tasks and people
	{

		schedule = new ArrayList<Assignment>();	//Initialise schedule to contain nothing
		
		ArrayList<Task> completed = new ArrayList<Task>();	//Create list to hold tasks that are done
		
		ArrayList<Task> working = new ArrayList<Task>();	//Create list to hold tasks that are in progress
		
		ArrayList<Task> availableTasks = findAvailableTasks(completed, working);	//Create list that contains tasks that have their prerequisites met

		ArrayList<Task> previousAvailableTasks = availableTasks;	//Create list that contains tasks that have their prerequisites met
		
		ArrayList<Person> busy = new ArrayList<Person>();	//Create list that contains people who are currently working on a task
		

		double currentTime = 0.0;	//Create double to keep track of what time it is currently
		
		boolean tasksRunning = false;	//Create boolean to determine whether there are tasks currently being worked on
		
		while (!(availableTasks.isEmpty()) || tasksRunning)	//Repeat assignment process while there are available tasks or there are tasks running (i.e. there may be available tasks in the near future)
		{
			assign(availableTasks, busy, currentTime, working);	//Assign people to all the available tasks
			
			Assignment chosen = findNextEndingTime(currentTime);	//Find the next task that ends, and move the currentTime to it; returned value is the task's assignment
			
			if (chosen != null)	//Validation
			{
				currentTime = chosen.getEndTime();
				
				completed.add(chosen.getTask());	//Add the completed task to the completed list
				working.remove(chosen.getTask());
			
				for (Person person : chosen.getPeople())	//Make people that were working on that task available again
				{
					busy.remove(person);
				}
			}

			availableTasks = findAvailableTasks(completed, working);	//Find the new available tasks after adding the completed task
			if (previousAvailableTasks.equals(availableTasks))
			{
				break;
			}
			else
			{
				previousAvailableTasks = availableTasks;
			}
			
			tasksRunning = false;
			
			for (Assignment assignment : schedule)	//Check whether there are any tasks that are still running at currentTime
			{
				if (assignment.getEndTime() > currentTime)
				{
					tasksRunning = true;
				}
			}
		}

		if (completed.size() == tasks.size())	//Check for a partial assignment once the loop assigns as many tasks as possible
		{
			partialAssignment = false;
		}
		else
		{
			partialAssignment = true;
		}
	}
	
	private Assignment findNextEndingTime(double currentTime)	//Method to find the next task that ends
	{
		double nextEndingTime = -1.0;
		Assignment chosen = null;
		for (Assignment assignment : schedule)	//Loop through the schedule and find the assignment which ends soonest after the current time
		{
			if (assignment.getEndTime() > currentTime
					&& (nextEndingTime == -1.0 || assignment.getEndTime() < nextEndingTime))
			{
				nextEndingTime = assignment.getEndTime();
				chosen = assignment;
			}
		}
		return chosen;	//Return the soonest found assignment
	}
	
	private void assign(ArrayList<Task> availableTasks, ArrayList<Person> busy, double currentTime, ArrayList<Task> working)	//Create assignments based on the available tasks, busy people, and current time
	{
		for (Task task : availableTasks)	//For each available task, create a new assignment at the current time, assign as many people as possible to it, and add it to the schedule
		{
			Assignment assignment = new Assignment(task, new ArrayList<Person>());
			assignment.setStartTime(currentTime);
			for (Person person : people)
			{
				if (person.getSkillset().contains(task.getSkillRequirements()) && !(busy.contains(person)))	//Ensure the assigned people have the correct skillset and are not busy
				{
					busy.add(person);	//Add the people assigned to this task to the busy list
					assignment.addPerson(person);
				}
			}
			
			if (assignment.getPeople().size() > 0)
			{
				assignment.calculateEndTime();
				working.add(task);
				schedule.add(assignment);
			}
		}
	}
	
	private ArrayList<Task> findAvailableTasks(ArrayList<Task> completed, ArrayList<Task> working)	//Find the tasks that have all their prerequisites met right now
	{
		ArrayList<Task> available = new ArrayList<Task>();
		
		for (Task task : tasks)
		{
			boolean satisfiedPreReqs = true;
			
			if (!(completed.contains(task)) && !(working.contains(task)))	//Make sure the task hasn't already been completed
			{
				if (task.prerequisites() != null) // Make sure the task has prerequisites before checking
					{
						for (Task preReq : task.prerequisites())
							{
							if (!(completed.contains(preReq)))	//Make sure the task has all its prerequisites completed
								{
							satisfiedPreReqs = false;
						}
					}
				}
				if (satisfiedPreReqs)	//Add the task to the list of available tasks if all above needs are met
				{
					available.add(task);
				}
			}
		}
		
		return available;
	}

	@Override
	public String toString() 
	{
		return schedule.toString();
	}
	
	
}
