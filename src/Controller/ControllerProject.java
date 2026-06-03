package Controller;

import Model.Project.*;
import View.Dashboard.ViewDashboardPM; 
import View.Login.ViewLogin;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerProject {
    private ViewDashboardPM view;
    private InterfaceDAOProject daoProject;
    private List<ModelProject> listProject;

    public ControllerProject(ViewDashboardPM view) {
        this.view = view;
        this.daoProject = new DAOProject();
    }

    public void renderTable() {
        listProject = daoProject.getAll();
        ModelTableProject modelTable = new ModelTableProject(listProject);
        view.getTabelProyek().setModel(modelTable);
    }

    // Fungsi mengambil ID asli dari baris yang dipilih
    public int getSelectedProjectId(int selectedRow) {
        if (selectedRow == -1) return -1;
        ModelTableProject model = (ModelTableProject) view.getTabelProyek().getModel();
        return model.getActualId(selectedRow);
    }

    public void insertProject() {
        String nama = view.getTxtNamaProyek().getText();
        String deskripsi = view.getTxtDeskripsiProyek().getText();

        if(nama.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nama proyek tidak boleh kosong!");
            return;
        }

        ModelProject proj = new ModelProject();
        proj.setNamaProyek(nama);
        proj.setDeskripsi(deskripsi);

        daoProject.insert(proj);
        JOptionPane.showMessageDialog(view, "Proyek Berhasil Dibuat!");
        renderTable(); 
    }

    public void deleteProject(int idSelectedProject) {
        daoProject.delete(idSelectedProject);
        JOptionPane.showMessageDialog(view, "Proyek Berhasil Dihapus!");
        renderTable();
    }

    // FITUR BARU ALAT TRELLO: Tambah Anggota ke Proyek Terpilih
    public void assignMember(int projectId, String developerName) {
        int userId = developerName.equalsIgnoreCase("Budi (Developer)") ? 2 : 3;
        daoProject.addMemberToProject(projectId, userId);
        JOptionPane.showMessageDialog(view, "Anggota tim berhasil dimasukkan ke dalam Proyek!");
    }

    // FITUR BARU: Logout PM
    public void logout() {
        int opsi = JOptionPane.showConfirmDialog(view, "Apakah yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.YES_OPTION) {
            view.dispose();
            new ViewLogin().setVisible(true);
        }
    }
    
    // FITUR BARU: Menampilkan list anggota yang terdaftar di proyek yang dipilih
    public void showProjectMembers(int projectId) {
        try {
            String query = "SELECT u.username FROM project_members pm JOIN users u ON pm.user_id = u.id WHERE pm.project_id = ?;";
            java.sql.PreparedStatement st = Config.Connector.Connect().prepareStatement(query);
            st.setInt(1, projectId);
            java.sql.ResultSet rs = st.executeQuery();

            StringBuilder listNama = new StringBuilder("Anggota Tim di Proyek ini:\n");
            int nomor = 1;
            while (rs.next()) {
                listNama.append(nomor).append(". ").append(rs.getString("username")).append("\n");
                nomor++;
            }
            st.close();

            if (nomor == 1) {
                JOptionPane.showMessageDialog(view, "Proyek ini belum memiliki anggota tim.");
            } else {
                JOptionPane.showMessageDialog(view, listNama.toString(), "Detail Tim Kolaborasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Gagal memuat daftar anggota: " + e.getMessage());
        }
}
}