package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.*;

public class NhapHang_GUI {
    private JFrame frame ;

    public NhapHang_GUI(){
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
        // Panel danh sach san pham ton kho
        JPanel dsspPanel = new JPanel(new BorderLayout());
        dsspPanel.setPreferredSize(new Dimension(600,400));
        JLabel dsspTitle = new JLabel("Kho hang:");
        dsspTitle.setFont(new Font("Arial", Font.BOLD, 20));
    //tim kiem
        JPanel timkiemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10, 10));
        timkiemPanel.setPreferredSize(new Dimension(600,50));
        JLabel timkiemLabel = new JLabel("Tim kiem:");
        JTextField timkiemField = new JTextField();
        timkiemField.setPreferredSize(new Dimension(300,20));
        timkiemPanel.add(dsspTitle);
        timkiemPanel.add(timkiemLabel);
        timkiemPanel.add(timkiemField);
        dsspPanel.add(timkiemPanel, BorderLayout.NORTH);    
        DefaultTableModel dssp_dtm = new DefaultTableModel();
        dssp_dtm.addColumn("Ma san pham");
        dssp_dtm.addColumn("Ten san pham");
        dssp_dtm.addColumn("So luong");
        JTable dsspTable = new JTable();
        dsspTable.setModel(dssp_dtm);
        JTableHeader dssp_header = dsspTable.getTableHeader();
        dssp_header.setFont(new Font("SansSerif", Font.BOLD, 12));
        JScrollPane dsspScrollPane = new JScrollPane(dsspTable);
        dsspPanel.add(dsspScrollPane, BorderLayout.CENTER);
    // Pannel cphieu nhap
        JPanel phieunhapPanel = new JPanel(new BorderLayout());
        phieunhapPanel.setPreferredSize(new Dimension(600, 300));
        JLabel phieunhapTitle = new JLabel("Phieu nhap:");
        phieunhapTitle.setFont(new Font("Arial", Font.BOLD, 20));
        phieunhapPanel.add(phieunhapTitle, BorderLayout.NORTH);
        DefaultTableModel phieunhap_dtm = new DefaultTableModel();
        phieunhap_dtm.addColumn("Ma san pham");
        phieunhap_dtm.addColumn("Ten san pham");
        phieunhap_dtm.addColumn("So luong");
        phieunhap_dtm.addColumn("Don gia");
        phieunhap_dtm.addColumn(("Thanh tien"));
        JTable phieunhapTable = new JTable();
        phieunhapTable.setModel(phieunhap_dtm);
        JTableHeader phieunhap_header = dsspTable.getTableHeader();
        phieunhap_header.setFont(new Font("SansSerif", Font.BOLD, 12));
        TableColumnModel columnModel = phieunhapTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(100);
        JScrollPane phieunhapScrollPane = new JScrollPane(phieunhapTable);
        phieunhapPanel.add(phieunhapScrollPane, BorderLayout.CENTER);

//right panel        
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(250,700));
        rightPanel.setBorder(new EmptyBorder(10, 0, 10, 10));
    //chi tiet san pham panel
        JPanel chitietPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,20));
        chitietPanel.setPreferredSize(new Dimension(250, 350));
        JLabel chitietTitle = new JLabel("Chi tiet san pham:");
        chitietTitle.setFont(new Font("Arial", Font.BOLD, 20));
        chitietPanel.add(chitietTitle);
        
        JTextField maspField = new JTextField();
        maspField.setEditable(false);
        maspField.setBorder(BorderFactory.createTitledBorder("Ma san pham:") );
        maspField.setPreferredSize(new Dimension(230,40));
        maspField.setFont(new Font("Arial",Font.PLAIN, 15));
        
        JTextField tenspField = new JTextField();
        tenspField.setEditable(false);
        tenspField.setBorder(BorderFactory.createTitledBorder("Ten san pham:") );
        tenspField.setPreferredSize(new Dimension(230,40));
        tenspField.setFont(new Font("Arial",Font.PLAIN, 15));
        
        JTextField soluongField = new JTextField();
        soluongField.setEditable(true);
        soluongField.setBorder(BorderFactory.createTitledBorder("So luong:") );
        soluongField.setPreferredSize(new Dimension(230,40));
        soluongField.setFont(new Font("Arial",Font.PLAIN, 15));
        
        JTextField dongiaField = new JTextField();
        dongiaField.setEditable(true);
        dongiaField.setBorder(BorderFactory.createTitledBorder("Don gia nhap:") );
        dongiaField.setPreferredSize(new Dimension(230,40));
        dongiaField.setFont(new Font("Arial",Font.PLAIN, 15));

        JButton themspButton = new JButton("Them");
        themspButton.setBackground(new Color(16,158,61));
        themspButton.setPreferredSize(new Dimension(150,50));
        themspButton.setFont(new Font("Arial",Font.BOLD , 30));

        chitietPanel.add(maspField);
        chitietPanel.add(tenspField);
        chitietPanel.add(soluongField);
        chitietPanel.add(dongiaField);
        chitietPanel.add(themspButton);
    //chuc nang panel
        JPanel chucnangPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        chucnangPanel.setPreferredSize(new Dimension(250,350));
        chucnangPanel.setBorder(new EmptyBorder(50, 0, 10, 10));

        JLabel ttphieunhapTitle = new JLabel("Thong tin phieu nhap:");
        ttphieunhapTitle.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel nccPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        nccPanel.setMaximumSize(new Dimension(250, 30));
        nccPanel.add(new JLabel("Nha cung cap"));
        JComboBox<String> nccCombo = new JComboBox<>(new String[]{"0 - Chon nha cung cap"});
        nccCombo.setPreferredSize(new Dimension(150, 25));
        nccPanel.add(nccCombo);
        JPanel nvPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nvPanel.setMaximumSize(new Dimension(250, 30));
        nvPanel.add(new JLabel("Nha cung cap"));
        JComboBox<String> nvCombo = new JComboBox<>(new String[]{"0 - Chon nha cung cap"});
        nvCombo.setPreferredSize(new Dimension(150, 25));
        nvPanel.add(nvCombo);
        
        JButton xuatButton = new JButton("Xuat phieu nhap");
        xuatButton.setFont(new Font("Arial", Font.BOLD,20));
        xuatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        xuatButton.setPreferredSize(new Dimension(230,40));
        xuatButton.setBackground(new Color(255, 128, 0));
        xuatButton.setForeground(Color.WHITE);

        
        chucnangPanel.add(ttphieunhapTitle);
        chucnangPanel.add(nccPanel);
        chucnangPanel.add(nvPanel);
        chucnangPanel.add(xuatButton);
        chucnangPanel.add(Box.createVerticalStrut(10));
        
//add item
        leftPanel.add(dsspPanel, BorderLayout.NORTH);
        leftPanel.add(phieunhapPanel, BorderLayout.CENTER);
        rightPanel.add(chitietPanel, BorderLayout.NORTH);
        rightPanel.add(chucnangPanel, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }   
    public static void main(String[] args) {
        new NhapHang_GUI();
    } 
}
