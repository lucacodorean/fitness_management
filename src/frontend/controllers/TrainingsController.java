package frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class TrainingsController {

    @FXML private VBox           vbTrainingsList;
    @FXML private Button         btnAddTraining;

    @FXML
    void initialize() {
        assert vbTrainingsList != null : "fx:id=\"vbTrainings\" was not injected: check your FXML file 'trainings.fxml'.";
        assert btnAddTraining  != null : "fx:id=\"btnAddTraining\" was not injected: check your FXML file 'trainings.fxml'.";
    }
}
