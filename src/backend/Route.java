package backend;

public class Route {

  public String url;
  public Runnable action;

  public Route(String url, Runnable action) {
    this.url = url;
    this.action = action;
  }

  public void execute() {
    action.run();
  }
}
