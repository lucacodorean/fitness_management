package frontend.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import backend.DatabaseManager;
import backend.models.Client;
import backend.models.Employee;
import backend.models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RatingController implements Initializable {

    @FXML private Button            btnConfirm;
    @FXML private TextField         txtEmail;
    @FXML private TextField         txtRating;
    @FXML private TextField         txtCodCard;
    @FXML private ComboBox<String>  cbEmployee;
    @FXML private TextField          txtDescription;

    private ObservableList<String>getNames(Model model) {
        ObservableList<String> result = FXCollections.observableArrayList();
        try {
            ResultSet resultset = model.all();
            while(resultset.next()) {
                result.add(resultset.getString("firstname") + " " + resultset.getString("lastname"));
            }
            return result;
        } catch (Exception ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    public void insert(ActionEvent e) {
        try {
            if(txtRating.getText().length() == 0  || txtEmail.getText().length() == 0 || 
               txtCodCard.getText().length() == 0 || cbEmployee.getSelectionModel().getSelectedIndex() == -1 ||
               txtDescription.getText().length() > 255) return;

            ArrayList<String> parameters = new ArrayList<>();
            parameters.add(txtEmail.getText());

            /* client_id, employee_id, rating, description */

            ResultSet rs = new DatabaseManager().selectPreparedSQL(
                "select id from " + new Client().getTable() + " where email = ?;", 
                parameters
            );

            rs.next();

            if(rs.getString(1).compareTo(txtCodCard.getText()) != 0) {
                System.err.println("ID's don't match");
                JFrame frame = new JFrame("Notification Box");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JOptionPane.showMessageDialog(
                            frame,
                            "Codul de card este invalid",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                });
                return;
            }

            Integer clientId = rs.getInt(1);
            System.out.println(clientId);

            parameters.clear();

            String[] temp = cbEmployee.getSelectionModel().getSelectedItem().split(" ");
            parameters.add(temp[0]);
            parameters.add(temp[1]);

            rs = new DatabaseManager().selectPreparedSQL(
                "select id from " + new Employee().getTable() + " where firstname = ? and lastname = ?;",
                parameters
            );
            rs.next();

            Integer employeeId = rs.getInt(1);
            System.out.println(employeeId);

            parameters.clear();
            parameters.add(clientId.toString());
            parameters.add(employeeId.toString());
            parameters.add(String.valueOf(Integer.parseInt(txtRating.getText())));
            parameters.add(txtDescription.getText());

            rs = new DatabaseManager().callableSQL(
                "call create_feedback(?,?,?,?);",
                parameters
            );

            JFrame frame = new JFrame("Notification Box");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Recenzia a fost adaugata cu succes.",
                        "SUCCES",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });

        } catch(Exception ex) {
            JFrame frame = new JFrame("Notification Box");
            System.err.println(ex.getStackTrace());
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Eroare la adaugarea recenziei. Contactati un angajat al salii.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            });
        }
    }

    @Override public void initialize(URL url, ResourceBundle rs) {
        cbEmployee.setItems(getNames(new Employee()));
    }
}
