package main.java.window.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.TableRow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.window.database.DatabaseConnection;
import main.java.window.models.ChangeWindow;
import main.java.window.models.LoggedInUser;
import main.java.window.utils.Load;
import main.java.window.utils.TaskEditor;
import main.java.window.utils.TaskService;
import main.java.window.utils.Update;
import main.java.window.utils.AlertMessage;
import main.java.window.models.Task;

public class MainDocument implements Initializable{
    @FXML
    private AnchorPane edit_settings_changeEmail;
    @FXML
    private AnchorPane edit_settings_changeName;
    @FXML
    private AnchorPane edit_settings_changePassword;
    @FXML
    private AnchorPane edit_settings_default;
    @FXML
    private Button mainform_settings_changeEmail_applyChangesBtn;
    @FXML
    private Button mainform_settings_changeEmail_cancelBtn;
    @FXML
    private TextField mainform_settings_changeEmail_emailFillText;
    @FXML
    private Label statistics_specificDate;
    @FXML
    private Label mainform_settings_changeEmail_fullName;
    @FXML
    private Button mainform_settings_changeEmail_fullNameBtn;
    @FXML
    private Label mainform_settings_changeEmail_password;
    @FXML
    private Button mainform_settings_changeEmail_passwordBtn;
    @FXML
    private Button mainform_settings_changeName_applyChangesBtn;
    @FXML
    private Button mainform_settings_changeName_cancelBtn;
    @FXML
    private Label mainform_settings_changeName_email;
    @FXML
    private Button mainform_settings_changeName_emailBtn;
    @FXML
    private TextField mainform_settings_changeName_firstNameFillText;
    @FXML
    private TextField mainform_settings_changeName_lastNameFillText;
    @FXML
    private Label mainform_settings_changeName_password;
    @FXML
    private Button mainform_settings_changeName_passwordBtn;
    @FXML
    private Button mainform_settings_changePassword_applyChangesBtn;
    @FXML
    private Button mainform_settings_changePassword_cancelBtn;
    @FXML
    private PasswordField mainform_settings_changePassword_confirmPasswordFillText;
    @FXML
    private Label mainform_settings_changePassword_email;
    @FXML
    private Button mainform_settings_changePassword_emailBtn;
    @FXML
    private Label mainform_settings_changePassword_fullName;
    @FXML
    private Button mainform_settings_changePassword_fullNameBtn;
    @FXML
    private PasswordField mainform_settings_changePassword_passwordFillText;
    @FXML
    private Label mainform_settings_default_email;
    @FXML
    private Button mainform_settings_default_emailBtn;
    @FXML
    private Label mainform_settings_default_fullName;
    @FXML
    private Button mainform_settings_default_fullNameBtn;
    @FXML
    private Label mainform_settings_default_password;
    @FXML
    private Button mainform_settings_default_passwordBtn;
    @FXML
    private Button sidebar_logOut;
    @FXML
    private Button sidebar_settings;
    @FXML
    private Label sidebar_settings_default_fullName;
    @FXML
    private Button sidebar_task;
    @FXML
    private Button sidebar_today;
    @FXML
    private Button sidebar_yourStats;
    @FXML
    private Button statistics_button_left;
    @FXML
    private Button statistics_button_right;
    @FXML
    private Button statistics_goBackBtn;
    @FXML
    private GridPane statistics_calendar;
    @FXML
    private Label statistics_month;
    @FXML
    private Button today_cancelCreatingTaskBtn;
    @FXML
    private ChoiceBox<String> today_categoryChoiceBox;
    @FXML
    private AnchorPane today_createTask;
    @FXML
    private AnchorPane today_editTask;
    @FXML
    private Button today_creatingTaskBtn;
    @FXML
    private TextField today_creatingTask_nameOfTask;
    @FXML
    private ChoiceBox<String> today_creatingTask_selectCategory;
    @FXML
    private ChoiceBox<String> today_creatingTask_setNotification;
    @FXML
    private Button today_cancelEditingTaskBtn;
    @FXML
    private Button today_editingTaskBtn;
    @FXML
    private Button today_createTaskBtn;
    @FXML
    private TextField today_editingTask_nameOfTask;
    @FXML
    private ChoiceBox<String> today_editingTask_selectCategory;
    @FXML
    private ChoiceBox<String> today_editingTask_setNotification;
    @FXML
    private AnchorPane today_main;
    @FXML
    private TableColumn<Task, String> today_taskCategory;
    @FXML
    private TableColumn<Task, String> today_taskName;
    @FXML
    private TableColumn<Task, Void> today_buttons;
    @FXML
    private TableView<Task> today_taskTable;
    @FXML
    private FlowPane today_weekView;
    @FXML
    private AnchorPane window_settings;
    @FXML
    private AnchorPane window_statistics;
    @FXML
    private AnchorPane window_today;
    @FXML
    private AnchorPane today_boundaryClick;
    @FXML
    private AnchorPane today_boundaryClickEdit;
    @FXML
    private Label statistics_timesCompletedLabel;
    @FXML
    private Label statistics_averageDailyLabel;
    @FXML
    private Label statistics_completionRateLabel;
    @FXML
    private AnchorPane window_ongoing;
    @FXML
    private TableColumn<Task, String> ongoing_taskCategory;
    @FXML
    private TableColumn<Task, String> ongoing_taskName;
    @FXML
    private TableColumn<Task, String> ongoing_taskDate;
    @FXML
    private TableColumn<Task, Void> ongoing_buttons;
    @FXML
    private TableView<Task> ongoing_taskTable;
    @FXML
    private TableColumn<Task, String> statistics_taskName;
    @FXML
    private TableColumn<Task, String> statistics_taskCategory;
    @FXML
    private TableColumn<Task, Void> statistics_buttons;
    @FXML
    private TableView<Task> statistics_taskTable;
    @FXML
    private ChoiceBox<String> statistics_categoryChoiceBox;
    @FXML
    private AnchorPane statistics_dateTaskWindow;
    @FXML
    private AnchorPane statistics_dateWindow;
    @FXML
    private AnchorPane statistics_createTask;
    @FXML
    private AnchorPane statistics_boundaryClick;
    @FXML
    private TextField statistics_creatingTask_nameOfTask;
    @FXML
    private ChoiceBox<String> statistics_creatingTask_selectCategory;
    @FXML
    private ChoiceBox<String> statistics_creatingTask_setNotification;
    @FXML
    private Button statistics_createTaskBtn;
    @FXML
    private Button statistics_creatingTaskBtn;
    @FXML
    private Button statistics_cancelCreatingTaskBtn;
    @FXML
    private AnchorPane statistics_editTask;
    @FXML
    private AnchorPane statistics_boundaryClickEdit;
    @FXML
    private TextField statistics_editingTask_nameOfTask;
    @FXML
    private ChoiceBox<String> statistics_editingTask_selectCategory;
    @FXML
    private ChoiceBox<String> statistics_editingTask_setNotification;
    @FXML
    private Button statistics_editingTaskBtn;
    @FXML
    private Button statistics_cancelEditingTaskBtn;
    @FXML
    private Label statistics_totalTaskMade;

    private Connection connect = DatabaseConnection.connectDB();
    private PreparedStatement prepare;
    private ResultSet result;
    private ObservableList<Task> masterTaskList = FXCollections.observableArrayList();
    private YearMonth currentYearMonth = YearMonth.now();
    private LocalDate selectedDate;
    private Load load = new Load();
    private AlertMessage alert = new AlertMessage();
    private TaskService taskService = new TaskService();
    private final TaskEditor taskEditor = new TaskEditor();
    private Update update = new Update();
    private final List<String> categories = List.of("Personal", "School", "Event");
    private final List<String> categoriesWithAll = List.of("All", "Personal", "School", "Event");
    private final List<String> notifications = List.of("None", "5 Minutes", "30 Minutes", "1 Hour", "5 Hour", "12 Hours");

    public void notificationTimer(Task task){
        if (task == null) return;

        String notif = task.getTaskNotification();
        String title = task.getTaskTitle();
        int seconds = convertNotificationToSeconds(notif);

        if (seconds <= 0) return;

        final Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(seconds), e -> {
            if ("Pending".equals(task.getStatus())) {
                new AlertMessage().notificationAlert("Reminder: " + title);
            } else {
                timeline.stop();
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    private int convertNotificationToSeconds(String notif) {
        if (notif == null) return 0;

        switch (notif) {
            case "5 Minutes": return 5 * 60;
            case "30 Minutes": return 30 * 60;
            case "1 Hour": return 60 * 60;
            case "5 Hour": return 5 * 60 * 60;
            case "12 Hours": return 12 * 60 * 60;
            default: return 0;
        }
    }
    public void windowDisappear(javafx.scene.input.MouseEvent event){
        if(event.getSource() == today_boundaryClick){
            today_createTask.setVisible(false);
        }else if(event.getSource() == today_boundaryClickEdit){
            today_editTask.setVisible(false);
        }
    }

    public void updateName() {
        String first = mainform_settings_changeName_firstNameFillText.getText();
        String last = mainform_settings_changeName_lastNameFillText.getText();

        if (first.isEmpty() || last.isEmpty()) {
            alert.errorAlert("Please fill all fields");
            return;
        }

        Update updateService = new Update();

        if (updateService.updateName(first, last)) {
            alert.succesfulAlert("Name updated!");
            edit_settings_default.setVisible(true);
            edit_settings_changeName.setVisible(false);
            edit_settings_changeEmail.setVisible(false);
            edit_settings_changePassword.setVisible(false);
            displayUserInformation();
        }
    }

    public void updateEmail() {
        String newEmail = mainform_settings_changeEmail_emailFillText.getText();
        String userFirstName = LoggedInUser.getFirstName();
        String userLastName = LoggedInUser.getLastName();
        String currentEmail = LoggedInUser.getEmail();

        AlertMessage alert = new AlertMessage();

        if (newEmail.isEmpty()) {
            alert.errorAlert("Must fill up the field");
            return;
        }
        if (!newEmail.endsWith("@gmail.com")) {
            alert.errorAlert("Please enter a valid email address");
            return;
        }
        if (newEmail.equals(currentEmail)) {
            return;
        }

        boolean success = update.updateEmail(newEmail);

        if (success) {
            alert.succesfulAlert("Email updated successfully!");

            LoggedInUser.setUser(userFirstName, userLastName, newEmail);

            edit_settings_default.setVisible(true);
            edit_settings_changeName.setVisible(false);
            edit_settings_changeEmail.setVisible(false);
            edit_settings_changePassword.setVisible(false);

            displayUserInformation();
        }
    }

    public void updatePassword() {
        String newPassword = mainform_settings_changePassword_passwordFillText.getText();
        String newConfirmPassword = mainform_settings_changePassword_confirmPasswordFillText.getText();

        AlertMessage alert = new AlertMessage();

        if (newPassword.isEmpty() || newConfirmPassword.isEmpty()) {
            alert.errorAlert("Please fill up both password fields");
            return;
        }
        if (!newPassword.equals(newConfirmPassword)) {
            alert.errorAlert("Passwords do not match! Try again");
            return;
        }
        if (newPassword.length() < 8) {
            alert.errorAlert("Invalid Password! at least 8 characters needed");
            return;
        }

        Update update = new Update();
        boolean success = update.updatePassword(newPassword);

        if (success) {
            alert.succesfulAlert("Password updated successfully!");

            edit_settings_default.setVisible(true);
            edit_settings_changeName.setVisible(false);
            edit_settings_changeEmail.setVisible(false);
            edit_settings_changePassword.setVisible(false);

            displayUserInformation();
        }
    }

    public void switchForm(ActionEvent event){
        if(event.getSource() == mainform_settings_default_fullNameBtn || event.getSource() == mainform_settings_changeEmail_fullNameBtn || event.getSource() == mainform_settings_changePassword_fullNameBtn){
            edit_settings_default.setVisible(false);
            edit_settings_changeName.setVisible(true);
            edit_settings_changeEmail.setVisible(false);
            edit_settings_changePassword.setVisible(false);
            loadCurrentName();
        }else if (event.getSource() == mainform_settings_default_emailBtn || event.getSource() == mainform_settings_changeName_emailBtn || event.getSource() == mainform_settings_changePassword_emailBtn) {
            edit_settings_default.setVisible(false);
            edit_settings_changeName.setVisible(false);
            edit_settings_changeEmail.setVisible(true);
            edit_settings_changePassword.setVisible(false);
            loadCurrentEmail();
        }else if (event.getSource() == mainform_settings_default_passwordBtn || event.getSource() == mainform_settings_changeName_passwordBtn || event.getSource() == mainform_settings_changeEmail_passwordBtn) {
            edit_settings_default.setVisible(false);
            edit_settings_changeName.setVisible(false);
            edit_settings_changeEmail.setVisible(false);
            edit_settings_changePassword.setVisible(true);
        }else if (event.getSource() == mainform_settings_changeName_cancelBtn || event.getSource() == mainform_settings_changeEmail_cancelBtn || event.getSource() == mainform_settings_changePassword_cancelBtn){
            edit_settings_default.setVisible(true);
            edit_settings_changeName.setVisible(false);
            edit_settings_changeEmail.setVisible(false);
            edit_settings_changePassword.setVisible(false);
        }
    }

    public void backOneMonth(ActionEvent event) {
        currentYearMonth = currentYearMonth.minusMonths(1);
        loadCurrentMonth();
    }

    public void forwardOneMonth(ActionEvent event) {
        currentYearMonth = currentYearMonth.plusMonths(1);
        loadCurrentMonth();
    }

    public void backToCalendar(ActionEvent event) {
        statistics_dateTaskWindow.setVisible(false);
        statistics_dateWindow.setVisible(true);
        statistics_creatingTask_nameOfTask.clear();
        statistics_creatingTask_selectCategory.setValue("Personal");
        statistics_creatingTask_setNotification.setValue("None");
        loadCurrentMonth();
        loadStatisticsTimesCompleted();
        loadStatisticsAverageDaily();
        loadStatisticsCompletionRate();
        loadTotalTasks();
    }

    public void addTask(ActionEvent event) {
        if (event.getSource() == today_createTaskBtn) showPane(today_createTask);
        else if (event.getSource() == today_cancelCreatingTaskBtn) hidePane(today_createTask);
        else if (event.getSource() == today_creatingTaskBtn) handleTodayTaskCreation();

        else if (event.getSource() == statistics_createTaskBtn) showPane(statistics_createTask);
        else if (event.getSource() == statistics_cancelCreatingTaskBtn) hidePane(statistics_createTask);
        else if (event.getSource() == statistics_creatingTaskBtn) handleStatisticsTaskCreation();
    }

    private void showPane(AnchorPane pane) {
        pane.setVisible(true);
    }

    private void hidePane(AnchorPane pane) {
        pane.setVisible(false);
    }

    private int getUserId(String email) throws Exception {
        String query = "SELECT id FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.connectDB();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }
        return -1;
    }

    private void insertTask(int userId, String title, String category, String notification, LocalDate date) throws Exception {
        String sql = "INSERT INTO tasks (user_id, task_title, task_category, task_notification, task_status, date_created) VALUES (?, ?, ?, ?, 'Pending', ?)";
        try (Connection conn = DatabaseConnection.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, title);
            stmt.setString(3, category);
            stmt.setString(4, notification);
            stmt.setDate(5, java.sql.Date.valueOf(date));
            stmt.executeUpdate();
        }
    }

    private void handleTodayTaskCreation() {
        String taskName = today_creatingTask_nameOfTask.getText();
        String category = today_creatingTask_selectCategory.getValue();
        String notifications = today_creatingTask_setNotification.getValue();
        String email = LoggedInUser.getEmail();

        if (taskName.isEmpty()) {
            new AlertMessage().errorAlert("Please enter a task name!");
            return;
        }

        try {
            int userId = getUserId(email);
            if (userId == -1) {
                new AlertMessage().errorAlert("User not found!");
                return;
            }

            insertTask(userId, taskName, category, notifications, LocalDate.now());

            Task newTask = new Task(taskName, category, "Pending", notifications);
            notificationTimer(newTask);

            today_main.setVisible(true);
            hidePane(today_createTask);
            today_creatingTask_nameOfTask.clear();
            loadTodayTasks();
            filterCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleStatisticsTaskCreation() {
        String taskName = statistics_creatingTask_nameOfTask.getText();
        String category = statistics_creatingTask_selectCategory.getValue();
        String notif = statistics_creatingTask_setNotification.getValue();
        String email = LoggedInUser.getEmail();

        if (selectedDate == null) {
            new AlertMessage().errorAlert("Please select a date from the calendar first!");
            return;
        }

        if (taskName.isEmpty()) {
            new AlertMessage().errorAlert("Please enter a task name!");
            return;
        }

        if (category == null || category.isEmpty()) category = "Personal";

        if (notif == null || category.isEmpty()) category = "None";

        try {
            int userId = getUserId(email);
            if (userId == -1) {
                new AlertMessage().errorAlert("User not found!");
                return;
            }

            insertTask(userId, taskName, category, notif, selectedDate);

            hidePane(statistics_createTask);
            statistics_creatingTask_nameOfTask.clear();
            statistics_creatingTask_selectCategory.setValue("Personal");
            statistics_creatingTask_setNotification.setValue("None");
            loadStatisticsTasks(selectedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeWindow(ActionEvent event) {
        Object source = event.getSource();

        if (source == sidebar_logOut) {
            AlertMessage alert = new AlertMessage();
            if (alert.logOutAlert(event)) {
                ChangeWindow.loginStage.show();
                ChangeWindow.currentStage = (Stage) sidebar_logOut.getScene().getWindow();
                ChangeWindow.currentStage.hide();
            }
            return;
        }

        Map<Button, AnchorPane> windowMap = Map.of(
            sidebar_today, window_today,
            sidebar_yourStats, window_statistics,
            sidebar_task, window_ongoing,
            sidebar_settings, window_settings
        );

        Map<Button, Runnable> extraLoadMap = Map.of(
            sidebar_yourStats, () -> {
                loadStatisticsTimesCompleted();
                loadStatisticsAverageDaily();
                loadStatisticsCompletionRate();
                loadTotalTasks();
            },
            sidebar_task, this::loadOngoingTask,
            sidebar_settings, () -> {
                edit_settings_default.setVisible(true);
                edit_settings_changeName.setVisible(false);
                edit_settings_changeEmail.setVisible(false);
                edit_settings_changePassword.setVisible(false);
            }
        );

        for (Map.Entry<Button, AnchorPane> entry : windowMap.entrySet()) {
            boolean active = source == entry.getKey();
            entry.getValue().setVisible(active);

            entry.getKey().getStyleClass().setAll(active ? "button-color-mainColor" : "button-color");
        }

        if (extraLoadMap.containsKey(source)) {
            extraLoadMap.get(source).run();
        }
    }

    private void editTask(Task task) {
        today_editTask.setVisible(true);
        today_editingTask_nameOfTask.setText(task.getTaskTitle());
        today_editingTask_selectCategory.setValue(task.getTaskCategory());
        today_editingTask_setNotification.setValue(task.getTaskNotification());

        today_cancelEditingTaskBtn.setOnAction(e -> {
            today_editTask.setVisible(false);
            today_editingTask_nameOfTask.clear();
            loadTodayTasks();
        });

        today_editingTaskBtn.setOnAction(e -> {
            String title = today_editingTask_nameOfTask.getText();
            String category = today_editingTask_selectCategory.getValue();
            String notifications = today_editingTask_setNotification.getValue();

            taskEditor.updateTask(task, title, category, notifications);

            if (!notifications.equals(task.getTaskNotification()) && "Pending".equals(task.getStatus())) {
                notificationTimer(task);
            }

            today_editTask.setVisible(false);
            today_editingTask_nameOfTask.clear();
            loadTodayTasks();
        });
    }

    private void editStatisticsTask(Task task) { 
        statistics_editTask.setVisible(true);
        statistics_editingTask_nameOfTask.setText(task.getTaskTitle());
        statistics_editingTask_selectCategory.setValue(task.getTaskCategory());
        statistics_editingTask_setNotification.setValue(task.getTaskNotification());

        statistics_cancelEditingTaskBtn.setOnAction(e -> {
            statistics_editTask.setVisible(false);
            statistics_editingTask_nameOfTask.clear();
            loadStatisticsTasks(selectedDate);
        });

        statistics_editingTaskBtn.setOnAction(e -> {
            String title = statistics_editingTask_nameOfTask.getText();
            String category = statistics_editingTask_selectCategory.getValue();
            String notification = statistics_editingTask_setNotification.getValue();

            taskEditor.updateTask(task, title, category, notification);

            statistics_editTask.setVisible(false);
            statistics_editingTask_nameOfTask.clear();
            loadStatisticsTasks(selectedDate);
        });
    }

    public void displayUserInformation() {
        String sql = "SELECT first_name, last_name, email, password FROM users WHERE email = ?";

        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, LoggedInUser.getEmail());
            result = prepare.executeQuery();

            if (result.next()) {
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String email = result.getString("email");
                String password = "********";

                LoggedInUser.setUser(firstName, lastName, email);

                sidebar_settings_default_fullName.setText(firstName + " " + lastName);
                mainform_settings_default_fullName.setText(firstName + " " + lastName);
                mainform_settings_default_email.setText(email);
                mainform_settings_default_password.setText(password);

                mainform_settings_changeName_firstNameFillText.setText(firstName);
                mainform_settings_changeName_lastNameFillText.setText(lastName);
                mainform_settings_changeName_email.setText(email);
                mainform_settings_changeName_password.setText(password);

                mainform_settings_changeEmail_fullName.setText(firstName + " " + lastName);
                mainform_settings_changeEmail_emailFillText.setText(email);
                mainform_settings_changeEmail_password.setText(password);

                mainform_settings_changePassword_fullName.setText(firstName + " " + lastName);
                mainform_settings_changePassword_email.setText(email);
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    private void loadCurrentWeek() {
        today_weekView.getChildren().clear();

        LocalDate today = LocalDate.now();
        DayOfWeek currentDay = today.getDayOfWeek();
        LocalDate startOfWeek = today.minusDays(currentDay.getValue() % 7);

        for (int i = 0; i < 7; i++) {
            LocalDate date = startOfWeek.plusDays(i);
            VBox dayBox = new VBox();
            dayBox.setAlignment(Pos.CENTER);
            dayBox.setPrefWidth(45);

            Label dayNumber = new Label(String.valueOf(date.getDayOfMonth()));

            if (date.equals(today)) {
                dayNumber.setStyle(
                    "-fx-background-color: #4781FF;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 20;" +
                    "-fx-font-weight: bold;" +
                    "-fx-padding: 8;"
                );
            } else {
                dayNumber.setStyle(
                    "-fx-text-fill: #cccccc;" +
                    "-fx-font-size: 14px;" +
                    "-fx-padding: 8;"
                );
            }

            dayBox.getChildren().add(dayNumber);
            today_weekView.getChildren().add(dayBox);
        }

        today_weekView.setHgap(75);
        today_weekView.setAlignment(Pos.CENTER);
    }

    public void loadCurrentName() {
        mainform_settings_changeName_firstNameFillText.setText(LoggedInUser.getFirstName());
        mainform_settings_changeName_lastNameFillText.setText(LoggedInUser.getLastName());
    }

    public void loadCurrentEmail() {
        mainform_settings_changeEmail_emailFillText.setText(LoggedInUser.getEmail());
    }

    public void loadTodayTasks() {
        masterTaskList.setAll(taskService.getTodayTasks(LoggedInUser.getUserId()));

        today_taskName.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));
        today_taskCategory.setCellValueFactory(new PropertyValueFactory<>("taskCategory"));

        today_buttons.setCellFactory(col -> new TableCell<Task, Void>() {
            private final HBox pane = new HBox(10);
            private final Button editBtn = new Button("Edit");
            private final Button doneBtn = new Button("Done");
            private final Button skipBtn = new Button("Skip");
            private final Button undoBtn = new Button("Undo");

            {
                pane.setAlignment(Pos.CENTER);

                doneBtn.setOnAction(e -> {
                    Task task = getTableView().getItems().get(getIndex());
                    taskService.markTaskAsDone(task);
                    today_taskTable.refresh();
                    loadTodayTasks();
                });

                skipBtn.setOnAction(e -> {
                    Task task = getTableView().getItems().get(getIndex());
                    taskService.markTaskAsSkipped(task);
                    today_taskTable.refresh();
                    loadTodayTasks();
                });

                editBtn.setOnAction(e -> {
                    Task task = getTableView().getItems().get(getIndex());
                    editTask(task);
                    loadTodayTasks();
                });

                undoBtn.setOnAction(e -> {
                    Task task = getTableView().getItems().get(getIndex());
                    taskService.undoTaskStatus(task);
                    today_taskTable.refresh();
                    loadTodayTasks();
                    notificationTimer(task);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Task task = getTableView().getItems().get(getIndex());
                    pane.getChildren().clear();
                    if ("Done".equalsIgnoreCase(task.getStatus()) || "Skipped".equalsIgnoreCase(task.getStatus())) {
                        pane.getChildren().addAll(editBtn, undoBtn);
                    } else {
                        pane.getChildren().addAll(editBtn, doneBtn, skipBtn);
                    }
                    setGraphic(pane);
                }
            }
        });

        today_taskTable.setRowFactory(tv -> new TableRow<Task>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setStyle("");
                } else if ("Done".equalsIgnoreCase(task.getStatus())) {
                    setStyle("-fx-background-color: #d4edda;");
                } else if ("Skipped".equalsIgnoreCase(task.getStatus())) {
                    setStyle("-fx-background-color: #f8d7da;");
                } else {
                    setStyle("");
                }
            }
        });

        today_taskTable.setItems(masterTaskList);
    }

    public void loadStatisticsTasks(LocalDate selectedDate) {
        ObservableList<Task> statisticsList = FXCollections.observableArrayList(
            taskService.getTasksByDate(LoggedInUser.getUserId(), selectedDate)
        );

        try{
            statistics_taskName.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));
            statistics_taskCategory.setCellValueFactory(new PropertyValueFactory<>("taskCategory"));

            statistics_buttons.setCellFactory(col -> new TableCell<Task, Void>() {
                private final HBox pane = new HBox(10);
                private final Button editBtn = new Button("Edit");
                private final Button doneBtn = new Button("Done");
                private final Button skipBtn = new Button("Skip");
                private final Button undoBtn = new Button("Undo");

                {
                    pane.setAlignment(Pos.CENTER);

                    doneBtn.setOnAction(e -> {
                        Task task = getTableView().getItems().get(getIndex());
                        taskService.markTaskAsDone(task);
                        loadStatisticsTasks(selectedDate);
                    });

                    skipBtn.setOnAction(e -> {
                        Task task = getTableView().getItems().get(getIndex());
                        taskService.markTaskAsSkipped(task);
                        loadStatisticsTasks(selectedDate);
                    });

                    editBtn.setOnAction(e -> {
                        Task task = getTableView().getItems().get(getIndex());
                        editStatisticsTask(task);
                    });

                    undoBtn.setOnAction(e -> {
                        Task task = getTableView().getItems().get(getIndex());
                        taskService.undoTaskStatus(task);
                        loadStatisticsTasks(selectedDate);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        Task task = getTableView().getItems().get(getIndex());
                        pane.getChildren().clear();
                        if ("Done".equalsIgnoreCase(task.getStatus()) || "Skipped".equalsIgnoreCase(task.getStatus())) {
                            pane.getChildren().addAll(editBtn, undoBtn);
                        } else {
                            pane.getChildren().addAll(editBtn, doneBtn, skipBtn);
                        }
                        setGraphic(pane);
                    }
                }
            });

            statistics_taskTable.setRowFactory(tv -> new TableRow<Task>() {
                @Override
                protected void updateItem(Task task, boolean empty) {
                    super.updateItem(task, empty);
                    if (empty || task == null) {
                        setStyle("");
                    } else if ("Done".equalsIgnoreCase(task.getStatus())) {
                        setStyle("-fx-background-color: #d4edda;");
                    } else if ("Skipped".equalsIgnoreCase(task.getStatus())) {
                        setStyle("-fx-background-color: #f8d7da;");
                    } else {
                        setStyle("");
                    }
                }
            });
            statistics_taskTable.setItems(statisticsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadStatisticsTimesCompleted(){
        int doneCount = load.loadStatisticsTimesCompleted();
        statistics_timesCompletedLabel.setText(String.valueOf(doneCount));
    }

    public void loadStatisticsAverageDaily() {
        double avgDaily = load.loadStatisticsAverageDaily();
        statistics_averageDailyLabel.setText(String.format("%.2f", avgDaily));
    }

    public void loadStatisticsCompletionRate() {
        double completionRate = load.loadStatisticsCompletionRate();
        statistics_completionRateLabel.setText(String.format("%.2f%%", completionRate));
    }

    public void loadTotalTasks(){
        int count = load.loadTotalTasks();
        statistics_totalTaskMade.setText(String.valueOf(count));
    }

    public void loadOngoingTask() {
        ObservableList<Task> ongoingList = FXCollections.observableArrayList(taskService.getPendingTasks(LoggedInUser.getUserId()));

        try{
            ongoing_taskName.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));
            ongoing_taskCategory.setCellValueFactory(new PropertyValueFactory<>("taskCategory"));
            ongoing_taskDate.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

            ongoing_buttons.setCellFactory(col -> new TableCell<Task, Void>() {
                private final Button doneBtn = new Button("Done");
                private final Button skipBtn = new Button("Skip");
                private final HBox pane = new HBox(10, doneBtn, skipBtn);

                {
                    pane.setAlignment(Pos.CENTER);

                    doneBtn.setOnAction(e -> {
                        Task task = getTableView().getItems().get(getIndex());
                        taskService.updateTaskStatus(task, "Done");
                        loadOngoingTask();
                        loadTodayTasks();
                    });

                    skipBtn.setOnAction(e -> {
                        Task task = getTableView().getItems().get(getIndex());
                        taskService.updateTaskStatus(task, "Skipped");
                        loadOngoingTask();
                        loadTodayTasks();
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(empty ? null : pane);
                }
            });

            ongoing_taskTable.setItems(ongoingList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCurrentMonth() {
        statistics_calendar.getChildren().clear();

        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = currentYearMonth.atDay(1);
        LocalDate startDate = firstOfMonth.with(java.time.temporal.TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        for (int week = 0; week < 6; week++) {
            for (int day = 0; day < 7; day++) {
                LocalDate date = startDate.plusDays(week * 7 + day);

                VBox dayBox = new VBox(5);
                dayBox.setAlignment(Pos.TOP_CENTER);
                dayBox.setPrefSize(80, 80);

                Label dayNumber = new Label(String.valueOf(date.getDayOfMonth()));
                dayNumber.setStyle("-fx-font-size: 14px; -fx-text-fill: #F2F2F2;");

                if (date.equals(today)) {
                    dayNumber.setStyle(
                        "-fx-background-color: #4781FF;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 5;"
                    );
                } else if (!date.getMonth().equals(currentYearMonth.getMonth())) {
                    dayNumber.setStyle("-fx-text-fill: #AAAAAA; -fx-opacity: 0.5; -fx-font-size: 14px;");
                }

                Circle taskDot = null;
                if (taskService.getTasksByDate(LoggedInUser.getUserId(), date).size() > 0) {
                    taskDot = new Circle(4, Color.web("#173c86ff"));
                }
                if (taskDot != null) {
                    dayBox.getChildren().addAll(dayNumber, taskDot);
                } else {
                    dayBox.getChildren().add(dayNumber);
                }

                dayBox.setOnMouseClicked(e -> openDateTasks(date));
                statistics_calendar.add(dayBox, day, week);
            }
        }

        statistics_month.setText(currentYearMonth.getMonth() + " " + currentYearMonth.getYear());
    }


    private void openDateTasks(LocalDate date) {
        selectedDate = date;
        
        statistics_dateWindow.setVisible(false);
        statistics_dateTaskWindow.setVisible(true);

        statistics_specificDate.setText(
            date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"))
        );

        loadStatisticsTasks(date);
    }

    private void setupChoiceBox(javafx.scene.control.ChoiceBox<String> box, List<String> items, String defaultValue) {
        setupChoiceBox(box, items, defaultValue, null);
    }

    private void setupChoiceBox(javafx.scene.control.ChoiceBox<String> box, List<String> items, String defaultValue, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        box.getItems().setAll(items);
        box.setValue(defaultValue);
        if (handler != null) {
            box.setOnAction(handler);
        }
    }

    public void filterCategory(){
        String selectedCategory = today_categoryChoiceBox.getValue();

        if (selectedCategory == null || selectedCategory.equals("All")) {
            today_taskTable.setItems(masterTaskList);
            return;
        }

        ObservableList<Task> filteredList = FXCollections.observableArrayList();

        for (Task task : masterTaskList) {
            if (task.getTaskCategory().equalsIgnoreCase(selectedCategory)) {
                filteredList.add(task);
            }
        }

        today_taskTable.setItems(filteredList);
    }

    public void loadChoiceBox() {
        // Today Tab
        setupChoiceBox(today_categoryChoiceBox, categoriesWithAll, "All", e -> filterCategory());
        setupChoiceBox(today_creatingTask_selectCategory, categories, "Personal");
        setupChoiceBox(today_creatingTask_setNotification, notifications, "None");
        setupChoiceBox(today_editingTask_selectCategory, categories, "Personal");
        setupChoiceBox(today_editingTask_setNotification, notifications, "None");

        // Statistics Tab
        setupChoiceBox(statistics_categoryChoiceBox, categoriesWithAll, "All");
        setupChoiceBox(statistics_creatingTask_selectCategory, categories, "Personal");
        setupChoiceBox(statistics_creatingTask_setNotification, notifications, "None");
        setupChoiceBox(statistics_editingTask_selectCategory, categories, "Personal");
        setupChoiceBox(statistics_editingTask_setNotification, notifications, "None");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        displayUserInformation();
        loadCurrentWeek();
        loadTodayTasks();
        loadCurrentMonth();
        loadStatisticsTimesCompleted();
        loadStatisticsAverageDaily();
        loadStatisticsCompletionRate();
        loadTotalTasks();
        loadOngoingTask();
        loadChoiceBox();

        for (Task task : masterTaskList) {
            if ("Pending".equals(task.getStatus())) {
                notificationTimer(task);
            }
        }

        Platform.runLater(() -> {
            sidebar_today.getScene().getStylesheets().add(
                getClass().getResource("/design/sideBarDesign.css").toExternalForm()
            );
        });
    }
}