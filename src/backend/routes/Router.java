package backend.routes;

import backend.contracts.IRequest;

public class Router {

  public final Route[] routes;
  public static Router instance;

  public Router(Route[] routes) {
    this.routes = routes;
    instance = this;
  }

  public static Router getInstance() {
    if (instance == null) {
      Route[] routes = new Route[] {};
      instance = new Router(routes);
    }

    return instance;
  }

  public void go(String routeName) {
    for (Route route : this.routes) {
      if (route.url.equals(routeName)) {
        route.execute(null);
      }
    }
  }

  public void go(String routeName, IRequest request) {
    for (Route route : this.routes) {
      if (route.url.equals(routeName)) {
        route.execute(request);
      }
    }
  }
}
