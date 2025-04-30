package GUI;

import DAO.SanPham_DAO;
import DTO.SanPham_DTO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

public class QuanLySanPham_GUI extends JFrame {
    private JPanel mainPanel;
    private JTable tblSanPham;
    private DefaultTableModel model;
    private SanPham_DAO spDAO;

    // Menu buttons
    private JButton btnBanHang;
    private JButton btnQuanLyNhanVien;
    private JButton btnQuanLySanPham;
    private JButton btnQuanLyKhachHang;
    private JButton btnQuanLyTaiKhoan;
    private JButton btnNhapHang;
    private JButton btnDangXuat;

    // Các biến để lưu trữ reference đến các components
    private JTextField txtMaSP;
    private JTextField txtTenSP;
    private JTextField txtGiaBan;
    private JTextField txtSoLuong;
    private JTextField txtMaLoai;
    private JTextField txtSearch;

    public QuanLySanPham_GUI() {
        spDAO = new SanPham_DAO();
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
        ImageIcon logo = new ImageIcon("images/logo.png");
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
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(240, 240, 240));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE);
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
        JLabel lblMaSP = new JLabel("Mã SP:");
        JLabel lblTenSP = new JLabel("Tên SP:");
        JLabel lblGiaBan = new JLabel("Giá bán:");
        JLabel lblSoLuong = new JLabel("Số lượng:");
        JLabel lblMaLoai = new JLabel("Mã loại:");

        // Set white color for labels
        lblMaSP.setForeground(Color.WHITE);
        lblTenSP.setForeground(Color.WHITE);
        lblGiaBan.setForeground(Color.WHITE);
        lblSoLuong.setForeground(Color.WHITE);
        lblMaLoai.setForeground(Color.WHITE);

        // Add form fields with labels
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblMaSP, gbc);
        gbc.gridx = 1;
        txtMaSP = new JTextField(20);
        formPanel.add(txtMaSP, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblTenSP, gbc);
        gbc.gridx = 1;
        txtTenSP = new JTextField(20);
        formPanel.add(txtTenSP, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblGiaBan, gbc);
        gbc.gridx = 1;
        txtGiaBan = new JTextField(20);
        formPanel.add(txtGiaBan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblSoLuong, gbc);
        gbc.gridx = 1;
        txtSoLuong = new JTextField(20);
        formPanel.add(txtSoLuong, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblMaLoai, gbc);
        gbc.gridx = 1;
        txtMaLoai = new JTextField(20);
        formPanel.add(txtMaLoai, gbc);

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
                    timKiemSanPham();
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
        String[] columnNames = {"Mã SP", "Tên SP", "Giá bán", "Số lượng tồn", "Mã loại", "Trạng thái"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Create table
        tblSanPham = new JTable(model);
        tblSanPham.setRowHeight(30);
        tblSanPham.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblSanPham.setFont(new Font("Arial", Font.PLAIN, 14));
        tblSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(tblSanPham);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        contentPanel.add(tablePanel, BorderLayout.CENTER);

        // Add button events
        btnThem.addActionListener(e -> themSanPham());
        btnLuu.addActionListener(e -> luuSanPham());
        btnXoa.addActionListener(e -> xoaSanPham());
        btnTimKiem.addActionListener(e -> timKiemSanPham());
        btnXuat.addActionListener(e -> xuatDuLieu());
        btnNhap.addActionListener(e -> nhapDuLieu());

        // Load initial data
        loadData();

        return contentPanel;
    }

    private void addFormField(JPanel panel, String label, JComponent field, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);

        // Lưu reference đến các components
        if (label.equals("Mã SP:")) {
            txtMaSP = (JTextField) field;
        } else if (label.equals("Tên SP:")) {
            txtTenSP = (JTextField) field;
        } else if (label.equals("Giá bán:")) {
            txtGiaBan = (JTextField) field;
        } else if (label.equals("Số lượng:")) {
            txtSoLuong = (JTextField) field;
        } else if (label.equals("Mã loại:")) {
            txtMaLoai = (JTextField) field;
        }
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
            // Handle bán hàng action
        });
        
        btnQuanLyNhanVien.addActionListener(e -> {
            // Handle quản lý nhân viên action
        });
        
        btnQuanLySanPham.addActionListener(e -> {
            // Handle quản lý sản phẩm action
        });
        
        btnQuanLyKhachHang.addActionListener(e -> {
            // Handle quản lý khách hàng action
        });
        
        btnQuanLyTaiKhoan.addActionListener(e -> {
            // Handle quản lý tài khoản action
        });
        
        btnNhapHang.addActionListener(e -> {
            // Handle nhập hàng action
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
        List<SanPham_DTO> danhSach = spDAO.LayDanhSachSanPham();
        loadDataToTable(danhSach);
        } catch (Exception e) {
            System.out.println("Lỗi khi tải dữ liệu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void loadDataToTable(List<SanPham_DTO> danhSach) {
        try {
            model.setRowCount(0); // Xóa dữ liệu cũ
        for (SanPham_DTO sp : danhSach) {
            model.addRow(new Object[]{
                sp.getMa_san_pham(),
                sp.getTen_san_pham(),
                    sp.getGia_ban(),
                sp.getSo_luong_ton(),
                    sp.getMa_loai(),
                    sp.getTrang_thai()
                });
            }
            System.out.println("Đã tải " + danhSach.size() + " sản phẩm vào bảng");
        } catch (Exception e) {
            System.out.println("Lỗi khi hiển thị dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void themSanPham() {
        try {
            // Lấy thông tin từ các trường nhập liệu
            String maSP = txtMaSP.getText().trim();
            String tenSP = txtTenSP.getText().trim();
            String giaBanStr = txtGiaBan.getText().trim();
            String soLuongStr = txtSoLuong.getText().trim();
            String maLoai = txtMaLoai.getText().trim();

            // Kiểm tra dữ liệu nhập vào
            if (maSP.isEmpty() || tenSP.isEmpty() || giaBanStr.isEmpty() || 
                soLuongStr.isEmpty() || maLoai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin sản phẩm");
                return;
            }

            // Kiểm tra định dạng mã SP
            if (!maSP.matches("SP\\d+")) {
                JOptionPane.showMessageDialog(this, "Mã SP phải có định dạng 'SP' + số (ví dụ: SP01, SP02,...)");
                return;
            }

            // Kiểm tra định dạng mã loại
            if (!maLoai.matches("LSP\\d+")) {
                JOptionPane.showMessageDialog(this, "Mã loại phải có định dạng 'LSP' + số (ví dụ: LSP01, LSP02,...)");
                return;
            }

            // Kiểm tra mã SP đã tồn tại chưa
            if (spDAO.LaySanPhamTheoMa(maSP) != null) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại!");
                return;
            }

            // Chuyển đổi và kiểm tra giá bán và số lượng
            int giaBan = Integer.parseInt(giaBanStr);
            int soLuong = Integer.parseInt(soLuongStr);

            if (giaBan < 0 || soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Giá bán và số lượng phải là số dương");
                return;
            }

            // Tạo đối tượng sản phẩm mới
            SanPham_DTO sp = new SanPham_DTO();
            sp.setMa_san_pham(maSP);
            sp.setTen_san_pham(tenSP);
            sp.setGia_ban(giaBan);
            sp.setSo_luong_ton(soLuong);
            sp.setMa_loai(maLoai);
            sp.setTrang_thai("Còn hàng");

            // Thêm sản phẩm vào database
            if (spDAO.ThemSanPham(sp)) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                clearInputFields();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại. Vui lòng kiểm tra lại mã loại sản phẩm!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá bán và số lượng phải là số");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearInputFields() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtGiaBan.setText("");
        txtSoLuong.setText("");
        txtMaLoai.setText("");
        txtMaSP.requestFocus();
    }

    private void luuSanPham() {
        themSanPham(); // Gọi phương thức thêm sản phẩm khi nhấn nút Lưu
    }

    private void xoaSanPham() {
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc muốn xóa sản phẩm này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String maSP = tblSanPham.getValueAt(selectedRow, 0).toString();
            if (spDAO.XoaSanPham(maSP)) {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");
                loadData(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại");
            }
        }
    }

    private void timKiemSanPham() {
        try {
            String searchText = txtSearch.getText().trim();
            System.out.println("Đang tìm kiếm với từ khóa: " + searchText);

            if (!searchText.isEmpty()) {
                List<SanPham_DTO> searchResults = spDAO.TimKiemSanPham(searchText);
                
                if (searchResults.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
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
                new QuanLySanPham_GUI().setVisible(true);
        });
    }
}
