package frontend;

import backend.*;
import backend.controllers.LoginRequest;
import backend.routes.Router;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginPage {

  public static JPanel build(Router router) {
    return build(router, false);
  }

  public static JPanel build(Router router, Boolean loginError) {
    JTextField emailTextField = new JTextField();
    JPasswordField passwordTextField = new JPasswordField();

    emailTextField.setPreferredSize(new Dimension(200, 25));
    passwordTextField.setPreferredSize(new Dimension(200, 25));

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(0, 2));
    panel.setBackground(Color.WHITE);

    JPanel form = new JPanel();
    form.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.gridx = 0;
    // gbc.weighty = 1;

    JLabel label = new JLabel();
    ImageIcon icon = new ImageIcon("lib/assets/logo.jpeg");
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setIcon(icon);
    panel.add(label, BorderLayout.CENTER);

    JPanel panelEmail = new JPanel();
    panelEmail.add(new JLabel(" Email:"), BorderLayout.PAGE_START);
    panelEmail.add(emailTextField);

    JPanel panelPassword = new JPanel();
    panelPassword.add(new JLabel("Parola:"), BorderLayout.PAGE_START);
    panelPassword.add(passwordTextField);

    JButton loginButton = new JButton("Login");

    JPanel error = new JPanel();
    JLabel errorMessage = new JLabel("Datele introduse sunt gresite!");
    errorMessage.setForeground(Color.RED);
    error.add(errorMessage);
    if (loginError) {
      form.add(error, gbc);
    }

    form.add(panelEmail, gbc);
    form.add(panelPassword, gbc);
    form.add(loginButton, gbc);
    panel.add(form);

    loginButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          LoginRequest request = new LoginRequest(
            emailTextField.getText(),
            new String(passwordTextField.getPassword())
          );

          router.go("login", request);
        }
      }
    );

    return panel;
  }
}
