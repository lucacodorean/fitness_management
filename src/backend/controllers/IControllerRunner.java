package backend.controllers;

import backend.contracts.IRequest;

public interface IControllerRunner {
  public void run(IRequest request);
}
