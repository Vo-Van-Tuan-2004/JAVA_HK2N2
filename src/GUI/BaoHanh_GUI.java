// package GUI;

// import java.awt.*;
// import javax.swing.*;

// public class BaoHanh_GUI extends JPanel {
//     public BaoHanh_GUI() {
//         setLayout(new BorderLayout());

//         JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
//         setPreferredSize(new Dimension(900,700));
//         formPanel.add(new JLabel("Mã Bảo Hành:"));
//         JTextField warrantyIdField = new JTextField();
//         formPanel.add(warrantyIdField);

//         formPanel.add(new JLabel("Mã Sản Phẩm:"));
//         JTextField productNameField = new JTextField();
//         formPanel.add(productNameField);

//         formPanel.add(new JLabel("Ngày Bắt Đầu:"));
//         JTextField startDateField = new JTextField();
//         formPanel.add(startDateField);

//         formPanel.add(new JLabel("Ngày Kết Thúc:"));
//         JTextField endDateField = new JTextField();
//         formPanel.add(endDateField);

//         add(formPanel, BorderLayout.CENTER);

//         JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//         JButton saveButton = new JButton("Lưu");
//         JButton cancelButton = new JButton("Hủy");
//         buttonPanel.add(saveButton);
//         buttonPanel.add(cancelButton);

//         add(buttonPanel, BorderLayout.SOUTH);
//     }
//     public JPanel getMainPanel(){
//         return this;
//     }
// }


package GUI;

import java.awt.*;
import javax.swing.*;

public class BaoHanh_GUI extends JPanel { // Thay JFrame bằng JPanel
    public BaoHanh_GUI() {
        setLayout(new BorderLayout());

        // Panel chứa form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Mã Bảo Hành
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Mã Bảo Hành:"), gbc);

        gbc.gridx = 1;
        JTextField warrantyIdField = new JTextField(20);
        warrantyIdField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(warrantyIdField, gbc);

        // Mã Sản Phẩm
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Mã Sản Phẩm:"), gbc);

        gbc.gridx = 1;
        JTextField productNameField = new JTextField(20);
        productNameField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(productNameField, gbc);

        // Ngày Bắt Đầu
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Ngày Bắt Đầu:"), gbc);

        gbc.gridx = 1;
        JTextField startDateField = new JTextField(20);
        startDateField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(startDateField, gbc);

        // Ngày Kết Thúc
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Ngày Kết Thúc:"), gbc);

        gbc.gridx = 1;
        JTextField endDateField = new JTextField(20);
        endDateField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(endDateField, gbc);

        // Thêm formPanel vào CENTER với padding
      JPanel centerPanel = new JPanel(new BorderLayout());
       centerPanel.add(formPanel, BorderLayout.NORTH); // Đặt ở trên cùng
       add(centerPanel, BorderLayout.NORTH);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Lưu");
        saveButton.setPreferredSize(new Dimension(100, 35));
        JButton cancelButton = new JButton("Hủy");
        cancelButton.setPreferredSize(new Dimension(100, 35));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Đặt kích thước tối đa để tránh bị phóng to
        setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getMainPanel() {
        return this; 
    }
}