package Model.Project;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTableProject extends AbstractTableModel {
    private List<ModelProject> daftarProject;
    private String kolom[] = {"No", "Nama Proyek", "Deskripsi"};

    public ModelTableProject(List<ModelProject> daftarProject) {
        this.daftarProject = daftarProject;
    }

    @Override
    public int getRowCount() { return daftarProject.size(); }
    @Override
    public int getColumnCount() { return kolom.length; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return rowIndex + 1; 
            case 1: return daftarProject.get(rowIndex).getNamaProyek();
            case 2: return daftarProject.get(rowIndex).getDeskripsi();
            default: return null;
        }
    }

    public int getActualId(int rowIndex) {
        return daftarProject.get(rowIndex).getId();
    }

    @Override
    public String getColumnName(int columnIndex) { return kolom[columnIndex]; }
}