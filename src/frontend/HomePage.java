package frontend;

import backend.models.Employee;
import backend.routes.Router;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePage {

  public static JPanel build(Router router, Employee auth) {
    JPanel panel = new JPanel();
    panel.setBackground(Color.RED);
    JButton goToPanel1Button = new JButton("Go to About");
    JButton logout = new JButton("Iesi din cont");
    panel.add(goToPanel1Button);
    panel.add(logout);
    panel.add(
      new JLabel("Buna ziua, " + auth.getFirstName() + " " + auth.getLastName())
    );

    goToPanel1Button.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          router.go("about");
        }
      }
    );

    logout.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          router.go("logout");
        }
      }
    );
    return panel;
  }
}
