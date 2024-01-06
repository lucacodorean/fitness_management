package backend.controllers;

import backend.routes.Router;
import frontend.AboutPage;
import frontend.Window;

public class AboutController implements IController {

  public static void index() {
    // Window.getInstance().changePanel(AboutPage.build(Router.getInstance()));
  }
}
