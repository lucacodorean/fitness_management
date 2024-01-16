package frontend.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import backend.DatabaseManager;
import backend.models.Bill;
import frontend.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillsController implements Initializable {
 
    @FXML public TableView<Bill>                   tbBills;

    @FXML public TableColumn<Bill,  String>        tcSubscriptionDetails;
    @FXML public TableColumn<Bill,  String>        tcClientName;
    @FXML public TableColumn<Bill,  LocalDateTime> tcDate;

    public void redirectHomepage(ActionEvent e) { Window.getInstance().setView("homepage");}

    private void setColumnSettings() {
        tcClientName.setCellValueFactory(new PropertyValueFactory<Bill, String>("client"));
        tcSubscriptionDetails.setCellValueFactory(new PropertyValueFactory<Bill, String>("subscription"));
        tcDate.setCellValueFactory(new PropertyValueFactory<Bill, LocalDateTime>("payed_at"));
    }

    private static ObservableList<Bill> getBills() {
        ObservableList<Bill> list = FXCollections.observableArrayList();
        try {
            ResultSet resultSetPayments = new DatabaseManager().callableSQL("call fetch_payments();", null);
            while(resultSetPayments.next()) {
                list.add(Bill.getDetails(resultSetPayments.getInt(1)));
            }

            return list;
        } catch (Exception ex) { 
            System.out.println(ex.toString()); 
            return null;
        }
    }

    @Override public void initialize(URL url, ResourceBundle rb) {
        setColumnSettings();
        tbBills.setItems(getBills());
    }
}
