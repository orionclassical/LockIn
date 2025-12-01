package main.java.window.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import main.java.window.database.DatabaseConnection;
import main.java.window.models.LoggedInUser;
import main.java.window.models.Task;

public class TaskEditor {

    /**
     * Update a task in the database and restart its notification timer.
     * Works for both "today" and "statistics" tasks.
     */
    public void updateTask(Task task, String updatedTitle, String updatedCategory, String updatedNotification) {
        String sql = "UPDATE tasks SET task_title = ?, task_category = ?, task_notification = ? " +
                     "WHERE user_id = ? AND task_id = ?";

        try (Connection connect = DatabaseConnection.connectDB();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, updatedTitle);
            prepare.setString(2, updatedCategory);
            prepare.setString(3, updatedNotification);
            prepare.setInt(4, LoggedInUser.getUserId());
            prepare.setInt(5, task.getTaskId());
            prepare.executeUpdate();

            task.setTaskTitle(updatedTitle);
            task.setTaskCategory(updatedCategory);
            task.setTaskNotification(updatedNotification);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
