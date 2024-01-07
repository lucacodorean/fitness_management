package frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class ClientInformationController {

    @FXML private Label     lblSubscriptionInformation;
    @FXML private Button    btnRenewSubscription;
    @FXML private Label     lblSubscriptionStatus;
    @FXML private ImageView pbClientImage;
    @FXML private FlowPane  flpFeedback;
    @FXML private Label     lblEmail;
    @FXML private Label     lblName;
}
