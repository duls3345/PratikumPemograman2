import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class FormPendaftaran extends JFrame {
    private JTextField namaField, nomorHPField;
    private JRadioButton lakiLakiButton, perempuanButton;
    private JCheckBox wnaCheckBox;
    private JTextArea resultArea;
    private JList<String> jenisTabunganList;
    private JSpinner frekuensiTransaksiSpinner, tanggalLahirSpinner;
    private JPasswordField passwordField, confirmPasswordField;
    private ButtonGroup genderGroup;
    private JSpinner.DateEditor dateEditor;

    public FormPendaftaran() {
        setTitle("Form Pendaftaran Nasabah");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(11, 1));

        // Panel untuk Nama
        JPanel panelNama = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel namaLabel = new JLabel("Nama:");
        namaField = new JTextField(20);
        panelNama.add(namaLabel);
        panelNama.add(namaField);
        add(panelNama);

        // Panel untuk Nomor HP
        JPanel panelNomorHP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nomorHPLabel = new JLabel("Nomor HP:");
        nomorHPField = new JTextField(20);
        panelNomorHP.add(nomorHPLabel);
        panelNomorHP.add(nomorHPField);
        add(panelNomorHP);

        // Panel untuk Jenis Kelamin
        JPanel panelJenisKelamin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jenisKelaminLabel = new JLabel("Jenis Kelamin:");
        lakiLakiButton = new JRadioButton("Laki-Laki");
        perempuanButton = new JRadioButton("Perempuan");
        genderGroup = new ButtonGroup(); // Deklarasi dan inisialisasi genderGroup
        genderGroup.add(lakiLakiButton);
        genderGroup.add(perempuanButton);
        panelJenisKelamin.add(jenisKelaminLabel);
        panelJenisKelamin.add(lakiLakiButton);
        panelJenisKelamin.add(perempuanButton);
        add(panelJenisKelamin);

        // Checkbox untuk Warga Negara Asing
        JPanel panelWNA = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wnaCheckBox = new JCheckBox("Warga Negara Asing");
        panelWNA.add(wnaCheckBox);
        add(panelWNA);

        // Panel untuk Jenis Tabungan
        JPanel panelJenisTabungan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jenisTabunganLabel = new JLabel("Jenis Tabungan:");
        String[] tabunganOptions = {"Simpanan Pelajar", "Tabungan Haji", "Deposito", "Tabungan Berjangka"};
        jenisTabunganList = new JList<>(tabunganOptions);
        jenisTabunganList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jenisTabunganList);
        scrollPane.setPreferredSize(new Dimension(200, 60));
        panelJenisTabungan.add(jenisTabunganLabel);
        panelJenisTabungan.add(scrollPane);
        add(panelJenisTabungan);

        // Panel untuk Frekuensi Transaksi per Bulan
        JPanel panelFrekuensi = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel frekuensiLabel = new JLabel("Frekuensi Transaksi (1-100):");
        frekuensiTransaksiSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        panelFrekuensi.add(frekuensiLabel);
        panelFrekuensi.add(frekuensiTransaksiSpinner);
        add(panelFrekuensi);

        // Panel untuk Password dan Konfirmasi Password
        JPanel panelPassword = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(10);
        JLabel confirmPasswordLabel = new JLabel("Konfirmasi Password:");
        confirmPasswordField = new JPasswordField(10);
        panelPassword.add(passwordLabel);
        panelPassword.add(passwordField);
        panelPassword.add(confirmPasswordLabel);
        panelPassword.add(confirmPasswordField);
        add(panelPassword);

        // Panel untuk Tanggal Lahir
        JPanel panelTanggalLahir = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel tanggalLahirLabel = new JLabel("Tanggal Lahir:");
        tanggalLahirSpinner = new JSpinner(new SpinnerDateModel());
        dateEditor = new JSpinner.DateEditor(tanggalLahirSpinner, "dd-MM-yyyy");
        tanggalLahirSpinner.setEditor(dateEditor);
        panelTanggalLahir.add(tanggalLahirLabel);
        panelTanggalLahir.add(tanggalLahirSpinner);
        add(panelTanggalLahir);

        // Tombol Simpan
        JButton simpanButton = new JButton("Simpan");
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanData();
            }
        });
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButton.add(simpanButton);
        add(panelButton);

        // Area untuk menampilkan hasil
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem resetMenuItem = new JMenuItem("Reset");
        resetMenuItem.addActionListener(e -> resetForm());
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        menu.add(resetMenuItem);
        menu.add(exitMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    private void simpanData() {
        String nama = namaField.getText();
        String nomorHP = nomorHPField.getText();
        String jenisKelamin = lakiLakiButton.isSelected() ? "Laki-Laki" : "Perempuan";
        String wna = wnaCheckBox.isSelected() ? "Ya" : "Tidak";
        String jenisTabungan = jenisTabunganList.getSelectedValue();
        int frekuensi = (Integer) frekuensiTransaksiSpinner.getValue();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        Date tanggalLahir = (Date) tanggalLahirSpinner.getValue();

        String passwordStatus = password.equals(confirmPassword) ? "Password cocok" : "Password tidak cocok";

        String result = "Nama\t\t: " + nama + "\n" +
                        "Nomor HP\t: " + nomorHP + "\n" +
                        "Jenis Kelamin\t: " + jenisKelamin + "\n" +
                        "WNA\t\t: " + wna + "\n" +
                        "Jenis Tabungan\t: " + (jenisTabungan != null ? jenisTabungan : "Tidak dipilih") + "\n" +
                        "Frekuensi Transaksi: " + frekuensi + "\n" +
                        "Tanggal Lahir\t: " + dateEditor.getFormat().format(tanggalLahir) + "\n" +
                        "Password Status\t: " + passwordStatus + "\n" +
                        "========================================\n";

        resultArea.setText(result);
    }

    private void resetForm() {
        namaField.setText("");
        nomorHPField.setText("");
        genderGroup.clearSelection();
        wnaCheckBox.setSelected(false);
        jenisTabunganList.clearSelection();
        frekuensiTransaksiSpinner.setValue(1);
        passwordField.setText("");
        confirmPasswordField.setText("");
        tanggalLahirSpinner.setValue(new Date());
        resultArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPendaftaran());
    }
}
