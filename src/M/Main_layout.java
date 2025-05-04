package M;

import DTO.taiKhoan_DTO;
import GUI.BanHang_GUI;
import GUI.NhapHang_GUI;
import GUI.QuanLy_SP_GUI;
import GUI.QuanLy_KH_GUI;
import GUI.QuanLy_NV_GUI;
import GUI.QuanLyKhachHang_GUI;
import GUI.QuanLyNhanVien_GUI;
import GUI.QuanLySanPham_GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class Main_layout extends javax.swing.JFrame {

    private javax.swing.JLabel currentUser_label;
    private taiKhoan_DTO currentUser;

    public Main_layout() {
        this(new taiKhoan_DTO("", "Guest", "", "Nhân viên"));
    }

    public Main_layout(taiKhoan_DTO user) {
        this.currentUser = user;
        initComponents();
        setTitle("Quản lý cửa hàng bán xe máy");
        jPanel1.setBackground(new Color(70, 20, 180));
        jPanel2.setBackground(new Color(70, 130, 180));

        ImageIcon icon1 = new ImageIcon(getClass().getResource("/IMG/logo.png"));
        Image logo = icon1.getImage().getScaledInstance(230, 100, Image.SCALE_SMOOTH);
        jLabel2.setIcon(new ImageIcon(logo));

        chucNang_label.setFont(new Font("Arial", Font.BOLD, 16));
        chucNang_label.setForeground(Color.white);

        currentUser_label = new javax.swing.JLabel();
        currentUser_label.setFont(new Font("Arial", Font.ITALIC, 14));
        currentUser_label.setForeground(Color.white);
        currentUser_label.setText("Người dùng hiện tại: " + user.getTenTaiKhoan() + " (" + user.getChucVu() + ")");
        jPanel1.add(currentUser_label);
        currentUser_label.setBounds(banHang_btn.getX(), chucNang_label.getY() + 25, 250, 20);

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        Color buttonBackground = new Color(100, 149, 237);
        Color buttonForeground = Color.WHITE;

        javax.swing.JButton[] buttons = {
            banHang_btn, qlnv_btn, qlsp_btn, qlkh_btn, qltk_btn, nhapHang_btn, dangXuat_btn, thongKe_btn
        };

        for (javax.swing.JButton btn : buttons) {
            btn.setFont(buttonFont);
            btn.setBackground(buttonBackground);
            btn.setForeground(buttonForeground);
            btn.setFocusPainted(false);
            btn.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(65, 105, 225), 2));
        }

        // Điều khiển hiển thị button dựa trên vai trò
        if (!"Quản lý".equalsIgnoreCase(user.getChucVu())) {
            // Ẩn các nút không dành cho nhân viên
            qlnv_btn.setVisible(false); // Quản lý nhân viên
            qlsp_btn.setVisible(false); // Quản lý sản phẩm
            nhapHang_btn.setVisible(false); // Nhập hàng
            thongKe_btn.setVisible(false); // Thống kê
            System.out.print("nhan vien dang dang nhap");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        banHang_btn = new javax.swing.JButton();
        qlnv_btn = new javax.swing.JButton();
        qlsp_btn = new javax.swing.JButton();
        qlkh_btn = new javax.swing.JButton();
        qltk_btn = new javax.swing.JButton();
        nhapHang_btn = new javax.swing.JButton();
        chucNang_label = new javax.swing.JLabel();
        dangXuat_btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        thongKe_btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        banHang_btn.setText("Bán hàng");
        banHang_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                banHang_btnActionPerformed(e);
            }
        });

        qlnv_btn.setText("Quản lý nhân viên");
        qlnv_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qlnv_btnActionPerformed(evt);
            }
        });

        qlsp_btn.setText("Quản lý sản phẩm");
        qlsp_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qlsp_btnActionPerformed(evt);
            }
        });

        qlkh_btn.setText("Quản lý khách hàng");
        qlkh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qlkh_btnActionPerformed(evt);
            }
        });

        qltk_btn.setText("Quản lý tài khoản");
        qltk_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qltk_btnActionPerformed(evt);
            }
        });

        nhapHang_btn.setText("Nhập hàng");
        nhapHang_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhapHang_btnActionPerformed(evt);
            }
        });

        chucNang_label.setText("Chức năng");

        dangXuat_btn.setText("Đăng xuất");
        dangXuat_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dangXuat_btnActionPerformed(evt);
            }
        });

        thongKe_btn.setText("Thống kê");
        thongKe_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thongKe_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(dangXuat_btn)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(chucNang_label)
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(banHang_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(qlnv_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(qlsp_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(qlkh_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(qltk_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nhapHang_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thongKe_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(chucNang_label)
                .addGap(32, 32, 32)
                .addComponent(banHang_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(qlnv_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(qlsp_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(qlkh_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(qltk_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nhapHang_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(thongKe_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(dangXuat_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 918, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void banHang_btnActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel2.removeAll();
        BanHang_GUI banHang = new BanHang_GUI(currentUser);
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(banHang.getMainPanel(), BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }
    private void qlsp_btnActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel2.removeAll();
        QuanLy_SP_GUI QLSanPham = new QuanLy_SP_GUI();
        QLSanPham.setPreferredSize(new Dimension(900,700));
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(QLSanPham.getMainPanel(), BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    private void qlkh_btnActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel2.removeAll();
        QuanLy_KH_GUI QLKhachHang = new QuanLy_KH_GUI();
        QLKhachHang.setPreferredSize(new Dimension(900,700));
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(QLKhachHang.getMainPanel(), BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    private void qltk_btnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO: Thêm xử lý cho quản lý tài khoản
        // Nhân viên chỉ được chỉnh sửa thông tin tài khoản của chính mình
        if (!"Quản lý".equalsIgnoreCase(currentUser.getChucVu())) {
            // Gọi GUI quản lý tài khoản với giới hạn chỉ cho phép chỉnh sửa tài khoản của currentUser
            // Ví dụ: new QuanLyTaiKhoan_GUI(currentUser, true);
        } else {
            // Gọi GUI quản lý tài khoản với quyền quản lý đầy đủ
            // Ví dụ: new QuanLyTaiKhoan_GUI(currentUser, false);
        }
    }

    private void nhapHang_btnActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel2.removeAll();
        NhapHang_GUI nhapHang = new NhapHang_GUI(currentUser);
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(nhapHang.getMainPanel(), BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    private void thongKe_btnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO: Thêm xử lý cho thống kê
    }

    private void qlnv_btnActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel2.removeAll();
        QuanLy_NV_GUI QLNhanVien = new QuanLy_NV_GUI();
        QLNhanVien.setPreferredSize(new Dimension(900,700));
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(QLNhanVien.getMainPanel(), BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    private void dangXuat_btnActionPerformed(java.awt.event.ActionEvent evt) {
        GUI.GUIDangNhap dangNhap = new GUI.GUIDangNhap();
        dangNhap.setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Main_layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_layout().setVisible(true);
            }
        });
    }

    private javax.swing.JButton banHang_btn;
    private javax.swing.JLabel chucNang_label;
    private javax.swing.JButton dangXuat_btn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton nhapHang_btn;
    private javax.swing.JButton qlkh_btn;
    private javax.swing.JButton qlnv_btn;
    private javax.swing.JButton qlsp_btn;
    private javax.swing.JButton qltk_btn;
    private javax.swing.JButton thongKe_btn;
}