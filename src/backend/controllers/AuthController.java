package backend.controllers;

import backend.Employee;
import backend.IController;
import backend.Router;
import backend.StateManager;
import frontend.LoginPage;
import frontend.Window;

public class AuthController implements IController {

  public static void view(LoginViewRequest request) {
    Window
      .getInstance()
      .changePanel(LoginPage.build(Router.getInstance(), request.getError()));
  }

  public static void login(LoginRequest request) {
    Employee employee;

    try {
      employee = new Employee(request.getEmail(), request.getPassword());
    } catch (Exception exception) {
      System.out.println(exception.toString());
      Router.getInstance().go("login.view", new LoginViewRequest(true));
      return;
    }

    StateManager.getInstance().setAuth(employee);
    Router.getInstance().go("home");
  }

  public static void logout() {
    StateManager.getInstance().setAuth(null);
    Router.getInstance().go("login.view", new LoginViewRequest(false));
  }
}
