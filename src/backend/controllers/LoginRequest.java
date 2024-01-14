package backend.controllers;

import java.sql.ResultSet;
import java.util.ArrayList;

import backend.DatabaseManager;
import backend.StateManager;
import backend.contracts.IRequest;
import backend.models.Employee;

public class LoginRequest implements IRequest {

  private String email;
  private String password;

  public LoginRequest(String emailString, String passwordString) {
    setEmail(emailString);
    setPassword(passwordString);
    try {
      
      ArrayList<String> parameters = new ArrayList<>();
      parameters.add(emailString);
      parameters.add(passwordString);
      ResultSet rs = new DatabaseManager().selectPreparedSQL("select * from employee where email = ? and pass = ?", parameters);
      rs.next();
      
      StateManager.getInstance().setAuth(Employee.getDetails(rs.getInt("id")));
      System.out.println(StateManager.getInstance().getAuth());
    } catch (Exception ex) {
      System.out.println("Eroare la conectare.\n" + ex.toString());
    }
  }

  private void setEmail(String email) {
    this.email = email;
  }

  private void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }
}
