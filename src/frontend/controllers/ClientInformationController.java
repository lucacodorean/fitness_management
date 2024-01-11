package frontend.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import backend.ClientSingleton;
import backend.StateManager;
import backend.models.Feedback;
import frontend.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ClientInformationController implements Initializable {

    @FXML private Label     lblSubscriptionInformation;
    @FXML private Button    btnRenewSubscription;
    @FXML private Label     lblSubscriptionStatus;
    @FXML private ImageView pbClientImage;
    @FXML private VBox      vbFeedback;
    @FXML private Label     lblEmail;
    @FXML private Label     lblName;

    public void redirectRenewSubscription(ActionEvent e) { Window.getInstance().setView("renew_subscription"); }
    public void redirectHomepage(ActionEvent e)          { Window.getInstance().setView("homepage"); }

    private void getFeedback() {
          ObservableList<Feedback> list = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = new Feedback().all();
            while (resultSet.next()) {
                if(resultSet.getInt("client_id") == ClientSingleton.getCurrentInstance().getClient().getId()) {
                    list.add(Feedback.getDetails(resultSet.getInt(1)));
                }
            }
            
            for (Feedback feedback : list) {
                BorderPane feedbackFrame = new BorderPane();
                feedbackFrame.setStyle("-fx-background-color: #bc3020;");
                
                Label employeeNameLabel       = new Label("Pentru angajatul: " + feedback.getEmployee().getFirstName() + " " + feedback.getEmployee().getLastName());
                Label ratingLevelLabel        = new Label("Rating-ul oferit: " + feedback.getRating());
                Label descriptionLabelLabel   = new Label(feedback.getDescription());
                Label feedbackLabel           = new Label("Feedback");
                
                employeeNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
                ratingLevelLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
                descriptionLabelLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
                feedbackLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

                feedbackLabel.setStyle("-fx-text-fill: white");
                ratingLevelLabel.setStyle("-fx-text-fill: white");
                employeeNameLabel.setStyle("-fx-text-fill: white");
                descriptionLabelLabel.setStyle("-fx-text-fill: white");
                descriptionLabelLabel.setWrapText(true);
                descriptionLabelLabel.setMaxSize(400, 150);

                feedbackFrame.setLeft(ratingLevelLabel);
                feedbackFrame.setRight(employeeNameLabel);
                feedbackFrame.setTop(new StackPane(feedbackLabel));
                feedbackFrame.setPadding(new Insets(10, 10, 10, 10));
                feedbackFrame.setBottom(new StackPane(descriptionLabelLabel));
                employeeNameLabel.setPadding(new Insets(0,5,0,0));

                vbFeedback.getChildren().add(feedbackFrame);
            }

        } catch (Exception ex) { System.out.println(ex.toString()); }
    }

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

        if(StateManager.getInstance().getAuth().getRole() == 4) btnRenewSubscription.setVisible(false);

        getFeedback();
    }
}
