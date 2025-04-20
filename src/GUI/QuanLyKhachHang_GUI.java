package GUI;

import DAO.KhachHang_DAO;
import DTO.KhachHang_DTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QuanLyKhachHang_GUI extends JFrame {
    private JPanel mainPanel;
    private JTable tblKhachHang;
    private DefaultTableModel model;
    private JTextField txtMaKH, txtTen, txtSDT, txtEmail, txtDiaChi;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi;
    private KhachHang_DAO khDAO;

    public QuanLyKhachHang_GUI() {
        khDAO = new KhachHang_DAO();
        initComponents();
        loadData();
    }

    private void initComponents() {
        setTitle("Quản lý khách hàng");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel bên trái chứa form nhập liệu và nút chức năng
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        
        // Panel nhập liệu
        JPanel pnlNhapLieu = new JPanel(new GridLayout(5, 2, 5, 5));
        pnlNhapLieu.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Thông tin khách hàng"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        pnlNhapLieu.add(new JLabel("Mã khách hàng:"));
        txtMaKH = new JTextField();
        pnlNhapLieu.add(txtMaKH);

        pnlNhapLieu.add(new JLabel("Tên:"));
        txtTen = new JTextField();
        pnlNhapLieu.add(txtTen);

        pnlNhapLieu.add(new JLabel("Số điện thoại:"));
        txtSDT = new JTextField();
        pnlNhapLieu.add(txtSDT);

        pnlNhapLieu.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        pnlNhapLieu.add(txtEmail);

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
            BorderFactory.createTitledBorder("Danh sách khách hàng"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        String[] columnNames = {"Mã KH", "Tên", "SĐT", "Email", "Địa chỉ"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblKhachHang = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblKhachHang);
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
        tblKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblKhachHang.getSelectedRow();
                if (row >= 0) {
                    txtMaKH.setText(tblKhachHang.getValueAt(row, 0).toString());
                    txtTen.setText(tblKhachHang.getValueAt(row, 1).toString());
                    txtSDT.setText(tblKhachHang.getValueAt(row, 2).toString());
                    txtEmail.setText(tblKhachHang.getValueAt(row, 3).toString());
                    txtDiaChi.setText(tblKhachHang.getValueAt(row, 4).toString());
                }
            }
        });

        // Sự kiện nút Thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    KhachHang_DTO kh = getKhachHangFromInput();
                    if (khDAO.ThemKhachHang(kh)) {
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
                        loadData();
                        clearInput();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!");
                    }
                }
            }
        });

        // Sự kiện nút Sửa
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    KhachHang_DTO kh = getKhachHangFromInput();
                    if (khDAO.CapNhatKhachHang(kh)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công!");
                        loadData();
                        clearInput();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thất bại!");
                    }
                }
            }
        });

        // Sự kiện nút Xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maKH = txtMaKH.getText();
                if (!maKH.isEmpty()) {
                    int choice = JOptionPane.showConfirmDialog(null, 
                        "Bạn có chắc muốn xóa khách hàng này?", 
                        "Xác nhận xóa", 
                        JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        if (khDAO.XoaKhachHang(maKH)) {
                            JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công!");
                            loadData();
                            clearInput();
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại!");
                        }
                    }
                }
            }
        });

        // Sự kiện nút Tìm kiếm
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maKH = txtMaKH.getText().trim();
                if (!maKH.isEmpty()) {
                    List<KhachHang_DTO> danhSach = khDAO.TimKiemKhachHang(maKH);
                    if (danhSach.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với mã " + maKH);
                    } else {
                        loadDataToTable(danhSach);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng cần tìm!");
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
        List<KhachHang_DTO> danhSach = khDAO.LayDanhSachKhachHang();
        loadDataToTable(danhSach);
    }

    private void loadDataToTable(List<KhachHang_DTO> danhSach) {
        model.setRowCount(0);
        for (KhachHang_DTO kh : danhSach) {
            model.addRow(new Object[]{
                kh.getMa_khach_hang(),
                kh.getTen(),
                kh.getSdt(),
                kh.getEmail(),
                kh.getDia_chi()
            });
        }
    }

    private void clearInput() {
        txtMaKH.setText("");
        txtTen.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
    }

    private boolean validateInput() {
        if (txtMaKH.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng!");
            return false;
        }
        if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng!");
            return false;
        }
        if (txtSDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại!");
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập email!");
            return false;
        }
        if (txtDiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ!");
            return false;
        }
        return true;
    }

    private KhachHang_DTO getKhachHangFromInput() {
        return new KhachHang_DTO(
            txtMaKH.getText(),
            txtTen.getText(),
            txtSDT.getText(),
            txtEmail.getText(),
            txtDiaChi.getText()
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuanLyKhachHang_GUI().setVisible(true);
            }
        });
    }
}
