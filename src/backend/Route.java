package backend;

public class Route {

  public String url;
  public IControllerRunner action;

  public Route(String url, IControllerRunner action) {
    this.url = url;
    this.action = action;
  }

  public void execute(IRequest request) {
    action.run(request);
  }
}
