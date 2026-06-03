package Model.Task;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTableTask extends AbstractTableModel {
    private List<ModelTask> daftarTask;
    private String kolom[] = {"ID", "Judul Tugas", "Status", "Tipe Tugas", "Lampiran"};
    
    public ModelTableTask(List<ModelTask> daftarTask) {
        this.daftarTask = daftarTask;
    }

    @Override
    public int getRowCount() { return daftarTask.size(); }

    @Override
    public int getColumnCount() { return kolom.length; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return daftarTask.get(rowIndex).getId();
            case 1: return daftarTask.get(rowIndex).getJudul();
            case 2: return daftarTask.get(rowIndex).getStatus();
            case 3: return daftarTask.get(rowIndex).getTipeTugas();
            case 4: return daftarTask.get(rowIndex).getLampiranSpesifik();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) { return kolom[columnIndex]; }
}