package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import DTO.HoaDonBan_DTO;

public class XuatPhieuNhap_GUI extends JFrame {
    public XuatPhieuNhap_GUI(HoaDonBan_DTO haodon){
        setSize(300,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setVisible(true);
    }
}
