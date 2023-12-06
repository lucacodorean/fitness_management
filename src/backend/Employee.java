package backend;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Employee extends Model{

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Float rating;
  private Integer wage;
  private Integer role;
  private LocalDateTime employementDate;

  public Employee() {}

  public Employee(String email, String password) throws SQLException {
    if (!Employee.login(email, password)) {
      System.err.println("Invalid email or password.");
      return;
    }

    System.out.println("Welcome, " + email + "!");
    String[] data = getEmployeeData(email);

    if (Arrays.equals(data, new String[0])) {
      System.err.println("Invalid email.");
      return;
    }

    setFirstName(data[0]);
    setLastName(data[1]);
    setEmail(data[2]);
    setPassword(data[3]);
    setWage(Integer.parseInt(data[4]));
    setRating(Float.parseFloat(data[5]));
    setEmployement(
      LocalDateTime.parse(
        data[6],
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
      )
    );
    setRole(Integer.parseInt(data[7]));
  }

  public void setWage(Integer wage) {
    this.wage = wage;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setRating(Float rating) {
    this.rating = rating;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setEmployement(LocalDateTime time) {
    this.employementDate = time;
  }

  public Integer getWage() {
    return wage;
  }

  public Integer getRole() {
    return role;
  }

  public String getEmail() {
    return email;
  }

  public Float getRating() {
    return rating;
  }

  public String getPassword() {
    return password;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public LocalDateTime getEmployment() {
    return employementDate;
  }

  private static boolean login(String email, String password)
    throws SQLException {
    VariablesSingleton env = VariablesSingleton.getInstance();
    Connection connection = DriverManager.getConnection(
      env.CONNECTION_URL,
      env.CONNECTION_USER,
      env.CONNECTION_PASS
    );

    Boolean status = false;

    CallableStatement statement = connection.prepareCall(
      " { call login(?, ?, ?) }"
    );
    statement.setString(1, email);
    statement.setString(2, password);
    statement.setBoolean(3, status);

    try {
      statement.executeQuery();
      status = statement.getBoolean(3);
    } finally {
      statement.close();
      connection.close();
    }

    return status;
  }

  private static String[] getEmployeeData(String email) throws SQLException {
    String[] result = new String[8];

    VariablesSingleton env = VariablesSingleton.getInstance();
    Connection connection = DriverManager.getConnection(
      env.CONNECTION_URL,
      env.CONNECTION_USER,
      env.CONNECTION_PASS
    );

    PreparedStatement statement = connection.prepareStatement(
      "select * from employee where email = ?"
    );
    statement.setString(1, email);

    try {
      ResultSet output = statement.executeQuery();
      if (output.next()) {
        result[0] = output.getString("firstname");
        result[1] = output.getString("lastname");
        result[2] = output.getString("email");
        result[3] = output.getString("pass");
        result[4] = output.getString("wage");
        result[5] = output.getString("rating");
        result[6] = output.getString("employed_at");
        result[7] = output.getString("role_id");
      }
    } catch (SQLException exception) {
      System.out.println(exception.toString());
      return new String[0];
    } finally {
      System.out.println("Employee data: ");
      for (int i = 0; i < 8; i++) {
        System.out.println("\t" + result[i]);
      }

      statement.close();
      connection.close();
    }
    return result;
  }
}
