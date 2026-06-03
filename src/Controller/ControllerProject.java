package Controller;

import Model.Project.*;
import View.Dashboard.ViewDashboardPM; 
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
}