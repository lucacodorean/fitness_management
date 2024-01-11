package backend.controllers;

import backend.StateManager;
import backend.models.Client;
import backend.routes.Router;
import frontend.Window;

public class HomeController implements IController {

  public static void index() {
    StateManager state = StateManager.getInstance();
    if (!state.isAuth()) {
      System.out.println(state.getAuth().getFirstName());
      Router.getInstance().go("login.view", new LoginViewRequest(false));
      return;
    }

    Window.getInstance().setView("homepage");
    // Window
    //   .getInstance()
    //   .changePanel(
    //     HomePage.build(
    //       Router.getInstance(),
    //       state.getAuth(),
    //       new Client().getTableDataFormat(),
    //       Client.getColumns()
    //     )
    //   );
  }
}
