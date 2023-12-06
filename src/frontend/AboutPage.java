package frontend;

import backend.Router;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AboutPage {

  public static JPanel build(Router router) {
    JPanel panel = new JPanel();
    panel.setBackground(Color.BLUE);
    JButton goToPanel1Button = new JButton("Go to Home");
    panel.add(goToPanel1Button);

    goToPanel1Button.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          router.go("home");
        }
      }
    );
    return panel;
  }
}
