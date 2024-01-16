package frontend.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import backend.StateManager;
import backend.models.Employee;
import frontend.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeController implements Initializable{

    @FXML private Button btnHomepage;
    @FXML private TableView<Employee> tbEmployees;
    @FXML private TableColumn<Employee, String> tcFirstName;
    @FXML private TableColumn<Employee, String> tcLastName;
    @FXML private TableColumn<Employee, String> tcEmail;
    @FXML private TableColumn<Employee, Integer> tcWage;
    @FXML private TableColumn<Employee, Float> tcRating;
    @FXML private TableColumn<Employee, LocalDateTime> tcEmploymentDate;
    @FXML private ComboBox<String> cbRoles;
    @FXML private TextField txtNewEmail;
    @FXML private TextField txtNewWage;

    public void redirectHomepage()       { Window.getInstance().setView("homepage"); }
    public void redirectCreateEmployee() { Window.getInstance().setView("create_employee"); }
    public void redirectEvents()         { Window.getInstance().setView("jurnal_view");}

    public void deleteEmployee() {
        try {
            new Employee().delete(tbEmployees.getSelectionModel().getSelectedItem().getId());

            JFrame frame = new JFrame("Notification Box");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Stergerea angajatului nou s-a efectuat cu succes.",
                        "SUCCES",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    tbEmployees.refresh();
                    tbEmployees.setItems(getEmployees());
                }
            });

        } catch(Exception ex) {
            System.out.println(ex.toString());

            JFrame frame = new JFrame("Notification Box");
             SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Eroare la stergerea angajatului.",
                        "ERROR",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });
        }
    }

    public void updateEmployee() {
         try {
            ArrayList<String> columns = new ArrayList<>();
            ArrayList<String> parameters = new ArrayList<>();
            
            if(!txtNewEmail.getText().isEmpty()) {
                columns.add("email");   
                parameters.add(txtNewEmail.getText());
            }

            if(!txtNewWage.getText().isEmpty()) {
                columns.add("wage");  
                parameters.add(txtNewWage.getText());
            }

            if(cbRoles.getSelectionModel().getSelectedIndex() >= 0) {
                columns.add("role_id");  
                parameters.add(String.valueOf(cbRoles.getSelectionModel().getSelectedIndex()+1));
            }

            if(StateManager.getInstance().getAuth().getRole() == 2 && cbRoles.getSelectionModel().getSelectedIndex() == 1 ) {
               throw new Exception();
            } 

            new Employee().update(tbEmployees.getSelectionModel().getSelectedItem().getId(), columns, parameters);

            JFrame frame = new JFrame("Notification Box");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Actualizarea angajatului s-a efectuat cu succes.",
                        "SUCCES",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    tbEmployees.refresh();
                    tbEmployees.setItems(getEmployees());
                }
            });

        } catch(Exception ex) {
            System.out.println(ex.toString());

            JFrame frame = new JFrame("Notification Box");
             SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Eroare la actualizarea angajatului.",
                        "EROARE",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });
        }
    }

    private static ObservableList<Employee> getEmployees() {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = new Employee().all();
            while(rs.next()) {
                list.add(Employee.getDetails(rs.getInt(1)));
            }
            return list;
        } catch (Exception ex) { 
            System.out.println(ex.toString());
            return null;
        }
    }

    private void initColumns() {
        tbEmployees.setEditable(true);

        tcFirstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        tcWage.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("wage"));
        tcRating.setCellValueFactory(new PropertyValueFactory<Employee, Float>("rating"));

        tcEmploymentDate.setCellValueFactory(new PropertyValueFactory<Employee, LocalDateTime>("employmentDate"));
    }
    
    @Override public void initialize(URL url, ResourceBundle rs) {
        initColumns();
        tbEmployees.setItems(getEmployees());
        cbRoles.getItems().addAll("Super Administrator", "Administrator", "Casier", "Antrenor");
    }
}
