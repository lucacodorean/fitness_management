package backend.controllers;

import backend.IController;
import backend.IControllerRunner;
import backend.IRequest;
import java.lang.reflect.Method;

public class ControllerRunner implements IControllerRunner {

  private String action;
  private Class<? extends IController> controller;

  public ControllerRunner(
    Class<? extends IController> controller,
    String action
  ) {
    this.controller = controller;
    this.action = action;
  }

  public void run(IRequest request) {
    try {
      if (request == null) {
        Method method = controller.getMethod(this.action);
        method.invoke(null);
      } else {
        Method method = controller.getMethod(this.action, request.getClass());
        method.invoke(null, request);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
