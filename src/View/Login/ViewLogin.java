package View.Login;

import Controller.ControllerLogin;
import javax.swing.*;

public class ViewLogin extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private ControllerLogin controller;

    public ViewLogin() {
        initComponents();

        controller = new ControllerLogin(this);

        btnLogin.addActionListener(e -> {
            controller.login();
        });
    }

    private void initComponents() {

        setTitle("Login - Project Management System");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitle = new JLabel("PROJECT MANAGEMENT SYSTEM");
        lblTitle.setBounds(80, 20, 250, 25);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 70, 100, 25);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 70, 180, 25);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 110, 100, 25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 110, 180, 25);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 160, 100, 30);

        add(lblTitle);
        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);

        setVisible(true);
    }

    // Getter untuk Controller
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }
}