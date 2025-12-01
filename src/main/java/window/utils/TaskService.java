package main.java.window.utils;

import main.java.window.database.DatabaseConnection;
import main.java.window.models.LoggedInUser;
import main.java.window.models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private final TaskRepository repo = new TaskRepository();

    public void markTaskAsDone(Task task) { 
        task.setStatus("Done");
        updateTaskStatus(task, "Done");
    }
    public void markTaskAsSkipped(Task task) { 
        task.setStatus("Skipped"); 
        updateTaskStatus(task, "Skipped");
    }
    public void undoTaskStatus(Task task) { 
        task.setStatus("Pending");
        updateTaskStatus(task, "Pending");
    }

    public List<Task> getTodayTasks(int userId) {
        return repo.getTodayTasks(userId);
    }

    public List<Task> getTasksByDate(int userId, LocalDate date) {
        return repo.getTasksByDate(userId, date);
    }

    public List<Task> getPendingTasks(int userId) {
        return repo.getPendingTasks(userId);
    }

    public void updateTaskStatus(Task task, String status) {
        String sql = "UPDATE tasks SET task_status = ?, date_updated = NOW() WHERE task_id = ? AND user_id = ?";

            try (Connection connect = DatabaseConnection.connectDB();
                PreparedStatement prepare = connect.prepareStatement(sql)) {

                prepare.setString(1, status);
                prepare.setInt(2, task.getTaskId()); 
                prepare.setInt(3, LoggedInUser.getUserId());
                prepare.executeUpdate();

                task.setStatus(status);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}

