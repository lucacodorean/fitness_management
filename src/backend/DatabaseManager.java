package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DatabaseManager {

    private Connection connection;

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
        PreparedStatement statement2 = this.connection.prepareStatement(statement);
        for(int i = 0; i<parameters.size(); i++) {
            statement2.setString(i+1, parameters.get(i));
        }
        
        return statement2.executeQuery();
    }

    public Integer updatePreparedSQL(String statement, List<String> parameters) throws SQLException {
        PreparedStatement statement2 = this.connection.prepareStatement(statement);
        for(int i = 0; i<parameters.size(); i++) {
            statement2.setString(i+1, parameters.get(i));
        }
        
        return statement2.executeUpdate();
    }
}
