package frontend.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import backend.DatabaseManager;
import backend.StateManager;
import frontend.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateClientController implements Initializable {
    
    @FXML private TextField txtEmail;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private Button    btnReturn;
    @FXML private Button    btnCreate;

    public void returnToHomepage(ActionEvent e) { Window.getInstance().setView("homepage"); }
    public void createClient(ActionEvent e) {
        try {
            Boolean ok = false;
            if(txtEmail.getText().length() != 0 && 
                txtFirstName.getText().length() != 0 && 
                txtLastName.getText().length() != 0) {

                    ArrayList<String> parameters = new ArrayList<>();

                    parameters.add(txtFirstName.getText());
                    parameters.add(txtLastName.getText());
                    parameters.add(txtEmail.getText());
                    parameters.add(StateManager.getInstance().getAuth().getId().toString());
                    parameters.add(ok.toString());

                    DatabaseManager databaseManager = new DatabaseManager();
                    databaseManager.callableSQL(
                        "call create_client(?,?,?,?,?)", 
                        parameters);
                    
                    JFrame frame = new JFrame("Notification Box");
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            JOptionPane.showMessageDialog(
                                frame,
                                "Inregistrarea in clientului s-a efectuat cu succes.",
                                "SUCCES",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    });
                }

        } catch(Exception ex) {
            System.out.println(ex.getStackTrace());

            JFrame frame = new JFrame("Notification Box");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(
                        frame,
                        ex.toString(), 
                        "ERROR", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            });
        }
    }

    @Override public void initialize(URL url, ResourceBundle rs) { 
        txtEmail.setText("Introdu adresa de email.");
        txtFirstName.setText("Intru prenumele.");
        txtFirstName.setText("Intru numele.");
    }
}
