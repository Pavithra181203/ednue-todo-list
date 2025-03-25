package com.ednue.todo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.*;
public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private LinkedList<Task> completedTasks = new LinkedList<>();
    private HashMap<Integer,Task> taskMap = new HashMap<>();
    String FILE_NAME="tasks.data";

    public void addTask(Task task){
        if (taskMap.containsKey(task.taskID)){
            System.out.println("Task ID already exists! ");
            return;
        }
        else{
            tasks.add(task);
            taskMap.put(task.taskID,task);
            System.out.println("Task added successfully!");
        }
    }
    public void editTask(int taskID,String newTitle,String newDescription,int newPriority, String newDeadline){
        Task task=taskMap.get(taskID);
        if(task!=null){
            task.title=newTitle;
            task.description=newDescription;
            task.priority=newPriority;
            task.deadline=newDeadline;
            System.out.println("Task updated successfully!");
        }
        else{
            System.out.println("Task not found!");
        }
    }
    public void removeTask(int taskID){
        Task task=taskMap.get(taskID);
        if(task!=null){
            tasks.remove(task);
            taskMap.remove(taskID);
            System.out.println("Task removed successfully!");
        }
        else{
            System.out.println("Task not found!");
        }
    }
    public void markTaskAsCompleted(int taskID){
        Task task=taskMap.get(taskID);
        if(task!=null){
            task.completed=true;
            tasks.remove(task);
            completedTasks.add(task);
            taskMap.remove(taskID);
            System.out.println("Task marked as completed!");
        }
        else{
            System.out.println("Task not found!");
        }
    }
    public void sortTasksByPriority(){
        Collections.sort(tasks);
        System.out.println("Tasks sorted by priority!");
    }
    public void saveTasksToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(tasks);
            out.writeObject(completedTasks);
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /*@SuppressWarnings("unchecked")
    public void loadTasksFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (ArrayList<Task>) in.readObject();
            completedTasks = (LinkedList<Task>) in.readObject();
            for (Task task : tasks) taskMap.put(task.taskID, task);
            for (Task task : completedTasks) taskMap.put(task.taskID, task);
            System.out.println("Tasks loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved tasks found");
        }
    }

     */
    public void displayTasks() {
        System.out.println("\n Current Task");
        if (tasks.isEmpty()) System.out.println("No pending tasks!");
        for (Task task : tasks) System.out.println(task);

        System.out.println("\n Completed Tasks ");
        if (completedTasks.isEmpty()){
            System.out.println("No completed tasks!");
        }
        for (Task task : completedTasks){
            System.out.println(task);
        }
    }
}

