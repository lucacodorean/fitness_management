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

        panel.add(new JLabel("Welcome to your fitness app!"));

        JButton loginButton = new JButton("Login"); loginButton.addActionListener(this);

        panel.add(emailTextField, BorderLayout.CENTER);
        panel.add(passwordTextField, BorderLayout.CENTER);
        panel.add(loginButton, BorderLayout.CENTER);


        JFrame frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login page");
        frame.pack();
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
