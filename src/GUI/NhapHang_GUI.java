package GUI;

import DAO.NhaCungCap_DAO;
import DAO.SanPham_DAO;
import DTO.ChiTietPhieuNhap_DTO;
import DTO.NhaCungCap_DTO;
import DTO.PhieuNhap_DTO;
import DTO.SanPham_DTO;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class NhapHang_GUI {
    private JFrame frame ;
    private PhieuNhap_DTO phieunhap = new PhieuNhap_DTO();
    private ArrayList<ChiTietPhieuNhap_DTO> dsctpn = new ArrayList<>(); 

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
        dsspPanel.setPreferredSize(new Dimension(600,300));
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

        DefaultTableModel dssp_dtm = new DefaultTableModel(null, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Tồn kho"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
       
        SanPham_DAO sanphamDao = new SanPham_DAO();
        ArrayList<SanPham_DTO> dssplist = sanphamDao.LayDanhSachSanPham();
        for (SanPham_DTO tmp : dssplist){
                dssp_dtm.addRow(new Object[]{
                        tmp.getMa_san_pham(), tmp.getTen_san_pham(), tmp.getSo_luong_ton(),
                });
        }

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

    // Panel tong tien phieu nhap
        JPanel tongtienPanel = new JPanel(new BorderLayout());
        tongtienPanel.setPreferredSize(new Dimension(600,70));
        JLabel tongtienLabel = new JLabel("Tong tien");
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
        JPanel chitietPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
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
        dongiaField.setBorder(BorderFactory.createTitledBorder("Đơn giá nhập:") );
        dongiaField.setPreferredSize(new Dimension(230,40));
        dongiaField.setFont(new Font("Arial",Font.PLAIN, 15));

        JTextField thanhtienField = new JTextField();
        thanhtienField.setEditable(true);
        thanhtienField.setBorder(BorderFactory.createTitledBorder("Thành tiền:") );
        thanhtienField.setEditable(false);
        thanhtienField.setPreferredSize(new Dimension(230,40));
        thanhtienField.setFont(new Font("Arial",Font.PLAIN, 15));

        JButton themButton = new JButton("Thêm");
        themButton.setBackground(new Color(16,158,61));
        themButton.setPreferredSize(new Dimension(150,50));
        themButton.setFont(new Font("Arial",Font.BOLD , 30));

        chitietPanel.add(maspField);
        chitietPanel.add(tenspField);
        chitietPanel.add(dongiaField);
        chitietPanel.add(soluongField);
        chitietPanel.add(thanhtienField);
        chitietPanel.add(themButton);
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
        
        NhaCungCap_DAO nhacungcapDao = new NhaCungCap_DAO();
        ArrayList<NhaCungCap_DTO> dsncc = nhacungcapDao.layDanhSachNhaCungCap();
        for (NhaCungCap_DTO tmp : dsncc){
            nccCombo.addItem(tmp.getMa_nha_cung_cap() + "-" + tmp.getTen());
        }

        JPanel nvPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nvPanel.setMaximumSize(new Dimension(250, 30));
        nvPanel.add(new JLabel("Nhân viên"));
        JTextField nvField = new JTextField();
//thêm mã nhân viên đang đăng nhập
        nvField.setText("Tui đang đăng nhập");
        nvField.setEditable(false);
        nvPanel.add(nvField);
        
        JButton xuatButton = new JButton("Xuat hoa don");
        xuatButton.setFont(new Font("Arial", Font.BOLD,20));
        xuatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        xuatButton.setPreferredSize(new Dimension(230,40));
        xuatButton.setBackground(new Color(255, 128, 0));
        xuatButton.setForeground(Color.WHITE);
        JButton xoaButton = new JButton("Xoa");
        xoaButton.setFont(new Font("Arial", Font.BOLD,20));
        xoaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        xoaButton.setPreferredSize(new Dimension(230,40));
        xoaButton.setBackground(new Color(255, 128, 0));
        xoaButton.setForeground(Color.WHITE);
        
        chucnangPanel.add(ttphieunhapTitle);
        chucnangPanel.add(nccPanel);
        chucnangPanel.add(nvPanel);
        chucnangPanel.add(xoaButton);
        chucnangPanel.add(xuatButton);
        chucnangPanel.add(Box.createVerticalStrut(10));
        
//add item
        leftPanel.add(dsspPanel, BorderLayout.NORTH);
        leftPanel.add(phieunhapPanel, BorderLayout.CENTER);
        leftPanel.add(tongtienPanel, BorderLayout.SOUTH);
        rightPanel.add(chitietPanel, BorderLayout.NORTH);
        rightPanel.add(chucnangPanel, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
// hành động cho Panel tìm kiếm
        timkiemField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updatedssp();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updatedssp();
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updatedssp();
            }
            public void updatedssp() {
              //  DefaultTableModel dssp_dtm_find = (DefaultTableModel) dsspTable.getModel();
                String keyword = timkiemField.getText();
                ArrayList<SanPham_DTO> dssplistfind = sanphamDao.TimKiemSanPham(keyword);
                dssp_dtm.setRowCount(0); // Xóa bảng cũ

                for (SanPham_DTO sp : dssplistfind) {
                    dssp_dtm.addRow(new Object[]{
                        sp.getMa_san_pham(), sp.getTen_san_pham(), sp.getSo_luong_ton(), sp.getXuat_xu()
                    });
                }
                if (dssplistfind.isEmpty()) {
                    System.out.println("Không tìm thấy sản phẩm phù hợp với từ khóa: " + keyword);
                }
            }
            });

// hành động chọn một dòng trong danh sach ton kho
        dsspTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                     int selectedRow = dsspTable.getSelectedRow();
                    if (selectedRow >= 0) {
                            maspField.setText(dssp_dtm.getValueAt(selectedRow, 0).toString());
                            tenspField.setText(dssp_dtm.getValueAt(selectedRow, 1).toString());
                            soluongField.setText("1");
                            dongiaField.setText(dssp_dtm.getValueAt(selectedRow, 3).toString());
                            thanhtienField.setText(dongiaField.getText());
                    }
            }
    });


//hành động tự cập nhật thành tiền theo số lượng nhập
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
                if (!soluongText.matches("\\d+")){
                    soluongField.setBackground(new Color(230,113,113));;
                } else {
                    soluongField.setBackground(new Color(255,255,255));
                }
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

// hành động cho nút thêm
        themButton.addActionListener(e -> {
            String maSP = maspField.getText();
            String tenSP = tenspField.getText();
            String soLuong = soluongField.getText();
            int donGia = Integer.parseInt(dongiaField.getText());
            int thanhtien = donGia * Integer.parseInt(soLuong);
            if (maSP.isEmpty() || tenSP.isEmpty() || soLuong.isEmpty() || soLuong.equals("0") || donGia==0 ){
                    JOptionPane.showMessageDialog(frame, "Thông tin sản phẩm không đầy đủ");
                    return ;
            }

            phieunhap_dtm.addRow(new Object[]{maSP, tenSP, soLuong, donGia, thanhtien});
            int tongtien = Integer.parseInt(tongtienField.getText());
            tongtien += thanhtien;
            tongtienField.setText(Integer.toString(tongtien));
        });
// hành động cho nút xóa
        xoaButton.addActionListener(e -> {
            phieunhap_dtm.setRowCount(0);
        });

// hanh dong cho nut xac nhan
        xuatButton.addActionListener(e ->{
            //lay thong tin ncc
            String nccSelectedItem = (String) nccCombo.getSelectedItem();
            nccSelectedItem = nccSelectedItem.split("-")[0].trim();
            PhieuNhap_DTO phieunhap = new PhieuNhap_DTO();

            phieunhap.setMa_nha_cung_cap(nccSelectedItem);
            phieunhap.setTong_tien(Integer.parseInt(tongtienField.getText()));
            new XuatPhieuNhap_GUI(phieunhap, phieunhap_dtm);
            
        });


    }  
    public static void main(String[] args) {
        new NhapHang_GUI();
    } 
}
