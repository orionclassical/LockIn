package main.java.window.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.java.window.database.DatabaseConnection;
import main.java.window.models.LoggedInUser;

public class Load {

    Connection connect = DatabaseConnection.connectDB();
    PreparedStatement prepare;

    public int loadStatisticsTimesCompleted() {
        String sql = "SELECT COUNT(*) AS doneCount FROM tasks WHERE user_id = ? AND task_status = 'Done' AND date_updated IS NOT NULL";

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, LoggedInUser.getUserId());
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                return result.getInt("doneCount");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double loadStatisticsCompletionRate() {
        String sql = "SELECT (SUM(CASE WHEN task_status = 'Done' THEN 1 ELSE 0 END) * 100.0 / COUNT(*)) AS completionRate " +
                     "FROM tasks WHERE user_id = ? AND date_updated IS NOT NULL";

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, LoggedInUser.getUserId());
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                return result.getDouble("completionRate");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double loadStatisticsAverageDaily() {
        String sql = "SELECT AVG(done_per_day) AS avgDaily " +
                     "FROM (SELECT DATE(date_updated) AS day, COUNT(*) AS done_per_day " +
                     "      FROM tasks " +
                     "      WHERE user_id = ? AND task_status = 'Done' AND date_updated IS NOT NULL " +
                     "      GROUP BY DATE(date_updated)) AS dailyStats";

        try (Connection connect = DatabaseConnection.connectDB();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setInt(1, LoggedInUser.getUserId());
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                return result.getDouble("avgDaily");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int loadTotalTasks() {
        String sql = "SELECT COUNT(*) AS count FROM tasks WHERE user_id = ? AND task_status IN ('Done', 'Pending', 'Skipped') AND date_updated IS NOT NULL";

        try (Connection connect = DatabaseConnection.connectDB();
            PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setInt(1, LoggedInUser.getUserId());
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                return result.getInt("count");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
