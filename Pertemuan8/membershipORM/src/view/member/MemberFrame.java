package view.member;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import model.JenisMember;
import model.Member;
import dao.MemberDao;
import dao.JenisMemberDao;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private JTable table; // Tabel untuk menampilkan member
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;
    private Member editingMember; // Untuk menyimpan member yang sedang diedit

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        // UI Components
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 20);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 20);

        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 100, 20);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 200, 20);

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 150, 80, 30);

        JButton buttonClear = new JButton("Clear");
        buttonClear.setBounds(110, 150, 80, 30);

        // Adding components to the frame
        this.add(labelInput);
        this.add(textFieldNama);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(buttonSimpan);
        this.add(buttonClear);

        this.setSize(400, 500);
        this.setLayout(null);

        // Table setup
        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220, 350, 200);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        // Action listeners
        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        buttonSimpan.addActionListener(actionListener);

        buttonClear.addActionListener(e -> clearForm()); // Clear button action

        this.add(scrollableTable);
        populateComboJenis(); // Populate combo box with JenisMember data

        // Add listener for row selection in the table (for editing)
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Member selectedMember = memberList.get(selectedRow);
                    loadMemberToForm(selectedMember); // Load member to form for editing
                }
            }
        });
    }

    // Populate combo box with JenisMember data
    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    // Update the member in the table
    public void updateMember(Member member) {
        int selectedRow = table.getSelectedRow(); // Get selected row
        if (selectedRow >= 0) {
            tableModel.setValueAt(member.getNama(), selectedRow, 0); // Update name in table
            tableModel.setValueAt(member.getJenisMember().getNama(), selectedRow, 1); // Update JenisMember in table
        }
    }

    // Clear the form
    public void clearForm() {
        textFieldNama.setText(""); // Clear the text field
        comboJenis.setSelectedIndex(0); // Reset combo box to default index
        editingMember = null; // Reset editing member
    }

    // Get name input from the form
    public String getNama() {
        return textFieldNama.getText();
    }

    // Get selected JenisMember from the combo box
    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    // Add a new member to the table and clear the form
    public void addMember(Member member) {
        tableModel.add(member); // Add member to table model
        clearForm(); // Clear form after adding member
    }

    // Show alert dialog with a message
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Allow editing of the selected member in the table
    public void loadMemberToForm(Member member) {
        textFieldNama.setText(member.getNama()); // Set name in text field
        comboJenis.setSelectedItem(member.getJenisMember().getNama()); // Set selected jenis member in combo box
        editingMember = member; // Set editing member
    }
}
