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
    private KhachHang_DAO khachHangDAO;
    private JTextField txtMaKH, txtTenKH, txtSDT, txtEmail, txtDiaChi, txtTimKiem;
    private JTable tableKhachHang;
    private DefaultTableModel tableModel;
    private JButton btnThem, btnLuu, btnXoa, btnTimKiem;

    private JPanel mainPanel;
    private JTable tblKhachHang;
    private DefaultTableModel model;
    private KhachHang_DAO khDAO;    

    private JButton btnBanHang;
    private JButton btnQuanLyNhanVien;
    private JButton btnQuanLySanPham;
    private JButton btnQuanLyKhachHang;
    private JButton btnQuanLyTaiKhoan;
    private JButton btnNhapHang;
    private JButton btnDangXuat;
    
    
    
    
    
    public QuanLyKhachHang_GUI() {
        khachHangDAO = new KhachHang_DAO();
        initComponents();
        loadDataToTable();
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
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);


        // Thêm các components vào input panel
        JLabel lblMaKH = new JLabel("Mã KH:");
        JLabel lblTenKH = new JLabel("Tên KH:");
        JLabel lblSDT = new JLabel("SĐT:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblDiaChi = new JLabel("Địa chỉ:");

        lblMaKH.setForeground(Color.WHITE);
        lblTenKH.setForeground(Color.WHITE);
        lblSDT.setForeground(Color.WHITE);
        lblEmail.setForeground(Color.WHITE);
        lblDiaChi.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(lblMaKH, gbc);     
        gbc.gridx = 1;
        txtMaKH = new JTextField(20);
        contentPanel.add(txtMaKH, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(lblTenKH, gbc);
        gbc.gridx = 1;
        txtTenKH = new JTextField(20);
        contentPanel.add(txtTenKH, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(lblSDT, gbc);
        gbc.gridx = 1;
        txtSDT = new JTextField(20);
        contentPanel.add(txtSDT, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        contentPanel.add(txtEmail, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPanel.add(lblDiaChi, gbc);
        gbc.gridx = 1;
        txtDiaChi = new JTextField(20);
        contentPanel.add(txtDiaChi, gbc);   
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(73, 138, 186));
        JLabel lblSearch = new JLabel("Từ khóa tìm:");
        lblSearch.setForeground(Color.WHITE);
        txtTimKiem = new JTextField(40);
        searchPanel.add(lblSearch);
        searchPanel.add(txtTimKiem);
        
       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
       buttonPanel.setBackground(new Color(73, 138, 186));

       Dimension buttonSize = new Dimension(120, 35);
        btnThem = createActionButton("Thêm", "images/add.png", buttonSize);
        btnLuu = createActionButton("Lưu", "images/save.png", buttonSize);
        btnXoa = createActionButton("Xóa", "images/delete.png", buttonSize);
        btnTimKiem = createActionButton("Tìm kiếm", "images/search.png", buttonSize);
        

       txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    timKiemKhachHang();
                }
            }
        });

        buttonPanel.add(btnThem);
        buttonPanel.add(btnLuu);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnTimKiem);
       

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(73, 138, 186));
        topPanel.add(contentPanel, BorderLayout.CENTER);

        JPanel searchButtonPanel = new JPanel(new BorderLayout());
        searchButtonPanel.setBackground(new Color(73, 138, 186));
        searchButtonPanel.add(searchPanel, BorderLayout.NORTH);
        searchButtonPanel.add(buttonPanel, BorderLayout.CENTER);
        topPanel.add(searchButtonPanel, BorderLayout.SOUTH);

        this.add(topPanel, BorderLayout.NORTH);
        tableKhachHang = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.setBackground(new Color(73, 138, 186));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Mã KH", "Tên KH", "SĐT", "Email", "Địa chỉ"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableKhachHang = new JTable(tableModel);
        tableKhachHang.setRowHeight(30);
        tableKhachHang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableKhachHang.setFont(new Font("Arial", Font.PLAIN, 14));
        tableKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
        scrollPane.setBackground(new Color(73, 138, 186));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Thêm sự kiện cho các nút
        btnThem.addActionListener(e -> themKhachHang());
        btnLuu.addActionListener(e -> luuKhachHang());
        btnXoa.addActionListener(e -> xoaKhachHang());
        btnTimKiem.addActionListener(e -> timKiemKhachHang());
       
        // Sự kiện khi click vào hàng trong bảng
        
        

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

   
        
        
        
        // Hover effect
      
    private void loadDataToTable() {
        tableModel.setRowCount(0);
        List<KhachHang_DTO> danhSachKH = khachHangDAO.LayDanhSachKhachHang();
        for (KhachHang_DTO kh : danhSachKH) {
            Object[] row = {
                kh.getMa_khach_hang(),
                kh.getTen_khach_hang(),
                kh.getSo_dien_thoai(),
                kh.getEmail(),
                kh.getDia_chi()
            };
            tableModel.addRow(row);
        }
    }

    private void themKhachHang() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtMaKH.requestFocus();
    }

    private void luuKhachHang() {
        String maKH = txtMaKH.getText().trim();
        String tenKH = txtTenKH.getText().trim();
        String sdt = txtSDT.getText().trim();
        String email = txtEmail.getText().trim();
        String diaChi = txtDiaChi.getText().trim();

        if (maKH.isEmpty() || tenKH.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin bắt buộc (Mã KH, Tên KH)");
            return;
        }

        KhachHang_DTO kh = new KhachHang_DTO(maKH, tenKH, sdt, email, diaChi);
        
        if (khachHangDAO.ThemKhachHang(kh)) {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
            loadDataToTable();
            themKhachHang(); // Clear form
        } else {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại!");
        }
    }

    private void xoaKhachHang() {
        int row = tableKhachHang.getSelectedRow();
        if (row >= 0) {
            String maKH = tableModel.getValueAt(row, 0).toString();
            int option = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc muốn xóa khách hàng này?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                if (khachHangDAO.XoaKhachHang(maKH)) {
                    JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!");
                    loadDataToTable();
                    themKhachHang(); // Clear form
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa khách hàng thất bại!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa!");
        }
    }

    private void timKiemKhachHang() {
        String tuKhoa = txtTimKiem.getText().trim();
        if (!tuKhoa.isEmpty()) {
            tableModel.setRowCount(0);
            List<KhachHang_DTO> ketQua = khachHangDAO.TimKiemKhachHang(tuKhoa);
            for (KhachHang_DTO kh : ketQua) {
                Object[] row = {
                    kh.getMa_khach_hang(),
                    kh.getTen_khach_hang(),
                    kh.getSo_dien_thoai(),
                    kh.getEmail(),
                    kh.getDia_chi()
                };
                tableModel.addRow(row);
            }
        } else {
            loadDataToTable();
        }
    }

    private void xuatDuLieu() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn vị trí lưu file");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }
                
            
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất dữ liệu: " + e.getMessage());
        }
    }

    private void nhapDuLieu() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn file nhập dữ liệu");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));
            
            
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                
                JOptionPane.showMessageDialog(this, "Nhập dữ liệu thành công!");
                loadDataToTable();
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
