package GUI;

import BLL.BanHang_BLL;
import DAO.SanPham_DAO;
import DTO.ChiTietHoaDonBan_DTO;
import DTO.HoaDonBan_DTO;
import DTO.SanPham_DTO;
import DTO.taiKhoan_DTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class BanHang_GUI {
    private JPanel frame;
    private BanHang_BLL banHangBLL;
    private DefaultTableModel giohang_dtm;
    private JLabel tongtienField;
    private DefaultTableModel dssp_dtm;
    private SanPham_DAO dao;

    public BanHang_GUI(taiKhoan_DTO user) {
        banHangBLL = new BanHang_BLL();
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
        JLabel dsspTitle = new JLabel("Danh sách sản phẩm :");
        dsspTitle.setFont(new Font("Arial", Font.BOLD, 20));
        dsspPanel.add(dsspTitle, BorderLayout.NORTH);
        dssp_dtm = new DefaultTableModel(null, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Số lượng", "Xuất xứ"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dao = new SanPham_DAO();
        ArrayList<SanPham_DTO> dssplist = dao.LayDanhSachSanPham();
        for (SanPham_DTO tmp : dssplist) {
            dssp_dtm.addRow(new Object[]{
                    tmp.getMa_san_pham(), tmp.getTen_san_pham(), tmp.getGia_ban(), tmp.getSo_luong_ton(), tmp.getXuat_xu()
            });
        }

        JTable dsspTable = new JTable();
        dsspTable.setModel(dssp_dtm);
        JTableHeader dssp_header = dsspTable.getTableHeader();
        dssp_header.setFont(new Font("SansSerif", Font.BOLD, 12));
        JScrollPane dsspScrollPane = new JScrollPane(dsspTable);
        dsspPanel.add(dsspScrollPane, BorderLayout.CENTER);

        JPanel giohangPanel = new JPanel(new BorderLayout());
        giohangPanel.setPreferredSize(new Dimension(600, 300));
        JLabel giohangTitle = new JLabel("Giỏ hàng:");
        giohangTitle.setFont(new Font("Arial", Font.BOLD, 20));
        giohangPanel.add(giohangTitle, BorderLayout.NORTH);
        giohang_dtm = new DefaultTableModel(null, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable giohangTable = new JTable();
        giohangTable.setModel(giohang_dtm);
        JTableHeader giohang_header = giohangTable.getTableHeader();
        giohang_header.setFont(new Font("SansSerif", Font.BOLD, 12));
        JScrollPane giohangScrollPane = new JScrollPane(giohangTable);
        giohangPanel.add(giohangScrollPane, BorderLayout.CENTER);

        JPanel tongtienPanel = new JPanel(new BorderLayout());
        tongtienPanel.setPreferredSize(new Dimension(600, 70));
        JLabel tongtienLabel = new JLabel("Tổng tiền");
        tongtienLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tongtienField = new JLabel();
        tongtienField.setText("0");
        tongtienField.setPreferredSize(new Dimension(400, 70));
        tongtienField.setFont(new Font("Arial", Font.BOLD, 20));
        tongtienPanel.add(tongtienLabel, BorderLayout.CENTER);
        tongtienPanel.add(tongtienField, BorderLayout.EAST);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(250, 700));
        rightPanel.setBorder(new EmptyBorder(10, 0, 10, 10));

        JPanel chitietPanel = new JPanel();
        chitietPanel.setLayout(new BoxLayout(chitietPanel, BoxLayout.Y_AXIS));
        chitietPanel.setPreferredSize(new Dimension(250, 400));
        JLabel chitietTitle = new JLabel("Chi tiết sản phẩm:");
        chitietTitle.setFont(new Font("Arial", Font.BOLD, 20));
        chitietPanel.add(chitietTitle);

        JTextField maspField = new JTextField();
        maspField.setEditable(false);
        maspField.setBorder(BorderFactory.createTitledBorder("Mã sản phẩm:"));
        maspField.setPreferredSize(new Dimension(230, 30));
        maspField.setFont(new Font("Arial", Font.PLAIN, 15));

        JTextField tenspField = new JTextField();
        tenspField.setEditable(false);
        tenspField.setBorder(BorderFactory.createTitledBorder("Tên sản phẩm:"));
        tenspField.setPreferredSize(new Dimension(230, 30));
        tenspField.setFont(new Font("Arial", Font.PLAIN, 15));

        JTextField xuatxuField = new JTextField();
        xuatxuField.setEditable(false);
        xuatxuField.setBorder(BorderFactory.createTitledBorder("Xuất xứ:"));
        xuatxuField.setPreferredSize(new Dimension(230, 30));
        xuatxuField.setFont(new Font("Arial", Font.PLAIN, 15));

        JTextField soluongField = new JTextField();
        soluongField.setEditable(true);
        soluongField.setBorder(BorderFactory.createTitledBorder("Số lượng bán:"));
        soluongField.setPreferredSize(new Dimension(230, 30));
        soluongField.setFont(new Font("Arial", Font.PLAIN, 15));

        JTextField dongiaField = new JTextField();
        dongiaField.setEditable(false);
        dongiaField.setBorder(BorderFactory.createTitledBorder("Giá:"));
        dongiaField.setPreferredSize(new Dimension(230, 30));
        dongiaField.setFont(new Font("Arial", Font.PLAIN, 15));

        JTextField thanhtienField = new JTextField();
        thanhtienField.setEditable(false);
        thanhtienField.setBorder(BorderFactory.createTitledBorder("Thành tiền:"));
        thanhtienField.setPreferredSize(new Dimension(230, 30));
        thanhtienField.setFont(new Font("Arial", Font.PLAIN, 15));

        chitietPanel.add(maspField);
        chitietPanel.add(tenspField);
        chitietPanel.add(xuatxuField);
        chitietPanel.add(soluongField);
        chitietPanel.add(dongiaField);
        chitietPanel.add(thanhtienField);

        JPanel chucnangPanel = new JPanel();
        chucnangPanel.setLayout(new BoxLayout(chucnangPanel, BoxLayout.Y_AXIS));
        chucnangPanel.setPreferredSize(new Dimension(250, 300));
        chucnangPanel.setBorder(new EmptyBorder(50, 0, 10, 10));

        JButton xoaButton = new JButton("Tạo giỏ mới");
        xoaButton.setFont(new Font("Arial", Font.BOLD, 20));
        xoaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        xoaButton.setMaximumSize(new Dimension(200, 40));
        xoaButton.setBackground(new Color(255, 128, 0));
        xoaButton.setForeground(Color.WHITE);

        JButton themButton = new JButton("Thêm vào giỏ");
        themButton.setFont(new Font("Arial", Font.BOLD, 20));
        themButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        themButton.setMaximumSize(new Dimension(200, 40));
        themButton.setBackground(new Color(255, 128, 0));
        themButton.setForeground(Color.WHITE);

        JButton xuatButton = new JButton("Xuất hóa đơn");
        xuatButton.setFont(new Font("Arial", Font.BOLD, 20));
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

        leftPanel.add(dsspPanel, BorderLayout.NORTH);
        leftPanel.add(giohangPanel, BorderLayout.CENTER);
        leftPanel.add(tongtienPanel, BorderLayout.SOUTH);
        rightPanel.add(chitietPanel, BorderLayout.NORTH);
        rightPanel.add(chucnangPanel, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        dsspTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int selectedRow = dsspTable.getSelectedRow();
                if (selectedRow >= 0) {
                    maspField.setText(dssp_dtm.getValueAt(selectedRow, 0).toString());
                    tenspField.setText(dssp_dtm.getValueAt(selectedRow, 1).toString());
                    soluongField.setText("1");
                    dongiaField.setText(dssp_dtm.getValueAt(selectedRow, 2).toString());
                    xuatxuField.setText(dssp_dtm.getValueAt(selectedRow, 4).toString());
                    thanhtienField.setText(dongiaField.getText());
                }
            }
        });

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

                if (!dongiaText.isEmpty() && !soluongText.isEmpty()) {
                    try {
                        long dongia = Long.parseLong(dongiaText);
                        int soluong = Integer.parseInt(soluongText);
                        thanhtienField.setText(Long.toString(dongia * soluong));
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
            String soLuongText = soluongField.getText();
            String donGiaText = dongiaField.getText();

            if (maSP.isEmpty() || tenSP.isEmpty() || soLuongText.isEmpty() || donGiaText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Thông tin sản phẩm không đầy đủ");
                return;
            }

            try {
                int soLuong = Integer.parseInt(soLuongText);
                long donGia = Long.parseLong(donGiaText);
                if (soLuong <= 0 || donGia <= 0) {
                    JOptionPane.showMessageDialog(frame, "Số lượng và đơn giá phải lớn hơn 0");
                    return;
                }

                int selectedRow = dsspTable.getSelectedRow();
                int soLuongTon = Integer.parseInt(dssp_dtm.getValueAt(selectedRow, 3).toString());
                if (soLuong > soLuongTon) {
                    JOptionPane.showMessageDialog(frame, "Số lượng vượt quá tồn kho");
                    return;
                }

                giohang_dtm.addRow(new Object[]{maSP, tenSP, soLuong, donGia, soLuong * donGia});
                long tongtien = Long.parseLong(tongtienField.getText());
                tongtien += soLuong * donGia;
                tongtienField.setText(Long.toString(tongtien));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Số lượng hoặc đơn giá phải là số hợp lệ");
            }
        });

        xoaButton.addActionListener(e -> {
            giohang_dtm.setRowCount(0);
            tongtienField.setText("0");
        });

        xuatButton.addActionListener(e -> {
            if (giohang_dtm.getRowCount() == 0) {
                JOptionPane.showMessageDialog(frame, "Giỏ hàng trống");
                return;
            }

            // Tạo DTO cho hóa đơn
            HoaDonBan_DTO hoadon = new HoaDonBan_DTO();
            hoadon.setTong_tien(Long.parseLong(tongtienField.getText()));
            hoadon.setMa_nhan_vien(user.getMaTaiKhoan());
            hoadon.setNgay_xuat(LocalDate.now());
            hoadon.setTrang_thai("Hoàn thành");

            // Tạo DefaultTableModel để truyền sang XuatHoaDonBan_GUI
            DefaultTableModel xuatHoaDonModel = new DefaultTableModel(
                null, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"}
            );

            // Tạo danh sách chi tiết hóa đơn
            ArrayList<ChiTietHoaDonBan_DTO> chiTietList = new ArrayList<>();
            for (int i = 0; i < giohang_dtm.getRowCount(); i++) {
                ChiTietHoaDonBan_DTO chiTiet = new ChiTietHoaDonBan_DTO();
                String maSP = giohang_dtm.getValueAt(i, 0).toString();
                String tenSP = giohang_dtm.getValueAt(i, 1).toString();
                int soLuong = Integer.parseInt(giohang_dtm.getValueAt(i, 2).toString());
                int donGia = Integer.parseInt(giohang_dtm.getValueAt(i, 3).toString());
                chiTiet.setMa_san_pham(maSP);
                chiTiet.setSo_luong(soLuong);
                chiTiet.setDon_gia(donGia);
                chiTietList.add(chiTiet);
                xuatHoaDonModel.addRow(new Object[]{maSP, tenSP, soLuong, donGia, soLuong * donGia});
            }

            // Mở giao diện XuatHoaDonBan_GUI, truyền tham chiếu đến BanHang_GUI
            new XuatHoaDonBan_GUI(hoadon, xuatHoaDonModel, chiTietList, user);
        });
    }

    // Phương thức công khai để cập nhật giao diện sau khi lưu hóa đơn
    public void resetUIAfterInvoiceSaved() {
        giohang_dtm.setRowCount(0);
        tongtienField.setText("0");
        dssp_dtm.setRowCount(0);
        ArrayList<SanPham_DTO> dssp = dao.LayDanhSachSanPham();
        for (SanPham_DTO tmp : dssp) {
            dssp_dtm.addRow(new Object[]{
                    tmp.getMa_san_pham(), tmp.getTen_san_pham(), tmp.getGia_ban(), tmp.getSo_luong_ton(), tmp.getXuat_xu()
            });
        }
    }

    public JPanel getMainPanel() {
        return frame;
    }

    public static void main(String[] args) {
        new BanHang_GUI(new taiKhoan_DTO("NV001", "user", "pass", "NhanVien"));
    }
}