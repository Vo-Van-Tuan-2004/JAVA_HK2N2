package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test1 extends JFrame {
    CardLayout cardLayout;
    JPanel cardPanel;

    public test1() {
        setTitle("CardLayout với hai nút điều khiển");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Tạo 2 nút điều khiển ---
        JButton btnShowBlue = new JButton("Hiện Panel Xanh");
        JButton btnShowRed = new JButton("Hiện Panel Đỏ");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnShowBlue);
        buttonPanel.add(btnShowRed);

        // --- Tạo CardLayout và các panel ---
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Panel màu xanh
        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.CYAN);
        bluePanel.add(new JLabel("Đây là panel màu xanh"));

        // Panel màu đỏ
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.PINK);
        redPanel.add(new JLabel("Đây là panel màu đỏ"));

        // Thêm panel vào CardLayout
        cardPanel.add(bluePanel, "Blue");
        cardPanel.add(redPanel, "Red");

        // --- Gắn sự kiện cho nút ---
        btnShowBlue.addActionListener(e -> cardLayout.show(cardPanel, "Blue"));
        btnShowRed.addActionListener(e -> cardLayout.show(cardPanel, "Red"));

        // --- Thêm các thành phần vào JFrame ---
        add(buttonPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Căn giữa màn hình
        setVisible(true);
    }

    public static void main(String[] args) {
        new test1();
    }
}
