package backend;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DatabaseManager {

    private Connection connection;

    public Connection getConnection() { return this.connection; }

    public DatabaseManager() {
        VariablesSingleton env = VariablesSingleton.getInstance();

        try {
            this.connection = DriverManager.getConnection(
                env.CONNECTION_URL, env.CONNECTION_USER, env.CONNECTION_PASS);

        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public ResultSet selectPreparedSQL(String statement, List<String> parameters) throws SQLException {
        try {
            PreparedStatement command = this.connection.prepareStatement(statement);
            for(int i = 0; i<parameters.size(); i++) {
                command.setString(i+1, parameters.get(i));
            }
            
            return command.executeQuery();
        } catch(SQLException ex) {
            System.err.println(ex.toString());
            return null;
        } 
    }

    public Integer updatePreparedSQL(String statement, List<String> parameters) throws SQLException {
        try {
            PreparedStatement command = this.connection.prepareStatement(statement);
            for(int i = 0; i<parameters.size(); i++) {
                command.setString(i+1, parameters.get(i));
            }
            
            return command.executeUpdate();
        } catch(SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    public ResultSet callableSQL(String statement, List<String> parameters) throws SQLException {
        try {
            CallableStatement command = this.connection.prepareCall(statement);
            for(int i = 0; i<parameters.size(); i++) {
                command.setString(i+1, parameters.get(i));
            }

            return command.executeQuery();
        } catch(SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
}
