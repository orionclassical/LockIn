package main.java.window.models;

public class Task {
    private int taskId;
    private String taskTitle;
    private String taskCategory;
    private String status;
    private String taskNotification;
    private String dateCreated;

    public Task(String title, String category, String status, String notification) {
        this.taskTitle = title;
        this.taskCategory = category;
        this.status = status;
        this.taskNotification = notification;
    }

    public Task(int taskId, String taskTitle, String taskCategory, String status, String taskNotification, String dateCreated) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskCategory = taskCategory;
        this.status = status;
        this.taskNotification = taskNotification;
        this.dateCreated = dateCreated;
    }

    public Task(String taskTitle, String taskCategory) {
        this(taskTitle, taskCategory, "Pending");
    }

    public Task(String taskTitle, String taskCategory, String status) {
        this.taskTitle = taskTitle;
        this.taskCategory = taskCategory;
        this.status = status;
    }   

    // Getters
    public int getTaskId() { return taskId; }
    public String getTaskTitle() { return taskTitle; }
    public String getTaskCategory() { return taskCategory; }
    public String getStatus() { return status; }
    public String getTaskNotification() { return taskNotification; }
    public String getDateCreated() { return dateCreated; }

    // Setters
    public void setTaskId(int taskId) { this.taskId = taskId; }
    public void setTaskTitle(String taskTitle) { this.taskTitle = taskTitle; }
    public void setTaskCategory(String taskCategory) { this.taskCategory = taskCategory; }
    public void setStatus(String status) { this.status = status; }
    public void setTaskNotification(String taskNotification) { this.taskNotification = taskNotification; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
}
