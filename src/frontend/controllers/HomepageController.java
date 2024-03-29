package frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;

import backend.StateManager;
import backend.models.Client;
import frontend.Window;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomepageController implements Initializable {

    @FXML private BorderPane         bpMain;
    @FXML private BorderPane         anchMain;
    @FXML private Button             btnCreate;
    @FXML private VBox               vbButtons;
    @FXML private Button             btnFacturi;
    @FXML private Button             btnTrainings;
    @FXML private Button             btnEmployee;
    
    @FXML private TableView<Client>                  tbClients;
    @FXML private TableColumn<Client, String>        lastNameColumn;
    @FXML private TableColumn<Client, String>        firstNameColumn;
    @FXML private TableColumn<Client, LocalDateTime> nextPaymentDateColumn;
    @FXML private TableColumn<Client, String>        subscriptionInfoColumn;
    @FXML private TableColumn<Client, Boolean>       activeSubscriptionColumn;
    @FXML private TableColumn<Client, Button>        additionalInformationColumn    = new TableColumn<Client, Button>();
    @FXML private TableColumn<Client, Button>        logEntryInColumn        = new TableColumn<Client, Button>();
    @FXML private TableColumn<Client, Button>        logEntryOutColumn       = new TableColumn<Client, Button>();

    private static ObservableList<Client> getClients() {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        try {
            ResultSet resultSetClients = new Client().all();
            while(resultSetClients.next()) {
                clients.add(Client.getDetails(resultSetClients.getInt(1)));
            }

            return clients;
        } catch (Exception ex) { 
            System.out.println(ex.toString()); 
            return null;
        }
    }

    private void setColumnSettings() {
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        subscriptionInfoColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("subscription"));
        activeSubscriptionColumn.setCellValueFactory(new PropertyValueFactory<Client, Boolean>("hasActiveSub"));
        nextPaymentDateColumn.setCellValueFactory(new PropertyValueFactory<Client, LocalDateTime>("nextPaymentAt"));

        additionalInformationColumn.setCellValueFactory(new PropertyValueFactory<Client, Button>("additionalInformation"));
        logEntryInColumn.setCellValueFactory(new PropertyValueFactory<Client, Button>("logEntryIn"));
        logEntryOutColumn.setCellValueFactory(new PropertyValueFactory<Client, Button>("logEntryOut"));
    }

    public void redirectViewClient(ActionEvent e)   { Window.getInstance().setView("clients_information");  }
    public void redirectCreateClient(ActionEvent e) { Window.getInstance().setView("clients_create");  }
    public void redirectTrainings(ActionEvent e)    { Window.getInstance().setView("trainings");  }
    public void redirectBills(ActionEvent e)        { Window.getInstance().setView("bills");  }
    public void redirectEmployee(ActionEvent e)     { Window.getInstance().setView("employees");}
    
    @Override public void initialize(URL url, ResourceBundle rs) {
        setColumnSettings();        

        additionalInformationColumn.setText("Additional information"); tbClients.getColumns().add(additionalInformationColumn);
        logEntryInColumn.setText("Log Entry In");                      tbClients.getColumns().add(logEntryInColumn);
        logEntryOutColumn.setText("Log Entry Out");                    tbClients.getColumns().add(logEntryOutColumn);
        
        tbClients.setItems(getClients());
    
        switch (StateManager.getInstance().getAuth().getRole()) {
            case 3:
                btnTrainings.setVisible(false);
                btnEmployee.setVisible(false);
                break;
            case 4:
                btnFacturi.setVisible(false);
                btnEmployee.setVisible(false);
                btnCreate.setVisible(false);
                break;
            default:
                break;
        }
    }
}
