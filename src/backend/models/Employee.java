package backend.models;

import java.sql.*;
import java.util.List;

import backend.DatabaseManager;
import backend.VariablesSingleton;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Employee extends Model {

  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Float rating;
  private Integer wage;
  private Integer role;
  private LocalDateTime employementDate;

  public Employee() {
    this.settableName("employees");
  }

  public Employee(String email, String password) throws Exception {
    if (!Employee.login(email, password)) {
      System.err.println("Invalid email or password.");
      this.settableName("employees");
      throw new Exception("Invalid email or password");
    }

    System.out.println("Welcome, " + email + "!");

    DatabaseManager database = new DatabaseManager();
    ArrayList<String> parameters = new ArrayList<String>();
    parameters.add(email);

    ResultSet temp = database.selectPreparedSQL("select id from " + new Employee().getTable() + " where email = ?", parameters);

    if (temp.next()) {
      Employee tempEmployee = Employee.getDetails(temp.getInt(1));
      if (tempEmployee != null) {
        setFirstName(tempEmployee.getFirstName());
        setLastName(tempEmployee.getLastName());
        setEmail(tempEmployee.getEmail());
        setPassword(tempEmployee.getPassword());
        setWage(tempEmployee.getWage());
        setRating(tempEmployee.getRating());
        setEmployement(tempEmployee.getEmploymentDate());
        setRole(tempEmployee.getRole());
      }
    }
  }

  protected Employee(
      Integer id, String firstName, String lastName, String email, String password,
      Float rating, Integer wage, Integer role, LocalDateTime employementDate) {
    this.setId(id);
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setEmail(email);
    this.setPassword(password);
    this.setRating(rating);
    this.setWage(wage);
    this.setRole(role);
    this.setEmployement(employementDate);
    this.settableName("employees");
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

  public void setId(Integer id) {
    this.id = id;
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

  public LocalDateTime getEmploymentDate() {
    return employementDate;
  }

  public Integer getId() {
    return id;
  }

  private static boolean login(String email, String password)
      throws SQLException {
    VariablesSingleton env = VariablesSingleton.getInstance();
    Connection connection = DriverManager.getConnection(
        env.CONNECTION_URL,
        env.CONNECTION_USER,
        env.CONNECTION_PASS);

    Boolean status = false;

    CallableStatement statement = connection.prepareCall(
        " { call login(?, ?, ?) }");
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

  public static Employee getDetails(Integer id) throws SQLException {
    ResultSet temp = new Employee().find(id);

    return temp != null ? new Employee(
        temp.getInt(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getString(5),
        temp.getFloat(7), temp.getInt(6), temp.getInt(9),
        LocalDateTime.parse(
            temp.getString(8),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        : null;
  }

  public static List<Feedback> getFeedback(Integer id) throws SQLException {
    ArrayList<Feedback> result = new ArrayList<Feedback>();
    ArrayList<String> parameters = new ArrayList<String>();
    parameters.add(id.toString());

    DatabaseManager databaseManager = new DatabaseManager();
    ResultSet temp = databaseManager.selectPreparedSQL("select * from " + new Feedback().getTable() + " where employee_id = ?", parameters);
    temp.next();

    while (temp.next()) {
      result.add(Feedback.getDetails(temp.getInt(1)));
    }

    return result;
  }

  public static List<Schedule> getSchedule(Integer id) throws SQLException {
    ArrayList<Schedule> result = new ArrayList<Schedule>();
    ArrayList<String> parameters = new ArrayList<String>();
    parameters.add(id.toString());

    DatabaseManager databaseManager = new DatabaseManager();
    ResultSet temp = databaseManager.selectPreparedSQL("select * from " + new Schedule().getTable() + " where trainer_id = ?", parameters);
    temp.next();

    while (temp.next()) {
      result.add(Schedule.getDetails(temp.getInt(1)));
    }

    return result;
  }

  @Override
  public String toString() {
    return "Employee [id=" + this.getId() + ", firstName=" + this.getFirstName() +
        ", lastName=" + this.getLastName() + ", email=" + this.getEmail() +
        ", password=" + this.getPassword() + ", rating=" + this.getRating() +
        ", wage=" + this.getWage() + ", role=" + this.getRole() + ", employementDate=" + this.getEmploymentDate()
        + "]";
  }
}
