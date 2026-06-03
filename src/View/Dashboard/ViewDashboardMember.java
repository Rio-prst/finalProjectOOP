package View.Dashboard;

import Controller.ControllerTask;
import javax.swing.*;

public class ViewDashboardMember extends JFrame {

    // =========================
    // CONTROLLER
    // =========================
    private ControllerTask controllerTask;

    // =========================
    // COMPONENT
    // =========================
    private JTable tabelTugas;
    private JButton btnDone;

    public ViewDashboardMember() {

        initComponents();

        controllerTask = new ControllerTask(this);

        controllerTask.renderTable();

        initEvents();
    }

    private void initComponents() {

        setTitle("Dashboard Member");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("DASHBOARD MEMBER - TASK LIST");
        title.setBounds(250, 10, 300, 25);

        tabelTugas = new JTable();

        JScrollPane scroll = new JScrollPane(tabelTugas);
        scroll.setBounds(20, 50, 740, 350);

        btnDone = new JButton("Mark as Done");
        btnDone.setBounds(300, 420, 150, 30);

        add(title);
        add(scroll);
        add(btnDone);
    }

    private void initEvents() {

        btnDone.addActionListener(e -> {

            int row = tabelTugas.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih task terlebih dahulu!");
                return;
            }

            int id = Integer.parseInt(
                    tabelTugas.getValueAt(row, 0).toString()
            );

            controllerTask.markAsDone(id);
        });
    }

    // =========================
    // GETTER
    // =========================
    public JTable getTabelTugas() {
        return tabelTugas;
    }
}