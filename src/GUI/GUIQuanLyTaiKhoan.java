/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.taiKhoan_BLL;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class GUIQuanLyTaiKhoan extends javax.swing.JFrame {

    /**
     * Creates new form Main_layout
     */
    private javax.swing.JLabel currentUser_label;

    public GUIQuanLyTaiKhoan() {
        this("");
    }

    public GUIQuanLyTaiKhoan(String username) {
        initComponents();
        setTitle("Quản lý cửa hàng bán xe máy");
        jPanel1.setBackground(new Color(70,20,180));
        jPanel2.setBackground(Color.WHITE);
        jPanel3.setBackground(Color.WHITE);
        jPanel4.setBackground(Color.WHITE);
        
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/IMG/logo.png"));
        Image logo = icon1.getImage().getScaledInstance(230,100, Image.SCALE_SMOOTH);
        jLabel2.setIcon(new ImageIcon(logo));
        
        chucNang_label.setFont(new Font("Arial", Font.BOLD, 16));
        chucNang_label.setForeground(Color.white);
 
        currentUser_label = new javax.swing.JLabel();
        currentUser_label.setFont(new Font("Arial", Font.ITALIC, 14));
        currentUser_label.setForeground(Color.white);
        currentUser_label.setText("Người dùng hiện tại: " + username);
        jPanel1.add(currentUser_label);
        currentUser_label.setBounds(banHang_btn.getX(), chucNang_label.getY() + 25, 200, 20);
 
        // Custom button styles
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        Color buttonBackground = new Color(100, 149, 237); 
        Color buttonForeground = Color.WHITE;
 
        javax.swing.JButton[] buttons = {
            banHang_btn, qlnv_btn, qlsp_btn, qlkh_btn, qltk_btn, nhapHang_btn, dangXuat_btn
        };
 
        for (javax.swing.JButton btn : buttons) {
            btn.setFont(buttonFont);
            btn.setBackground(buttonBackground);
            btn.setForeground(buttonForeground);
            btn.setFocusPainted(false);
            btn.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(65, 105, 225), 2)); // Royal Blue border
        }
        
        // Add event listener for themTaiKhoan_btn
        themTaiKhoan_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themTaiKhoan_btnActionPerformed(evt);
            }
        });

        // Load accounts into table on startup
        loadAccountsToTable();
        
        taiKhoanTable.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = taiKhoanTable.rowAtPoint(e.getPoint());
        int column = taiKhoanTable.columnAtPoint(e.getPoint());

        // Giả sử cột "Sửa | Xóa" là cột cuối (index = 4)
        if (column == taiKhoanTable.getColumnCount() - 1 && row >= 0) {
            String taiKhoan = taiKhoanTable.getValueAt(row, 0).toString();

            // Hiển thị lựa chọn
            Object[] options = {"Sửa", "Xóa", "Hủy"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Bạn muốn làm gì với tài khoản: " + taiKhoan + "?",
                    "Lựa chọn",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            if (choice == 0) { // Sửa
                String newTenTaiKhoan = JOptionPane.showInputDialog(null, "Nhập tên người dùng mới:");
                if (newTenTaiKhoan == null || newTenTaiKhoan.trim().isEmpty()) {
                    showError("Tên người dùng không được để trống!");
                    return;
                }
                String newMatKhau = JOptionPane.showInputDialog(null, "Nhập mật khẩu mới:");
                if (newMatKhau == null || newMatKhau.trim().isEmpty()) {
                    showError("Mật khẩu không được để trống!");
                    return;
                }
                try {
                    taiKhoan_BLL bll = new taiKhoan_BLL();
                    bll.updateAccount(taiKhoan, newTenTaiKhoan.trim(), newMatKhau.trim());
                    JOptionPane.showMessageDialog(null, "Đã cập nhật tài khoản.");
                    showTable(); // load lại bảng
                } catch (Exception ex) {
                    showError("Lỗi cập nhật tài khoản: " + ex.getMessage());
                }
            } else if (choice == 1) { // Xóa
                int xacNhan = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc muốn xóa tài khoản " + taiKhoan + "?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION);
                if (xacNhan == JOptionPane.YES_OPTION) {
                    taiKhoan_BLL bll = null;
                    try {
                        bll = new taiKhoan_BLL();
                    } catch (Exception ex) {
                        Logger.getLogger(GUIQuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    bll.deleteAccount(taiKhoan);
                    JOptionPane.showMessageDialog(null, "Đã xóa tài khoản.");
                    showTable(); // load lại bảng
                }
            }
        }
    }

            private void showTable() {
                try {
                    BLL.taiKhoan_BLL taiKhoanBLL = new BLL.taiKhoan_BLL();
                    var model = (javax.swing.table.DefaultTableModel) taiKhoanTable.getModel();
                    model.setRowCount(0);
                    for (var acc : taiKhoanBLL.getAllAccounts()) {
                        model.addRow(new Object[]{acc.getMaTaiKhoan(), acc.getTenTaiKhoan(), acc.getMatKhau(), "Sửa | Xóa"});
                    }
                } catch (Exception e) {
                    showError("Lỗi tải danh sách tài khoản: " + e.getMessage());
                }
            }
    
});

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taiKhoanTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        themTaiKhoan_btn = new javax.swing.JButton();
        timKiemLabel = new javax.swing.JLabel();
        timKiemField = new javax.swing.JTextField();
        timKiem_btn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        banHang_btn.setText("Bán hàng");

        qlnv_btn.setText("Quản lý nhân viên ");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(banHang_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(qlnv_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(qlsp_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(qlkh_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(qltk_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nhapHang_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(dangXuat_btn)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(chucNang_label)
                        .addGap(104, 104, 104))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dangXuat_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        taiKhoanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã tài khoản", "Tên người dùng", "Mật khẩu", "Chức năng"
            }
        ));
        jScrollPane1.setViewportView(taiKhoanTable);

        themTaiKhoan_btn.setText("Thêm tài khoản");

        timKiemLabel.setText("Tìm kiếm:");

        timKiem_btn.setText("Tìm");
        timKiem_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiem_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(425, 425, 425)
                .addComponent(timKiemLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timKiemField, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timKiem_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                .addComponent(themTaiKhoan_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themTaiKhoan_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timKiemLabel)
                    .addComponent(timKiemField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timKiem_btn))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void qlsp_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qlsp_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qlsp_btnActionPerformed

    private void qlkh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qlkh_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qlkh_btnActionPerformed

    private void qltk_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qltk_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qltk_btnActionPerformed

    private void nhapHang_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhapHang_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nhapHang_btnActionPerformed

    private void timKiem_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiem_btnActionPerformed
        try {
            String keyword = timKiemField.getText().trim();
            BLL.taiKhoan_BLL bll = new BLL.taiKhoan_BLL();
            var model = (javax.swing.table.DefaultTableModel) taiKhoanTable.getModel();
            model.setRowCount(0);
            if (keyword.isEmpty()) {
                // If search field is empty, load all accounts
                for (var acc : bll.getAllAccounts()) {
                    model.addRow(new Object[]{acc.getMaTaiKhoan(), acc.getTenTaiKhoan(), acc.getMatKhau(), "Sửa | Xóa"});
                }
            } else {
                // Search accounts by keyword
                for (var acc : bll.searchAccounts(keyword)) {
                    model.addRow(new Object[]{acc.getMaTaiKhoan(), acc.getTenTaiKhoan(), acc.getMatKhau(), "Sửa | Xóa"});
                }
            }
        } catch (Exception e) {
            showError("Lỗi tìm kiếm tài khoản: " + e.getMessage());
        }
    }//GEN-LAST:event_timKiem_btnActionPerformed

    private void themTaiKhoan_btnActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        String tenTaiKhoan = javax.swing.JOptionPane.showInputDialog(this, "Nhập tên tài khoản:");
        if (tenTaiKhoan == null || tenTaiKhoan.trim().isEmpty()) {
            showError("Tên tài khoản không được để trống!");
            return;
        }

        String matKhau = javax.swing.JOptionPane.showInputDialog(this, "Nhập mật khẩu:");
        if (matKhau == null || matKhau.trim().isEmpty()) {
            showError("Mật khẩu không được để trống!");
            return;
        }

        BLL.taiKhoan_BLL taiKhoanBLL = new BLL.taiKhoan_BLL();
        if (taiKhoanBLL.registerUser(tenTaiKhoan, matKhau)) {
            showMessage("Thêm tài khoản thành công!");
            loadAccountsToTable();
        } else {
            showError("Thêm tài khoản thất bại!");
        }
    } catch (Exception e) {
        showError("Lỗi: " + e.getMessage());
    }
}

private void loadAccountsToTable() {
    try {
        BLL.taiKhoan_BLL taiKhoanBLL = new BLL.taiKhoan_BLL();
        var model = (javax.swing.table.DefaultTableModel) taiKhoanTable.getModel();
        model.setRowCount(0);
        for (var acc : taiKhoanBLL.getAllAccounts()) {
            model.addRow(new Object[]{acc.getMaTaiKhoan(), acc.getTenTaiKhoan(), acc.getMatKhau(), "Sửa | Xóa"});
        }
    } catch (Exception e) {
        showError("Lỗi tải danh sách tài khoản: " + e.getMessage());
    }
}

private void showMessage(String message) {
    javax.swing.JOptionPane.showMessageDialog(this, message, "Thông báo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
}

private void showError(String message) {
    javax.swing.JOptionPane.showMessageDialog(this, message, "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIQuanLyTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIQuanLyTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIQuanLyTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIQuanLyTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIQuanLyTaiKhoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton banHang_btn;
    private javax.swing.JLabel chucNang_label;
    private javax.swing.JButton dangXuat_btn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nhapHang_btn;
    private javax.swing.JButton qlkh_btn;
    private javax.swing.JButton qlnv_btn;
    private javax.swing.JButton qlsp_btn;
    private javax.swing.JButton qltk_btn;
    private javax.swing.JTable taiKhoanTable;
    private javax.swing.JButton themTaiKhoan_btn;
    private javax.swing.JTextField timKiemField;
    private javax.swing.JLabel timKiemLabel;
    private javax.swing.JButton timKiem_btn;
    // End of variables declaration//GEN-END:variables

    
}