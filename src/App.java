import backend.*;
import frontend.*;
import java.sql.*;

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

  public static void main(String[] args) throws SQLException {
    try {
      System.out.println(
        connectionTest() ? "Connection established" : "Connection error"
      );
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }

    System.out.println("MIAU");
    new LoginPage();
  }
}
