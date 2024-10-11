import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormBiodata extends JFrame {

    public FormBiodata() {
        // Set default close operation and title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Form Biodata");
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Create a panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10,10, 10);
        
        // Add components to the panel
        JLabel titleLabel = new JLabel("Form Biodata");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);
        
        // Nama
        JLabel labelNama = new JLabel("Nama:");   
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(labelNama, gbc);

        JTextField textNama = new JTextField(5);
        gbc.ipady = 20;
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(textNama, gbc);

        // Jenis Kelamin
        JLabel labelJk = new JLabel("Jenis Kelamin:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(labelJk, gbc);
        
        JRadioButton radioJk1 = new JRadioButton("Laki-Laki");
        JRadioButton radioJk2 = new JRadioButton("Perempuan");
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioJk1);
        bg.add(radioJk2);
        
        JPanel genderPanel = new JPanel();
        genderPanel.add(radioJk1);
        JPanel genderPanel1 = new JPanel();
        genderPanel1.add(radioJk2);
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(genderPanel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(genderPanel1, gbc);
        

        // Nomor HP
        JLabel labelHp = new JLabel("Nomor HP:");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(labelHp, gbc);

        JTextField textHp = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(textHp, gbc);



        // WNA Checkbox
        JCheckBox wnCheckBox = new JCheckBox("Warga Negara Asing");
        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(wnCheckBox, gbc);

        // Simpan Button
        JButton button = new JButton("Simpan");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(button, gbc);

        // Add action to the button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textNama.getText();
                String hp = textHp.getText();
                String jenisKelamin = radioJk1.isSelected() ? "Laki-Laki" : "Perempuan";
                String wna = wnCheckBox.isSelected() ? "Ya" : "Tidak";

                JOptionPane.showMessageDialog(
                        null,
                        "Nama: " + nama + "\nNomor HP: " + hp + "\nJenis Kelamin: " + jenisKelamin + "\nWNA: " + wna,
                        "Data Biodata",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // Add panel to the frame
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormBiodata frame = new FormBiodata();
            frame.setVisible(true);
        });
    }
}