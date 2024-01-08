package frontend.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

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

    @FXML
    void initialize() {
        assert vbTrainingsList != null : "fx:id=\"vbTrainings\" was not injected: check your FXML file 'trainings.fxml'.";
        assert btnAddTraining  != null : "fx:id=\"btnAddTraining\" was not injected: check your FXML file 'trainings.fxml'.";
    }

    public void redirectNewTraining() { Window.getInstance().setView("training_create"); }
    public void redirectHomepage()    { Window.getInstance().setView("homepage"); }

    private void getSchedule() {
        ObservableList<Schedule> list = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = new Schedule().all();
            while (resultSet.next()) {
                list.add(Schedule.getDetails(resultSet.getInt(1)));
            }
            
            for (Schedule schedule : list) {
                BorderPane scheduleFrame = new BorderPane();
                scheduleFrame.setStyle("-fx-background-color: #bc3020;");
                
                Label trainerNameLabel  = new Label(schedule.getTrainer().getFirstName() + " " + schedule.getTrainer().getLastName());
                Label clientNameLabel   = new Label(schedule.getClient().getFirstName() + " " + schedule.getClient().getLastName());
                Label centerTextLabel   = new Label("este ocupat cu");
                Label timeFrame = new Label(schedule.getTimeStart().toString() + " - " + schedule.getTimeEnd().toString());

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
                vbTrainingsList.getChildren().add(scheduleFrame);
            }

        } catch (Exception ex) { System.out.println(ex.toString()); }
    }

    @Override public void initialize(URL url, ResourceBundle rs) {
        getSchedule();
    }
}
