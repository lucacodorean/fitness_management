package frontend;

import javafx.event.ActionEvent;

public class HomepageController {

  public void goToForm(ActionEvent event) {
    Window.getInstance().setView("clients_create");
  }
}
