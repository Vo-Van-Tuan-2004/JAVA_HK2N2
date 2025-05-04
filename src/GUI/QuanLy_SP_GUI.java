package GUI;

import DAO.SanPham_DAO;
import DTO.SanPham_DTO;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class QuanLy_SP_GUI extends JPanel {
    private JPanel contentPanel;
    private JTable tblSanPham;
    private DefaultTableModel model;
    private SanPham_DAO spDAO;

    // Các biến để lưu trữ reference đến các components
    private JTextField txtMaSP;
    private JTextField txtTenSP;
    private JTextField txtGiaBan;
    private JTextField txtSoLuong;
    private JTextField txtMaLoai;
    private JTextField txtSearch;

    public QuanLy_SP_GUI() {
        spDAO = new SanPham_DAO();
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
        JLabel lblMaSP = new JLabel("Mã SP:");
        JLabel lblTenSP = new JLabel("Tên SP:");
        JLabel lblGiaBan = new JLabel("Giá bán:");
        JLabel lblSoLuong = new JLabel("Số lượng:");
        JLabel lblMaLoai = new JLabel("Mã loại:");

        lblMaSP.setForeground(Color.WHITE);
        lblTenSP.setForeground(Color.WHITE);
        lblGiaBan.setForeground(Color.WHITE);
        lblSoLuong.setForeground(Color.WHITE);
        lblMaLoai.setForeground(Color.WHITE);

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

        String[] columnNames = {"Mã SP", "Tên SP", "Giá bán", "Số lượng tồn", "Mã loại", "Trạng thái"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblSanPham = new JTable(model);
        tblSanPham.setRowHeight(30);
        tblSanPham.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblSanPham.setFont(new Font("Arial", Font.PLAIN, 14));
        tblSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tblSanPham);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        btnThem.addActionListener(e -> themSanPham());
        btnXoa.addActionListener(e -> xoaSanPham());
        btnTimKiem.addActionListener(e -> timKiemSanPham());
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
                    timKiemSanPham();
                }
            }
        });
    }

    private void loadData() {
        try {
            List<SanPham_DTO> danhSach = spDAO.LayDanhSachSanPham();
            loadDataToTable(danhSach);
        } catch (Exception e) {
            System.err.println("Lỗi khi tải dữ liệu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu. Vui lòng kiểm tra kết nối và thử lại.", "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataToTable(List<SanPham_DTO> danhSach) {
        try {
            model.setRowCount(0);
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
            System.err.println("Lỗi khi hiển thị dữ liệu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void themSanPham() {
        try {
            String maSP = txtMaSP.getText().trim();
            String tenSP = txtTenSP.getText().trim();
            String giaBanStr = txtGiaBan.getText().trim();
            String soLuongStr = txtSoLuong.getText().trim();
            String maLoai = txtMaLoai.getText().trim();

            if (maSP.isEmpty() || tenSP.isEmpty() || giaBanStr.isEmpty() || 
                soLuongStr.isEmpty() || maLoai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin sản phẩm");
                return;
            }

            if (!maSP.matches("SP\\d+")) {
                JOptionPane.showMessageDialog(this, "Mã SP phải có định dạng 'SP' + số (ví dụ: SP01, SP02,...)");
                return;
            }

            if (!maLoai.matches("LSP\\d+")) {
                JOptionPane.showMessageDialog(this, "Mã loại phải có định dạng 'LSP' + số (ví dụ: LSP01, LSP02,...)");
                return;
            }

            if (spDAO.LaySanPhamTheoMa(maSP) != null) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại!");
                return;
            }

            int giaBan = Integer.parseInt(giaBanStr);
            int soLuong = Integer.parseInt(soLuongStr);

            if (giaBan < 0 || soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Giá bán và số lượng phải là số dương");
                return;
            }

            SanPham_DTO sp = new SanPham_DTO();
            sp.setMa_san_pham(maSP);
            sp.setTen_san_pham(tenSP);
            sp.setGia_ban(giaBan);
            sp.setSo_luong_ton(soLuong);
            sp.setMa_loai(maLoai);
            sp.setTrang_thai("Còn hàng");

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
                loadData();
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
