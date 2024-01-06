package frontend;

import java.net.URL;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window {

  private Stage stage;
  private static Window instance;

  public Window() {
    stage = new Stage();
    stage.setTitle("Fitness App");
    stage.show();

    instance = this;
  }

  public static Window getInstance() {
    if (instance == null) {
      instance = new Window();
    }

    return instance;
  }

  public void setView(String view) {
    Platform.runLater(() -> {
      try {
        URL viewPath = getClass().getResource("views/" + view + ".fxml");
        FXMLLoader loader = new FXMLLoader(viewPath);
        stage.setScene(new Scene(loader.load()));
      } catch (Exception ex) {
        System.out.println(ex.toString());
      }
    });
  }
}
