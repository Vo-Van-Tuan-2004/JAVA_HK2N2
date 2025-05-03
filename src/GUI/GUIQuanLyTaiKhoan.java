package GUI;

import BLL.taiKhoan_BLL;
import DTO.taiKhoan_DTO;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class GUIQuanLyTaiKhoan extends JFrame {
    private JPanel mainPanel;
    private JTable tblTaiKhoan;
    private DefaultTableModel model;
    private taiKhoan_BLL tkBLL;

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
    private JTextField txtMaTaiKhoan;
    private JTextField txtTenTaiKhoan;
    private JTextField txtMatKhau;
    private JTextField txtSearch;

    // New JComboBox for employee selection
    private JComboBox<String> comboNhanVien;

    // Action buttons
    private JButton btnThem;
    private JButton btnLuu;
    private JButton btnXoa;
    private JButton btnTimKiem;
    private JButton btnXuat;
    private JButton btnNhap;

    public GUIQuanLyTaiKhoan() {
        try {
            tkBLL = new taiKhoan_BLL();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khởi tạo BLL: " + e.getMessage());
        }
        initComponents();
        loadData();
    }

    private void loadNhanVienToCombo() {
        try {
            // Assuming there is a BLL or DAO to get employee list
            // I will create a new instance of QuanLyNhanVien_GUI to get employee list or directly use DAO
            // For simplicity, I will use DAO here
            java.util.List<DTO.NhanVien_DTO> danhSachNhanVien = new DAO.NhanVien_DAO().LayDanhSachNhanVien();
            comboNhanVien.removeAllItems();
            for (DTO.NhanVien_DTO nv : danhSachNhanVien) {
                comboNhanVien.addItem(nv.getMa_nhan_vien() + " - " + nv.getTen_nhan_vien());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách nhân viên: " + e.getMessage());
        }
    }

    private void initComponents() {
        setTitle("Quản lý cửa hàng bán xe máy");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(0, 0));

        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, BorderLayout.WEST);

        JPanel contentPanel = createContentPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
        addEvents();
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new BorderLayout(0, 0));
        menuPanel.setBackground(new Color(72, 25, 161));
        menuPanel.setPreferredSize(new Dimension(250, getHeight()));

        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setBackground(new Color(72, 25, 161));
        logoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon logo = new ImageIcon(
            new ImageIcon(getClass().getResource("/IMG/logo.png"))
            .getImage()
            .getScaledInstance(230, 100, Image.SCALE_SMOOTH)
        );
        JLabel logoLabel = new JLabel(logo);
        logoPanel.add(logoLabel, BorderLayout.CENTER);

        JLabel chucNangLabel = new JLabel("Chức năng");
        chucNangLabel.setForeground(Color.WHITE);
        chucNangLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chucNangLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoPanel.add(chucNangLabel, BorderLayout.SOUTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(new Color(72, 25, 161));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        btnBanHang = createMenuButton("Bán hàng");
        btnQuanLyNhanVien = createMenuButton("Quản lý nhân viên");
        btnQuanLySanPham = createMenuButton("Quản lý sản phẩm");
        btnQuanLyKhachHang = createMenuButton("Quản lý khách hàng");
        btnQuanLyTaiKhoan = createMenuButton("Quản lý tài khoản");
        btnNhapHang = createMenuButton("Nhập hàng");
        btnThongKe = createMenuButton("Thống kê");
        btnDangXuat = createMenuButton("Đăng xuất");

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
        contentPanel.setBackground(new Color(73, 138, 186));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(73, 138, 186));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblMaTaiKhoan = new JLabel("Mã tài khoản:");
        JLabel lblTenTaiKhoan = new JLabel("Tên tài khoản:");
        JLabel lblMatKhau = new JLabel("Mật khẩu:");

        lblMaTaiKhoan.setForeground(Color.WHITE);
        lblTenTaiKhoan.setForeground(Color.WHITE);
        lblMatKhau.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblMaTaiKhoan, gbc);
        gbc.gridx = 1;
        // Reintroduce txtMaTaiKhoan as editable text field for account code input
        txtMaTaiKhoan = new JTextField(15);
        txtMaTaiKhoan.setPreferredSize(new Dimension(200, 25));
        formPanel.add(txtMaTaiKhoan, gbc);

        // Keep comboNhanVien for employee selection but do not add to form here
        comboNhanVien = new JComboBox<>();
        comboNhanVien.setPreferredSize(new Dimension(200, 25));
        comboNhanVien.setVisible(false); // hide combo box from UI, used for validation only
        formPanel.add(comboNhanVien, gbc);

        // Load employee list into comboNhanVien for validation
        loadNhanVienToCombo();

        // Remove listener that updates txtMaTaiKhoanDisplay (no longer needed)

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblTenTaiKhoan, gbc);
        gbc.gridx = 1;
        txtTenTaiKhoan = new JTextField(20);
        formPanel.add(txtTenTaiKhoan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblMatKhau, gbc);
        gbc.gridx = 1;
        txtMatKhau = new JTextField(20);
        formPanel.add(txtMatKhau, gbc);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(73, 138, 186));
        JLabel lblSearch = new JLabel("Từ khóa tìm:");
        lblSearch.setForeground(Color.WHITE);
        txtSearch = new JTextField(40);
        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        buttonPanel.setBackground(new Color(73, 138, 186));

        Dimension buttonSize = new Dimension(120, 35);
        btnThem = createActionButton("Thêm", "/IMG/add.png", buttonSize);
        btnLuu = createActionButton("Lưu", "/IMG/save.png", buttonSize);
        btnXoa = createActionButton("Xóa", "/IMG/delete.png", buttonSize);
        btnTimKiem = createActionButton("Tìm kiếm", "/IMG/search.png", buttonSize);
        btnXuat = createActionButton("Xuất", "/IMG/export.png", buttonSize);
        btnNhap = createActionButton("Nhập", "/IMG/import.png", buttonSize);

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    timKiemTaiKhoan();
                }
            }
        });

        buttonPanel.add(btnThem);
        buttonPanel.add(btnLuu);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnTimKiem);
        buttonPanel.add(btnXuat);
        buttonPanel.add(btnNhap);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(73, 138, 186));
        topPanel.add(formPanel, BorderLayout.CENTER);

        JPanel searchButtonPanel = new JPanel(new BorderLayout());
        searchButtonPanel.setBackground(new Color(73, 138, 186));
        searchButtonPanel.add(searchPanel, BorderLayout.NORTH);
        searchButtonPanel.add(buttonPanel, BorderLayout.CENTER);
        topPanel.add(searchButtonPanel, BorderLayout.SOUTH);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(73, 138, 186));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Mã tài khoản", "Tên tài khoản", "Mật khẩu"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblTaiKhoan = new JTable(model);
        tblTaiKhoan.setRowHeight(30);
        tblTaiKhoan.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblTaiKhoan.setFont(new Font("Arial", Font.PLAIN, 14));
        tblTaiKhoan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tblTaiKhoan);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        return contentPanel;
    }

    private JButton createActionButton(String text, String iconPath, Dimension size) {
        JButton button = new JButton(text);
        try {
            java.net.URL iconURL = getClass().getResource(iconPath);
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(img));
            } else {
                System.out.println("Không tìm thấy icon tại đường dẫn: " + iconPath);
            }
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
            // TODO: Implement Bán hàng GUI
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
            try {
                new QuanLySanPham_GUI().setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Chức năng đang hoàn thiện", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
            // Already in this GUI, do nothing or refresh
        });

        btnNhapHang.addActionListener(e -> {
            // Commented out because NhapHang_GUI does not have setVisible method
            /*
            try {
                new NhapHang_GUI().setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Chức năng Nhập hàng đang phát triển!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            */
        });

        btnThongKe.addActionListener(e -> {
            // TODO: Implement Thống kê GUI
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

        btnThem.addActionListener(e -> themTaiKhoan());
        btnLuu.addActionListener(e -> luuTaiKhoan());
        btnXoa.addActionListener(e -> xoaTaiKhoan());
        btnTimKiem.addActionListener(e -> timKiemTaiKhoan());
        btnXuat.addActionListener(e -> xuatDuLieu());
        btnNhap.addActionListener(e -> nhapDuLieu());

        tblTaiKhoan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblTaiKhoan.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    String maTaiKhoan = tblTaiKhoan.getValueAt(row, 0).toString();
                    String tenTaiKhoan = tblTaiKhoan.getValueAt(row, 1).toString();
                    String matKhau = tblTaiKhoan.getValueAt(row, 2).toString();

                    txtMaTaiKhoan.setText(maTaiKhoan);

                    // Set comboNhanVien selection based on maTaiKhoan for validation
                    for (int i = 0; i < comboNhanVien.getItemCount(); i++) {
                        String item = comboNhanVien.getItemAt(i);
                        if (item.startsWith(maTaiKhoan + " -")) {
                            comboNhanVien.setSelectedIndex(i);
                            break;
                        }
                    }

                    txtTenTaiKhoan.setText(tenTaiKhoan);
                    txtMatKhau.setText(matKhau);
                }
            }
        });
    }

    private void loadData() {
        try {
            List<taiKhoan_DTO> danhSach = tkBLL.getAllAccounts();
            loadDataToTable(danhSach);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void loadDataToTable(List<taiKhoan_DTO> danhSach) {
        model.setRowCount(0);
        for (taiKhoan_DTO tk : danhSach) {
            model.addRow(new Object[]{
                tk.getMaTaiKhoan(),
                tk.getTenTaiKhoan(),
                tk.getMatKhau()
            });
        }
    }

    private void themTaiKhoan() {
        try {
            String maTK = txtMaTaiKhoan.getText().trim();
            String tenTK = txtTenTaiKhoan.getText().trim();
            String matKhau = txtMatKhau.getText().trim();

            if (maTK.isEmpty() || tenTK.isEmpty() || matKhau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin tài khoản");
                return;
            }

            // Validate maTK against employee codes in comboNhanVien
            boolean validMaTK = false;
            for (int i = 0; i < comboNhanVien.getItemCount(); i++) {
                String item = comboNhanVien.getItemAt(i);
                String maNV = item.split(" - ")[0].trim();
                if (maNV.equals(maTK)) {
                    validMaTK = true;
                    break;
                }
            }
            if (!validMaTK) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại");
                return;
            }

            // Check if account ID exists by searching all accounts
            boolean exists = false;
            for (taiKhoan_DTO acc : tkBLL.getAllAccounts()) {
                if (acc.getMaTaiKhoan().equals(maTK)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                JOptionPane.showMessageDialog(this, "Mã tài khoản đã tồn tại");
                return;
            }

            taiKhoan_DTO tk = new taiKhoan_DTO(maTK, tenTK, matKhau);
            try {
                if (tkBLL.registerUser(maTK, tenTK, matKhau)) {
                    JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công");
                    clearInputFields();
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm tài khoản thất bại");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm tài khoản: " + ex.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void luuTaiKhoan() {
        try {
            String maTK = txtMaTaiKhoan.getText().trim();
            String tenTK = txtTenTaiKhoan.getText().trim();
            String matKhau = txtMatKhau.getText().trim();

            if (maTK.isEmpty() || tenTK.isEmpty() || matKhau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin tài khoản");
                return;
            }

            // Validate maTK against employee codes in comboNhanVien
            boolean validMaTK = false;
            for (int i = 0; i < comboNhanVien.getItemCount(); i++) {
                String item = comboNhanVien.getItemAt(i);
                String maNV = item.split(" - ")[0].trim();
                if (maNV.equals(maTK)) {
                    validMaTK = true;
                    break;
                }
            }
            if (!validMaTK) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại");
                return;
            }

            if (tkBLL.updateAccount(maTK, tenTK, matKhau)) {
                JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thành công");
                clearInputFields();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thất bại");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void xoaTaiKhoan() {
        int selectedRow = tblTaiKhoan.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần xóa");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc muốn xóa tài khoản này?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String maTK = tblTaiKhoan.getValueAt(selectedRow, 0).toString();
            if (tkBLL.deleteAccount(maTK)) {
                JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công");
                clearInputFields();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại");
            }
        }
    }

    private void timKiemTaiKhoan() {
        try {
            String keyword = txtSearch.getText().trim();
            List<taiKhoan_DTO> results;
            if (keyword.isEmpty()) {
                results = tkBLL.getAllAccounts();
            } else {
                results = tkBLL.searchAccounts(keyword);
            }
            loadDataToTable(results);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm tài khoản: " + e.getMessage());
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
                // TODO: Implement Excel export functionality
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
                // TODO: Implement Excel import functionality
                JOptionPane.showMessageDialog(this, "Nhập dữ liệu thành công từ file: " + filePath);
                loadData();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi nhập dữ liệu: " + e.getMessage());
        }
    }

    private void clearInputFields() {
        txtMaTaiKhoan.setText("");
        txtTenTaiKhoan.setText("");
        txtMatKhau.setText("");
        txtTenTaiKhoan.requestFocus();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new GUIQuanLyTaiKhoan().setVisible(true);
        });
    }

    private void generateAndSetUniqueAccountID() {
        DAO.NhanVien_DAO nvDAO = new DAO.NhanVien_DAO();
        List<String> employeeIDs = new java.util.ArrayList<>();
        for (DTO.NhanVien_DTO nv : nvDAO.LayDanhSachNhanVien()) {
            employeeIDs.add(nv.getMa_nhan_vien());
        }

        List<String> accountIDs = new java.util.ArrayList<>();
        for (taiKhoan_DTO acc : tkBLL.getAllAccounts()) {
            accountIDs.add(acc.getMaTaiKhoan());
        }

        String newID;
        do {
            newID = "TK" + (int)(Math.random() * 90000 + 10000); // TK + 5 digit random number
        } while (employeeIDs.contains(newID) || accountIDs.contains(newID));

        txtMaTaiKhoan.setText(newID);
    }
}
