package main.java.window.utils;

import main.java.window.database.DatabaseConnection;
import main.java.window.models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public List<Task> getTodayTasks(int userId) {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE user_id = ? AND DATE(date_created) = CURDATE()";

        try (Connection conn = DatabaseConnection.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Task(
                        rs.getInt("task_id"),
                        rs.getString("task_title"),
                        rs.getString("task_category"),
                        rs.getString("task_status"),
                        rs.getString("task_notification"),
                        rs.getString("date_created")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Task> getTasksByDate(int userId, LocalDate date) {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT task_id, task_title, task_category, task_status, task_notification, DATE(date_created) AS task_date " +
                    "FROM tasks WHERE user_id = ? AND DATE(date_created) = ? ORDER BY date_created DESC";

        try (Connection connect = DatabaseConnection.connectDB();
            PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setDate(2, java.sql.Date.valueOf(date)); // important for DATE comparison
            var rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task(
                    rs.getInt("task_id"),
                    rs.getString("task_title"),
                    rs.getString("task_category"),
                    rs.getString("task_status"),
                    rs.getString("task_notification"),
                    rs.getString("task_date")
                );
                list.add(task);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Task> getPendingTasks(int userId) {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE user_id = ? AND task_status = 'Pending' ORDER BY date_created DESC";

        try (Connection conn = DatabaseConnection.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Task(
                    rs.getInt("task_id"),
                    rs.getString("task_title"),
                    rs.getString("task_category"),
                    rs.getString("task_status"),
                    rs.getString("task_notification"),
                    rs.getString("date_created")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
