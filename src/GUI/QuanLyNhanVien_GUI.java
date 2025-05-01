package GUI;

import DAO.NhanVien_DAO;
import DTO.NhanVien_DTO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

public class QuanLyNhanVien_GUI extends JFrame {
    private JPanel mainPanel;
    private JTable tblNhanVien;
    private DefaultTableModel model;
    private NhanVien_DAO nvDAO;

    // Menu buttons
    private JButton btnBanHang;
    private JButton btnQuanLyNhanVien;
    private JButton btnQuanLySanPham;
    private JButton btnQuanLyKhachHang;
    private JButton btnQuanLyTaiKhoan;
    private JButton btnNhapHang;
    private JButton btnThongKe;
    private JButton btnDangXuat;

    // Form fields
    private JTextField txtMaNV;
    private JTextField txtTenNV;
    private JTextField txtSoDienThoai;
    private JTextField txtDiaChi;
    private JTextField txtChucVu;
    private JTextField txtLuong;
    private JTextField txtSearch;

    public QuanLyNhanVien_GUI() {
        nvDAO = new NhanVien_DAO();
        initComponents();
        loadData();
    }

    private void initComponents() {
        setTitle("Quản lý cửa hàng bán xe máy");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout(0, 0));

        // Left menu panel with purple background
        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, BorderLayout.WEST);

        // Content panel with light blue background
        JPanel contentPanel = createContentPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
        addEvents();
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new BorderLayout(0, 0));
        menuPanel.setBackground(new Color(72, 25, 161)); // Purple color
        menuPanel.setPreferredSize(new Dimension(250, getHeight()));

        // Logo panel
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setBackground(new Color(72, 25, 161));
        logoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add logo
        ImageIcon logo = new ImageIcon(
    new ImageIcon(getClass().getResource("/IMG/logo.png"))
        .getImage()
        .getScaledInstance(230, 100, Image.SCALE_SMOOTH)
);
JLabel logoLabel = new JLabel(logo);
        logoPanel.add(logoLabel, BorderLayout.CENTER);
        // Add "Chức năng" label
        JLabel chucNangLabel = new JLabel("Chức năng");
        chucNangLabel.setForeground(Color.WHITE);
        chucNangLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chucNangLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoPanel.add(chucNangLabel, BorderLayout.SOUTH);

        // Menu buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(new Color(72, 25, 161));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        // Create menu buttons
        btnBanHang = createMenuButton("Bán hàng");
        btnQuanLyNhanVien = createMenuButton("Quản lý nhân viên");
        btnQuanLySanPham = createMenuButton("Quản lý sản phẩm");
        btnQuanLyKhachHang = createMenuButton("Quản lý khách hàng");
        btnQuanLyTaiKhoan = createMenuButton("Quản lý tài khoản");
        btnNhapHang = createMenuButton("Nhập hàng");
        btnThongKe = createMenuButton("Thống Kê");
        btnDangXuat = createMenuButton("Đăng xuất");

        // Add buttons to panel with spacing
        buttonsPanel.add(btnBanHang);
        addSpacing(buttonsPanel, 10);
        buttonsPanel.add(btnQuanLyNhanVien);
        addSpacing(buttonsPanel, 10);
        buttonsPanel.add(btnQuanLySanPham);
        addSpacing(buttonsPanel, 10);
        buttonsPanel.add(btnQuanLyKhachHang);
        addSpacing(buttonsPanel, 10);
        buttonsPanel.add(btnQuanLyTaiKhoan);
        addSpacing(buttonsPanel, 10);
        buttonsPanel.add(btnNhapHang);
        addSpacing(buttonsPanel, 10);
        buttonsPanel.add(btnThongKe);
        

        // Panel for logout button at bottom
        JPanel logoutPanel = new JPanel(new BorderLayout());
        logoutPanel.setBackground(new Color(72, 25, 161));
        logoutPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 20, 15));
        btnDangXuat.setMaximumSize(new Dimension(220, 40));
        btnDangXuat.setAlignmentX(Component.RIGHT_ALIGNMENT);
        logoutPanel.add(btnDangXuat, BorderLayout.SOUTH);

        menuPanel.add(logoPanel, BorderLayout.NORTH);
        menuPanel.add(buttonsPanel, BorderLayout.CENTER);
        menuPanel.add(logoutPanel, BorderLayout.SOUTH);

        return menuPanel;
    }

    private void addSpacing(JPanel panel, int height) {
        panel.add(Box.createRigidArea(new Dimension(0, height)));
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(220, 40));
        button.setPreferredSize(new Dimension(220, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.BLUE);
        button.setBackground(new Color(100, 149, 237));
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
            new LineBorder(new Color(100, 149, 237), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237));
            }
        });
        
        return button;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(73, 138, 186)); // Màu xanh dương đậm

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(73, 138, 186));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add form labels with white text
        JLabel lblMaNV = new JLabel("Mã NV:");
        JLabel lblTenNV = new JLabel("Tên NV:");
        JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        JLabel lblChucVu = new JLabel("Chức vụ:");
        JLabel lblLuong = new JLabel("Lương:");

        // Set white color for labels
        lblMaNV.setForeground(Color.WHITE);
        lblTenNV.setForeground(Color.WHITE);
        lblSoDienThoai.setForeground(Color.WHITE);
        lblDiaChi.setForeground(Color.WHITE);
        lblChucVu.setForeground(Color.WHITE);
        lblLuong.setForeground(Color.WHITE);

        // Add form fields with labels
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblMaNV, gbc);
        gbc.gridx = 1;
        txtMaNV = new JTextField(20);
        formPanel.add(txtMaNV, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblTenNV, gbc);
        gbc.gridx = 1;
        txtTenNV = new JTextField(20);
        formPanel.add(txtTenNV, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblSoDienThoai, gbc);
        gbc.gridx = 1;
        txtSoDienThoai = new JTextField(20);
        formPanel.add(txtSoDienThoai, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblDiaChi, gbc);
        gbc.gridx = 1;
        txtDiaChi = new JTextField(20);
        formPanel.add(txtDiaChi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblChucVu, gbc);
        gbc.gridx = 1;
        txtChucVu = new JTextField(20);
        formPanel.add(txtChucVu, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(lblLuong, gbc);
        gbc.gridx = 1;
        txtLuong = new JTextField(20);
        formPanel.add(txtLuong, gbc);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(73, 138, 186));
        JLabel lblSearch = new JLabel("Từ khóa tìm:");
        lblSearch.setForeground(Color.WHITE);
        txtSearch = new JTextField(40);
        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        buttonPanel.setBackground(new Color(73, 138, 186));

        // Create buttons with icons and wider size
        Dimension buttonSize = new Dimension(120, 35);
        JButton btnThem = createActionButton("Thêm", "images/add.png", buttonSize);
        JButton btnLuu = createActionButton("Lưu", "images/save.png", buttonSize);
        JButton btnXoa = createActionButton("Xóa", "images/delete.png", buttonSize);
        JButton btnTimKiem = createActionButton("Tìm kiếm", "images/search.png", buttonSize);
        JButton btnXuat = createActionButton("Xuất", "images/export.png", buttonSize);
        JButton btnNhap = createActionButton("Nhập", "images/import.png", buttonSize);

        // Add search event with Enter key
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    timKiemNhanVien();
                }
            }
        });

        // Add buttons to panel
        buttonPanel.add(btnThem);
        buttonPanel.add(btnLuu);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnTimKiem);
        buttonPanel.add(btnXuat);
        buttonPanel.add(btnNhap);

        // Add components to content panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(73, 138, 186));
        topPanel.add(formPanel, BorderLayout.CENTER);
        
        // Add search and buttons panel
        JPanel searchButtonPanel = new JPanel(new BorderLayout());
        searchButtonPanel.setBackground(new Color(73, 138, 186));
        searchButtonPanel.add(searchPanel, BorderLayout.NORTH);
        searchButtonPanel.add(buttonPanel, BorderLayout.CENTER);
        topPanel.add(searchButtonPanel, BorderLayout.SOUTH);
        
        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(73, 138, 186));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create table model
        String[] columnNames = {"Mã NV", "Tên NV", "Số điện thoại", "Địa chỉ", "Chức vụ", "Lương"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Create table
        tblNhanVien = new JTable(model);
        tblNhanVien.setRowHeight(30);
        tblNhanVien.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblNhanVien.setFont(new Font("Arial", Font.PLAIN, 14));
        tblNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(tblNhanVien);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        contentPanel.add(tablePanel, BorderLayout.CENTER);

        // Add button events
        btnThem.addActionListener(e -> themNhanVien());
        btnLuu.addActionListener(e -> luuNhanVien());
        btnXoa.addActionListener(e -> xoaNhanVien());
        btnTimKiem.addActionListener(e -> timKiemNhanVien());
        btnXuat.addActionListener(e -> xuatDuLieu());
        btnNhap.addActionListener(e -> nhapDuLieu());

        return contentPanel;
    }

    private JButton createActionButton(String text, String iconPath, Dimension size) {
        JButton button = new JButton(text);
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println("Không thể tải icon cho nút " + text);
        }
        button.setPreferredSize(size);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return button;
    }

    private void addEvents() {
        btnBanHang.addActionListener(e -> {
            // try {
            //     new BanHang_GUI();
            //     dispose();
            // } catch (Exception ex) {
            //     JOptionPane.showMessageDialog(this, "Chức năng Bán hàng đang phát triển!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            // }
        });
        
        btnQuanLyNhanVien.addActionListener(e -> {
            try {
                new QuanLyNhanVien_GUI().setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Chức năng Quản lý nhân viên đang phát triển!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        btnQuanLySanPham.addActionListener(e -> {
            try{
                new QuanLySanPham_GUI().setVisible(true);
                dispose();
            }catch(Exception ex){
                JOptionPane.showConfirmDialog(this, "Chức năng đang hoàn thiện");
            }
        });
        
        btnQuanLyKhachHang.addActionListener(e -> {
            try {
                new QuanLyKhachHang_GUI().setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Chức năng Quản lý khách hàng đang phát triển!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        btnQuanLyTaiKhoan.addActionListener(e -> {
            try {
                new GUIQuanLyTaiKhoan().setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Chức năng Quản lý tài khoản đang phát triển!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        btnNhapHang.addActionListener(e -> {
            try {
                new NhapHang_GUI();
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Chức năng Nhập hàng đang phát triển!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        btnThongKe.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Chức năng Thống kê đang phát triển!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });
        
        btnDangXuat.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn đăng xuất?",
                "Xác nhận đăng xuất",
                JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
    }

    private void loadData() {
        try {
        List<NhanVien_DTO> danhSach = nvDAO.LayDanhSachNhanVien();
        loadDataToTable(danhSach);
        } catch (Exception e) {
            System.out.println("Lỗi khi tải dữ liệu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void loadDataToTable(List<NhanVien_DTO> danhSach) {
        try {
            model.setRowCount(0); // Xóa dữ liệu cũ
        for (NhanVien_DTO nv : danhSach) {
            model.addRow(new Object[]{
                    nv.getMa_nhan_vien(),
                    nv.getTen_nhan_vien(),
                    nv.getSo_dien_thoai(),
                    nv.getDia_chi(),
                    nv.getChuc_vu(),
                    nv.getLuong()
                });
            }
            System.out.println("Đã tải " + danhSach.size() + " nhân viên vào bảng");
        } catch (Exception e) {
            System.out.println("Lỗi khi hiển thị dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void themNhanVien() {
        try {
            // Lấy thông tin từ các trường nhập liệu
            String maNV = txtMaNV.getText().trim();
            String tenNV = txtTenNV.getText().trim();
            String soDienThoai = txtSoDienThoai.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String chucVu = txtChucVu.getText().trim();
            String luongStr = txtLuong.getText().trim();

            // Kiểm tra dữ liệu nhập vào
            if (maNV.isEmpty() || tenNV.isEmpty() || soDienThoai.isEmpty() || 
                diaChi.isEmpty() || chucVu.isEmpty() || luongStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin nhân viên");
                return;
            }

            // Kiểm tra định dạng mã NV
            if (!maNV.matches("NV\\d+")) {
                JOptionPane.showMessageDialog(this, "Mã NV phải có định dạng 'NV' + số (ví dụ: NV01, NV02,...)");
                return;
            }

            // Kiểm tra định dạng số điện thoại
            if (!soDienThoai.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số");
                return;
            }

            // Kiểm tra mã NV đã tồn tại chưa
            if (nvDAO.LayNhanVienTheoMa(maNV) != null) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!");
                return;
            }

            // Chuyển đổi và kiểm tra lương
            double luong = Double.parseDouble(luongStr);
            if (luong < 0) {
                JOptionPane.showMessageDialog(this, "Lương phải là số dương");
                return;
            }

            // Tạo đối tượng nhân viên mới
            NhanVien_DTO nv = new NhanVien_DTO();
            nv.setMa_nhan_vien(maNV);
            nv.setTen_nhan_vien(tenNV);
            nv.setSo_dien_thoai(soDienThoai);
            nv.setDia_chi(diaChi);
            nv.setChuc_vu(chucVu);
            nv.setLuong(luong);

            // Thêm nhân viên vào database
            if (nvDAO.ThemNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                clearInputFields();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lương phải là số");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearInputFields() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtSoDienThoai.setText("");
        txtDiaChi.setText("");
        txtChucVu.setText("");
        txtLuong.setText("");
        txtMaNV.requestFocus();
    }

    private void luuNhanVien() {
        themNhanVien();
    }

    private void xoaNhanVien() {
        int selectedRow = tblNhanVien.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc muốn xóa nhân viên này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String maNV = tblNhanVien.getValueAt(selectedRow, 0).toString();
            if (nvDAO.XoaNhanVien(maNV)) {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại");
            }
        }
    }

    private void timKiemNhanVien() {
        try {
            String searchText = txtSearch.getText().trim();
            System.out.println("Đang tìm kiếm với từ khóa: " + searchText);

            if (!searchText.isEmpty()) {
                List<NhanVien_DTO> searchResults = nvDAO.TimKiemNhanVien(searchText);
                
                if (searchResults.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên");
                    loadData();
                } else {
                    System.out.println("Tìm thấy " + searchResults.size() + " kết quả");
                    loadDataToTable(searchResults);
                }
            } else {
                loadData();
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm kiếm: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage());
        }
    }

    private void xuatDuLieu() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn vị trí lưu file");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));
            
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }
                
                // TODO: Implement Excel export
                JOptionPane.showMessageDialog(this, "Xuất dữ liệu thành công: " + filePath);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất dữ liệu: " + e.getMessage());
        }
    }

    private void nhapDuLieu() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn file dữ liệu");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));
            
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                
                // TODO: Implement Excel import
                JOptionPane.showMessageDialog(this, "Nhập dữ liệu thành công từ file: " + filePath);
                loadData();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi nhập dữ liệu: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
                new QuanLyNhanVien_GUI().setVisible(true);
        });
    }
}
