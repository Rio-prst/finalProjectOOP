package Controller;

import Model.Task.*;
import View.Dashboard.ViewDashboardPM;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerTask {

    private ViewDashboardPM viewPM;
    private InterfaceDAOTask daoTask;
    private List<ModelTask> listTask;
    private int currentProjectId = -1; // Menyimpan ID proyek yang sedang dipilih

    public ControllerTask(ViewDashboardPM view) {
        this.viewPM = view;
        this.daoTask = new DAOTask();
    }

    // Mengubah fungsi render agar wajib menerima ID Proyek yang aktif
    public void renderTable(int projectId) {
        this.currentProjectId = projectId;
        
        // Panggil fungsi DAO yang sudah kamu modifikasi untuk filter berdasar Project ID
        // Contoh: listTask = daoTask.getTasksByProjectId(projectId);
        listTask = daoTask.getAll(); // SEMENTARA: Ganti dengan fungsi filter proyekmu jika DAO sudah siap
        
        ModelTableTask model = new ModelTableTask(listTask);
        viewPM.getTabelTugas().setModel(model);
    }

    public void insertTask() {
        if (currentProjectId == -1) {
            JOptionPane.showMessageDialog(viewPM, "Pilih Proyek terlebih dahulu di Tab Project!");
            return;
        }

        String judul = viewPM.getTxtJudul().getText();
        String tipe = viewPM.getCbTipe().getSelectedItem().toString();
        String deadline = viewPM.getTxtDeadlineTask().getText();
        String lampiran = viewPM.getTxtLampiran().getText();

        if (judul.isEmpty() || deadline.isEmpty()) {
            JOptionPane.showMessageDialog(viewPM, "Judul dan Deadline wajib diisi!");
            return;
        }

        ModelTask task;
        if (tipe.equalsIgnoreCase("DEVELOPMENT")) {
            DevelopmentTask dev = new DevelopmentTask();
            dev.setRepositoryUrl(lampiran);
            task = dev;
        } else {
            DesignTask des = new DesignTask();
            des.setFigmaLink(lampiran);
            task = des;
        }

        task.setProjectId(currentProjectId); // Set dinamis berdasarkan proyek yang dipilih!
        task.setUserId(tipe.equalsIgnoreCase("DEVELOPMENT") ? 2 : 3);
        task.setJudul(judul);
        task.setDeadline(deadline);
        task.setStatus("To Do");

        daoTask.insert(task);
        JOptionPane.showMessageDialog(viewPM, "Task berhasil ditambahkan ke Proyek ini!");
        renderTable(currentProjectId);
        clearForm();
    }

    // MENERIMA AKSI DARI POP-UP JDIALOG EDIT FORM
    public void updateTask(int idAsli, String judul, String deadline, String lampiran) {
        try {
            String query = "UPDATE tasks SET judul=?, deadline=?, lampiran_spesifik=? WHERE id=?;";
            java.sql.PreparedStatement st = Config.Connector.Connect().prepareStatement(query);
            st.setString(1, judul);
            st.setString(2, deadline);
            st.setString(3, lampiran);
            st.setInt(4, idAsli);
            st.executeUpdate();
            st.close();
            
            JOptionPane.showMessageDialog(viewPM, "Task berhasil diperbarui!");
            renderTable(currentProjectId);
        } catch (Exception e) {
            System.out.println("Update Task Error: " + e.getMessage());
        }
    }

    public void markAsDone(int id) {
        daoTask.updateStatus(id, "Done");
        JOptionPane.showMessageDialog(viewPM, "Task diselesaikan!");
        renderTable(currentProjectId);
    }

    public void deleteTask(int id) {
        daoTask.delete(id);
        JOptionPane.showMessageDialog(viewPM, "Task berhasil dihapus!");
        renderTable(currentProjectId);
    }
    
    private void clearForm() {
        viewPM.getTxtJudul().setText("");
        viewPM.getTxtDeadlineTask().setText("");
        viewPM.getTxtLampiran().setText("");
    }
}