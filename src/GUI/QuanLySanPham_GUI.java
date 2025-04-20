package GUI;

import DAO.SanPham_DAO;
import DTO.SanPham_DTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class QuanLySanPham_GUI extends JFrame {
    private JPanel mainPanel;
    private JTable tblSanPham;
    private DefaultTableModel model;
    private JTextField txtMaSP, txtMaLoai, txtTenSP, txtSoLuongTon, txtGiaBan, txtGiaNhap;
    private JTextField txtTrangThai, txtXuatXu, txtBaoHanh, txtThuongHieu;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi;
    private JComboBox<String> cboSearchType;
    private SanPham_DAO spDAO;

    public QuanLySanPham_GUI() {
        spDAO = new SanPham_DAO();
        initComponents();
        loadData();
    }

    private void initComponents() {
        setTitle("Quản lý sản phẩm");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel bên trái chứa form nhập liệu và nút chức năng
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        
        // Panel nhập liệu
        JPanel pnlNhapLieu = new JPanel(new GridLayout(10, 2, 5, 5));
        pnlNhapLieu.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Thông tin sản phẩm"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        pnlNhapLieu.add(new JLabel("Mã sản phẩm:"));
        txtMaSP = new JTextField();
        pnlNhapLieu.add(txtMaSP);

        pnlNhapLieu.add(new JLabel("Mã loại:"));
        txtMaLoai = new JTextField();
        pnlNhapLieu.add(txtMaLoai);

        pnlNhapLieu.add(new JLabel("Tên sản phẩm:"));
        txtTenSP = new JTextField();
        pnlNhapLieu.add(txtTenSP);

        pnlNhapLieu.add(new JLabel("Số lượng tồn:"));
        txtSoLuongTon = new JTextField();
        pnlNhapLieu.add(txtSoLuongTon);

        pnlNhapLieu.add(new JLabel("Giá bán:"));
        txtGiaBan = new JTextField();
        pnlNhapLieu.add(txtGiaBan);

        pnlNhapLieu.add(new JLabel("Giá nhập:"));
        txtGiaNhap = new JTextField();
        pnlNhapLieu.add(txtGiaNhap);

        pnlNhapLieu.add(new JLabel("Trạng thái:"));
        txtTrangThai = new JTextField();
        pnlNhapLieu.add(txtTrangThai);

        pnlNhapLieu.add(new JLabel("Xuất xứ:"));
        txtXuatXu = new JTextField();
        pnlNhapLieu.add(txtXuatXu);

        pnlNhapLieu.add(new JLabel("Bảo hành:"));
        txtBaoHanh = new JTextField();
        pnlNhapLieu.add(txtBaoHanh);

        pnlNhapLieu.add(new JLabel("Thương hiệu:"));
        txtThuongHieu = new JTextField();
        pnlNhapLieu.add(txtThuongHieu);

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
            BorderFactory.createTitledBorder("Danh sách sản phẩm"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        String[] columnNames = {"Mã SP", "Mã loại", "Tên SP", "Số lượng tồn", "Giá bán", 
                              "Giá nhập", "Trạng thái", "Xuất xứ", "Bảo hành", "Thương hiệu"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblSanPham = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblSanPham);
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
        tblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblSanPham.getSelectedRow();
                if (row >= 0) {
                    txtMaSP.setText(tblSanPham.getValueAt(row, 0).toString());
                    txtMaLoai.setText(tblSanPham.getValueAt(row, 1).toString());
                    txtTenSP.setText(tblSanPham.getValueAt(row, 2).toString());
                    txtSoLuongTon.setText(tblSanPham.getValueAt(row, 3).toString());
                    txtGiaBan.setText(tblSanPham.getValueAt(row, 4).toString());
                    txtGiaNhap.setText(tblSanPham.getValueAt(row, 5).toString());
                    txtTrangThai.setText(tblSanPham.getValueAt(row, 6).toString());
                    txtXuatXu.setText(tblSanPham.getValueAt(row, 7).toString());
                    txtBaoHanh.setText(tblSanPham.getValueAt(row, 8).toString());
                    txtThuongHieu.setText(tblSanPham.getValueAt(row, 9).toString());
                }
            }
        });

        // Sự kiện nút Thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    SanPham_DTO sp = getSanPhamFromInput();
                    if (spDAO.ThemSanPham(sp)) {
                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
                        loadData();
                        clearInput();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
                    }
                }
            }
        });

        // Sự kiện nút Sửa
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    SanPham_DTO sp = getSanPhamFromInput();
                    if (spDAO.CapNhatSanPham(sp)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thành công!");
                        loadData();
                        clearInput();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thất bại!");
                    }
                }
            }
        });

        // Sự kiện nút Xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSP = txtMaSP.getText();
                if (!maSP.isEmpty()) {
                    int choice = JOptionPane.showConfirmDialog(null, 
                        "Bạn có chắc muốn xóa sản phẩm này?", 
                        "Xác nhận xóa", 
                        JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        if (spDAO.XoaSanPham(maSP)) {
                            JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công!");
                            loadData();
                            clearInput();
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa sản phẩm thất bại!");
                        }
                    }
                }
            }
        });

        // Sự kiện nút Tìm kiếm
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSP = txtMaSP.getText().trim();
                if (!maSP.isEmpty()) {
                    SanPham_DTO sp = spDAO.LaySanPhamTheoMa(maSP);
                    if (sp != null) {
                        List<SanPham_DTO> danhSach = new ArrayList<>();
                        danhSach.add(sp);
                        loadDataToTable(danhSach);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với mã " + maSP);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sản phẩm cần tìm!");
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
        List<SanPham_DTO> danhSach = spDAO.LayDanhSachSanPham();
        loadDataToTable(danhSach);
    }

    private void loadDataToTable(List<SanPham_DTO> danhSach) {
        model.setRowCount(0);
        for (SanPham_DTO sp : danhSach) {
            model.addRow(new Object[]{
                sp.getMa_san_pham(),
                sp.getMa_loai(),
                sp.getTen_san_pham(),
                sp.getSo_luong_ton(),
                sp.getGia_ban(),
                sp.getGia_nhap(),
                sp.getTrang_thai(),
                sp.getXuat_xu(),
                sp.getBao_hanh(),
                sp.getThuong_hieu()
            });
        }
    }

    private void clearInput() {
        txtMaSP.setText("");
        txtMaLoai.setText("");
        txtTenSP.setText("");
        txtSoLuongTon.setText("");
        txtGiaBan.setText("");
        txtGiaNhap.setText("");
        txtTrangThai.setText("");
        txtXuatXu.setText("");
        txtBaoHanh.setText("");
        txtThuongHieu.setText("");
    }

    private boolean validateInput() {
        if (txtMaSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sản phẩm!");
            return false;
        }
        if (txtMaLoai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã loại!");
            return false;
        }
        if (txtTenSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm!");
            return false;
        }
        try {
            Integer.parseInt(txtSoLuongTon.getText());
            Integer.parseInt(txtGiaBan.getText());
            Integer.parseInt(txtGiaNhap.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng tồn, giá bán và giá nhập phải là số!");
            return false;
        }
        if (txtTrangThai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập trạng thái!");
            return false;
        }
        if (txtXuatXu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập xuất xứ!");
            return false;
        }
        if (txtBaoHanh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập bảo hành!");
            return false;
        }
        if (txtThuongHieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thương hiệu!");
            return false;
        }
        return true;
    }

    private SanPham_DTO getSanPhamFromInput() {
        return new SanPham_DTO(
            txtMaSP.getText(),
            txtMaLoai.getText(),
            txtTenSP.getText(),
            Integer.parseInt(txtSoLuongTon.getText()),
            Integer.parseInt(txtGiaBan.getText()),
            Integer.parseInt(txtGiaNhap.getText()),
            txtTrangThai.getText(),
            txtXuatXu.getText(),
            txtBaoHanh.getText(),
            txtThuongHieu.getText()
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuanLySanPham_GUI().setVisible(true);
            }
        });
    }
}
