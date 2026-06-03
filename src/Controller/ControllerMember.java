package Controller;

import Model.Task.*;
import Model.User.ModelUser;
import View.Dashboard.ViewDashboardMember;
import View.Login.ViewLogin;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerMember {
    private ViewDashboardMember view;
    private InterfaceDAOTask daoTask;
    private List<ModelTask> listTask;
    private ModelUser currentUser; // Menyimpan data user yang sedang login

    public ControllerMember(ViewDashboardMember view, ModelUser user) {
        this.view = view;
        this.daoTask = new DAOTask();
        this.currentUser = user;
    }

    // Mengisi informasi komponen UI dashboard member
    public void initDashboard() {
        // 1. Set text label profil di UI temanmu
        view.getLblWelcome().setText("Selamat Datang, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
        
        // 2. Render tabel terfilter
        renderTable();
    }

    public void renderTable() {
        // 1. Ambil teks proyek yang sedang dipilih oleh member dari Dropdown JComboBox
        String namaProjectTerpilih = view.getCbPilihProjectMember().getSelectedItem().toString();

        // Jika member belum memilih proyek board, kosongkan tabel atau return saja
        if (namaProjectTerpilih.equals("-- Pilih Project Board --")) {
            return;
        }

        // Tentukan ID proyek berdasarkan pilihan dropdown (Bisa disesuaikan dengan database-mu)
        int projectId = namaProjectTerpilih.contains("Rumah Sakit") ? 1 : 2;

        // 2. Jalankan Query Filter Spesifik: BERDASARKAN USER ID DAN PROJECT ID
        // Pastikan di DAOTask kamu sudah membuat fungsi getTasksByProjectAndUser(projectId, currentUser.getId())
        listTask = daoTask.getTasksByUserId(currentUser.getId()); // Ganti dengan query filter ganda jika sudah ada

        // Proses filter list agar hanya menampilkan tugas yang project_id nya cocok
        List<ModelTask> filteredList = listTask.stream()
                .filter(t -> t.getProjectId() == projectId)
                .toList();

        // 3. JIKA TUGAS KOSONG: Tampilkan pesan peringatan "Belum ada tugas"
        if (filteredList.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Belum ada tugas yang ditugaskan untukmu di Project Board ini!", "Workspace Kosong", JOptionPane.INFORMATION_MESSAGE);
        }

        // 4. Set ke dalam JTable member
        ModelTableTask modelTable = new ModelTableTask(filteredList);
        view.getTabelTugasMember().setModel(modelTable);

        // 5. Hitung ulang statistik mini khusus proyek board yang dipilih
        long countToDo = filteredList.stream().filter(t -> t.getStatus().equalsIgnoreCase("To Do") || t.getStatus().equalsIgnoreCase("In Progress")).count();
        long countDone = filteredList.stream().filter(t -> t.getStatus().equalsIgnoreCase("Done")).count();

        view.getLblStats().setText("Tugas Tersisa: " + countToDo + "  |  Tugas Selesai: " + countDone);
    }

    public void markAsDone(int idSelectedTask) {
        daoTask.updateStatus(idSelectedTask, "Done");
        JOptionPane.showMessageDialog(view, "Tugas berhasil diselesaikan! Tetap semangat.");
        renderTable(); // Refresh tabel dan statistik
    }

    public void logout() {
        int opsi = JOptionPane.showConfirmDialog(view, "Apakah kamu yakin ingin logout?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.YES_OPTION) {
            view.dispose(); // Tutup dashboard
            new ViewLogin().setVisible(true); // Buka kembali menu login
        }
    }
    
    public void undoTaskStatus(int idSelectedTask) {
        daoTask.updateStatus(idSelectedTask, "To Do");
        JOptionPane.showMessageDialog(view, "Task dikembalikan ke status 'To Do'!");
        renderTable(); 
    }
}