import java.sql.*;
import backend.*;

public class App {

    private static boolean connectionTest() throws SQLException {
        try (Connection connection = DriverManager.getConnection(
            Variables.getConnectionURL(), 
            Variables.getConnectionUSER(), 
            Variables.getConnectionPASS()
        )) { return true;
        }
        catch (SQLException exception) {
            System.out.println(exception.toString());
            return false;
        }
    }
    public static void main(String[] args) throws SQLException {
        System.out.println(connectionTest() ? "Connection established" : "Connection error");

        Employee myWEmployee = new Employee("super.admin@myfitness.com", "parola");
        System.out.println("Logged as: " + myWEmployee.getFirstName() + " " + myWEmployee.getLastName());
    }
}
