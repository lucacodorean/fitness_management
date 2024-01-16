package backend.controllers;

import backend.StateManager;
import backend.models.Employee;
import backend.routes.Router;
import frontend.Window;

public class AuthController implements IController {

  public static void view(LoginViewRequest request) {
    Window.getInstance().setView("login");
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
    Window.getInstance().setView("homepage");
  }

  public static void logout() {
    StateManager.getInstance().setAuth(null);
    Router.getInstance().go("login.view", new LoginViewRequest(false));
  }
}
