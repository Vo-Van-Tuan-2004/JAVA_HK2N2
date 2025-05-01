package GUI;
import DAO.SanPham_DAO;
import DTO.HoaDonBan_DTO;
import DTO.SanPham_DTO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class BanHang_GUI {
    private JFrame frame ;
    public BanHang_GUI(ActionListener listen ){
       
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
        dsspPanel.setPreferredSize(new Dimension(600,300));
        JLabel dsspTitle = new JLabel("Danh sách sản phẩm :");
        dsspTitle.setFont(new Font("Arial", Font.BOLD, 20));
        dsspPanel.add( dsspTitle, BorderLayout.NORTH);
        DefaultTableModel dssp_dtm = new DefaultTableModel(null, new String[]{"Mã sản phảm", "Tên sản phẩm", "Giá bán", "Số lượng", "Xuất xứ"}){
                @Override
                public boolean isCellEditable(int row, int column){
                        return false;
                }
        };
//
        SanPham_DAO dao = new SanPham_DAO();
        ArrayList<SanPham_DTO> dssplist = dao.LayDanhSachSanPham();
        for (SanPham_DTO tmp : dssplist){
                dssp_dtm.addRow(new Object[]{
                        tmp.getMa_san_pham(), tmp.getTen_san_pham(), tmp.getGia_ban(), tmp.getSo_luong_ton(), tmp.getXuat_xu()
                });
        }
//
        JTable dsspTable = new JTable();
        dsspTable.setModel(dssp_dtm);
        JTableHeader dssp_header = dsspTable.getTableHeader();
        dssp_header.setFont(new Font("SansSerif", Font.BOLD, 12));
        JScrollPane dsspScrollPane = new JScrollPane(dsspTable);
        dsspPanel.add(dsspScrollPane, BorderLayout.CENTER);
        // Pannel gio hang
        JPanel giohangPanel = new JPanel(new BorderLayout());
        giohangPanel.setPreferredSize(new Dimension(600, 300));
        JLabel giohangTitle = new JLabel("Giỏ hàng:");
        giohangTitle.setFont(new Font("Arial", Font.BOLD, 20));
        giohangPanel.add(giohangTitle, BorderLayout.NORTH);
        DefaultTableModel giohang_dtm = new DefaultTableModel(null,new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số luộng", "Đơn giá", "Thanh tien"}){
                @Override
                public boolean isCellEditable(int row, int column){
                        return false;
                };
        };
        JTable giohangTable = new JTable();
        giohangTable.setModel(giohang_dtm);
        JTableHeader giohang_header = dsspTable.getTableHeader();
        giohang_header.setFont(new Font("SansSerif", Font.BOLD, 12));
        JScrollPane giohangScrollPane = new JScrollPane(giohangTable);
        giohangPanel.add(giohangScrollPane, BorderLayout.CENTER);

        //Panel tong tien
        JPanel tongtienPanel = new JPanel(new BorderLayout());
        tongtienPanel.setPreferredSize(new Dimension(600,70));
        JLabel tongtienLabel = new JLabel("Tổng tiền");
        tongtienLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel tongtienField = new JLabel();
        tongtienField.setText("0");
        tongtienField.setPreferredSize(new Dimension(400,70));
        tongtienField.setFont(new Font("Arrial", Font.BOLD, 20));
        tongtienPanel.add(tongtienLabel, BorderLayout.CENTER);
        tongtienPanel.add(tongtienField, BorderLayout.EAST); 

//right panel        
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(250,700));
        rightPanel.setBorder(new EmptyBorder(10, 0, 10, 10));
        //chi tiet san pham panel
        JPanel chitietPanel = new JPanel();
        chitietPanel.setLayout(new BoxLayout(chitietPanel, BoxLayout.Y_AXIS));
        chitietPanel.setPreferredSize(new Dimension(250, 400));
        JLabel chitietTitle = new JLabel("Chi tiết sản phẩm:");
        chitietTitle.setFont(new Font("Arial", Font.BOLD, 20));
        chitietPanel.add(chitietTitle);
        
        JTextField maspField = new JTextField();
        maspField.setEditable(false);
        maspField.setBorder(BorderFactory.createTitledBorder("Mã sản phẩm:") );
        maspField.setPreferredSize(new Dimension(230,30));
        maspField.setFont(new Font("Arial",Font.PLAIN, 15));
        
        JTextField tenspField = new JTextField();
        tenspField.setEditable(false);
        tenspField.setBorder(BorderFactory.createTitledBorder("Tên sản phẩm:") );
        tenspField.setPreferredSize(new Dimension(230,30));
        tenspField.setFont(new Font("Arial",Font.PLAIN, 15));
       
        JTextField mausacField = new JTextField();
        mausacField.setEditable(false);
        mausacField.setBorder(BorderFactory.createTitledBorder("Xuất xứ:") );
        mausacField.setPreferredSize(new Dimension(230,30));
        mausacField.setFont(new Font("Arial",Font.PLAIN, 15));
        
        JTextField soluongField = new JTextField();
        soluongField.setEditable(true);
        soluongField.setBorder(BorderFactory.createTitledBorder("Số lượng bán:") );
        soluongField.setPreferredSize(new Dimension(230,30));
        soluongField.setFont(new Font("Arial",Font.PLAIN, 15));
        
        JTextField dongiaField = new JTextField();
        dongiaField.setEditable(false);
        dongiaField.setBorder(BorderFactory.createTitledBorder("Giá:") );
        dongiaField.setPreferredSize(new Dimension(230,30));
        dongiaField.setFont(new Font("Arial",Font.PLAIN, 15));

        JTextField thanhtienField = new JTextField();
        thanhtienField.setEditable(false);
        thanhtienField.setBorder(BorderFactory.createTitledBorder("Thành tiền:") );
        thanhtienField.setPreferredSize(new Dimension(230,30));
        thanhtienField.setFont(new Font("Arial",Font.PLAIN, 15));

        chitietPanel.add(maspField);
        chitietPanel.add(tenspField);
        chitietPanel.add(mausacField);
        chitietPanel.add(soluongField);
        chitietPanel.add(dongiaField);
        chitietPanel.add(thanhtienField);
        //chuc nang panel
        JPanel chucnangPanel = new JPanel();
        chucnangPanel.setLayout(new BoxLayout(chucnangPanel, BoxLayout.Y_AXIS));
        chucnangPanel.setPreferredSize(new Dimension(250,300));
        chucnangPanel.setBorder(new EmptyBorder(50, 0, 10, 10));

        JButton xoaButton = new JButton("Tạo giỏ mới");
        xoaButton.setFont(new Font("Arial",Font.BOLD, 20));
        xoaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        xoaButton.setMaximumSize(new Dimension(200, 40));
        xoaButton.setBackground(new Color(255, 128, 0));
        xoaButton.setForeground(Color.WHITE);
        
        JButton themButton = new JButton("Thêm vào giỏ");
        themButton.setFont(new Font("Arial", Font.BOLD,20));
        themButton.setFont(new Font("Arial",Font.BOLD, 20));
        themButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        themButton.setMaximumSize(new Dimension(200, 40));
        themButton.setBackground(new Color(255, 128, 0));
        themButton.setForeground(Color.WHITE);
        
        JButton xuatButton = new JButton("Xuất hóa đơn");
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
        leftPanel.add(tongtienPanel, BorderLayout.SOUTH);
        rightPanel.add(chitietPanel, BorderLayout.NORTH);
        rightPanel.add(chucnangPanel, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

// cai dat hanh dong cho viec chon mot dong trong danh sacah san pham
        dsspTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                         int selectedRow = dsspTable.getSelectedRow();
                        if (selectedRow >= 0) {
                                maspField.setText(dssp_dtm.getValueAt(selectedRow, 0).toString());
                                tenspField.setText(dssp_dtm.getValueAt(selectedRow, 1).toString());
                                soluongField.setText("1");
                                dongiaField.setText(dssp_dtm.getValueAt(selectedRow, 2).toString());
                                mausacField.setText(dssp_dtm.getValueAt(selectedRow, 4).toString());
                                thanhtienField.setText(dongiaField.getText());
                        }
                }
        });
//update thanh tien theo du lieu lien tuc
        soluongField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    updateThanhTien();
                }
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    updateThanhTien();
                }
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    updateThanhTien();
                }
            
                public void updateThanhTien() {
                    String dongiaText = dongiaField.getText();
                    String soluongText = soluongField.getText();
                    String thanhtienText = thanhtienField.getText();

                    if (!dongiaText.isEmpty() && !soluongText.isEmpty()) {
                        try {
                                int dongia = Integer.parseInt(dongiaText);
                                int soluong = Integer.parseInt(soluongText);
                                thanhtienText = Integer.toString(dongia * soluong);
                                thanhtienField.setText(thanhtienText);
                        } catch (NumberFormatException ex) {
                                thanhtienField.setText(""); // Nếu nhập sai, reset thành tiền
                        }
                    } else {
                        thanhtienField.setText(""); // Nếu một trong hai rỗng thì reset
                    }
                }
            });
//Cài đặth hành đọng cho nút thêm     
        themButton.addActionListener(e -> {
                String maSP = maspField.getText();
                String tenSP = tenspField.getText();
                String soLuong = soluongField.getText();
                int donGia = Integer.parseInt(dongiaField.getText());
                if (maSP.isEmpty() || tenSP.isEmpty() || soLuong.isEmpty() || soLuong.equals("0") || donGia==0 ){
                        JOptionPane.showMessageDialog(frame, "Thông tin sản phẩm không đầy đủ");
                        return ;
                }

                giohang_dtm.addRow(new Object[]{maSP, tenSP, soLuong, donGia, thanhtienField.getText()});
                int tongtien = Integer.parseInt(tongtienField.getText());
                tongtien += Integer.parseInt(thanhtienField.getText());
                tongtienField.setText(Integer.toString(tongtien));

        });
//cài đặt hành động cho nút xóa
        xoaButton.addActionListener(e -> {
                giohang_dtm.setRowCount(0);
        });
//cài đặt hành động cho nút xuất hóa đơn
        xuatButton.addActionListener(e -> {
                HoaDonBan_DTO hoadon = new HoaDonBan_DTO(); 
                new XuatHoaDonBan_GUI(hoadon);
        });
    }
    public static void main(String[] args) {
       new BanHang_GUI(null);
    }
}
