import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SparepartMotor extends JFrame {
    private JTextField namaField, jumlahField;
    private JTextArea deskripsiArea;
    private JRadioButton oriRadioButton, nonOriRadioButton;
    private JCheckBox tersediaCheckBox, diskonCheckBox;
    private JComboBox<String> kategoriComboBox;
    private JList<String> merekList;
    private JSlider hargaSlider;
    private JSpinner stokSpinner;
    private JTable table;
    private DefaultTableModel tableModel;

    public SparepartMotor() {
        setTitle("Input Stok Sparepart Motor");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(11, 2, 5, 5));

        // Panel untuk Nama Sparepart
        JLabel namaLabel = new JLabel("Nama Sparepart:");
        namaField = new JTextField(15);
        add(namaLabel);
        add(namaField);

        // Panel untuk Jumlah Sparepart
        JLabel jumlahLabel = new JLabel("Jumlah:");
        jumlahField = new JTextField(15);
        add(jumlahLabel);
        add(jumlahField);

        // Text Area untuk deskripsi sparepart (kombinasi dengan text field)
        JLabel deskripsiLabel = new JLabel("Deskripsi Sparepart:");
        deskripsiArea = new JTextArea(3, 15);
        add(deskripsiLabel);
        add(new JScrollPane(deskripsiArea));

        // RadioButton untuk status Ori/Non Ori
        JLabel statusLabel = new JLabel("Status:");
        oriRadioButton = new JRadioButton("Ori");
        nonOriRadioButton = new JRadioButton("Non-Ori");
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(oriRadioButton);
        statusGroup.add(nonOriRadioButton);
        JPanel panelRadioButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRadioButton.add(oriRadioButton);
        panelRadioButton.add(nonOriRadioButton);
        add(statusLabel);
        add(panelRadioButton);

        // CheckBox untuk kondisi sparepart
        tersediaCheckBox = new JCheckBox("Tersedia");
        diskonCheckBox = new JCheckBox("Diskon");
        JPanel panelCheckBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCheckBox.add(tersediaCheckBox);
        panelCheckBox.add(diskonCheckBox);
        add(new JLabel("Kondisi:"));
        add(panelCheckBox);

        // ComboBox untuk kategori sparepart
        JLabel kategoriLabel = new JLabel("Kategori:");
        kategoriComboBox = new JComboBox<>(new String[]{"Mesin", "Bodi", "Elektrik", "Suspensi"});
        add(kategoriLabel);
        add(kategoriComboBox);

        // List untuk memilih merek
        JLabel merekLabel = new JLabel("Merek:");
        String[] merekOptions = {"Honda", "Yamaha", "Suzuki", "Kawasaki"};
        merekList = new JList<>(merekOptions);
        merekList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane merekScrollPane = new JScrollPane(merekList);
        add(merekLabel);
        add(merekScrollPane);

        // Slider untuk harga sparepart
        JLabel hargaLabel = new JLabel("Harga:");
        hargaSlider = new JSlider(0, 1000000, 500000);
        hargaSlider.setPaintTicks(true);
        hargaSlider.setPaintLabels(true);
        hargaSlider.setMajorTickSpacing(200000);
        hargaSlider.setMinorTickSpacing(50000);
        add(hargaLabel);
        add(hargaSlider);

        // Spinner untuk stok sparepart
        JLabel stokLabel = new JLabel("Stok:");
        stokSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        add(stokLabel);
        add(stokSpinner);

        // Tombol Simpan
        JButton simpanButton = new JButton("Simpan");
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanData();
            }
        });
        add(new JLabel());
        add(simpanButton);

        // Tabel untuk menampilkan data sparepart
        String[] columnNames = { "Nama", "Deskripsi", "Status", "Kondisi", "Kategori", "Merek", "Harga", "Stok"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        add(new JLabel("Data Sparepart:"));
        add(tableScrollPane);

        // MenuBar dan Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem resetMenuItem = new JMenuItem("Reset");
        resetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(resetMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    private void simpanData() {
        String nama = namaField.getText();
        String jumlah = jumlahField.getText();
        String deskripsi = deskripsiArea.getText();
        String status = oriRadioButton.isSelected() ? "Ori" : "Non-Ori";
        String kondisi = (tersediaCheckBox.isSelected() ? "Tersedia" : "Tidak Tersedia") + 
                         (diskonCheckBox.isSelected() ? ", Diskon" : "");
        String kategori = (String) kategoriComboBox.getSelectedItem();
        String merek = merekList.getSelectedValue();
        int harga = hargaSlider.getValue();
        int stok = (Integer) stokSpinner.getValue();

        if (nama.isEmpty() || jumlah.isEmpty() || deskripsi.isEmpty() || merek == null) {
            JOptionPane.showMessageDialog(this, "Mohon isi semua data", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tambah data ke tabel
        tableModel.addRow(new Object[]{nama, deskripsi, status, kondisi, kategori, merek, harga, stok});

        JOptionPane.showMessageDialog(this, "Data sparepart berhasil disimpan!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetForm() {
        namaField.setText("");
        jumlahField.setText("");
        deskripsiArea.setText("");
        oriRadioButton.setSelected(false);
        nonOriRadioButton.setSelected(false);
        tersediaCheckBox.setSelected(false);
        diskonCheckBox.setSelected(false);
        kategoriComboBox.setSelectedIndex(0);
        merekList.clearSelection();
        hargaSlider.setValue(500000);
        stokSpinner.setValue(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SparepartMotor());
    }
}
