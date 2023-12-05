package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Model {

    protected Model() { }
    private static final String WHERE_ID = " where id = ?";

      public Integer create(List<String> columns, List<String> newValues) throws SQLException {
        DatabaseManager database = new DatabaseManager();
        List<String>parameters = new ArrayList<String>();

        
        StringBuilder columnsStr = new StringBuilder();
        StringBuilder parametersStr = new StringBuilder();
        
        for(String currentString : columns) {
            columnsStr.append(currentString + ", ");
        }
        
        for(int i = 0; i<newValues.size(); i++) {
            parameters.add(newValues.get(i));
            parametersStr.append("?, ");
        }

        columnsStr.deleteCharAt(columnsStr.lastIndexOf(","));
        parametersStr.deleteCharAt(parametersStr.lastIndexOf(","));

        return database.updatePreparedSQL("insert into " + this.getClass().getSimpleName().toLowerCase() + 
            "(" + columnsStr +") values (" + parametersStr + ")" , parameters
        );
    }

    public ResultSet find(Integer id) throws SQLException {

        DatabaseManager database = new DatabaseManager();

        List<String>parameters = new ArrayList<String>();
        parameters.add(id.toString());
        ResultSet temp = database.selectPreparedSQL("select * from " + 
            this.getClass().getSimpleName().toLowerCase() + WHERE_ID, parameters
        );

        return temp.next() ? temp : null;
    }

    public Integer update(Integer id, List<String> columns, List<String> newValues) throws SQLException {
        DatabaseManager database = new DatabaseManager();
        List<String>parameters = new ArrayList<String>();

        for(int i = 0; i<newValues.size(); i++) {
            parameters.add(newValues.get(i));
        }

        parameters.add(id.toString());

        StringBuilder columnsParameters = new StringBuilder();
        for(String currentString : columns) {
            columnsParameters.append(currentString + " = ?, ");
        }

        columnsParameters.deleteCharAt(columnsParameters.lastIndexOf(","));

        return database.updatePreparedSQL("update " + this.getClass().getSimpleName().toLowerCase() + 
            " set " + columnsParameters + WHERE_ID , parameters
        );
    }

    public Boolean delete(Integer id) throws SQLException {
        DatabaseManager database = new DatabaseManager();
        List<String>parameters = new ArrayList<String>();
        parameters.add(id.toString());

        database.updatePreparedSQL("delete from " +
            this.getClass().getSimpleName().toLowerCase() + WHERE_ID, parameters
        );

        return this.find(id) == null;
    }
}
