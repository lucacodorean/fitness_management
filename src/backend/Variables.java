package backend;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Variables implements Closeable {

  public String CONNECTION_URL = "";
  public String CONNECTION_USER = "";
  public String CONNECTION_PASS = "";

  public Variables() {
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

  @Override
  public void close() throws IOException {
    // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'close'");
  }
}
