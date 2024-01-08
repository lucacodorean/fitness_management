package frontend.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import backend.DatabaseManager;
import backend.models.Client;
import backend.models.Employee;
import backend.models.Model;
import frontend.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class NewTrainingController implements Initializable{
    
    @FXML private Button             btnTraninigs;
    @FXML private Button             btnSchedule;
    @FXML private ComboBox<String>   cbTrainers;
    @FXML private ComboBox<String>   cbClients;
    @FXML private DatePicker         dpStartingDate;
    @FXML private DatePicker         dpEndingDate;
    @FXML private TextField          txtStartSchedule;
    @FXML private TextField          txtEndSchedule;

    public void redirectTrainings() { Window.getInstance().setView("trainings"); }

    public void conductSchedule(ActionEvent e) { 
        try {
            DatabaseManager databaseManager = new DatabaseManager();
            
            ArrayList<String>parametersClient = new ArrayList<String>();
            String[] temp = cbClients.getSelectionModel().getSelectedItem().split(" ");
            parametersClient.add(temp[0]);
            parametersClient.add(temp[1]);

            System.out.println(temp);

            ArrayList<String> parametersTrainer = new ArrayList<String>();
            temp = cbTrainers.getSelectionModel().getSelectedItem().split(" ");
            parametersTrainer.add(temp[0]);
            parametersTrainer.add(temp[1]);

            ArrayList<String> parameters = new ArrayList<String>();
            
            ResultSet rs = databaseManager
                .selectPreparedSQL(
                    "select * from clients where firstname = ? and lastname = ?", 
                    parametersClient
            );

            if(rs.next()) parameters.add(String.valueOf(rs.getInt("id")));

            
            rs = databaseManager
                .selectPreparedSQL(
                    "select * from employee where firstname = ? and lastname = ?", 
                    parametersTrainer
            );

            if(rs.next()) parameters.add(String.valueOf(rs.getInt("id")));

            parameters.add(dpStartingDate.getValue().toString() + "T" + txtStartSchedule.getText());
            parameters.add(dpEndingDate.getValue().toString()   + "T" + txtEndSchedule.getText());

            databaseManager.updatePreparedSQL(
                "insert into schedule (client_id, trainer_id, time_start, time_end) values (?,?,?,?)", 
                parameters
            );

            JFrame frame = new JFrame("Notification Box");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Inregistrarea in jurnalul de programari a intrarii s-a efectuat cu succes.",
                        "SUCCES",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });

        } catch(Exception ex) {
            System.out.println(ex.toString());

            JFrame frame = new JFrame("Notification Box");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Eroare la inregistrarea programarii.", 
                        "ERROR", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            });
        }
    }

    private ObservableList<String>getNames(Model model) {
        ObservableList<String> result = FXCollections.observableArrayList();
        try {
            ResultSet resultset = model.all();
            while(resultset.next()) {
                if(model instanceof Employee) {
                    if(resultset.getInt("role_id") == 3) continue;
                } 
                result.add(resultset.getString("firstname") + " " + resultset.getString("lastname"));
            }
            return result;
        } catch (Exception ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    @Override public void initialize(URL url, ResourceBundle rs) {
        cbClients.setItems(getNames(new Client()));
        cbTrainers.setItems(getNames(new Employee()));
    }
}
