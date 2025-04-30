package GUI;
import javax.swing.*;


import java.awt.*;


public class  BaoHanh_GUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BẢO HÀNH");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Bảo Hành", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        
        formPanel.add(new JLabel("Mã Khách Hàng:"));
        JTextField customerNameField = new JTextField();
        formPanel.add(customerNameField);

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

        frame.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Lưu");
        JButton cancelButton = new JButton("Hủy");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
