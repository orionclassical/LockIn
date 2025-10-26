public class Task {
    private int taskId;
    private String taskTitle;
    private String taskCategory;
    private String status;

    public Task(String taskTitle, String taskCategory) {
        this.taskTitle = taskTitle;
        this.taskCategory = taskCategory;
    }

    public Task(String taskTitle, String taskCategory, String status) {
        this.taskTitle = taskTitle;
        this.taskCategory = taskCategory;
        this.status = status;
    }

    public int getTaskId() { return taskId; }
    public String getTaskTitle() { return taskTitle; }
    public String getTaskCategory() { return taskCategory; }
    public String getStatus() { return status; }

    public void setTaskTitle(String taskTitle) { this.taskTitle = taskTitle; }
    public void setTaskCategory(String taskCategory) { this.taskCategory = taskCategory; }
    public void setStatus(String status) { this.status = status; }
}
