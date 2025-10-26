import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;
import javafx.scene.control.TableRow;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainFormDocumentController implements Initializable{

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
    private FlowPane today_weekView1;

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

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private ObservableList<Task> masterTaskList = FXCollections.observableArrayList();
    private YearMonth currentYearMonth = YearMonth.now();

    public void windowDisappear(javafx.scene.input.MouseEvent event){
        if(event.getSource() == today_boundaryClick){
            today_createTask.setVisible(false);
        }else if(event.getSource() == today_boundaryClickEdit){
            today_editTask.setVisible(false);
        }
    }

    public void addTask(ActionEvent event) {
        if (event.getSource() == today_createTaskBtn) {
            today_createTask.setVisible(true);
        } else if (event.getSource() == today_cancelCreatingTaskBtn) {
            today_createTask.setVisible(false);
        } else if (event.getSource() == today_creatingTaskBtn) {
            String taskName = today_creatingTask_nameOfTask.getText();
            String category = today_creatingTask_selectCategory.getValue();
            String userEmail = LoggedInUser.getEmail();
            AlertMessage alert = new AlertMessage();
            today_creatingTask_selectCategory.setValue("Personal");

            if (taskName.isEmpty()) {
                alert.errorAlert("Please enter a task name!");
                return;
            }

            String getUserIdQuery = "SELECT id FROM users WHERE email = ?";
            String insertTaskQuery = "INSERT INTO tasks (user_id, task_title, task_category, task_status, date_created) VALUES (?, ?, ?, 'Pending', CURDATE())";

            try (Connection connect = DatabaseConnection.connectDB()) {
                int userId = -1;

                try (PreparedStatement getUserIdStmt = connect.prepareStatement(getUserIdQuery)) {
                    getUserIdStmt.setString(1, userEmail);
                    try (ResultSet rs = getUserIdStmt.executeQuery()) {
                        if (rs.next()) userId = rs.getInt("id");
                    }
                }

                if (userId == -1) {
                    alert.errorAlert("User not found!");
                    return;
                }

                try (PreparedStatement insertStmt = connect.prepareStatement(insertTaskQuery)) {
                    insertStmt.setInt(1, userId);
                    insertStmt.setString(2, taskName);
                    insertStmt.setString(3, category);
                    insertStmt.executeUpdate();
                }

                today_main.setVisible(true);
                today_createTask.setVisible(false);
                today_creatingTask_nameOfTask.setText("");
                loadTodayTasks();
                filterCategory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void markTaskAsDone(Task task) {
        updateTaskStatus(task, "Done", "#d4edda"); // green background
    }

    private void markTaskAsSkipped(Task task) {
        updateTaskStatus(task, "Skipped", "#f8d7da"); // red background
    }

    private void undoTaskStatus(Task task) {
        updateTaskStatus(task, "Pending", "white"); // default
    }

    private void updateTaskStatus(Task task, String status, String color) {
        String sql = "UPDATE tasks SET task_status = ? WHERE task_title = ? AND user_id = ?";

        try (Connection connect = DatabaseConnection.connectDB();
            PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, status);
            prepare.setString(2, task.getTaskTitle());
            prepare.setInt(3, LoggedInUser.getUserId());
            prepare.executeUpdate();

            task.setStatus(status);

            loadTodayTasks();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editTask(Task task) {
        today_editTask.setVisible(true);
        today_editingTask_nameOfTask.setText(task.getTaskTitle());
        today_editingTask_selectCategory.setValue(task.getTaskCategory());
        
        today_cancelEditingTaskBtn.setOnAction(e -> {
            today_editTask.setVisible(false);
            today_editingTask_nameOfTask.clear();
            loadTodayTasks();
        });

        today_editingTaskBtn.setOnAction(e -> {
            String updatedTitle = today_editingTask_nameOfTask.getText();
            String updatedCategory = today_editingTask_selectCategory.getValue();

            String sql = "UPDATE tasks SET task_title = ?, task_category = ? WHERE user_id = ? AND task_title = ?";

            try (Connection connect = DatabaseConnection.connectDB();
                PreparedStatement prepare = connect.prepareStatement(sql)) {

                prepare.setString(1, updatedTitle);
                prepare.setString(2, updatedCategory);
                prepare.setInt(3, LoggedInUser.getUserId());
                prepare.setString(4, task.getTaskTitle());
                prepare.executeUpdate();

                today_editTask.setVisible(false);
                today_editingTask_nameOfTask.clear();
                loadTodayTasks();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void filterCategory(){
        String selectedCategory = today_categoryChoiceBox.getValue();

        // If "All" is selected, show everything
        if (selectedCategory == null || selectedCategory.equals("All")) {
            today_taskTable.setItems(masterTaskList);
            return;
        }

        // Otherwise, filter
        ObservableList<Task> filteredList = FXCollections.observableArrayList();

        for (Task task : masterTaskList) {
            if (task.getTaskCategory().equalsIgnoreCase(selectedCategory)) {
                filteredList.add(task);
            }
        }

        today_taskTable.setItems(filteredList);
    }

    public void changeWindow(ActionEvent event){
        if(event.getSource() == sidebar_logOut){
            AlertMessage alert = new AlertMessage();

            if(alert.logOutAlert(event)){
                ChangeWindow.loginStage.show();
                ChangeWindow.currentStage = (Stage) sidebar_logOut.getScene().getWindow();
                ChangeWindow.currentStage.hide();
            }
        }else if(event.getSource() == sidebar_today){
            window_today.setVisible(true);
            window_statistics.setVisible(false);
            //window_task.setVisible(false);
            window_settings.setVisible(false);
        }else if(event.getSource() == sidebar_yourStats){
            window_today.setVisible(false);
            window_statistics.setVisible(true);
            //window_task.setVisible(false);
            window_settings.setVisible(false);
        }else if(event.getSource() == sidebar_task){
            window_today.setVisible(false);
            window_statistics.setVisible(false);
            //window_task.setVisible(true);
            window_settings.setVisible(false);
        }else if(event.getSource() == sidebar_settings){
            window_today.setVisible(false);
            window_statistics.setVisible(false);
            //window_task.setVisible(false);
            window_settings.setVisible(true);
            edit_settings_default.setVisible(true);
            edit_settings_changeName.setVisible(false);
            edit_settings_changeEmail.setVisible(false);
            edit_settings_changePassword.setVisible(false);
        }
    }

    public void updateName(){
        String newFirstName = mainform_settings_changeName_firstNameFillText.getText();
        String newLastName = mainform_settings_changeName_lastNameFillText.getText();
        String userEmail = LoggedInUser.getEmail();

        AlertMessage alert = new AlertMessage();
        String currentFirstName = LoggedInUser.getFirstName();
        String currentLastName = LoggedInUser.getLastName();

        if(newFirstName.isEmpty() || newLastName.isEmpty()){
            alert.errorAlert("Must fill up the fields");
            return;
        }else if(newFirstName.equals(currentFirstName) && newLastName.equals(currentLastName)){
            return;
        }

        String sql = "UPDATE users SET first_name = ?, last_name = ? WHERE email = ?";

        try (Connection connect = DatabaseConnection.connectDB();
            PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, newFirstName);
            prepare.setString(2, newLastName);
            prepare.setString(3, userEmail);

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                alert.succesfulAlert("User name updated successfully!");

                LoggedInUser.setUser(newFirstName, newLastName, userEmail);
                edit_settings_default.setVisible(true);
                edit_settings_changeName.setVisible(false);
                edit_settings_changeEmail.setVisible(false);
                edit_settings_changePassword.setVisible(false);
                displayUserInformation();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmail(){
        String newEmail = mainform_settings_changeEmail_emailFillText.getText();
        String userFirstName = LoggedInUser.getFirstName();
        String userLastName = LoggedInUser.getLastName();

        AlertMessage alert = new AlertMessage();
        String currentEmail = LoggedInUser.getEmail();

        if(newEmail.isEmpty()){
            alert.errorAlert("Must fill up the field");
            return;
        }else if (!newEmail.endsWith("@gmail.com")) {
            alert.errorAlert("Please enter a valid email address");
            return;
        }else if(newEmail.equals(currentEmail)){
            return;
        }

        String sql = "UPDATE users SET email = ? WHERE email = ?";

        try (Connection connect = DatabaseConnection.connectDB();
            PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, newEmail);
            prepare.setString(2, currentEmail);


            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                alert.succesfulAlert("Email updated successfully!");

                LoggedInUser.setUser(userFirstName, userLastName, newEmail);
                edit_settings_default.setVisible(true);
                edit_settings_changeName.setVisible(false);
                edit_settings_changeEmail.setVisible(false);
                edit_settings_changePassword.setVisible(false);
                displayUserInformation();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(){
        String newPassword = mainform_settings_changePassword_passwordFillText.getText();
        String newConfirmPassword = mainform_settings_changePassword_confirmPasswordFillText.getText();
        String currentEmail = LoggedInUser.getEmail();

        AlertMessage alert = new AlertMessage();

        if (newPassword.isEmpty() || newConfirmPassword.isEmpty()) {
            alert.errorAlert("Please fill up both password fields");
            return;
        }else if(!newPassword.equals(newConfirmPassword)){
            alert.errorAlert("Passwords do not match! Try again");
            return;
        }else if(newPassword.length() < 8){
            alert.errorAlert("Invalid Password! at least 8 characters needed");
            return;
        }

        String sql = "UPDATE users SET password = ? WHERE email = ?";

        try(Connection connect = DatabaseConnection.connectDB(); 
            PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, newPassword);
            prepare.setString(2, currentEmail);

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                alert.succesfulAlert("Password updated successfully!");

                edit_settings_default.setVisible(true);
                edit_settings_changeName.setVisible(false);
                edit_settings_changeEmail.setVisible(false);
                edit_settings_changePassword.setVisible(false);
                displayUserInformation();
            }
        }catch (Exception e) {e.printStackTrace();}
    }

    public void displayUserInformation() {
        String sql = "SELECT first_name, last_name, email, password FROM users WHERE email = ?";

        connect = DatabaseConnection.connectDB();

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

    public void loadCurrentName() {
        mainform_settings_changeName_firstNameFillText.setText(LoggedInUser.getFirstName());
        mainform_settings_changeName_lastNameFillText.setText(LoggedInUser.getLastName());
    }

    public void loadCurrentEmail() {
        mainform_settings_changeEmail_emailFillText.setText(LoggedInUser.getEmail());
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

    private void loadCurrentMonth() {
        statistics_calendar.getChildren().clear();

        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = currentYearMonth.atDay(1);

        LocalDate startDate = firstOfMonth.with(java.time.temporal.TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        for (int week = 0; week < 5; week++) {  
            for (int day = 0; day < 7; day++) { 
                LocalDate date = startDate.plusDays(week * 7 + day);

                VBox dayBox = new VBox();
                dayBox.setAlignment(Pos.TOP_CENTER);
                dayBox.setPrefSize(80, 80);

                Label dayNumber = new Label(String.valueOf(date.getDayOfMonth()));

                if (date.equals(today)) {
                    dayNumber.setStyle(
                        "-fx-background-color: #4781FF;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 20;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 5;"
                    );
                } else if (date.getMonth().equals(currentYearMonth.getMonth())) {
                    dayNumber.setStyle("-fx-text-fill: #F2F2F2; -fx-font-size: 14px;");
                } else {
                    dayNumber.setStyle("-fx-text-fill: #AAAAAA; -fx-opacity: 0.5; -fx-font-size: 14px;");
                }

                dayBox.getChildren().add(dayNumber);

                statistics_calendar.add(dayBox, day, week);
            }
        }

        statistics_month.setText(currentYearMonth.getMonth() + " " + currentYearMonth.getYear());
    }

    public void loadTodayTasks() {
        masterTaskList.clear();
        String sql = "SELECT * FROM tasks WHERE user_id = ? AND DATE(date_created) = CURDATE()";
        
        try (Connection connect = DatabaseConnection.connectDB();
            PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setInt(1, LoggedInUser.getUserId());
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                masterTaskList.add(new Task(
                    result.getString("task_title"),
                    result.getString("task_category"),
                    result.getString("task_status") 
                ));
            }

            today_taskName.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));
            today_taskCategory.setCellValueFactory(new PropertyValueFactory<>("taskCategory"));

            today_buttons.setCellFactory(param -> new TableCell<Task, Void>() {
                private final Button editBtn = new Button("Edit");
                private final Button doneBtn = new Button("Done");
                private final Button skipBtn = new Button("Skip");
                private final Button undoBtn = new Button("Undo");
                private final HBox pane = new HBox(40);

                {
                    pane.setAlignment(Pos.CENTER);

                    doneBtn.setOnAction(e -> {
                        Task task = getTableView().getItems().get(getIndex());
                        markTaskAsDone(task);
                        today_taskTable.refresh(); // force table to re-evaluate row styles
                    });

                    skipBtn.setOnAction(e -> {
                        Task task = getTableView().getItems().get(getIndex());
                        markTaskAsSkipped(task);
                        today_taskTable.refresh();
                    });

                    editBtn.setOnAction(e -> {
                        Task task = getTableView().getItems().get(getIndex());
                        editTask(task);
                    });

                    undoBtn.setOnAction(e -> {
                        Task task = getTableView().getItems().get(getIndex());
                        undoTaskStatus(task);
                        today_taskTable.refresh();
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

                        if ("Done".equalsIgnoreCase(task.getStatus()) ||
                            "Skipped".equalsIgnoreCase(task.getStatus())) {
                            pane.getChildren().addAll(editBtn, undoBtn);
                        } else {
                            pane.getChildren().addAll(editBtn, doneBtn, skipBtn);
                        }
                        setGraphic(pane);
                    }
                }
            });

            // ✅ Use rowFactory to color each row
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        displayUserInformation();
        loadCurrentWeek();
        loadTodayTasks();
        loadCurrentMonth();
        today_categoryChoiceBox.getItems().addAll("All", "Personal", "School", "Event");
        today_categoryChoiceBox.setValue("All");
        today_categoryChoiceBox.setOnAction(e -> filterCategory());
        today_creatingTask_selectCategory.getItems().addAll("Personal", "School", "Event");
        today_creatingTask_selectCategory.setValue("Personal");
        today_editingTask_selectCategory.getItems().addAll("Personal", "School", "Event");
    }
}
