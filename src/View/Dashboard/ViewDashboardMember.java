package View.Dashboard;

import Controller.ControllerMember;
import Model.Task.ModelTableTask;
import Model.User.ModelUser;
import javax.swing.*;
import java.awt.*;

public class ViewDashboardMember extends JFrame {

    private ControllerMember controllerMember;

    // Components
    private JLabel lblWelcome;
    private JLabel lblStats;
    private JComboBox<String> cbPilihProjectMember; // SINKRONISASI ALUR TRELLO
    private JButton btnLogout;
    private JTable tabelTugas;
    private JButton btnDone;
    private JButton btnUndo;

    public ViewDashboardMember(ModelUser userLogon) {
        initComponents();

        controllerMember = new ControllerMember(this, userLogon);
        controllerMember.initDashboard();

        initEvents();
    }

    private void initComponents() {
        setTitle("Dashboard Member (Trello Workspace)");
        setSize(800, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("DASHBOARD MEMBER - WORKSPACE");
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setBounds(20, 15, 300, 25);

        lblWelcome = new JLabel("Selamat Datang, User (Role)");
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 12));
        lblWelcome.setBounds(20, 45, 450, 20);

        lblStats = new JLabel("Tugas Tersisa: 0 | Tugas Selesai: 0");
        lblStats.setFont(new Font("Arial", Font.ITALIC, 12));
        lblStats.setForeground(Color.BLUE);
        lblStats.setBounds(20, 65, 450, 20);

        // ALUR TRELLO: Dropdown Pemilihan Board Project yang Diikuti oleh Member
        JLabel lblPilihProj = new JLabel("Pilih Project Board:");
        lblPilihProj.setBounds(20, 95, 130, 25);
        
        // Sementara diisi dummy board proyek, nanti bisa diisi dinamis dari database relasi project_members
        cbPilihProjectMember = new JComboBox<>(new String[]{"-- Pilih Project Board --", "Sistem Informasi Rumah Sakit", "Aplikasi E-Learning Kampus"});
        cbPilihProjectMember.setBounds(150, 95, 250, 25);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(660, 40, 100, 25);

        tabelTugas = new JTable();
        JScrollPane scroll = new JScrollPane(tabelTugas);
        scroll.setBounds(20, 135, 740, 290); // Y diturunkan ke 135 agar tidak menabrak dropdown project

        btnDone = new JButton("Mark as Done");
        btnDone.setBounds(230, 450, 150, 30);

        btnUndo = new JButton("Undo Task Status");
        btnUndo.setBounds(400, 450, 150, 30);
        btnUndo.setBackground(new Color(255, 204, 204));

        add(title);
        add(lblWelcome);
        add(lblStats);
        add(lblPilihProj);
        add(cbPilihProjectMember);
        add(btnLogout);
        add(scroll);
        add(btnDone);
        add(add(btnUndo));
    }

    private void initEvents() {
        // EVENT ALUR TRELLO: Ketika member memilih board proyek, tabel otomatis refresh memfilter tugas proyek itu
        cbPilihProjectMember.addActionListener(e -> {
            int selectedIndex = cbPilihProjectMember.getSelectedIndex();
            if(selectedIndex > 0) {
                // Panggil fungsi render ulang tabel member berdasarkan ID Project yang dipilih
                controllerMember.renderTable(); 
            }
        });

        btnDone.addActionListener(e -> {
            int row = tabelTugas.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this, "Pilih task terlebih dahulu!"); return; }
            ModelTableTask model = (ModelTableTask) tabelTugas.getModel();
            controllerMember.markAsDone(model.getActualId(row));
        });

        btnUndo.addActionListener(e -> {
            int row = tabelTugas.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this, "Pilih urutan task di tabel dulu!"); return; }
            ModelTableTask model = (ModelTableTask) tabelTugas.getModel();
            controllerMember.undoTaskStatus(model.getActualId(row));
        });

        btnLogout.addActionListener(e -> controllerMember.logout());
    }

    // Getters
    public JTable getTabelTugasMember() { return tabelTugas; }
    public JLabel getLblWelcome() { return lblWelcome; }
    public JLabel getLblStats() { return lblStats; }
    public JComboBox<String> getCbPilihProjectMember() { return cbPilihProjectMember; }
}