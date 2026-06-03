package Controller;

import Model.Task.*;
import View.Dashboard.ViewDashboardPM;
import View.Dashboard.ViewDashboardMember;

import java.util.List;
import javax.swing.JOptionPane;

public class ControllerTask {

    private ViewDashboardPM viewPM;
    private ViewDashboardMember viewMember;

    private InterfaceDAOTask daoTask;
    private List<ModelTask> listTask;

    // =========================
    // CONSTRUCTOR PM
    // =========================
    public ControllerTask(ViewDashboardPM view) {
        this.viewPM = view;
        this.daoTask = new DAOTask();
    }

    // =========================
    // CONSTRUCTOR MEMBER
    // =========================
    public ControllerTask(ViewDashboardMember view) {
        this.viewMember = view;
        this.daoTask = new DAOTask();
    }

    // =========================
    // RENDER TABLE
    // =========================
    public void renderTable() {

        listTask = daoTask.getAll();
        ModelTableTask model = new ModelTableTask(listTask);

        if (viewPM != null) {
            viewPM.getTabelTugas().setModel(model);
        }

        if (viewMember != null) {
            viewMember.getTabelTugas().setModel(model);
        }
    }

    // =========================
    // INSERT TASK (ONLY PM)
    // =========================
    public void insertTask() {

        if (viewPM == null) return;

        String judul = viewPM.getTxtJudul().getText();
        String tipe = viewPM.getCbTipe().getSelectedItem().toString();
        String lampiran = viewPM.getTxtLampiran().getText();

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

        JOptionPane.showMessageDialog(viewPM, "Task berhasil ditambahkan!");

        renderTable();
    }

    // =========================
    // MARK AS DONE (PM & MEMBER)
    // =========================
    public void markAsDone(int id) {

        daoTask.updateStatus(id, "Done");

        JOptionPane.showMessageDialog(
                (viewPM != null ? viewPM : viewMember),
                "Task berhasil diselesaikan!"
        );

        renderTable();
    }

    // =========================
    // DELETE TASK (ONLY PM)
    // =========================
    public void deleteTask(int id) {

        if (viewPM == null) return;

        daoTask.delete(id);

        JOptionPane.showMessageDialog(viewPM, "Task berhasil dihapus!");

        renderTable();
    }
}