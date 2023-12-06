package backend.controllers;

import backend.Router;
import frontend.AboutPage;
import frontend.Window;

public class AboutController {

  public static void index() {
    Window.getInstance().changePanel(AboutPage.build(Router.getInstance()));
  }
}
