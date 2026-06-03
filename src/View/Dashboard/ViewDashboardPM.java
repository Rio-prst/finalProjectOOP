package View.Dashboard;

import Controller.ControllerProject;
import Controller.ControllerTask;

import javax.swing.*;

public class ViewDashboardPM extends JFrame {

    // =========================
    // CONTROLLER
    // =========================
    private ControllerProject controllerProject;
    private ControllerTask controllerTask;

    // =========================
    // PROJECT COMPONENTS
    // =========================
    private JTextField txtNamaProyek;
    private JTextField txtDeskripsiProyek;
    private JTable tabelProyek;

    private JButton btnTambahProject;
    private JButton btnHapusProject;

    // =========================
    // TASK COMPONENTS
    // =========================
    private JTextField txtJudul;
    private JComboBox<String> cbTipe;
    private JTextField txtLampiran;
    private JTable tabelTugas;

    private JButton btnTambahTask;
    private JButton btnDoneTask;
    private JButton btnHapusTask;

    // =========================
    // TAB
    // =========================
    private JTabbedPane tabPane;

    public ViewDashboardPM() {

        initComponents();

        controllerProject = new ControllerProject(this);
        controllerTask = new ControllerTask(this);

        controllerProject.renderTable();
        controllerTask.renderTable();

        initEvents();
    }

    private void initComponents() {

        setTitle("Dashboard Project Manager");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        tabPane = new JTabbedPane();
        tabPane.setBounds(10, 10, 960, 540);

        // =========================
        // PANEL PROJECT
        // =========================
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

        tabelProyek = new JTable();
        JScrollPane spProject = new JScrollPane(tabelProyek);
        spProject.setBounds(20, 110, 900, 350);

        panelProject.add(lblNama);
        panelProject.add(txtNamaProyek);
        panelProject.add(lblDesc);
        panelProject.add(txtDeskripsiProyek);
        panelProject.add(btnTambahProject);
        panelProject.add(btnHapusProject);
        panelProject.add(spProject);

        // =========================
        // PANEL TASK
        // =========================
        JPanel panelTask = new JPanel(null);

        JLabel lblJudul = new JLabel("Judul Task");
        lblJudul.setBounds(20, 20, 120, 25);

        txtJudul = new JTextField();
        txtJudul.setBounds(150, 20, 250, 25);

        JLabel lblTipe = new JLabel("Tipe");
        lblTipe.setBounds(20, 60, 120, 25);

        cbTipe = new JComboBox<>();
        cbTipe.setBounds(150, 60, 250, 25);
        cbTipe.addItem("DEVELOPMENT");
        cbTipe.addItem("DESIGN");

        JLabel lblLampiran = new JLabel("Lampiran");
        lblLampiran.setBounds(20, 100, 120, 25);

        txtLampiran = new JTextField();
        txtLampiran.setBounds(150, 100, 250, 25);

        btnTambahTask = new JButton("Tambah Task");
        btnTambahTask.setBounds(430, 20, 150, 30);

        btnDoneTask = new JButton("Done");
        btnDoneTask.setBounds(430, 60, 150, 30);

        btnHapusTask = new JButton("Hapus");
        btnHapusTask.setBounds(430, 100, 150, 30);

        tabelTugas = new JTable();
        JScrollPane spTask = new JScrollPane(tabelTugas);
        spTask.setBounds(20, 150, 900, 310);

        panelTask.add(lblJudul);
        panelTask.add(txtJudul);
        panelTask.add(lblTipe);
        panelTask.add(cbTipe);
        panelTask.add(lblLampiran);
        panelTask.add(txtLampiran);
        panelTask.add(btnTambahTask);
        panelTask.add(btnDoneTask);
        panelTask.add(btnHapusTask);
        panelTask.add(spTask);

        // =========================
        // ADD TABS
        // =========================
        tabPane.add("PROJECT", panelProject);
        tabPane.add("TASK", panelTask);

        add(tabPane);
    }

    private void initEvents() {

        // ================= PROJECT =================
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

        // ================= TASK =================
        btnTambahTask.addActionListener(e -> {
            controllerTask.insertTask();
        });

        btnDoneTask.addActionListener(e -> {
            int row = tabelTugas.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih task dulu!");
                return;
            }

            int id = Integer.parseInt(tabelTugas.getValueAt(row, 0).toString());
            controllerTask.markAsDone(id);
        });

        btnHapusTask.addActionListener(e -> {
            int row = tabelTugas.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih task dulu!");
                return;
            }

            int id = Integer.parseInt(tabelTugas.getValueAt(row, 0).toString());
            controllerTask.deleteTask(id);
        });
    }

    // =========================
    // GETTER PROJECT
    // =========================
    public JTable getTabelProyek() {
        return tabelProyek;
    }

    public JTextField getTxtNamaProyek() {
        return txtNamaProyek;
    }

    public JTextField getTxtDeskripsiProyek() {
        return txtDeskripsiProyek;
    }

    // =========================
    // GETTER TASK
    // =========================
    public JTable getTabelTugas() {
        return tabelTugas;
    }

    public JTextField getTxtJudul() {
        return txtJudul;
    }

    public JComboBox<String> getCbTipe() {
        return cbTipe;
    }

    public JTextField getTxtLampiran() {
        return txtLampiran;
    }
}