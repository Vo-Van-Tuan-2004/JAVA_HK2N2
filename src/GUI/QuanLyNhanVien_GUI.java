package GUI;

import DAO.NhanVien_DAO;
import DTO.NhanVien_DTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QuanLyNhanVien_GUI extends JFrame {
    private JPanel mainPanel;
    private JTable tblNhanVien;
    private DefaultTableModel model;
    private JTextField txtMaNV, txtTen, txtSDT, txtUsername, txtPassword, txtMucLuong, txtDiaChi;
    private JComboBox<String> cboChucVu, cboGioiTinh;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi;
    private NhanVien_DAO nvDAO;

    public QuanLyNhanVien_GUI() {
        nvDAO = new NhanVien_DAO();
        initComponents();
        loadData();
    }

    private void initComponents() {
        setTitle("Quản lý nhân viên");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel bên trái chứa form nhập liệu và nút chức năng
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        
        // Panel nhập liệu
        JPanel pnlNhapLieu = new JPanel(new GridLayout(9, 2, 5, 5));
        pnlNhapLieu.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Thông tin nhân viên"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        pnlNhapLieu.add(new JLabel("Mã nhân viên:"));
        txtMaNV = new JTextField();
        pnlNhapLieu.add(txtMaNV);

        pnlNhapLieu.add(new JLabel("Tên:"));
        txtTen = new JTextField();
        pnlNhapLieu.add(txtTen);

        pnlNhapLieu.add(new JLabel("Số điện thoại:"));
        txtSDT = new JTextField();
        pnlNhapLieu.add(txtSDT);

        pnlNhapLieu.add(new JLabel("Chức vụ:"));
        String[] chucVu = {"Nhân viên bán hàng", "Quản lý", "Admin"};
        cboChucVu = new JComboBox<>(chucVu);
        pnlNhapLieu.add(cboChucVu);

        pnlNhapLieu.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        pnlNhapLieu.add(txtUsername);

        pnlNhapLieu.add(new JLabel("Password:"));
        txtPassword = new JTextField();
        pnlNhapLieu.add(txtPassword);

        pnlNhapLieu.add(new JLabel("Mức lương:"));
        txtMucLuong = new JTextField();
        pnlNhapLieu.add(txtMucLuong);

        pnlNhapLieu.add(new JLabel("Giới tính:"));
        String[] gioiTinh = {"Nam", "Nữ"};
        cboGioiTinh = new JComboBox<>(gioiTinh);
        pnlNhapLieu.add(cboGioiTinh);

        pnlNhapLieu.add(new JLabel("Địa chỉ:"));
        txtDiaChi = new JTextField();
        pnlNhapLieu.add(txtDiaChi);

        // Panel nút bấm
        JPanel pnlNut = new JPanel(new GridLayout(5, 1, 5, 5));
        pnlNut.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTimKiem = new JButton("Tìm kiếm");
        btnLamMoi = new JButton("Làm mới");

        // Tùy chỉnh kích thước nút
        Dimension buttonSize = new Dimension(120, 30);
        btnThem.setPreferredSize(buttonSize);
        btnSua.setPreferredSize(buttonSize);
        btnXoa.setPreferredSize(buttonSize);
        btnTimKiem.setPreferredSize(buttonSize);
        btnLamMoi.setPreferredSize(buttonSize);

        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.add(btnThem);
        pnlNut.add(buttonWrapper);

        buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.add(btnSua);
        pnlNut.add(buttonWrapper);

        buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.add(btnXoa);
        pnlNut.add(buttonWrapper);

        buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.add(btnTimKiem);
        pnlNut.add(buttonWrapper);

        buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.add(btnLamMoi);
        pnlNut.add(buttonWrapper);

        leftPanel.add(pnlNhapLieu, BorderLayout.CENTER);
        leftPanel.add(pnlNut, BorderLayout.SOUTH);

        // Panel bảng bên phải
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Danh sách nhân viên"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        String[] columnNames = {"Mã NV", "Tên", "Chức vụ", "SĐT", "Username", "Mức lương", "Giới tính", "Địa chỉ"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblNhanVien = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblNhanVien);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // Split pane để chia màn hình thành 2 phần
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(400); // Điều chỉnh vị trí phân chia
        splitPane.setResizeWeight(0.0); // Cố định kích thước panel bên trái

        mainPanel.add(splitPane, BorderLayout.CENTER);
        add(mainPanel);

        // Thêm sự kiện
        addEvents();
    }

    private void addEvents() {
        // Sự kiện chọn dòng trong bảng
        tblNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblNhanVien.getSelectedRow();
                if (row >= 0) {
                    txtMaNV.setText(tblNhanVien.getValueAt(row, 0).toString());
                    txtTen.setText(tblNhanVien.getValueAt(row, 1).toString());
                    cboChucVu.setSelectedItem(tblNhanVien.getValueAt(row, 2).toString());
                    txtSDT.setText(tblNhanVien.getValueAt(row, 3).toString());
                    txtUsername.setText(tblNhanVien.getValueAt(row, 4).toString());
                    txtMucLuong.setText(tblNhanVien.getValueAt(row, 5).toString());
                    cboGioiTinh.setSelectedItem(tblNhanVien.getValueAt(row, 6).toString());
                    txtDiaChi.setText(tblNhanVien.getValueAt(row, 7).toString());
                }
            }
        });

        // Sự kiện nút Thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    NhanVien_DTO nv = getNhanVienFromInput();
                    if (nvDAO.ThemNhanVien(nv)) {
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
                        loadData();
                        clearInput();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại!");
                    }
                }
            }
        });

        // Sự kiện nút Sửa
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    NhanVien_DTO nv = getNhanVienFromInput();
                    if (nvDAO.CapNhatNhanVien(nv)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công!");
                        loadData();
                        clearInput();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thất bại!");
                    }
                }
            }
        });

        // Sự kiện nút Xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maNV = txtMaNV.getText();
                if (!maNV.isEmpty()) {
                    int choice = JOptionPane.showConfirmDialog(null, 
                        "Bạn có chắc muốn xóa nhân viên này?", 
                        "Xác nhận xóa", 
                        JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        if (nvDAO.XoaNhanVien(maNV)) {
                            JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!");
                            loadData();
                            clearInput();
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại!");
                        }
                    }
                }
            }
        });

        // Sự kiện nút Tìm kiếm
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maNV = txtMaNV.getText().trim();
                if (!maNV.isEmpty()) {
                    List<NhanVien_DTO> danhSach = nvDAO.TimKiemNhanVien(maNV);
                    if (danhSach.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên với mã " + maNV);
                    } else {
                        loadDataToTable(danhSach);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên cần tìm!");
                }
            }
        });

        // Sự kiện nút Làm mới
        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                loadData();
            }
        });
    }

    private void loadData() {
        List<NhanVien_DTO> danhSach = nvDAO.LayDanhSachNhanVien();
        loadDataToTable(danhSach);
    }

    private void loadDataToTable(List<NhanVien_DTO> danhSach) {
        model.setRowCount(0);
        for (NhanVien_DTO nv : danhSach) {
            model.addRow(new Object[]{
                nv.getMaNhanVien(),
                nv.getTen(),
                nv.getChucVu(),
                nv.getSoDienThoai(),
                nv.getUsername(),
                nv.getMucLuong(),
                nv.getGioiTinh(),
                nv.getDiaChi()
            });
        }
    }

    private void clearInput() {
        txtMaNV.setText("");
        txtTen.setText("");
        txtSDT.setText("");
        cboChucVu.setSelectedIndex(0);
        txtUsername.setText("");
        txtPassword.setText("");
        txtMucLuong.setText("");
        cboGioiTinh.setSelectedIndex(0);
        txtDiaChi.setText("");
    }

    private boolean validateInput() {
        if (txtMaNV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên!");
            return false;
        }
        if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhân viên!");
            return false;
        }
        if (txtSDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại!");
            return false;
        }
        if (txtUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập username!");
            return false;
        }
        if (txtPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập password!");
            return false;
        }
        if (txtMucLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mức lương!");
            return false;
        }
        if (txtDiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ!");
            return false;
        }
        return true;
    }

    private NhanVien_DTO getNhanVienFromInput() {
        return new NhanVien_DTO(
            txtMaNV.getText(),
            txtTen.getText(),
            cboChucVu.getSelectedItem().toString(),
            txtSDT.getText(),
            txtUsername.getText(),
            txtPassword.getText(),
            txtMucLuong.getText(),
            cboGioiTinh.getSelectedItem().toString(),
            txtDiaChi.getText()
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuanLyNhanVien_GUI().setVisible(true);
            }
        });
    }
}
