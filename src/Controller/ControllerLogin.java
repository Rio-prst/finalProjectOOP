package Controller;

import Model.User.DAOUser;
import Model.User.InterfaceDAOUser;
import Model.User.ModelUser;
import View.Login.ViewLogin;
import View.Dashboard.ViewDashboardPM;
import View.Dashboard.ViewDashboardMember;
import javax.swing.JOptionPane;

public class ControllerLogin {
    private ViewLogin view;
    private InterfaceDAOUser daoUser;

    public ControllerLogin(ViewLogin view) {
        this.view = view;
        this.daoUser = new DAOUser();
    }

    public void login() {
        String username = view.getTxtUsername().getText();
        // Mengubah JPasswordField menjadi String
        String password = new String(view.getTxtPassword().getPassword());

        // Validasi input kosong
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Username dan Password tidak boleh kosong!");
            return;
        }

        // Cek ke database via DAO
        ModelUser userValid = daoUser.loginCheck(username, password);

        if (userValid != null) {
            JOptionPane.showMessageDialog(view, "Login Berhasil! Selamat Datang, " + userValid.getUsername());
            
            // Tutup halaman login
            view.dispose();

            // Logika Role-Based UI (Membuka dashboard sesuai Role di database)
            if (userValid.getRole().equalsIgnoreCase("Project Manager")) {
                ViewDashboardPM dashboardPM = new ViewDashboardPM();
                dashboardPM.setVisible(true);
            } else {
                // PERUBAHAN DI SINI: Kirim userValid ke dalam constructor ViewDashboardMember
                ViewDashboardMember dashboardMember = new ViewDashboardMember(userValid);
                dashboardMember.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Username atau Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }
}