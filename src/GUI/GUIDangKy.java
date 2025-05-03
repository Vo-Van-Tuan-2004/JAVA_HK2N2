package GUI;

import BLL.taiKhoan_BLL;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GUIDangKy extends javax.swing.JFrame {

    public GUIDangKy() {
        initComponents();
<<<<<<< HEAD
        setTitle("MotobikeSgu - Đăng ký");
        jPanel1.setBackground(Color.WHITE);
=======
        jPanel1.setBackground(new Color(70,20,180)); // Match Main_layout panel color

        // Custom button styles similar to Main_layout
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        Color buttonBackground = new Color(100, 149, 237); // Cornflower Blue
        Color buttonForeground = Color.WHITE;

        javax.swing.JButton[] buttons = {
            jButton1, jButton2
        };

        for (javax.swing.JButton btn : buttons) {
            btn.setFont(buttonFont);
            btn.setBackground(buttonBackground);
            btn.setForeground(buttonForeground);
            btn.setFocusPainted(false);
            btn.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(65, 105, 225), 2)); // Royal Blue border
        }
>>>>>>> 8e511c7ae845532776e61da95904dfcc54e57c20
        
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
<<<<<<< HEAD
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
=======
        dangKy_label = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pic = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        matKhauLabel = new javax.swing.JLabel();
        xacNhanMKLabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        ageLabel = new javax.swing.JLabel();
        dayBox = new javax.swing.JComboBox<>();
        monthBox = new javax.swing.JComboBox<>();
        yearBox = new javax.swing.JComboBox<>();
        sexLabel = new javax.swing.JLabel();
        namBtnRadio = new javax.swing.JRadioButton();
        nuBtnRadio = new javax.swing.JRadioButton();
        nameLabel = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField7.setColumns(20);
>>>>>>> 8e511c7ae845532776e61da95904dfcc54e57c20

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

<<<<<<< HEAD
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE)
=======
        userNameLabel.setText("Tên đăng nhập :");

        matKhauLabel.setText("Mật khẩu :");

        xacNhanMKLabel.setText("Xác nhận mật khẩu :");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        ageLabel.setText("Ngày sinh : ");

        dayBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dayBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dayBoxItemStateChanged(evt);
            }
        });
        dayBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayBoxActionPerformed(evt);
            }
        });

        monthBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        yearBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        sexLabel.setText("Giới tính : ");

        namBtnRadio.setText("Nam");

        nuBtnRadio.setText("Nữ");
        nuBtnRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuBtnRadioActionPerformed(evt);
            }
        });

        nameLabel.setText("Họ và tên :");

        emailLabel.setText("Email :");

        phoneLabel.setText("Số điện thoại :");

        jButton1.setText("Đăng Nhập");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Đăng Ký");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã nhân viên:");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sexLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(matKhauLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(xacNhanMKLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(namBtnRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nuBtnRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dayBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField2)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(jTextField1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(192, 192, 192)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(phoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField4)
                                            .addComponent(jTextField5)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(407, 407, 407)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
>>>>>>> 8e511c7ae845532776e61da95904dfcc54e57c20
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
<<<<<<< HEAD
                .addContainerGap(50, Short.MAX_VALUE))
=======
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matKhauLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xacNhanMKLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namBtnRadio)
                    .addComponent(nuBtnRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
>>>>>>> 8e511c7ae845532776e61da95904dfcc54e57c20
        );

        pack();
        setLocationRelativeTo(null);
    }

<<<<<<< HEAD
    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {
        String tenTaiKhoan = UserNameField.getText().trim();
        String matKhau = new String(MatKhauField.getPassword()).trim();

        if (tenTaiKhoan.isEmpty() || matKhau.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
=======
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String maNhanVien = jTextField7.getText().trim();
        String tenTaiKhoan = jTextField1.getText();
        String matKhau = jTextField2.getText();
        String xacNhanMatKhau = jTextField3.getText();
        String email = jTextField5.getText().trim();
        String phone = jTextField6.getText().trim();

        if (maNhanVien.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty() || xacNhanMatKhau.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin đăng ký.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!matKhau.equals(xacNhanMatKhau)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
>>>>>>> 8e511c7ae845532776e61da95904dfcc54e57c20
            return;
        }

        // Email validation regex
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!email.matches(emailRegex)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Email không đúng định dạng.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Phone number validation: only digits
        if (!phone.matches("\\d+")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Số điện thoại chỉ được nhập số.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
<<<<<<< HEAD
            taiKhoan_BLL bll = new taiKhoan_BLL();
            boolean success = bll.registerUser(tenTaiKhoan, matKhau);
=======
            BLL.taiKhoan_BLL bll = new BLL.taiKhoan_BLL();
            if (!bll.isEmployeeIdExists(maNhanVien)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean success = bll.registerUser(maNhanVien, tenTaiKhoan, matKhau);
>>>>>>> 8e511c7ae845532776e61da95904dfcc54e57c20
            if (success) {
                javax.swing.JOptionPane.showMessageDialog(this, "Đăng ký thành công!", "Thông báo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                GUIDangNhap dangNhap = new GUIDangNhap();
                dangNhap.setVisible(true);
                this.dispose();
            } else {
<<<<<<< HEAD
                javax.swing.JOptionPane.showMessageDialog(this, "Đăng ký thất bại.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
=======
                javax.swing.JOptionPane.showMessageDialog(this, "Đăng ký thất bại. Tài khoản có thể đã tồn tại hoặc mã nhân viên không hợp lệ.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
>>>>>>> 8e511c7ae845532776e61da95904dfcc54e57c20
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

<<<<<<< HEAD
=======
    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void dayBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayBoxActionPerformed

    private void dayBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dayBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_dayBoxItemStateChanged

    private void nuBtnRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuBtnRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuBtnRadioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GUIDangNhap pageDangNhap = new GUIDangNhap();
        pageDangNhap.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    /**
     * @param args the command line arguments
     */
>>>>>>> 8e511c7ae845532776e61da95904dfcc54e57c20
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

<<<<<<< HEAD
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
=======
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ageLabel;
    private javax.swing.JLabel dangKy_label;
    private javax.swing.JComboBox<String> dayBox;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel matKhauLabel;
    private javax.swing.JComboBox<String> monthBox;
    private javax.swing.JRadioButton namBtnRadio;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JRadioButton nuBtnRadio;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel pic;
    private javax.swing.JLabel sexLabel;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JLabel xacNhanMKLabel;
    private javax.swing.JComboBox<String> yearBox;
    // End of variables declaration//GEN-END:variables
}
>>>>>>> 8e511c7ae845532776e61da95904dfcc54e57c20
