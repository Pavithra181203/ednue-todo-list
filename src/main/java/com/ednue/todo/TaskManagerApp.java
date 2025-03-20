package com.ednue.todo;
import java.util.*;
import java.util.Scanner;
public class TaskManagerApp {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        TaskManager taskManager=new TaskManager();
        taskManager.loadTasksFromFile();

        ReminderThread reminderThread=new ReminderThread(taskManager);
        reminderThread.setDaemon(true);
        reminderThread.start();

        while (true){
            System.out.println("\n1. Add Task  2. Edit Task  3. Remove Task  4. Mark Completed  5. Sort Tasks  6. Display Tasks  7. Save & Exit");
            System.out.println("Enter the option you want to do: ");
            int choice;
            try{
                choice=s.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter a number.");
                s.nextLine();
                continue;
            }
            s.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Enter Task ID: ");
                    int id=s.nextInt();
                    s.nextLine();
                    System.out.print("Title: ");
                    String title = s.nextLine();
                    System.out.print("Description: ");
                    String description = s.nextLine();
                    System.out.print("Priority (1-5): ");
                    int priority = s.nextInt();
                    s.nextLine();
                    System.out.print("Deadline (YYYY-MM-DD): ");
                    String deadline = s.nextLine();
                    System.out.print("Category: ");
                    String category = s.nextLine();
                    taskManager.addTask(new Task(id,title,description,priority,deadline,category));
                    break;

                case 2:
                    System.out.print("Enter Task ID to edit: ");
                    int editID = s.nextInt();
                    s.nextLine();
                    System.out.print("New Title: ");
                    String newTitle = s.nextLine();
                    System.out.print("New Description: ");
                    String newDescription = s.nextLine();
                    System.out.print("New Priority: ");
                    int newPriority = s.nextInt();
                    s.nextLine();
                    System.out.print("New Deadline: ");
                    String newDeadline = s.nextLine();
                    taskManager.editTask(editID, newTitle, newDescription, newPriority, newDeadline);
                    break;
                case 3:
                    System.out.print("Enter Task ID to remove: ");
                    int removeID = s.nextInt();
                    s.nextLine();
                    taskManager.removeTask(removeID);
                    break;
                case 4:
                    System.out.print("Enter Task ID to mark as completed: ");
                    int completeID = s.nextInt();
                    s.nextLine();
                    taskManager.markTaskAsCompleted(completeID);
                    break;
                case 5:
                    taskManager.sortTasksByPriority();
                    break;
                case 6:
                    taskManager.displayTasks();
                    break;
                case 7:
                    taskManager.saveTasksToFile();
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
