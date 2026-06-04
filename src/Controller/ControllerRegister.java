package Controller;

import Model.User.DAOUser;
import Model.User.InterfaceDAOUser;
import Model.User.ModelUser;
import View.Login.ViewLogin;
import View.Login.ViewRegister;
import javax.swing.JOptionPane;

public class ControllerRegister {

    private ViewRegister view;
    private InterfaceDAOUser daoUser;

    // CONSTRUCTOR
    public ControllerRegister(ViewRegister view) {
        this.view = view;
        this.daoUser = new DAOUser();
    }

    // METHOD REGISTER
    public void register() {

        String username = view.getTxtUsername().getText().trim();
        String password = new String(
                view.getTxtPassword().getPassword()
        ).trim();

        // Ambil role dari ComboBox
        String role = view.getCbRole()
                          .getSelectedItem()
                          .toString();

        // VALIDASI INPUT KOSONG
        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    view,
                    "Username dan Password tidak boleh kosong!"
            );

            return;
        }

        // VALIDASI PANJANG PASSWORD
        if (password.length() < 3) {

            JOptionPane.showMessageDialog(
                    view,
                    "Password minimal 3 karakter!"
            );

            return;
        }

        // VALIDASI ROLE
        if (!role.equals("Developer")
                && !role.equals("Designer")
                && !role.equals("Project Manager")) {

            JOptionPane.showMessageDialog(
                    view,
                    "Role tidak valid!"
            );

            return;
        }

        // MEMBUAT OBJECT USER
        ModelUser user = new ModelUser();

        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        // INSERT KE DATABASE
        daoUser.insert(user);

        JOptionPane.showMessageDialog(
                view,
                "Register berhasil sebagai " + role + "!"
        );

        // BUKA LOGIN
        ViewLogin login = new ViewLogin();
        login.setVisible(true);

        // TUTUP REGISTER
        view.dispose();
    }

    // METHOD KEMBALI KE LOGIN
    public void openLogin() {

        ViewLogin login = new ViewLogin();
        login.setVisible(true);

        view.dispose();
    }
}