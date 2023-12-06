package backend.controllers;

import backend.IRequest;

public class LoginRequest implements IRequest {

  private String email;
  private String password;

  public LoginRequest(String emailString, String passwordString) {
    setEmail(emailString);
    setPassword(passwordString);
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
