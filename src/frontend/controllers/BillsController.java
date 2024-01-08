package frontend.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import frontend.Window;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class BillsController implements Initializable {
 
    public void redirectHomepage(ActionEvent e) { Window.getInstance().setView("homepage");}

    @Override public void initialize(URL url, ResourceBundle rb) {

    }
}
