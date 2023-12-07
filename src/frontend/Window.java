package frontend;

import backend.StateManager;
import backend.routes.Router;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    frame.setSize(1280, 1024);
    frame.setLayout(new BorderLayout());
    frame.setResizable(false);
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
    if (StateManager.getInstance().isAuth()) {
      frame.setJMenuBar(getMenuBar());
    } else frame.setJMenuBar(null);
    frame.revalidate();
    frame.repaint();
  }

  private JMenuBar getMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Cont");

    JMenuItem nume = new JMenuItem(
      StateManager.getInstance().getAuth().getFirstName()
    );
    JMenuItem logout = new JMenuItem("Iesi din cont");
    logout.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Router.getInstance().go("logout");
        }
      }
    );

    menu.add(nume);
    menu.addSeparator();
    menu.add(logout);

    menuBar.add(menu);
    return menuBar;
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
