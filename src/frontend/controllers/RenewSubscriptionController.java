package frontend.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import backend.ClientSingleton;
import backend.DatabaseManager;
import backend.models.Subscription;
import frontend.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class RenewSubscriptionController implements Initializable {

    @FXML private ComboBox<String> cbDescriptions;
    @FXML private Button btnConfirm;
    @FXML private Button btnHomePage;


    public void redirectHomepage(ActionEvent e) { Window.getInstance().setView("homepage"); }
    
    public void confirm(ActionEvent e) { 
        try {
            
            ArrayList<String> columns = new ArrayList<String>();
            columns.add("has_active_sub");
            columns.add("subscription_id");
            columns.add("next_payment_at");
            
            ArrayList<String> values = new ArrayList<String>();
            values.add("1");
            values.add(String.valueOf(cbDescriptions.getSelectionModel().getSelectedIndex() + 1));
            values.add((java.time.LocalDateTime.now().plusMonths(1)).toString());

            ClientSingleton.getCurrentInstance().getClient().update(
                ClientSingleton.getCurrentInstance().getClient().getId(), 
                columns, 
                values
            );

            ArrayList<String>parameters = new ArrayList<String>();
            parameters.add(ClientSingleton.getCurrentInstance().getClient().getId().toString());
            parameters.add("7");

            DatabaseManager databaseManager = new DatabaseManager();
            databaseManager.callableSQL(
                "call create_junal(?,?)", parameters
            );

            JOptionPane.showMessageDialog(null,"Plata a fost inregistrata cu succes.", "SUCCES", JOptionPane.INFORMATION_MESSAGE);

        } catch(Exception ex) {
            System.err.println(ex.toString());
            JOptionPane.showMessageDialog(null,"Eroare la inregistrarea platii.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ObservableList<String>getStringDescriptions() {
        ObservableList<String> result = FXCollections.observableArrayList();
        try {
            ResultSet resultset = new Subscription().all();
            while(resultset.next()) {
                if(resultset.getString("description").compareTo("Placeholder.") != 0)
                    result.add(resultset.getString("description"));
            }
            return result;
        } catch (Exception ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    @Override public void initialize(URL url, ResourceBundle rb) {
        cbDescriptions.getItems().addAll(getStringDescriptions());  
    }
}
