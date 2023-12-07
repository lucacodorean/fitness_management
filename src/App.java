import backend.*;
import frontend.*;
import java.sql.*;
import backend.routes.*;
import backend.controllers.*;

public class App {

  private static boolean connectionTest() throws SQLException {
    VariablesSingleton env = VariablesSingleton.getInstance();

    try (
      Connection connection = DriverManager.getConnection(
        env.CONNECTION_URL,
        env.CONNECTION_USER,
        env.CONNECTION_PASS
      )
    ) {
      return true;
    } catch (SQLException exception) {
      System.out.println(exception.toString());
      return false;
    }
  }

  public static void main(String[] args) throws Exception {

    try {
      System.out.println(
        connectionTest() ? "Connection established" : "Connection error"
      );
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
    new Window();

    Route[] routes = new Route[] {
      new Route("home", new ControllerRunner(HomeController.class, "index")),
      new Route("about", new ControllerRunner(AboutController.class, "index")),
      new Route(
        "login.view",
        new ControllerRunner(AuthController.class, "view")
      ),
      new Route("login", new ControllerRunner(AuthController.class, "login")),
      new Route("logout", new ControllerRunner(AuthController.class, "logout")),
    };
    Router router = new Router(routes);
    // router.go("login.view", new LoginViewRequest(false));
    router.go("login", new LoginRequest("admin@myfitness.com", "parola"));
  }
}
