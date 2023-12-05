package backend.controllers;

import backend.Router;
import frontend.HomePage;
import frontend.Window;

public class HomeController {

  public static void index() {
    Window.getInstance().changePanel(HomePage.build(Router.getInstance()));
  }
}
