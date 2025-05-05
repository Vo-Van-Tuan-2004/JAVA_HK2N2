package GUI;

import BLL.taiKhoan_BLL;
import DTO.taiKhoan_DTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuanLy_TK_GUI extends JPanel {
    private taiKhoan_BLL taiKhoanBLL;
    private taiKhoan_DTO currentUser;

    private JTextField txtMaNhanVien;
    private JTextField txtTenTaiKhoan;
    private JTextField txtChucVu;
    private JPasswordField txtMatKhau;
    private JPasswordField txtXacNhanMatKhau;
    private JButton btnCapNhat;
    private JButton btnHuy;

    public QuanLy_TK_GUI(taiKhoan_DTO loggedInUser) {
        try {
            taiKhoanBLL = new taiKhoan_BLL();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khởi tạo taiKhoan_BLL: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        this.currentUser = loggedInUser;

        setPreferredSize(new Dimension(900, 700));
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // Panel chính với layout BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Tiêu đề
        JLabel titleLabel = new JLabel("CẬP NHẬT THÔNG TIN TÀI KHOẢN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(0, 102, 204));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Panel chứa các trường thông tin
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Mã nhân viên
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblMaNhanVien = new JLabel("Mã nhân viên:");
        lblMaNhanVien.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(lblMaNhanVien, gbc);

        gbc.gridx = 1;
        txtMaNhanVien = new JTextField(20);
        txtMaNhanVien.setFont(new Font("Arial", Font.PLAIN, 16));
        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setText(currentUser.getMaTaiKhoan());
        infoPanel.add(txtMaNhanVien, gbc);

        // Tên tài khoản
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblTenTaiKhoan = new JLabel("Tên tài khoản:");
        lblTenTaiKhoan.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(lblTenTaiKhoan, gbc);

        gbc.gridx = 1;
        txtTenTaiKhoan = new JTextField(20);
        txtTenTaiKhoan.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTenTaiKhoan.setText(currentUser.getTenTaiKhoan());
        infoPanel.add(txtTenTaiKhoan, gbc);

        // Chức vụ
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblChucVu = new JLabel("Chức vụ:");
        lblChucVu.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(lblChucVu, gbc);

        gbc.gridx = 1;
        txtChucVu = new JTextField(20);
        txtChucVu.setFont(new Font("Arial", Font.PLAIN, 16));
        txtChucVu.setEditable(false);
        txtChucVu.setText(currentUser.getChucVu());
        infoPanel.add(txtChucVu, gbc);

        // Mật khẩu
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblMatKhau = new JLabel("Mật khẩu mới:");
        lblMatKhau.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(lblMatKhau, gbc);

        gbc.gridx = 1;
        txtMatKhau = new JPasswordField(20);
        txtMatKhau.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(txtMatKhau, gbc);

        // Xác nhận mật khẩu
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel lblXacNhanMatKhau = new JLabel("Xác nhận mật khẩu:");
        lblXacNhanMatKhau.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(lblXacNhanMatKhau, gbc);

        gbc.gridx = 1;
        txtXacNhanMatKhau = new JPasswordField(20);
        txtXacNhanMatKhau.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(txtXacNhanMatKhau, gbc);

        mainPanel.add(infoPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Nút Cập nhật
        btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setFont(new Font("Arial", Font.BOLD, 16));
        btnCapNhat.setBackground(new Color(0, 102, 204));
        btnCapNhat.setForeground(Color.WHITE);
        btnCapNhat.setPreferredSize(new Dimension(120, 40));
        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdate();
            }
        });
        buttonPanel.add(btnCapNhat);

        // Nút Hủy
        btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("Arial", Font.BOLD, 16));
        btnHuy.setBackground(new Color(204, 0, 0));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setPreferredSize(new Dimension(120, 40));
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
        buttonPanel.add(btnHuy);

        mainPanel.add(buttonPanel);

        // Thêm mainPanel vào JPanel chính
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
    }

    private void handleUpdate() {
        String tenTaiKhoan = txtTenTaiKhoan.getText().trim();
        String matKhau = new String(txtMatKhau.getPassword()).trim();
        String xacNhanMatKhau = new String(txtXacNhanMatKhau.getPassword()).trim();

        // Kiểm tra dữ liệu đầu vào
        if (tenTaiKhoan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tài khoản.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!matKhau.equals(xacNhanMatKhau)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cập nhật tài khoản
        boolean success = taiKhoanBLL.updateAccount(currentUser.getMaTaiKhoan(), tenTaiKhoan, matKhau);
        if (success) {
            JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            currentUser.setTenTaiKhoan(tenTaiKhoan);
            currentUser.setMatKhau(matKhau);
            resetFields();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thất bại. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        txtTenTaiKhoan.setText(currentUser.getTenTaiKhoan());
        txtMatKhau.setText("");
        txtXacNhanMatKhau.setText("");
    }

    public JPanel getMainPanel() {
        this.setPreferredSize(new Dimension(900,500));
        return this;
    }

}