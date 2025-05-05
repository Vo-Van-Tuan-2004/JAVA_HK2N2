package GUI;

import BLL.NhapHang_BLL;
import DAO.NhaCungCap_DAO;
import DAO.SanPham_DAO;
import DTO.ChiTietPhieuNhap_DTO;
import DTO.NhaCungCap_DTO;
import DTO.PhieuNhap_DTO;
import DTO.SanPham_DTO;
import DTO.taiKhoan_DTO;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class NhapHang_GUI {
    private JPanel frame;
    private PhieuNhap_DTO phieunhap = new PhieuNhap_DTO();
    private ArrayList<ChiTietPhieuNhap_DTO> dsctpn = new ArrayList<>();
    private Connection conn;
    private NhapHang_BLL nhapHangBLL;
    private DefaultTableModel phieunhap_dtm;

    public NhapHang_GUI(taiKhoan_DTO user) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
                    "sa",
                    "12345"
            );
            nhapHangBLL = new NhapHang_BLL(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JPanel();
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(900, 700));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(600, 700));
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel dsspPanel = new JPanel(new BorderLayout());
        dsspPanel.setPreferredSize(new Dimension(600, 300));
        JLabel dsspTitle = new JLabel("Kho hàng:");
        dsspTitle.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel timkiemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        timkiemPanel.setPreferredSize(new Dimension(600, 50));
        JLabel timkiemLabel = new JLabel("Tìm kiếm:");
        JTextField timkiemField = new JTextField();
        timkiemField.setPreferredSize(new Dimension(300, 20));
        timkiemPanel.add(dsspTitle);
        timkiemPanel.add(timkiemLabel);
        timkiemPanel.add(timkiemField);
        dsspPanel.add(timkiemPanel, BorderLayout.NORTH);

        DefaultTableModel dssp_dtm = new DefaultTableModel(null, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Tồn kho"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        SanPham_DAO sanphamDao = new SanPham_DAO();
        ArrayList<SanPham_DTO> dssplist = sanphamDao.LayDanhSachSanPham();
        for (SanPham_DTO tmp : dssplist) {
            dssp_dtm.addRow(new Object[]{
                    tmp.getMa_san_pham(), tmp.getTen_san_pham(), tmp.getSo_luong_ton()
            });
        }

        JTable dsspTable = new JTable();
        dsspTable.setModel(dssp_dtm);
        JTableHeader dssp_header = dsspTable.getTableHeader();
        dssp_header.setFont(new Font("SansSerif", Font.BOLD, 12));
        JScrollPane dsspScrollPane = new JScrollPane(dsspTable);
        dsspPanel.add(dsspScrollPane, BorderLayout.CENTER);

        JPanel phieunhapPanel = new JPanel(new BorderLayout());
        phieunhapPanel.setPreferredSize(new Dimension(600, 300));
        JLabel phieunhapTitle = new JLabel("Phiếu nhập:");
        phieunhapTitle.setFont(new Font("Arial", Font.BOLD, 20));
        phieunhapPanel.add(phieunhapTitle, BorderLayout.NORTH);

        phieunhap_dtm = new DefaultTableModel();
        phieunhap_dtm.addColumn("Mã sản phẩm");
        phieunhap_dtm.addColumn("Tên sản phẩm");
        phieunhap_dtm.addColumn("Số lượng");
        phieunhap_dtm.addColumn("Đơn giá");
        phieunhap_dtm.addColumn("Thành tiền");
        JTable phieunhapTable = new JTable();
        phieunhapTable.setModel(phieunhap_dtm);
        JTableHeader phieunhap_header = phieunhapTable.getTableHeader();
        phieunhap_header.setFont(new Font("SansSerif", Font.BOLD, 12));
        TableColumnModel columnModel = phieunhapTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(100);
        JScrollPane phieunhapScrollPane = new JScrollPane(phieunhapTable);
        phieunhapPanel.add(phieunhapScrollPane, BorderLayout.CENTER);

        JPanel tongtienPanel = new JPanel(new BorderLayout());
        tongtienPanel.setPreferredSize(new Dimension(600, 70));
        JLabel tongtienLabel = new JLabel("Tổng tiền");
        tongtienLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel tongtienField = new JLabel();
        tongtienField.setText("0");
        tongtienField.setPreferredSize(new Dimension(400, 70));
        tongtienField.setFont(new Font("Arial", Font.BOLD, 20));
        tongtienPanel.add(tongtienLabel, BorderLayout.CENTER);
        tongtienPanel.add(tongtienField, BorderLayout.EAST);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(250, 700));
        rightPanel.setBorder(new EmptyBorder(10, 0, 10, 10));

        JPanel chitietPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        chitietPanel.setPreferredSize(new Dimension(250, 350));
        JLabel chitietTitle = new JLabel("Chi tiết sản phẩm:");
        chitietTitle.setFont(new Font("Arial", Font.BOLD, 20));
        chitietPanel.add(chitietTitle);

        JTextField maspField = new JTextField();
        maspField.setEditable(false);
        maspField.setBorder(BorderFactory.createTitledBorder("Mã sản phẩm:"));
        maspField.setPreferredSize(new Dimension(230, 40));
        maspField.setFont(new Font("Arial", Font.PLAIN, 15));

        JTextField tenspField = new JTextField();
        tenspField.setEditable(false);
        tenspField.setBorder(BorderFactory.createTitledBorder("Tên sản phẩm:"));
        tenspField.setPreferredSize(new Dimension(230, 40));
        tenspField.setFont(new Font("Arial", Font.PLAIN, 15));

        JTextField soluongField = new JTextField();
        soluongField.setEditable(true);
        soluongField.setBorder(BorderFactory.createTitledBorder("Số lượng:"));
        soluongField.setPreferredSize(new Dimension(230, 40));
        soluongField.setFont(new Font("Arial", Font.PLAIN, 15));

        JTextField dongiaField = new JTextField();
        dongiaField.setEditable(true);
        dongiaField.setBorder(BorderFactory.createTitledBorder("Đơn giá nhập:"));
        dongiaField.setPreferredSize(new Dimension(230, 40));
        dongiaField.setFont(new Font("Arial", Font.PLAIN, 15));

        JTextField thanhtienField = new JTextField();
        thanhtienField.setEditable(false);
        thanhtienField.setBorder(BorderFactory.createTitledBorder("Thành tiền:"));
        thanhtienField.setPreferredSize(new Dimension(230, 40));
        thanhtienField.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton themButton = new JButton("Thêm");
        themButton.setBackground(new Color(16, 158, 61));
        themButton.setPreferredSize(new Dimension(150, 50));
        themButton.setFont(new Font("Arial", Font.BOLD, 30));

        chitietPanel.add(maspField);
        chitietPanel.add(tenspField);
        chitietPanel.add(dongiaField);
        chitietPanel.add(soluongField);
        chitietPanel.add(thanhtienField);
        chitietPanel.add(themButton);

        JPanel chucnangPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        chucnangPanel.setPreferredSize(new Dimension(250, 350));
        chucnangPanel.setBorder(new EmptyBorder(50, 0, 10, 10));

        JLabel ttphieunhapTitle = new JLabel("Thông tin phiếu nhập:");
        ttphieunhapTitle.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel nccPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        nccPanel.setMaximumSize(new Dimension(250, 30));
        nccPanel.add(new JLabel("Nhà cung cấp"));
        JComboBox<String> nccCombo = new JComboBox<>(new String[]{"0 - Chọn nhà cung cấp"});
        nccCombo.setPreferredSize(new Dimension(150, 25));
        nccPanel.add(nccCombo);

        NhaCungCap_DAO nhacungcapDao = new NhaCungCap_DAO();
        ArrayList<NhaCungCap_DTO> dsncc = nhacungcapDao.layDanhSachNhaCungCap();
        for (NhaCungCap_DTO tmp : dsncc) {
            nccCombo.addItem(tmp.getMa_nha_cung_cap() + "-" + tmp.getTen());
        }

        JPanel nvPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nvPanel.setMaximumSize(new Dimension(250, 30));
        nvPanel.add(new JLabel("Nhân viên"));
        JTextField nvField = new JTextField();
        nvField.setText(user != null ? user.getMaTaiKhoan() : "NV01");
        nvField.setEditable(false);
        nvPanel.add(nvField);

        JButton xuatButton = new JButton("Xuất phiếu nhập");
        xuatButton.setFont(new Font("Arial", Font.BOLD, 20));
        xuatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        xuatButton.setPreferredSize(new Dimension(230, 40));
        xuatButton.setBackground(new Color(255, 128, 0));
        xuatButton.setForeground(Color.WHITE);

        JButton xoaButton = new JButton("Xóa");
        xoaButton.setFont(new Font("Arial", Font.BOLD, 20));
        xoaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        xoaButton.setPreferredSize(new Dimension(230, 40));
        xoaButton.setBackground(new Color(255, 128, 0));
        xoaButton.setForeground(Color.WHITE);

        chucnangPanel.add(ttphieunhapTitle);
        chucnangPanel.add(nccPanel);
        chucnangPanel.add(nvPanel);
        chucnangPanel.add(xoaButton);
        chucnangPanel.add(xuatButton);
        chucnangPanel.add(Box.createVerticalStrut(10));

        leftPanel.add(dsspPanel, BorderLayout.NORTH);
        leftPanel.add(phieunhapPanel, BorderLayout.CENTER);
        leftPanel.add(tongtienPanel, BorderLayout.SOUTH);
        rightPanel.add(chitietPanel, BorderLayout.NORTH);
        rightPanel.add(chucnangPanel, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

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
                String keyword = timkiemField.getText();
                ArrayList<SanPham_DTO> dssplistfind = sanphamDao.TimKiemSanPham(keyword);
                dssp_dtm.setRowCount(0);

                for (SanPham_DTO sp : dssplistfind) {
                    dssp_dtm.addRow(new Object[]{
                            sp.getMa_san_pham(), sp.getTen_san_pham(), sp.getSo_luong_ton()
                    });
                }
                if (dssplistfind.isEmpty()) {
                    System.out.println("Không tìm thấy sản phẩm phù hợp với từ khóa: " + keyword);
                }
            }
        });

        dsspTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int selectedRow = dsspTable.getSelectedRow();
                if (selectedRow >= 0) {
                    maspField.setText(dssp_dtm.getValueAt(selectedRow, 0).toString());
                    tenspField.setText(dssp_dtm.getValueAt(selectedRow, 1).toString());
                    soluongField.setText("1");
                    dongiaField.setText("0");
                    thanhtienField.setText("0");
                }
            }
        });

        soluongField.getDocument().addDocumentListener(new DocumentListener() {
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
                if (!soluongText.matches("\\d+")) {
                    soluongField.setBackground(new Color(230, 113, 113));
                } else {
                    soluongField.setBackground(new Color(255, 255, 255));
                }
                if (!dongiaText.isEmpty() && !soluongText.isEmpty()) {
                    try {
                        int dongia = Integer.parseInt(dongiaText);
                        int soluong = Integer.parseInt(soluongText);
                        thanhtienField.setText(Integer.toString(dongia * soluong));
                    } catch (NumberFormatException ex) {
                        thanhtienField.setText("");
                    }
                } else {
                    thanhtienField.setText("");
                }
            }
        });

        dongiaField.getDocument().addDocumentListener(new DocumentListener() {
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
                if (!dongiaText.matches("\\d+")) {
                    dongiaField.setBackground(new Color(230, 113, 113));
                } else {
                    dongiaField.setBackground(new Color(255, 255, 255));
                }
                if (!dongiaText.isEmpty() && !soluongText.isEmpty()) {
                    try {
                        int dongia = Integer.parseInt(dongiaText);
                        int soluong = Integer.parseInt(soluongText);
                        thanhtienField.setText(Integer.toString(dongia * soluong));
                    } catch (NumberFormatException ex) {
                        thanhtienField.setText("");
                    }
                } else {
                    thanhtienField.setText("");
                }
            }
        });

        themButton.addActionListener(e -> {
            String maSP = maspField.getText();
            String tenSP = tenspField.getText();
            String soLuong = soluongField.getText();
            String donGia = dongiaField.getText();
            if (maSP.isEmpty() || tenSP.isEmpty() || soLuong.isEmpty() || donGia.isEmpty() || !soLuong.matches("\\d+") || !donGia.matches("\\d+")) {
                JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ và đúng định dạng thông tin sản phẩm");
                return;
            }
            int soLuongInt = Integer.parseInt(soLuong);
            int donGiaInt = Integer.parseInt(donGia);
            if (soLuongInt == 0 || donGiaInt == 0) {
                JOptionPane.showMessageDialog(frame, "Số lượng và đơn giá phải lớn hơn 0");
                return;
            }
            int thanhTien = donGiaInt * soLuongInt;

            ChiTietPhieuNhap_DTO chiTiet = new ChiTietPhieuNhap_DTO();
            chiTiet.setMa_san_pham(maSP);
            chiTiet.setSo_luong_nhap(soLuongInt);
            chiTiet.setGia_nhap(donGiaInt);
            chiTiet.setThanh_tien(thanhTien);
            dsctpn.add(chiTiet);

            phieunhap_dtm.addRow(new Object[]{maSP, tenSP, soLuong, donGia, thanhTien});
            int tongtien = Integer.parseInt(tongtienField.getText()) + thanhTien;
            tongtienField.setText(Integer.toString(tongtien));
        });

        xoaButton.addActionListener(e -> {
            phieunhap_dtm.setRowCount(0);
            dsctpn.clear();
            tongtienField.setText("0");
            phieunhap = new PhieuNhap_DTO();
        });

        xuatButton.addActionListener(e -> {
            String nccSelectedItem = (String) nccCombo.getSelectedItem();
            if (nccSelectedItem.equals("0 - Chọn nhà cung cấp")) {
                JOptionPane.showMessageDialog(frame, "Vui lòng chọn nhà cung cấp");
                return;
            }
            if (dsctpn.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Phiếu nhập không có sản phẩm");
                return;
            }
            nccSelectedItem = nccSelectedItem.split("-")[0].trim();
            phieunhap.setMa_nha_cung_cap(nccSelectedItem);
            phieunhap.setTong_tien(Integer.parseInt(tongtienField.getText()));
            new XuatPhieuNhap_GUI(phieunhap, dsctpn, phieunhap_dtm, nhapHangBLL, this);
        });
    }

    public JPanel getMainPanel() {
        return frame;
    }

    public void resetPhieuNhap() {
        phieunhap_dtm.setRowCount(0);
        dsctpn.clear();
        phieunhap = new PhieuNhap_DTO();
    }

    public static void main(String[] args) {
        new NhapHang_GUI(null);
    }
}