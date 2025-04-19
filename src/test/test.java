package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import GUI.Component.*;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Phần mềm quản lý cửa hàng pizza");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        // Thanh menu bên trái
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(150, 600));
        menuPanel.setBackground(new Color(50, 60, 70));

        JLabel logoLabel = new JLabel(new ImageIcon("pizza_logo.png")); // Thay bằng đường dẫn logo thực tế
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(logoLabel);
        menuPanel.add(Box.createVerticalStrut(20));

        String[] menuItems = {"Bán hàng", "Khuyến mãi", "Sản phẩm", "Nhân viên", "Khách hàng", "Nhập hàng", "Thống kê"};
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuButton.setMaximumSize(new Dimension(120, 40));
            menuButton.setBackground(new Color(70, 80, 90));
            menuButton.setForeground(Color.WHITE);
            menuPanel.add(menuButton);
            menuPanel.add(Box.createVerticalStrut(10));
        }
        
        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Phần bên trái: Danh sách sản phẩm và Giỏ hàng
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(600, 600)); // Phần bên trái lớn hơn

        // Panel Danh sách sản phẩm
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setPreferredSize(new Dimension(600, 400)); // Kích thước cố định
        JLabel orderLabel = new JLabel("Hóa đơn");
        orderLabel.setFont(new Font("Arial", Font.BOLD, 16));
        orderPanel.add(orderLabel, BorderLayout.NORTH);

        // Bảng danh sách sản phẩm
        String[] columnNames = {"Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Đơn vị tính"};
        Object[][] data = {
            {"111", "Pizza Hải Sản Pesto Xanh", "169.000", "34", "Cái"},
            {"112", "Pizza Hải Sản Nhiệt Đới", "129.000", "39", "Cái"},
            {"113", "Pizza Hải Sản Cocktail", "119.000", "35", "Cái"},
            {"114", "Pizza Tôm Cocktail", "159.000", "36", "Cái"},
            {"115", "Pizza Aloha", "119.000", "39", "Cái"},
            {"116", "Pizza Thịt Xông Khói", "139.000", "40", "Cái"},
            {"117", "Pizza Thịt Nguội", "129.000", "38", "Cái"},
            {"118", "Pizza Gà Nướng 3 Vị", "179.000", "37", "Cái"},
            {"119", "Pizza Hải Sản Pesto đế mỏng", "239.000", "14", "Cái"},
            {"120", "Pizza gà đế dày phô mai đế mỏng", "239.000", "37", "Cái"},
            {"121", "Pizza gà đế dày phô mai đế dày", "239.000", "41", "Cái"},
            {"122", "Pizza gà đế mỏng phô mai đế mỏng", "229.000", "41", "Cái"}
        };
        JTable orderTable = new JTable(data, columnNames);
        JScrollPane orderScrollPane = new JScrollPane(orderTable);
        orderPanel.add(orderScrollPane, BorderLayout.CENTER);

        // Panel Giỏ hàng
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setPreferredSize(new Dimension(600, 200)); // Kích thước cố định
        JLabel cartLabel = new JLabel("GIỎ HÀNG");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cartPanel.add(cartLabel, BorderLayout.NORTH);

        // Bảng giỏ hàng (rỗng)
        Object[][] cartData = {};
        JTable cartTable = new JTable(cartData, columnNames);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);

        leftPanel.add(orderPanel, BorderLayout.CENTER);
        leftPanel.add(cartPanel, BorderLayout.SOUTH);

        // Phần bên phải: Chi tiết sản phẩm và nút chức năng
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(250, 600)); // Phần bên phải nhỏ hơn

        // Panel Chi tiết sản phẩm
        JPanel productDetailPanel = new JPanel();
        productDetailPanel.setLayout(new BoxLayout(productDetailPanel, BoxLayout.Y_AXIS));
        productDetailPanel.setPreferredSize(new Dimension(250, 400)); // Kích thước cố định
        JLabel detailLabel = new JLabel("CHI TIẾT SẢN PHẨM");
        detailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        detailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productDetailPanel.add(detailLabel);
        productDetailPanel.add(Box.createVerticalStrut(10));

        // Loại SP
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.setMaximumSize(new Dimension(250, 30));
        typePanel.add(new JLabel("Loại SP"));
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"0 - Chọn loại"});
        typeCombo.setPreferredSize(new Dimension(150, 25));
        typePanel.add(typeCombo);
        productDetailPanel.add(typePanel);

        // Mã SP
        JPanel codePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        codePanel.setMaximumSize(new Dimension(250, 30));
        codePanel.add(new JLabel("Mã SP    "));
        JTextField codeField = new JTextField();
        codeField.setPreferredSize(new Dimension(150, 25));
        codePanel.add(codeField);
        productDetailPanel.add(codePanel);

        // Tên SP
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.setMaximumSize(new Dimension(250, 30));
        namePanel.add(new JLabel("Tên SP  "));
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(150, 25));
        namePanel.add(nameField);
        productDetailPanel.add(namePanel);

        // Đơn giá
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pricePanel.setMaximumSize(new Dimension(250, 30));
        pricePanel.add(new JLabel("Đơn giá "));
        JTextField priceField = new JTextField();
        priceField.setPreferredSize(new Dimension(150, 25));
        pricePanel.add(priceField);
        productDetailPanel.add(pricePanel);

        // Số lượng
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quantityPanel.setMaximumSize(new Dimension(250, 30));
        quantityPanel.add(new JLabel("Số lượng"));
        JTextField quantityField = new JTextField();
        quantityField.setPreferredSize(new Dimension(150, 25));
        quantityPanel.add(quantityField);
        productDetailPanel.add(quantityPanel);

        // Nhân viên
        JPanel employeePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        employeePanel.setMaximumSize(new Dimension(250, 30));
        employeePanel.add(new JLabel("Nhân viên"));
        JTextField employeeField = new JTextField("0 - Admin");
        employeeField.setPreferredSize(new Dimension(150, 25));
        employeeField.setEditable(false);
        employeePanel.add(employeeField);
        productDetailPanel.add(employeePanel);

        // Panel hình ảnh và nút
        JPanel imageButtonPanel = new JPanel(new BorderLayout());
        imageButtonPanel.setPreferredSize(new Dimension(250, 200)); // Kích thước cố định
        JLabel imageLabel = new JLabel(new ImageIcon("default.png")); // Thay bằng đường dẫn ảnh thực tế
        imageButtonPanel.add(imageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Thêm vào giỏ");
        addButton.setBackground(new Color(255, 165, 0));
        JButton deleteButton = new JButton("Xóa");
        deleteButton.setBackground(new Color(255, 165, 0));
        JButton exportButton = new JButton("Xuất hóa đơn");
        exportButton.setBackground(new Color(255, 165, 0));
        buttonPanel.add(deleteButton);
        buttonPanel.add(exportButton);
        imageButtonPanel.add(buttonPanel, BorderLayout.SOUTH);

        rightPanel.add(productDetailPanel, BorderLayout.CENTER);
        rightPanel.add(imageButtonPanel, BorderLayout.SOUTH);

        // Hiển thị thông tin sản phẩm khi chọn từ bảng Danh sách sản phẩm
        orderTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = orderTable.getSelectedRow();
                    if (selectedRow != -1) {
                        codeField.setText(orderTable.getValueAt(selectedRow, 0).toString());
                        nameField.setText(orderTable.getValueAt(selectedRow, 1).toString());
                        priceField.setText(orderTable.getValueAt(selectedRow, 2).toString());
                        quantityField.setText(orderTable.getValueAt(selectedRow, 3).toString());
                    }
                }
            }
        });

        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}