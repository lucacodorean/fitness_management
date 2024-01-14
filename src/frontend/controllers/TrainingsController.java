package frontend.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import backend.DatabaseManager;
import backend.StateManager;
import backend.models.Schedule;
import frontend.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TrainingsController implements Initializable {

    @FXML private VBox           vbTrainingsList;
    @FXML private Button         btnAddTraining;
    @FXML private Button         btnHomepage;
    @FXML private Button         btnPersonalSchedule;

    @FXML
    void initialize() {
        assert vbTrainingsList != null : "fx:id=\"vbTrainings\" was not injected: check your FXML file 'trainings.fxml'.";
        assert btnAddTraining  != null : "fx:id=\"btnAddTraining\" was not injected: check your FXML file 'trainings.fxml'.";
    }

    private BorderPane getFrame(String trainerName, String clientName, String timeStart, String timeEnd) {
        BorderPane scheduleFrame = new BorderPane();
        scheduleFrame.setStyle("-fx-background-color: #bc3020;");
        
        Label trainerNameLabel  = new Label(trainerName);
        Label clientNameLabel   = new Label(clientName);
        Label centerTextLabel   = new Label("este ocupat cu");
        Label timeFrame = new Label(timeStart + " - " + timeEnd);

        timeFrame.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        trainerNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        clientNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        centerTextLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        centerTextLabel.setStyle("-fx-text-fill: white");
        trainerNameLabel.setStyle("-fx-text-fill: white");
        clientNameLabel.setStyle("-fx-text-fill: white");
        timeFrame.setStyle("-fx-text-fill: white");

        scheduleFrame.setTop(new StackPane(timeFrame));
        scheduleFrame.setLeft(trainerNameLabel);
        scheduleFrame.setRight(clientNameLabel);
        scheduleFrame.setCenter(centerTextLabel);
        BorderPane.setMargin(trainerNameLabel, new Insets(10, 10, 20, 10));
        BorderPane.setMargin(clientNameLabel,  new Insets(10, 10, 10, 10));
        scheduleFrame.setPadding(new Insets(10, 10, 10, 10));
        return scheduleFrame;
    }

    public void redirectNewTraining() { Window.getInstance().setView("training_create"); }
    public void redirectHomepage()    { Window.getInstance().setView("homepage"); }
    public void redirectPersonalSchedule() {
        if(StateManager.getInstance().getAuth() == null) return;

        vbTrainingsList.getChildren().clear();

        DatabaseManager dbManager = new DatabaseManager();
        ArrayList<String>parameters = new ArrayList<>();
        Integer id;

        parameters.add(StateManager.getInstance().getAuth().getEmail());

        try {
            ResultSet resultId = dbManager.selectPreparedSQL("select id from employee where email = ?", parameters);
            resultId.next();
            id = resultId.getInt(1);

            parameters = new ArrayList<>();
            parameters.add(id.toString());    
            
            ResultSet result = dbManager.selectPreparedSQL(
                "select * from VIEW_TRAINER_SCHEDULE where trainer_id = ?", 
                parameters
            );

            result.next();

            while(result.next()) {
                BorderPane scheduleFrame = getFrame(result.getString(2), result.getString(1), result.getString(3), result.getString(4));
                vbTrainingsList.getChildren().add(scheduleFrame);
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }


    private void getSchedule() {
        ObservableList<Schedule> list = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = new Schedule().all();
            while (resultSet.next()) {
                list.add(Schedule.getDetails(resultSet.getInt(1)));
            }
            
            for (Schedule schedule : list) {
                BorderPane scheduleFrame = getFrame(
                    schedule.getTrainer().getFirstName() + " " + schedule.getTrainer().getLastName(),
                    schedule.getClient().getFirstName() + " " + schedule.getClient().getLastName(),
                    schedule.getTimeStart().toString(),
                    schedule.getTimeEnd().toString()
                );
                vbTrainingsList.getChildren().add(scheduleFrame);
            }

        } catch (Exception ex) { System.out.println(ex.toString()); }
    }

    @Override public void initialize(URL url, ResourceBundle rs) {
        getSchedule();
        btnPersonalSchedule.setVisible(StateManager.getInstance().getAuth().getRole() != 3);
    }
}
