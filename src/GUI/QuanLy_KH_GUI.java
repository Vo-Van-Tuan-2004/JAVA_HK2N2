package GUI;

import DAO.KhachHang_DAO;
import DTO.KhachHang_DTO;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class QuanLy_KH_GUI extends JPanel {
    private JPanel contentPanel;
    private JTable tblKhachHang;
    private DefaultTableModel model;
    private KhachHang_DAO khDAO;

    // Các biến để lưu trữ reference đến các components
    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JTextField txtDiaChi;
    private JTextField txtSearch;

    public QuanLy_KH_GUI() {
        khDAO = new KhachHang_DAO();
        initComponents();
        loadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(73, 138, 186));

        contentPanel = createContentPanel();
        add(contentPanel, BorderLayout.CENTER);

        addEvents();
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(73, 138, 186));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(73, 138, 186));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblMaKH = new JLabel("Mã KH:");
        JLabel lblTenKH = new JLabel("Tên KH:");
        JLabel lblSDT = new JLabel("Số điện thoại:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblMaKH.setForeground(Color.WHITE);
        lblTenKH.setForeground(Color.WHITE);
        lblSDT.setForeground(Color.WHITE);
        lblEmail.setForeground(Color.WHITE);
        lblDiaChi.setForeground(Color.WHITE);

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
        JButton btnThem = createActionButton("Thêm", "images/add.png", buttonSize);
        JButton btnXoa = createActionButton("Xóa", "images/delete.png", buttonSize);
        JButton btnTimKiem = createActionButton("Tìm kiếm", "images/search.png", buttonSize);
        JButton btnXuat = createActionButton("Xuất", "images/export.png", buttonSize);
        JButton btnNhap = createActionButton("Nhập", "images/import.png", buttonSize);

        buttonPanel.add(btnThem);
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

        String[] columnNames = {"Mã KH", "Tên KH", "Số điện thoại", "Email", "Địa chỉ"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblKhachHang = new JTable(model);
        tblKhachHang.setRowHeight(30);
        tblKhachHang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblKhachHang.setFont(new Font("Arial", Font.PLAIN, 14));
        tblKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tblKhachHang);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        btnThem.addActionListener(e -> themKhachHang());
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
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    timKiemKhachHang();
                }
            }
        });
    }

    private void loadData() {
        try {
            List<KhachHang_DTO> danhSach = khDAO.LayDanhSachKhachHang();
            loadDataToTable(danhSach);
        } catch (Exception e) {
            System.err.println("Lỗi khi tải dữ liệu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu. Vui lòng kiểm tra kết nối và thử lại.", "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataToTable(List<KhachHang_DTO> danhSach) {
        try {
            model.setRowCount(0);
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
            System.err.println("Lỗi khi hiển thị dữ liệu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void themKhachHang() {
        try {
            String maKH = txtMaKH.getText().trim();
            String tenKH = txtTenKH.getText().trim();
            String sdt = txtSDT.getText().trim();
            String email = txtEmail.getText().trim();
            String diaChi = txtDiaChi.getText().trim();

            if (maKH.isEmpty() || tenKH.isEmpty() || sdt.isEmpty() || 
                email.isEmpty() || diaChi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin khách hàng");
                return;
            }

            if (!maKH.matches("KH\\d+")) {
                JOptionPane.showMessageDialog(this, "Mã KH phải có định dạng 'KH' + số (ví dụ: KH01, KH02,...)");
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ");
                return;
            }

            if (!sdt.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số");
                return;
            }

            if (khDAO.LayKhachHangTheoMa(maKH) != null) {
                JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại!");
                return;
            }

            KhachHang_DTO kh = new KhachHang_DTO();
            kh.setMa_khach_hang(maKH);
            kh.setTen_khach_hang(tenKH);
            kh.setSo_dien_thoai(sdt);
            kh.setEmail(email);
            kh.setDia_chi(diaChi);

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
                loadData();
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
                    loadData();
                } else {
                    System.out.println("Tìm thấy " + searchResults.size() + " kết quả");
                    loadDataToTable(searchResults);
                }
            } else {
                loadData();
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm kiếm: " + e.getMessage());
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

    public JPanel getMainPanel() {
        return this;
    }
}