package GUI;

import BLL.taiKhoan_BLL;
import DTO.taiKhoan_DTO;
import M.Main_layout;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GUIDangNhap extends javax.swing.JFrame {

    public GUIDangNhap() {
        initComponents();
        setTitle("MotobikeSgu");
        jPanel1.setBackground(Color.WHITE);
        
        btnDangNhap.setBackground(Color.red);
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        btnDangNhap.setFocusPainted(false);
        btnDangNhap.setBorderPainted(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        cbHienMK.addActionListener(e -> {
            if (cbHienMK.isSelected()) {
                MatKhauField.setEchoChar((char) 0);
            } else {
                MatKhauField.setEchoChar('•');
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        btnDangNhap = new javax.swing.JButton();
        UserNameField = new javax.swing.JTextField();
        btnDangKy = new javax.swing.JButton();
        cbHienMK = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        DangNhapLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        UserNameLabel = new javax.swing.JLabel();
        MatKhauField = new javax.swing.JPasswordField();
        slogan1 = new javax.swing.JLabel();
        slogan2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnDangNhap.setText("Sign");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        btnDangKy.setText("Đăng kí tài khoản");
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });

        cbHienMK.setText("Hiện mật khẩu");

        DangNhapLabel.setText("ĐĂNG NHẬP");
        DangNhapLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        jLabel3.setText("Mật khẩu");

        UserNameLabel.setText("Tên đăng nhập");

        MatKhauField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MatKhauFieldActionPerformed(evt);
            }
        });

        slogan1.setText("Cửa hàng xe máy số một Việt Nam");

        slogan2.setText("Hãy chủ phong cách của bản thân mình");

        jLabel2.setIcon(new ImageIcon(getClass().getResource("/IMG/logo.png")));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(DangNhapLabel))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnDangNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnDangKy)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbHienMK))
                        .addComponent(MatKhauField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(UserNameField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(UserNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(slogan1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                        .addComponent(slogan2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(DangNhapLabel)
                .addGap(30, 30, 30)
                .addComponent(UserNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MatKhauField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangKy)
                    .addComponent(cbHienMK))
                .addGap(20, 20, 20)
                .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(slogan1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slogan2)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    // private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {
    //     String tenTaiKhoan = UserNameField.getText().trim();
    //     String matKhau = new String(MatKhauField.getPassword()).trim();

    //     if (tenTaiKhoan.isEmpty() || matKhau.isEmpty()) {
    //         javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
    //         return;
    //     }

    //     try {
    //         taiKhoan_BLL bll = new taiKhoan_BLL();
    //         boolean success = bll.loginUser(tenTaiKhoan, matKhau);
    //         if (success) {
    //             javax.swing.JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thông báo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    //             // Create a dummy DTO since loginUser doesn't return user details
    //             taiKhoan_DTO user = new taiKhoan_DTO();
    //             user = bll.getuser(tenTaiKhoan);
    //         //    taiKhoan_DTO user = new taiKhoan_DTO("", tenTaiKhoan, matKhau, "");
    //             Main_layout main_layout = new Main_layout(user);
    //             main_layout.setVisible(true);
    //             this.dispose();
    //         } else {
    //             javax.swing.JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
    //         }
    //     } catch (Exception e) {
    //         javax.swing.JOptionPane.showMessageDialog(this, "Lỗi khi đăng nhập: " + e.getMessage(), "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
    //     }
    //}

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {
        String tenTaiKhoan = UserNameField.getText().trim();
        String matKhau = new String(MatKhauField.getPassword()).trim();
    
        if (tenTaiKhoan.isEmpty() || matKhau.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try {
            taiKhoan_BLL bll = new taiKhoan_BLL();
            taiKhoan_DTO user = bll.getuser(tenTaiKhoan); // Lấy thông tin user
            if (user != null && bll.loginUser(tenTaiKhoan, matKhau)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thông báo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                Main_layout main_layout = new Main_layout(user);
                main_layout.setVisible(true);
                this.dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Lỗi khi đăng nhập: " + e.getMessage(), "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {
        GUIDangKy pageDangKy = new GUIDangKy();
        pageDangKy.setVisible(true);
        this.dispose();
    }

    private void MatKhauFieldActionPerformed(java.awt.event.ActionEvent evt) {
        btnDangNhapActionPerformed(evt);
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
            java.util.logging.Logger.getLogger(GUIDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new GUIDangNhap().setVisible(true);
        });
    }

    private javax.swing.JLabel DangNhapLabel;
    private javax.swing.JPasswordField MatKhauField;
    private javax.swing.JTextField UserNameField;
    private javax.swing.JLabel UserNameLabel;
    private javax.swing.JButton btnDangKy;
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JCheckBox cbHienMK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel slogan1;
    private javax.swing.JLabel slogan2;
}