package frontend;

import java.awt.*;
import javax.swing.*;

public class Window {

  private JFrame frame;
  private JPanel currentPanel;
  private static Window instance;

  public Window() {
    currentPanel = new JPanel();
    currentPanel.add(
      new JLabel("Welcome to your fitness app!"),
      BorderLayout.CENTER
    );

    frame = new JFrame("Fitness App");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(850, 600);
    frame.setLayout(new BorderLayout());
    frame.add(currentPanel, BorderLayout.CENTER);
    frame.setVisible(true);
    instance = this;
  }

  public static Window getInstance() {
    if (instance == null) {
      instance = new Window();
    }

    return instance;
  }

  public void changePanel(JPanel newPanel) {
    frame.getContentPane().remove(currentPanel);
    currentPanel = newPanel;
    frame.add(currentPanel, BorderLayout.CENTER);
    frame.revalidate();
    frame.repaint();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
      new Runnable() {
        public void run() {
          new Window();
        }
      }
    );
  }
}
