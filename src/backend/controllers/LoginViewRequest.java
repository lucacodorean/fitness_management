package backend.controllers;

import backend.contracts.IRequest;

public class LoginViewRequest implements IRequest {

  private Boolean hasError;

  public LoginViewRequest(Boolean hasError) {
    this.hasError = hasError;
  }

  public Boolean getError() {
    return this.hasError;
  }
}
