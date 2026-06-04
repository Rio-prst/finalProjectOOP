package View.Login;

import Controller.ControllerStart;
import javax.swing.*;

public class ViewStart extends JFrame {

    JButton btnLogin = new JButton("Login");
    JButton btnRegister = new JButton("Register");

    ControllerStart controller;

    public ViewStart() {

        setTitle("Welcome");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnLogin.setBounds(80, 40, 120, 30);
        btnRegister.setBounds(80, 90, 120, 30);

        add(btnLogin);
        add(btnRegister);

        controller = new ControllerStart(this);

        btnLogin.addActionListener(e -> controller.openLogin());
        btnRegister.addActionListener(e -> controller.openRegister());
    }

}