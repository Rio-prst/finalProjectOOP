package View.Dashboard;

import Controller.ControllerTask;
import javax.swing.*;
import java.awt.*;

public class FormEditTask extends JDialog {
    private JTextField txtJudul;
    private JTextField txtDeadline;
    private JTextField txtLampiran;
    private JButton btnSimpan;
    private JButton btnBatal;
    private ControllerTask controllerTask;
    private int idTask;

    public FormEditTask(JFrame parent, ControllerTask controller, int idTask, String judulLama, String deadlineLama, String lampiranLama) {
        // 'true' membuat window di belakangnya (Dashboard PM) terkunci saat modal aktif
        super(parent, "Form Modifikasi Task (Pop-up)", true); 
        this.controllerTask = controller;
        this.idTask = idTask;
        
        initComponents(judulLama, deadlineLama, lampiranLama);
    }

    private void initComponents(String judulLama, String deadlineLama, String lampiranLama) {
        setSize(400, 280);
        setLocationRelativeTo(getOwner());
        setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("UBAH DETAIL TUGAS");
        title.setFont(new Font("Arial", Font.BOLD, 12));
        title.setBounds(20, 15, 200, 20);
        add(title);

        JLabel lbl1 = new JLabel("Judul Baru"); 
        lbl1.setBounds(20, 50, 100, 25);
        txtJudul = new JTextField(judulLama); 
        txtJudul.setBounds(130, 50, 220, 25);

        JLabel lbl2 = new JLabel("Deadline Baru"); 
        lbl2.setBounds(20, 90, 100, 25);
        txtDeadline = new JTextField(deadlineLama); 
        txtDeadline.setBounds(130, 90, 220, 25);

        JLabel lbl3 = new JLabel("Lampiran Baru"); 
        lbl3.setBounds(20, 130, 100, 25);
        txtLampiran = new JTextField(lampiranLama); 
        txtLampiran.setBounds(130, 130, 220, 25);

        btnSimpan = new JButton("Simpan"); 
        btnSimpan.setBounds(130, 185, 100, 30);
        btnSimpan.setBackground(new Color(153, 255, 153));

        btnBatal = new JButton("Batal"); 
        btnBatal.setBounds(250, 185, 100, 30);

        add(lbl1); add(txtJudul);
        add(lbl2); add(txtDeadline);
        add(lbl3); add(txtLampiran);
        add(btnSimpan); add(btnBatal);

        // Event Klik Simpan Perubahan
        btnSimpan.addActionListener(e -> {
            controllerTask.updateTask(idTask, txtJudul.getText(), txtDeadline.getText(), txtLampiran.getText());
            dispose(); // Langsung menutup pop-up otomatis setelah data dikirim ke database
        });

        // Event Klik Batal
        btnBatal.addActionListener(e -> {
            dispose(); 
        });
    }
}