package frontend;

import backend.*;
import java.awt.*;
import javax.swing.*;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage implements ActionListener{

    JTextField emailTextField    = new JTextField();
    JTextField passwordTextField = new JTextField();
    public LoginPage() {

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(new JLabel("Welcome to your fitness app!"), BorderLayout.CENTER);

        JButton loginButton = new JButton("Login"); loginButton.addActionListener(this);

        emailTextField.setPreferredSize(new Dimension(200,25));
        passwordTextField.setPreferredSize(new Dimension(200,25));

        JPanel panelEmail = new JPanel();
        panelEmail.add(new JLabel("Email:   "), BorderLayout.PAGE_START);
        panelEmail.add(emailTextField);

        JPanel panelPassword = new JPanel();
        panelPassword.add(new JLabel("Password: "), BorderLayout.PAGE_START);
        panelPassword.add(passwordTextField);

        panel.add(panelEmail, BorderLayout.CENTER);
        panel.add(panelPassword, BorderLayout.CENTER);
        panel.add(loginButton, BorderLayout.CENTER);


        JFrame frame = new JFrame();
        frame.setSize(new Dimension(400, 400));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login page");
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Employee myWEmployee = new Employee();
        try {
            myWEmployee = new Employee(emailTextField.getText(), passwordTextField.getText());
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }
}
