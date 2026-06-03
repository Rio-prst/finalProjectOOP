package View.Dashboard;

import Controller.ControllerProject;
import Controller.ControllerTask;
import Model.Task.ModelTableTask;
import javax.swing.*;
import java.awt.*;

public class ViewDashboardPM extends JFrame {

    // =========================================================
    // CONTROLLER
    // =========================================================
    private ControllerProject controllerProject;
    private ControllerTask controllerTask;

    // =========================================================
    // PROJECT COMPONENTS
    // =========================================================
    private JTextField txtNamaProyek;
    private JTextField txtDeskripsiProyek;
    private JTable tabelProyek;

    private JButton btnTambahProject;
    private JButton btnHapusProject;
    
    // KOMPONEN BARU: SISTEM UNDANG MEMBER DI PROJECT BOARD
    private JComboBox<String> cbPilihMember;
    private JButton btnTambahMember;

    // =========================================================
    // TASK COMPONENTS
    // =========================================================
    private JTextField txtJudul;
    private JComboBox<String> cbTipe;
    private JTextField txtDeadlineTask; 
    private JTextField txtLampiran;
    private JTable tabelTugas;

    private JButton btnTambahTask;
    private JButton btnEditTask;  
    private JButton btnDoneTask;
    private JButton btnHapusTask;
    private JButton btnLogoutPM;  

    // =========================================================
    // TAB
    // =========================================================
    private JTabbedPane tabPane;

    public ViewDashboardPM() {
        initComponents();

        controllerProject = new ControllerProject(this);
        controllerTask = new ControllerTask(this);

        controllerProject.renderTable();
        
        // Default pancingan render table task (menggunakan dummy id proyek 1)
        controllerTask.renderTable(1);

        initEvents();
    }

    private void initComponents() {
        setTitle("Dashboard Project Manager (Trello Mode)");
        setSize(1000, 640); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Tombol Logout Utama di pojok kanan atas frame
        btnLogoutPM = new JButton("Logout");
        btnLogoutPM.setBounds(870, 5, 100, 25);
        btnLogoutPM.setBackground(new Color(255, 153, 153));
        add(btnLogoutPM);

        tabPane = new JTabbedPane();
        tabPane.setBounds(10, 25, 960, 560);

        // =========================================================
        // PANEL PROJECT (LENGKAP DENGAN FITUR UNDANG MEMBER)
        // =========================================================
        JPanel panelProject = new JPanel(null);

        JLabel lblNama = new JLabel("Nama Proyek");
        lblNama.setBounds(20, 20, 120, 25);

        txtNamaProyek = new JTextField();
        txtNamaProyek.setBounds(150, 20, 250, 25);

        JLabel lblDesc = new JLabel("Deskripsi");
        lblDesc.setBounds(20, 60, 120, 25);

        txtDeskripsiProyek = new JTextField();
        txtDeskripsiProyek.setBounds(150, 60, 250, 25);

        btnTambahProject = new JButton("Tambah");
        btnTambahProject.setBounds(430, 20, 120, 30);

        btnHapusProject = new JButton("Hapus");
        btnHapusProject.setBounds(430, 60, 120, 30);

        // --- KOMPONEN TAMBAH MEMBER WORKSPACE ---
        JSeparator sep = new JSeparator(JSeparator.VERTICAL);
        sep.setBounds(580, 15, 2, 80); 

        JLabel lblMember = new JLabel("Undang Tim:");
        lblMember.setBounds(600, 20, 100, 25);

        cbPilihMember = new JComboBox<>(new String[]{"Budi (Developer)", "Siti (Designer)"});
        cbPilihMember.setBounds(710, 20, 210, 25);

        btnTambahMember = new JButton("Masukkan ke Proyek");
        btnTambahMember.setBounds(710, 60, 210, 30);
        btnTambahMember.setBackground(new Color(204, 229, 255)); 

        tabelProyek = new JTable();
        JScrollPane spProject = new JScrollPane(tabelProyek);
        spProject.setBounds(20, 110, 910, 390);

        panelProject.add(lblNama);
        panelProject.add(txtNamaProyek);
        panelProject.add(lblDesc);
        panelProject.add(txtDeskripsiProyek);
        panelProject.add(btnTambahProject);
        panelProject.add(btnHapusProject);
        panelProject.add(sep);
        panelProject.add(lblMember);
        panelProject.add(cbPilihMember);
        panelProject.add(btnTambahMember);
        panelProject.add(spProject);

        // =========================================================
        // PANEL TASK
        // =========================================================
        JPanel panelTask = new JPanel(null);

        JLabel lblJudul = new JLabel("Judul Task");
        lblJudul.setBounds(20, 15, 120, 25);

        txtJudul = new JTextField();
        txtJudul.setBounds(150, 15, 250, 25);

        JLabel lblTipe = new JLabel("Tipe");
        lblTipe.setBounds(20, 50, 120, 25);

        cbTipe = new JComboBox<>();
        cbTipe.setBounds(150, 50, 250, 25);
        cbTipe.addItem("DEVELOPMENT");
        cbTipe.addItem("DESIGN");

        JLabel lblDeadline = new JLabel("Deadline Task");
        lblDeadline.setBounds(20, 85, 120, 25);

        txtDeadlineTask = new JTextField();
        txtDeadlineTask.setBounds(150, 85, 250, 25);

        JLabel lblLampiran = new JLabel("Lampiran");
        lblLampiran.setBounds(20, 120, 120, 25);

        txtLampiran = new JTextField();
        txtLampiran.setBounds(150, 120, 250, 25);

        // Tombol Aksi Kanan
        btnTambahTask = new JButton("Tambah Task");
        btnTambahTask.setBounds(430, 15, 150, 30);

        btnEditTask = new JButton("Ubah / Edit Task");
        btnEditTask.setBounds(430, 50, 150, 30);
        btnEditTask.setBackground(new Color(204, 255, 204));

        btnDoneTask = new JButton("Done");
        btnDoneTask.setBounds(430, 85, 150, 30);

        btnHapusTask = new JButton("Hapus");
        btnHapusTask.setBounds(430, 120, 150, 30);

        tabelTugas = new JTable();
        JScrollPane spTask = new JScrollPane(tabelTugas);
        spTask.setBounds(20, 165, 910, 340);

        panelTask.add(lblJudul);
        panelTask.add(txtJudul);
        panelTask.add(lblTipe);
        panelTask.add(cbTipe);
        panelTask.add(lblDeadline); 
        panelTask.add(txtDeadlineTask);
        panelTask.add(lblLampiran);
        panelTask.add(txtLampiran);
        panelTask.add(btnTambahTask);
        panelTask.add(btnEditTask);   
        panelTask.add(btnDoneTask);
        panelTask.add(btnHapusTask);
        panelTask.add(spTask);

        tabPane.add("PROJECT BOARDS", panelProject);
        tabPane.add("CARDS / TASK", panelTask);

        add(tabPane);
    }

    private void initEvents() {

        // ================= LOGOUT =================
        btnLogoutPM.addActionListener(e -> {
            controllerProject.logout();
        });

        // ================= PROJECT EVENTS =================
        btnTambahProject.addActionListener(e -> {
            controllerProject.insertProject();
        });

        btnHapusProject.addActionListener(e -> {
            int row = tabelProyek.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih project dulu!");
                return;
            }
            int id = Integer.parseInt(tabelProyek.getValueAt(row, 0).toString());
            controllerProject.deleteProject(id);
        });
        
        // EVENT BARU: Undang Member ke Proyek Terpilih di JTable
        btnTambahMember.addActionListener(e -> {
            int row = tabelProyek.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Silakan klik/pilih dulu baris proyek di tabel yang ingin ditambah anggotanya!");
                return;
            }
            
            // Mengambil model tabel proyek untuk menarik ID asli database
            Model.Project.ModelTableProject modelProj = (Model.Project.ModelTableProject) tabelProyek.getModel();
            int idProyekAsli = modelProj.getActualId(row);
            
            String namaUser = cbPilihMember.getSelectedItem().toString();
            
            // Lempar ke Controller Project
            controllerProject.assignMember(idProyekAsli, namaUser);
        });

        // ================= TASK EVENTS =================
        btnTambahTask.addActionListener(e -> {
            controllerTask.insertTask();
        });

        // KODE EDIT TASK: Membuka JDialog Pop-up Edit Form
        btnEditTask.addActionListener(e -> {
            int row = tabelTugas.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Klik baris tugas di tabel yang ingin diubah terlebih dahulu!");
                return;
            }
            
            ModelTableTask model = (ModelTableTask) tabelTugas.getModel();
            
            int idAsli = model.getActualId(row);
            String jdl = String.valueOf(tabelTugas.getValueAt(row, 1));
            String dline = String.valueOf(tabelTugas.getValueAt(row, 4));
            String lmp = String.valueOf(tabelTugas.getValueAt(row, 5));

            // Eksekusi Pop-up JDialog melayang
            FormEditTask popUp = new FormEditTask(this, controllerTask, idAsli, jdl, dline, lmp);
            popUp.setVisible(true);
        });

        btnDoneTask.addActionListener(e -> {
            int row = tabelTugas.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih task dulu!");
                return;
            }
            ModelTableTask model = (ModelTableTask) tabelTugas.getModel();
            controllerTask.markAsDone(model.getActualId(row));
        });

        btnHapusTask.addActionListener(e -> {
            int row = tabelTugas.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih task dulu!");
                return;
            }
            ModelTableTask model = (ModelTableTask) tabelTugas.getModel();
            controllerTask.deleteTask(model.getActualId(row));
        });
    }

    // =========================================================
    // GETTERS LENGKAP
    // =========================================================
    public JTable getTabelProyek() { return tabelProyek; }
    public JTextField getTxtNamaProyek() { return txtNamaProyek; }
    public JTextField getTxtDeskripsiProyek() { return txtDeskripsiProyek; }
    public JComboBox<String> getCbPilihMember() { return cbPilihMember; }

    public JTable getTabelTugas() { return tabelTugas; }
    public JTextField getTxtJudul() { return txtJudul; }
    public JComboBox<String> getCbTipe() { return cbTipe; }
    public JTextField getTxtDeadlineTask() { return txtDeadlineTask; } 
    public JTextField getTxtLampiran() { return txtLampiran; }
}