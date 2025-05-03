package GUI;

import BLL.taiKhoan_BLL;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GUIDangKy extends javax.swing.JFrame {

    public GUIDangKy() {
        initComponents();
        setTitle("MotobikeSgu - Đăng ký");
        jPanel1.setBackground(Color.WHITE);
        
        btnDangKy.setBackground(Color.red);
        btnDangKy.setForeground(Color.WHITE);
        btnDangKy.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        btnDangKy.setFocusPainted(false);
        btnDangKy.setBorderPainted(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        cbHienMK.addActionListener(e -> {
            if (cbHienMK.isSelected()) {
                MatKhauField.setEchoChar((char) 0);
            } else {
                MatKhauField.setEchoChar('•');
            }
        });

        jLabel2.setIcon(new ImageIcon(getClass().getResource("/IMG/logo.png")));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        btnDangKy = new javax.swing.JButton();
        UserNameField = new javax.swing.JTextField();
        btnQuayLai = new javax.swing.JButton();
        cbHienMK = new javax.swing.JCheckBox();
        DangKyLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        UserNameLabel = new javax.swing.JLabel();
        MatKhauField = new javax.swing.JPasswordField();
        slogan1 = new javax.swing.JLabel();
        slogan2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnDangKy.setText("Đăng ký");
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });

        btnQuayLai.setText("Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        cbHienMK.setText("Hiện mật khẩu");

        DangKyLabel.setText("ĐĂNG KÝ");
        DangKyLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        jLabel3.setText("Mật khẩu");

        UserNameLabel.setText("Tên đăng nhập");

        slogan1.setText("Cửa hàng xe máy số một Việt Nam");

        slogan2.setText("Hãy chủ phong cách của bản thân mình");

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
                        .addComponent(DangKyLabel))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnDangKy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnQuayLai)
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
                .addComponent(DangKyLabel)
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
                    .addComponent(btnQuayLai)
                    .addComponent(cbHienMK))
                .addGap(20, 20, 20)
                .addComponent(btnDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {
        String tenTaiKhoan = UserNameField.getText().trim();
        String matKhau = new String(MatKhauField.getPassword()).trim();

        if (tenTaiKhoan.isEmpty() || matKhau.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            taiKhoan_BLL bll = new taiKhoan_BLL();
            boolean success = bll.registerUser(tenTaiKhoan, matKhau);
            if (success) {
                javax.swing.JOptionPane.showMessageDialog(this, "Đăng ký thành công!", "Thông báo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                GUIDangNhap dangNhap = new GUIDangNhap();
                dangNhap.setVisible(true);
                this.dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Đăng ký thất bại.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Lỗi khi đăng ký: " + e.getMessage(), "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {
        GUIDangNhap dangNhap = new GUIDangNhap();
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
            java.util.logging.Logger.getLogger(GUIDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new GUIDangKy().setVisible(true);
        });
    }

    private javax.swing.JLabel DangKyLabel;
    private javax.swing.JPasswordField MatKhauField;
    private javax.swing.JTextField UserNameField;
    private javax.swing.JLabel UserNameLabel;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnDangKy;
    private javax.swing.JCheckBox cbHienMK;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel slogan1;
    private javax.swing.JLabel slogan2;
}
