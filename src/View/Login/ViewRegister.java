package View.Login;

import Controller.ControllerRegister;
import javax.swing.*;

public class ViewRegister extends JFrame {

    JLabel lblTitle = new JLabel("REGISTER");
    JLabel lblUsername = new JLabel("Username");
    JLabel lblPassword = new JLabel("Password");
    JLabel lblRole = new JLabel("Role");

    JTextField txtUsername = new JTextField();
    JPasswordField txtPassword = new JPasswordField();

    // ComboBox Role
    String[] roleList = {
        "Developer",
        "Designer",
        "Project Manager"
    };

    JComboBox<String> cbRole = new JComboBox<>(roleList);

    JButton btnRegister = new JButton("Register");
    JButton btnBack = new JButton("Back to Login");

    ControllerRegister controller;

    public ViewRegister() {

        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // TITLE
        lblTitle.setBounds(150, 20, 100, 30);

        // USERNAME
        lblUsername.setBounds(50, 70, 100, 25);
        txtUsername.setBounds(150, 70, 180, 25);

        // PASSWORD
        lblPassword.setBounds(50, 110, 100, 25);
        txtPassword.setBounds(150, 110, 180, 25);

        // ROLE
        lblRole.setBounds(50, 150, 100, 25);
        cbRole.setBounds(150, 150, 180, 25);

        // BUTTON
        btnRegister.setBounds(50, 210, 130, 30);
        btnBack.setBounds(200, 210, 130, 30);

        // ADD COMPONENT
        add(lblTitle);

        add(lblUsername);
        add(txtUsername);

        add(lblPassword);
        add(txtPassword);

        add(lblRole);
        add(cbRole);

        add(btnRegister);
        add(btnBack);

        // CONTROLLER
        controller = new ControllerRegister(this);

        // ACTION BUTTON
        btnRegister.addActionListener(e -> {
            controller.register();
        });

        btnBack.addActionListener(e -> {
            controller.openLogin();
        });
    }

    // GETTER
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JComboBox<String> getCbRole() {
        return cbRole;
    }
}