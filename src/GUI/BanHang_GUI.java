package GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;

import GUI.Component.CustomPanel;

public class BanHang_GUI {
    private JFrame frame ;
    public BanHang_GUI(){
       
        frame = new JFrame();
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
//menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(50, 60, 70));
        menuPanel.setPreferredSize(new Dimension(150,700));

//main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(850,700));
//left_main panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(600, 700));
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Panel danh sach san pham
        JPanel dsspPanel = new JPanel(new BorderLayout());
        dsspPanel.setPreferredSize(new Dimension(600,400));
        JLabel dsspTitle = new JLabel("Danh sach san pham:");
        dsspTitle.setFont(new Font("Arial", Font.BOLD, 20));
        dsspPanel.add( dsspTitle, BorderLayout.NORTH);
        DefaultTableModel dssp_dtm = new DefaultTableModel();
        dssp_dtm.addColumn("Ma san pham");
        dssp_dtm.addColumn("Ten san pham");
        dssp_dtm.addColumn("Dơn gia");
        dssp_dtm.addColumn("So luong");
        dssp_dtm.addColumn("Mau sac");
        JTable dsspTable = new JTable();
        dsspTable.setModel(dssp_dtm);
        JTableHeader dssp_header = dsspTable.getTableHeader();
        dssp_header.setFont(new Font("SansSerif", Font.BOLD, 12));
        JScrollPane dsspScrollPane = new JScrollPane(dsspTable);
        dsspPanel.add(dsspScrollPane, BorderLayout.CENTER);
        // Pannel gio hang
        JPanel giohangPanel = new JPanel(new BorderLayout());
        giohangPanel.setPreferredSize(new Dimension(600, 300));
        JLabel giohangTitle = new JLabel("Gio hang:");
        giohangTitle.setFont(new Font("Arial", Font.BOLD, 20));
        giohangPanel.add(giohangTitle, BorderLayout.NORTH);
        DefaultTableModel giohang_dtm = new DefaultTableModel();
        giohang_dtm.addColumn("Ma san pham");
        giohang_dtm.addColumn("Ten san pham");
        giohang_dtm.addColumn("So luong");
        giohang_dtm.addColumn("Don gia");
        JTable giohangTable = new JTable();
        giohangTable.setModel(giohang_dtm);
        JTableHeader giohang_header = dsspTable.getTableHeader();
        giohang_header.setFont(new Font("SansSerif", Font.BOLD, 12));
        JScrollPane giohangScrollPane = new JScrollPane(giohangTable);
        giohangPanel.add(giohangScrollPane, BorderLayout.CENTER);

//right panel        
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(250,700));
        rightPanel.setBorder(new EmptyBorder(10, 0, 10, 10));
        //chi tiet san pham panel
        JPanel chitietPanel = new JPanel();
        chitietPanel.setLayout(new BoxLayout(chitietPanel, BoxLayout.Y_AXIS));
        chitietPanel.setPreferredSize(new Dimension(250, 400));
        JLabel chitietTitle = new JLabel("Chi tiet san pham:");
        chitietTitle.setFont(new Font("Arial", Font.BOLD, 20));
        chitietPanel.add(chitietTitle);
        
        JTextField maspField = new JTextField();
        maspField.setEditable(false);
        maspField.setBorder(BorderFactory.createTitledBorder("Ma san pham:") );
        maspField.setPreferredSize(new Dimension(230,30));
        maspField.setFont(new Font("Arial",Font.PLAIN, 15));
        
        JTextField tenspField = new JTextField();
        tenspField.setEditable(false);
        tenspField.setBorder(BorderFactory.createTitledBorder("Ten san pham:") );
        tenspField.setPreferredSize(new Dimension(230,30));
        tenspField.setFont(new Font("Arial",Font.PLAIN, 15));
       
        JTextField loaispField = new JTextField();
        loaispField.setEditable(false);
        loaispField.setBorder(BorderFactory.createTitledBorder("Phan loai:") );
        loaispField.setPreferredSize(new Dimension(230,30));
        loaispField.setFont(new Font("Arial",Font.PLAIN, 15));
        
        JTextField soluongField = new JTextField();
        soluongField.setEditable(true);
        soluongField.setBorder(BorderFactory.createTitledBorder("So luong:") );
        soluongField.setPreferredSize(new Dimension(230,30));
        soluongField.setFont(new Font("Arial",Font.PLAIN, 15));
        
        JTextField dongiaField = new JTextField();
        dongiaField.setEditable(false);
        dongiaField.setBorder(BorderFactory.createTitledBorder("Don gia:") );
        dongiaField.setPreferredSize(new Dimension(230,30));
        dongiaField.setFont(new Font("Arial",Font.PLAIN, 15));

        chitietPanel.add(maspField);
        chitietPanel.add(tenspField);
        chitietPanel.add(loaispField);
        chitietPanel.add(soluongField);
        chitietPanel.add(dongiaField);
        //chuc nang panel
        JPanel chucnangPanel = new JPanel();
        chucnangPanel.setLayout(new BoxLayout(chucnangPanel, BoxLayout.Y_AXIS));
        chucnangPanel.setPreferredSize(new Dimension(250,300));
        chucnangPanel.setBorder(new EmptyBorder(50, 0, 10, 10));

        JButton xoaButton = new JButton("Xoa khoi gio");
        xoaButton.setFont(new Font("Arial",Font.BOLD, 20));
        xoaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        xoaButton.setMaximumSize(new Dimension(200, 40));
        xoaButton.setBackground(new Color(255, 128, 0));
        xoaButton.setForeground(Color.WHITE);
        
        JButton themButton = new JButton("Them vao gio");
        themButton.setFont(new Font("Arial", Font.BOLD,20));
        themButton.setFont(new Font("Arial",Font.BOLD, 20));
        themButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        themButton.setMaximumSize(new Dimension(200, 40));
        themButton.setBackground(new Color(255, 128, 0));
        themButton.setForeground(Color.WHITE);
        
        JButton xuatButton = new JButton("Xuat hoa don");
        xuatButton.setFont(new Font("Arial", Font.BOLD,20));
        xuatButton.setFont(new Font("Arial",Font.BOLD, 20));
        xuatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        xuatButton.setMaximumSize(new Dimension(200, 40));
        xuatButton.setBackground(new Color(255, 128, 0));
        xuatButton.setForeground(Color.WHITE);
        
        chucnangPanel.add(xoaButton);
        chucnangPanel.add(Box.createVerticalStrut(10));
        chucnangPanel.add(themButton);
        chucnangPanel.add(Box.createVerticalStrut(10));
        chucnangPanel.add(xuatButton);
        chucnangPanel.add(Box.createVerticalStrut(10));
//add item
        leftPanel.add(dsspPanel, BorderLayout.NORTH);
        leftPanel.add(giohangPanel, BorderLayout.CENTER);
        rightPanel.add(chitietPanel, BorderLayout.NORTH);
        rightPanel.add(chucnangPanel, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
    public static void main(String[] args) {
       new BanHang_GUI();
    }
}
