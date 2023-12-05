import java.sql.*;
import backend.*;
import frontend.*;
public class App{

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
        try {
            System.out.println(connectionTest() ? "Connection established" : "Connection error");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        new LoginPage();
    }
}   
