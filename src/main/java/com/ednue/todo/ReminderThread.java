package com.ednue.todo;

public class ReminderThread extends Thread {
    private TaskManager taskManager;
    public ReminderThread(TaskManager taskManager){
        this.taskManager=taskManager;
    }
    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(60000);
                System.out.println("Reminder:Check your pending tasks!");
                taskManager.displayTasks();
            }
            catch (InterruptedException e){
                System.out.println("Reminder thread interrupted");
                break;
            }
        }
    }
}
