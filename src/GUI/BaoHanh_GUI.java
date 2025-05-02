package GUI;

import javax.swing.*;
import java.awt.*;

public class BaoHanh_GUI extends JPanel {
    public BaoHanh_GUI() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        formPanel.add(new JLabel("Mã Bảo Hành:"));
        JTextField warrantyIdField = new JTextField();
        formPanel.add(warrantyIdField);

        formPanel.add(new JLabel("Mã Sản Phẩm:"));
        JTextField productNameField = new JTextField();
        formPanel.add(productNameField);

        formPanel.add(new JLabel("Ngày Bắt Đầu:"));
        JTextField startDateField = new JTextField();
        formPanel.add(startDateField);

        formPanel.add(new JLabel("Ngày Kết Thúc:"));
        JTextField endDateField = new JTextField();
        formPanel.add(endDateField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Lưu");
        JButton cancelButton = new JButton("Hủy");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
