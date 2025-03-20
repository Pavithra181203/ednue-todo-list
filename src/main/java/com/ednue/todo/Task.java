package com.ednue.todo;
import java.io.Serializable;
public class Task implements Comparable<Task>, Serializable {
    int taskID;
    String title, description, category;
    int priority;
    String deadline;
    boolean completed;

    public Task(int taskID, String title, String description, int priority, String deadline, String category) {
        this.taskID = taskID;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.category = category;
        this.completed = false;
    }
    @Override
    public int compareTo(Task t) {
        return Integer.compare(t.priority, this.priority);
    }

    @Override
    public String toString() {
        return "[" + (completed ? "✔" : "✘") + "] " + title + " (ID: " + taskID + ", Priority: " + priority + ", Due: " + deadline + ")";
    }
}
