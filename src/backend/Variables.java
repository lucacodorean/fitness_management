package backend;

public class Variables {
    private Variables() { }

    private static final String CONNECTION_URL  = "jdbc:mysql://localhost:3306/fitness_management_database";
    private static final String CONNECTION_USER = "root";
    private static final String CONNECTION_PASS = "pass";

    public static String getConnectionURL()  { return Variables.CONNECTION_URL;  }
    public static String getConnectionUSER() { return Variables.CONNECTION_USER; }
    public static String getConnectionPASS() { return Variables.CONNECTION_PASS; }

    public static final String[] NULL_ARRAY = {};
}
