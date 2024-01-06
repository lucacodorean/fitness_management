package frontend.controllers;

import backend.controllers.LoginRequest;
import backend.routes.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

  @FXML
  private TextField email;

  @FXML
  private PasswordField password;

  public void sendLogin(ActionEvent event) {
    System.out.println("Login button clicked");
    LoginRequest request = new LoginRequest(
      email.getText(),
      password.getText()
    );

    System.out.println(request.getEmail());
    Router.getInstance().go("login", request);
  }
}
