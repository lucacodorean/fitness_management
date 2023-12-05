package backend;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class VariablesSingleton {

  private static VariablesSingleton instance;

  public String CONNECTION_URL = "";
  public String CONNECTION_USER = "";
  public String CONNECTION_PASS = "";

  private VariablesSingleton() {
    Properties prop = new Properties();
    try (InputStream input = new FileInputStream("config.properties")) {
      prop.load(input);

      CONNECTION_URL = prop.getProperty("DB_URL");
      CONNECTION_USER = prop.getProperty("DB_USERNAME");
      CONNECTION_PASS = prop.getProperty("DB_PASSWORD");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static VariablesSingleton getInstance() {
    if (instance == null) {
      instance = new VariablesSingleton();
    }

    return instance;
  }
}
