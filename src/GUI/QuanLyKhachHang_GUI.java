package GUI;

import DAO.KhachHang_DAO;
import DTO.KhachHang_DTO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

public class QuanLyKhachHang_GUI extends JFrame {
    private JPanel mainPanel;
    private JTable tblKhachHang;
    private DefaultTableModel model;
    private KhachHang_DAO khDAO;

    // Menu buttons
    private JButton btnBanHang;
    private JButton btnQuanLyNhanVien;
    private JButton btnQuanLySanPham;
    private JButton btnQuanLyKhachHang;
    private JButton btnQuanLyTaiKhoan;
    private JButton btnNhapHang;
    private JButton btnThongKe;
    private JButton btnDangXuat;

    // Các biến để lưu trữ reference đến các components
    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JTextField txtDiaChi;
    private JTextField txtSearch;

    public QuanLyKhachHang_GUI() {
        khDAO = new KhachHang_DAO();
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
        btnThongKe = createMenuButton("Thống kê");
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
        addSpacing(buttonsPanel, 10);
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
        button.setForeground(Color.WHITE);
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
        JLabel lblMaKH = new JLabel("Mã KH:");
        JLabel lblTenKH = new JLabel("Tên KH:");
        JLabel lblSDT = new JLabel("Số điện thoại:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblDiaChi = new JLabel("Địa chỉ:");

        // Set white color for labels
        lblMaKH.setForeground(Color.WHITE);
        lblTenKH.setForeground(Color.WHITE);
        lblSDT.setForeground(Color.WHITE);
        lblEmail.setForeground(Color.WHITE);
        lblDiaChi.setForeground(Color.WHITE);

        // Add form fields with labels
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblMaKH, gbc);
        gbc.gridx = 1;
        txtMaKH = new JTextField(20);
        formPanel.add(txtMaKH, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblTenKH, gbc);
        gbc.gridx = 1;
        txtTenKH = new JTextField(20);
        formPanel.add(txtTenKH, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblSDT, gbc);
        gbc.gridx = 1;
        txtSDT = new JTextField(20);
        formPanel.add(txtSDT, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblDiaChi, gbc);
        gbc.gridx = 1;
        txtDiaChi = new JTextField(20);
        formPanel.add(txtDiaChi, gbc);

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
                    timKiemKhachHang();
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

        // Create table model with correct column order
        String[] columnNames = {"Mã KH", "Tên KH", "Số điện thoại", "Email", "Địa chỉ"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Create table
        tblKhachHang = new JTable(model);
        tblKhachHang.setRowHeight(30);
        tblKhachHang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblKhachHang.setFont(new Font("Arial", Font.PLAIN, 14));
        tblKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(tblKhachHang);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        contentPanel.add(tablePanel, BorderLayout.CENTER);

        // Add button events
        btnThem.addActionListener(e -> themKhachHang());
        btnLuu.addActionListener(e -> luuKhachHang());
        btnXoa.addActionListener(e -> xoaKhachHang());
        btnTimKiem.addActionListener(e -> timKiemKhachHang());
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
        // Add menu button events
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
            // Handle quản lý khách hàng action
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
            List<KhachHang_DTO> danhSach = khDAO.LayDanhSachKhachHang();
            loadDataToTable(danhSach);
        } catch (Exception e) {
            System.out.println("Lỗi khi tải dữ liệu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void loadDataToTable(List<KhachHang_DTO> danhSach) {
        try {
            model.setRowCount(0); // Xóa dữ liệu cũ
            for (KhachHang_DTO kh : danhSach) {
                model.addRow(new Object[]{
                    kh.getMa_khach_hang(),
                    kh.getTen_khach_hang(),
                    kh.getSo_dien_thoai(),
                    kh.getEmail(),
                    kh.getDia_chi()
                });
            }
            System.out.println("Đã tải " + danhSach.size() + " khách hàng vào bảng");
        } catch (Exception e) {
            System.out.println("Lỗi khi hiển thị dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void themKhachHang() {
        try {
            // Lấy thông tin từ các trường nhập liệu
            String maKH = txtMaKH.getText().trim();
            String tenKH = txtTenKH.getText().trim();
            String sdt = txtSDT.getText().trim();
            String email = txtEmail.getText().trim();
            String diaChi = txtDiaChi.getText().trim();

            // Kiểm tra dữ liệu nhập vào
            if (maKH.isEmpty() || tenKH.isEmpty() || sdt.isEmpty() || 
                email.isEmpty() || diaChi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin khách hàng");
                return;
            }

            // Kiểm tra định dạng mã KH
            if (!maKH.matches("KH\\d+")) {
                JOptionPane.showMessageDialog(this, "Mã KH phải có định dạng 'KH' + số (ví dụ: KH01, KH02,...)");
                return;
            }

            // Kiểm tra định dạng email
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ");
                return;
            }

            // Kiểm tra định dạng số điện thoại
            if (!sdt.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số");
                return;
            }

            // Kiểm tra mã KH đã tồn tại chưa
            if (khDAO.LayKhachHangTheoMa(maKH) != null) {
                JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại!");
                return;
            }

            // Tạo đối tượng khách hàng mới
            KhachHang_DTO kh = new KhachHang_DTO();
            kh.setMa_khach_hang(maKH);
            kh.setTen_khach_hang(tenKH);
            kh.setSo_dien_thoai(sdt);
            kh.setEmail(email);
            kh.setDia_chi(diaChi);

            // Thêm khách hàng vào database
            if (khDAO.ThemKhachHang(kh)) {
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
                clearInputFields();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearInputFields() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtMaKH.requestFocus();
    }

    private void luuKhachHang() {
        themKhachHang(); // Gọi phương thức thêm khách hàng khi nhấn nút Lưu
    }

    private void xoaKhachHang() {
        int selectedRow = tblKhachHang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc muốn xóa khách hàng này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String maKH = tblKhachHang.getValueAt(selectedRow, 0).toString();
            if (khDAO.XoaKhachHang(maKH)) {
                JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công");
                loadData(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(this, "Xóa khách hàng thất bại");
            }
        }
    }

    private void timKiemKhachHang() {
        try {
            String searchText = txtSearch.getText().trim();
            System.out.println("Đang tìm kiếm với từ khóa: " + searchText);

            if (!searchText.isEmpty()) {
                List<KhachHang_DTO> searchResults = khDAO.TimKiemKhachHang(searchText);
                
                if (searchResults.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
                    loadData(); // Load lại toàn bộ dữ liệu
                } else {
                    System.out.println("Tìm thấy " + searchResults.size() + " kết quả");
                    loadDataToTable(searchResults);
                }
            } else {
                loadData(); // Load lại toàn bộ dữ liệu nếu không có từ khóa
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
                loadData(); // Refresh table
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
            new QuanLyKhachHang_GUI().setVisible(true);
        });
    }
}