package test;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TestTextField {
    public static void main(String[] args) {
        // Run the GUI code on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(350,100));

            JTextField textField = new JTextField(15); // Set columns for width
            textField.setBorder(BorderFactory.createTitledBorder("Ma San Pham"));
            textField.setPreferredSize(new Dimension(150, 50)); // Optional: Set preferred size
            textField.setFont(new Font("Arial", Font.PLAIN, 15));

            frame.setLayout(new FlowLayout());
            frame.add(textField);

            frame.pack(); // Automatically size the frame based on contents
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setVisible(true);
        });
    }
}