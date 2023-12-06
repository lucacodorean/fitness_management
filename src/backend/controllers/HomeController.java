package backend.controllers;

import backend.IController;
import backend.Router;
import backend.StateManager;
import frontend.HomePage;
import frontend.Window;

public class HomeController implements IController {

  public static void index() {
    StateManager state = StateManager.getInstance();
    if (!state.isAuth()) {
      System.out.println(state.getAuth().getFirstName());
      Router.getInstance().go("login.view", new LoginViewRequest(false));
      return;
    }

    Window
      .getInstance()
      .changePanel(HomePage.build(Router.getInstance(), state.getAuth()));
  }
}
