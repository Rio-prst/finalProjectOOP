package Controller;

import Model.Task.*;
import View.Dashboard.ViewDashboardPM; 
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerTask {
    private ViewDashboardPM view;
    private InterfaceDAOTask daoTask;
    private List<ModelTask> listTask;

    public ControllerTask(ViewDashboardPM view) {
        this.view = view;
        this.daoTask = new DAOTask();
    }

    public void renderTable() {
        listTask = daoTask.getAll();
        ModelTableTask modelTable = new ModelTableTask(listTask);
        view.getTabelTugas().setModel(modelTable); 
    }

    public void insertTask() {
        String judul = view.getTxtJudul().getText(); // Mengambil input judul dari UI
        String tipe = view.getCbTipe().getSelectedItem().toString(); // Mengambil input tipe dari dropdown UI
        String lampiran = view.getTxtLampiran().getText(); // Mengambil input link lampiran dari UI
        
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
        
        task.setProjectId(1);
        task.setUserId(2);
        task.setJudul(judul);
        task.setStatus("To Do");

        daoTask.insert(task);
        JOptionPane.showMessageDialog(view, "Tugas Baru Berhasil Ditambahkan!");
        renderTable(); 
    }

    public void markAsDone(int idSelectedTask) {
        daoTask.updateStatus(idSelectedTask, "Done");
        JOptionPane.showMessageDialog(view, "Status Tugas Berhasil Diperbarui!");
        renderTable();
    }

    public void deleteTask(int idSelectedTask) {
        daoTask.delete(idSelectedTask);
        JOptionPane.showMessageDialog(view, "Tugas Berhasil Dihapus!");
        renderTable();
    }
}