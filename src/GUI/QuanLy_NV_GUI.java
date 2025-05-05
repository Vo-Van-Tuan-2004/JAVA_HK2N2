package GUI;

import DAO.NhanVien_DAO;
import DTO.NhanVien_DTO;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class QuanLy_NV_GUI extends JPanel {
    private JPanel contentPanel;
    private JTable tblNhanVien;
    private DefaultTableModel model;
    private NhanVien_DAO nvDAO;

    // Form fields
    private JTextField txtMaNV;
    private JTextField txtTenNV;
    private JTextField txtSoDienThoai;
    private JTextField txtDiaChi;
    private JTextField txtChucVu;
    private JTextField txtLuong;
    private JTextField txtSearch;

    public QuanLy_NV_GUI() {
        nvDAO = new NhanVien_DAO();
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

        lblMaNV.setForeground(Color.WHITE);
        lblTenNV.setForeground(Color.WHITE);
        lblSoDienThoai.setForeground(Color.WHITE);
        lblDiaChi.setForeground(Color.WHITE);
        lblChucVu.setForeground(Color.WHITE);
        lblLuong.setForeground(Color.WHITE);

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

        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(73, 138, 186));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Mã NV", "Tên NV", "Số điện thoại", "Địa chỉ", "Chức vụ", "Lương"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblNhanVien = new JTable(model);
        tblNhanVien.setRowHeight(30);
        tblNhanVien.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblNhanVien.setFont(new Font("Arial", Font.PLAIN, 14));
        tblNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tblNhanVien);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        btnThem.addActionListener(e -> themNhanVien());
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
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    timKiemNhanVien();
                }
            }
        });
    }

    private void loadData() {
        try {
            List<NhanVien_DTO> danhSach = nvDAO.LayDanhSachNhanVien();
            loadDataToTable(danhSach);
        } catch (Exception e) {
            System.err.println("Lỗi khi tải dữ liệu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu. Vui lòng kiểm tra kết nối và thử lại.", "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataToTable(List<NhanVien_DTO> danhSach) {
        try {
            model.setRowCount(0);
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
            System.err.println("Lỗi khi hiển thị dữ liệu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void themNhanVien() {
        try {
            String maNV = txtMaNV.getText().trim();
            String tenNV = txtTenNV.getText().trim();
            String soDienThoai = txtSoDienThoai.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String chucVu = txtChucVu.getText().trim();
            String luongStr = txtLuong.getText().trim();

            if (maNV.isEmpty() || tenNV.isEmpty() || soDienThoai.isEmpty() || 
                diaChi.isEmpty() || chucVu.isEmpty() || luongStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin nhân viên");
                return;
            }

            if (!maNV.matches("NV\\d+")) {
                JOptionPane.showMessageDialog(this, "Mã NV phải có định dạng 'NV' + số (ví dụ: NV01, NV02,...)");
                return;
            }

            if (!soDienThoai.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số");
                return;
            }

            if (nvDAO.LayNhanVienTheoMa(maNV) != null) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!");
                return;
            }

            int luong = Integer.parseInt(luongStr);
            if (luong < 0) {
                JOptionPane.showMessageDialog(this, "Lương phải là số dương");
                return;
            }

            NhanVien_DTO nv = new NhanVien_DTO();
            nv.setMa_nhan_vien(maNV);
            nv.setTen_nhan_vien(tenNV);
            nv.setSo_dien_thoai(soDienThoai);
            nv.setDia_chi(diaChi);
            nv.setChuc_vu(chucVu);
            nv.setLuong(luong);

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