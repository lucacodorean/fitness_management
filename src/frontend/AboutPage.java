package frontend;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import backend.routes.Router;

public class AboutPage {

  private AboutPage() { }

  public static JPanel build(Router router) {
    JPanel panel = new JPanel();
    panel.setBackground(Color.BLUE);
    JButton goToPanel1Button = new JButton("Go to Home");
    panel.add(goToPanel1Button);

    goToPanel1Button.addActionListener(
      e -> router.go("home")
    );
    return panel;
  }
}
