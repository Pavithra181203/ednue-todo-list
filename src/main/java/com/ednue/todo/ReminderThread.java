package com.ednue.todo;

public class ReminderThread extends Thread {
    private TaskManager taskManager;

    public ReminderThread(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60000); // Remind every 1 minute
                System.out.println("\n🔔 Reminder: Check your pending tasks!\n");
                taskManager.displayTasks();
            } catch (InterruptedException e) {
                System.out.println("Reminder thread interrupted.");
                break;
            }
        }
    }
}