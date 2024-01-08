package frontend.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import backend.ClientSingleton;
import frontend.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class ClientInformationController implements Initializable {

    @FXML private Label     lblSubscriptionInformation;
    @FXML private Button    btnRenewSubscription;
    @FXML private Label     lblSubscriptionStatus;
    @FXML private ImageView pbClientImage;
    @FXML private FlowPane  flpFeedback;
    @FXML private Label     lblEmail;
    @FXML private Label     lblName;

    public void redirectRenewSubscription(ActionEvent e) { Window.getInstance().setView("renew_subscription");}
    public void redirectHomepage(ActionEvent e)          { Window.getInstance().setView("homepage"); }

    @Override public void initialize(URL url, ResourceBundle rs) {
        lblName.setText(
            ClientSingleton.getCurrentInstance().getClient().getFirstName() + " " + 
            ClientSingleton.getCurrentInstance().getClient().getLastName()    
        );

        lblEmail.setText(ClientSingleton.getCurrentInstance().getClient().getEmail());
        lblSubscriptionStatus.setText(
            "Clientul " + (ClientSingleton.getCurrentInstance().getClient().isSubscribed() ? "" : " nu ") + "are un abonament activ."
        );

        lblSubscriptionInformation.setText(
            ClientSingleton.getCurrentInstance().getClient().getSubscription().getDescription()
        );
    }
}
