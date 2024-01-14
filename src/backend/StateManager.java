package backend;

import backend.models.Employee;

public class StateManager {

  private static final StateManager instance = new StateManager();
  private Employee auth;

  private StateManager() {}

  public static StateManager getInstance() { return instance; }
  public void setAuth(Employee auth) { this.auth = auth; }
  public boolean isAuth() { return this.auth != null; }
  public Employee getAuth() { return auth; }
}
