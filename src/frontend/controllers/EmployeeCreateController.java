package frontend.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import frontend.Window;
import javafx.fxml.FXML;
import backend.DatabaseManager;
import backend.StateManager;
import backend.models.Employee;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EmployeeCreateController implements Initializable {

    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtEmail;
    @FXML private ComboBox<String>  cbRoles;

    public void redirectHomepage() { Window.getInstance().setView("homepage"); }
    public void create() {
        if(txtFirstName.getText().isEmpty() || txtEmail.getText().isEmpty() || txtLastName.getText().isEmpty()) return;
        ArrayList<String> parameters = new ArrayList<>();

        int leftLimit = 97; 
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
        
        parameters.add(txtFirstName.getText());
        parameters.add(txtLastName.getText());
        parameters.add(txtEmail.getText());
        parameters.add(generatedString);
        parameters.add(String.valueOf(3100));
        parameters.add(String.valueOf(cbRoles.getSelectionModel().getSelectedIndex() + 1));
        parameters.add(StateManager.getInstance().getAuth().getId().toString());
        parameters.add(String.valueOf(0));
        
        JFrame frame = new JFrame("Notification Box");

        try {
            new DatabaseManager().callableSQL(
                "call create_employee(?,?,?,?,?,?,?,?);", 
                parameters
            );

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        ResultSet rs = new DatabaseManager().selectPreparedSQL("select id from " + new Employee().getTable() + " ORDER BY id DESC LIMIT 1;", null);
                        rs.next();

                        new Employee();
                        JOptionPane.showMessageDialog(
                            frame,
                            "Inregistrarea angajatului nou s-a efectuat cu succes.\n" + 
                            "Parola angajatului este: " + Employee.getDetails(new Employee().find(rs.getInt(1)).getInt(1)).getPassword(),
                            "SUCCES",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                }
            });

            txtEmail.setText(""); txtFirstName.setText(""); txtLastName.setText("");

        } catch (Exception ex) {

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Eroare la inregistrarea angajatului.", 
                        "ERROR", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            });
            System.out.println(ex.toString());
        }
    }

    @Override public void initialize(URL url, ResourceBundle rs) {
        cbRoles.getItems().addAll("Super Administrator", "Administrator", "Casier", "Antrenor");
    }
}
