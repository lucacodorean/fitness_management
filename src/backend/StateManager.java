package backend;

import backend.models.Employee;

public class StateManager {

  private static StateManager instance;

  private Employee auth = null;

  private StateManager() {}

  public static StateManager getInstance() {
    if (instance == null) {
      instance = new StateManager();
    }

    return instance;
  }

  public Employee getAuth() {
    return auth;
  }

  public void setAuth(Employee auth) {
    this.auth = auth;
  }

  public boolean isAuth() {
    return this.auth != null;
  }
}
