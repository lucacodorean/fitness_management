package frontend.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import backend.DatabaseManager;
import backend.models.Jurnal;
import frontend.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class JurnalController implements Initializable {

    @FXML private TableView<Jurnal> tbJurnal;
    @FXML private TableColumn<Jurnal, String> tcEvent;
    @FXML private TableColumn<Jurnal, String> tcClient;
    @FXML private TableColumn<Jurnal, LocalDateTime> tcTime;
    @FXML private TextField txtText;
    @FXML private Button btnHomepage;
    @FXML private Button btnAddEvent;

    private void setColumnSettings() {
        tcClient.setCellValueFactory(new PropertyValueFactory<Jurnal, String>("client"));
        tcEvent.setCellValueFactory(new PropertyValueFactory<Jurnal, String>("descr"));
        tcTime.setCellValueFactory(new PropertyValueFactory<Jurnal, LocalDateTime>("created_at"));
    }

    public void redirectToHomepage(ActionEvent e) { Window.getInstance().setView("homepage"); }
    public void addEvent(ActionEvent e) {
        try {
          
            if(txtText.getText().length() != 0) {
                ArrayList<String> parameters = new ArrayList<>();
                parameters.add(txtText.getText());

                DatabaseManager databaseManager = new DatabaseManager();
                databaseManager.callableSQL(
                    "call create_event(?);", 
                    parameters
                );
                
                JFrame frame = new JFrame("Notification Box");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JOptionPane.showMessageDialog(
                            frame,
                            "Inregistrarea noului eveniment s-a efectuat cu succes.",
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

    private static ObservableList<Jurnal> getJurnal() {
        ObservableList<Jurnal> list = FXCollections.observableArrayList();
        try {
            ResultSet resultSetPayments = new DatabaseManager().callableSQL("call fetch_jurnals();", null);
            while(resultSetPayments.next()) {
                list.add(Jurnal.getDetails(resultSetPayments.getInt(1)));
            }
            return list;
        } catch (Exception ex) { 
            System.out.println(ex.toString()); 
            return null;
        }
    }

    @Override public void initialize(URL url, ResourceBundle rs) { 
        setColumnSettings(); 
        tbJurnal.setItems(getJurnal());
    }
}
