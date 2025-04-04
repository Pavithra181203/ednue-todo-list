package com.ednue.todo;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskManagerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        taskManager.loadTasksFromFile();

        ReminderThread reminderThread = new ReminderThread(taskManager);
        reminderThread.setDaemon(true); // Ensures it stops when the main program exits
        reminderThread.start();

        while (true) {
            System.out.println("\n1. Add Task  2. Edit Task  3. Remove Task  4. Mark Completed  5. Sort Tasks  6. Display Tasks  7. Save & Exit");
            System.out.print("Enter the option you want to do: ");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Description: ");
                    String description = sc.nextLine();
                    System.out.print("Priority (1-5): ");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Deadline (YYYY-MM-DD): ");
                    String deadline = sc.nextLine();
                    System.out.print("Category: ");
                    String category = sc.nextLine();
                    taskManager.addTask(new Task(id, title, description, priority, deadline, category));
                    break;
                case 2:
                    System.out.print("Enter Task ID to edit: ");
                    int editID = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Title: ");
                    String newTitle = sc.nextLine();
                    System.out.print("New Description: ");
                    String newDesc = sc.nextLine();
                    System.out.print("New Priority: ");
                    int newPriority = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Deadline: ");
                    String newDeadline = sc.nextLine();
                    taskManager.editTask(editID, newTitle, newDesc, newPriority, newDeadline);
                    break;
                case 3:
                    System.out.print("Enter Task ID to remove: ");
                    int removeID = sc.nextInt();
                    sc.nextLine();
                    taskManager.removeTask(removeID);
                    break;
                case 4:
                    System.out.print("Enter Task ID to mark as completed: ");
                    int completeID = sc.nextInt();
                    sc.nextLine();
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
