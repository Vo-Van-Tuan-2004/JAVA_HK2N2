// package GUI;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// public class MainFrame extends JFrame {
//     private JPanel mainPanel;
//     private JPanel contentPanel;
//     private JButton btnBanHang;
//     private JButton btnQuanLyNhanVien;
//     private JButton btnQuanLySanPham;
//     private JButton btnQuanLyKhachHang;
//     private JButton btnQuanLyTaiKhoan;
//     private JButton btnNhapHang;
//     private JButton btnDangXuat;

//     public MainFrame() {
//         setupGUI();
//     }

//     private void setupGUI() {
//         setTitle("Quản lý cửa hàng bán xe máy");
//         setSize(1200, 700);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         mainPanel = new JPanel(new BorderLayout());

//         // Tạo menu bên trái
//         JPanel menuPanel = createMenuPanel();
//         mainPanel.add(menuPanel, BorderLayout.WEST);

//         // Panel chính để hiển thị nội dung
//         contentPanel = new JPanel(new BorderLayout());
//         contentPanel.setBackground(new Color(100, 149, 237)); // Màu xanh dương
//         mainPanel.add(contentPanel, BorderLayout.CENTER);

//         // Thêm panel chính vào frame
//         add(mainPanel);

//         // Thêm sự kiện cho các nút
//         addEvents();
//     }

//     private JPanel createMenuPanel() {
//         JPanel menuPanel = new JPanel();
//         menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
//         menuPanel.setBackground(new Color(75, 0, 130)); // Màu tím đậm
//         menuPanel.setPreferredSize(new Dimension(200, getHeight()));

//         // Tạo label "Chức năng"
//         JLabel lblChucNang = new JLabel("Chức năng");
//         lblChucNang.setForeground(Color.WHITE);
//         lblChucNang.setFont(new Font("Arial", Font.BOLD, 24));
//         lblChucNang.setAlignmentX(Component.CENTER_ALIGNMENT);
//         menuPanel.add(lblChucNang);
//         menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));

//         // Tạo các nút menu
//         btnBanHang = createMenuButton("Bán hàng");
//         btnQuanLyNhanVien = createMenuButton("Quản lý nhân viên");
//         btnQuanLySanPham = createMenuButton("Quản lý sản phẩm");
//         btnQuanLyKhachHang = createMenuButton("Quản lý khách hàng");
//         btnQuanLyTaiKhoan = createMenuButton("Quản lý tài khoản");
//         btnNhapHang = createMenuButton("Nhập hàng");
//         btnDangXuat = createMenuButton("Đăng xuất");

//         // Thêm các nút vào menu
//         menuPanel.add(btnBanHang);
//         menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//         menuPanel.add(btnQuanLyNhanVien);
//         menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//         menuPanel.add(btnQuanLySanPham);
//         menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//         menuPanel.add(btnQuanLyKhachHang);
//         menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//         menuPanel.add(btnQuanLyTaiKhoan);
//         menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//         menuPanel.add(btnNhapHang);
//         menuPanel.add(Box.createVerticalGlue()); // Đẩy nút đăng xuất xuống cuối
//         menuPanel.add(btnDangXuat);
//         menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));

//         return menuPanel;
//     }

//     private JButton createMenuButton(String text) {
//         JButton button = new JButton(text);
//         button.setAlignmentX(Component.CENTER_ALIGNMENT);
//         button.setMaximumSize(new Dimension(180, 40));
//         button.setBackground(Color.WHITE);
//         button.setFocusPainted(false);
//         button.setFont(new Font("Arial", Font.PLAIN, 14));
//         return button;
//     }

//     private void addEvents() {
//         btnBanHang.addActionListener(e -> {
//             // TODO: Mở form bán hàng
//         });

//         btnQuanLyNhanVien.addActionListener(e -> {
//             // TODO: Mở form quản lý nhân viên
//         });

//         btnQuanLySanPham.addActionListener(e -> {
//             // TODO: Mở form quản lý sản phẩm
//         });

//         btnQuanLyKhachHang.addActionListener(e -> {
//             QuanLyKhachHang_GUI quanLyKhachHangGUI = new QuanLyKhachHang_GUI();
//             quanLyKhachHangGUI.setVisible(true);
//         });

//         btnQuanLyTaiKhoan.addActionListener(e -> {
//             // TODO: Mở form quản lý tài khoản
//         });

//         btnNhapHang.addActionListener(e -> {
//             // TODO: Mở form nhập hàng
//         });

//         btnDangXuat.addActionListener(e -> {
//             int choice = JOptionPane.showConfirmDialog(
//                 this,
//                 "Bạn có chắc muốn đăng xuất?",
//                 "Xác nhận đăng xuất",
//                 JOptionPane.YES_NO_OPTION
//             );
//             if (choice == JOptionPane.YES_OPTION) {
//                 dispose();
//                 // TODO: Mở form đăng nhập
//             }
//         });
//     }

//     public static void main(String[] args) {
//         try {
//             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
        
//         SwingUtilities.invokeLater(() -> {
//             new MainFrame().setVisible(true);
//         });
//     }
// } 