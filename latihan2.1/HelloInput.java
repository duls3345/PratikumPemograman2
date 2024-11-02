import java.awt.event.*;
import javax.swing.*;

public class HelloInput extends JFrame {

    public HelloInput() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelInput = new JLabel("Input Nama");
        labelInput.setBounds(130, 40, 400, 10);

        JTextField textField = new JTextField();  // Perbaikan JTextField
        textField.setBounds(130, 60, 100, 30);

        JButton button = new JButton("Klik");
        button.setBounds(130, 100, 100, 40);

        JLabel labelOutput = new JLabel("");  // Perbaikan JLabel
        labelOutput.setBounds(130, 150, 100, 10);  // Penulisan variabel labelOutput benar

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textField.getText();  // Perbaikan String
                labelOutput.setText("Hello " + nama);  // Tambahkan spasi setelah "Hello"
            }
        });

        this.add(button);
        this.add(textField);
        this.add(labelInput);
        this.add(labelOutput);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloInput h = new HelloInput();
                h.setVisible(true);
            }
        });
    }
}
