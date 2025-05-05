package GUI;

import BLL.NhapHang_BLL;
import DTO.ChiTietPhieuNhap_DTO;
import DTO.PhieuNhap_DTO;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class XuatPhieuNhap_GUI extends JFrame {
    private PhieuNhap_DTO phieuNhap;
    private ArrayList<ChiTietPhieuNhap_DTO> dsChiTiet;
    private NhapHang_BLL nhapHangBLL;
    private NhapHang_GUI nhapHangGUI;
    private DefaultTableModel model;

    public XuatPhieuNhap_GUI(PhieuNhap_DTO phieuNhap, ArrayList<ChiTietPhieuNhap_DTO> dsChiTiet, DefaultTableModel model, NhapHang_BLL nhapHangBLL, NhapHang_GUI nhapHangGUI) {
        this.phieuNhap = phieuNhap;
        this.dsChiTiet = dsChiTiet;
        this.nhapHangBLL = nhapHangBLL;
        this.nhapHangGUI = nhapHangGUI;
        this.model = model;

        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel tt_Panel = new JPanel();
        tt_Panel.setLayout(new BoxLayout(tt_Panel, BoxLayout.Y_AXIS));
        tt_Panel.setPreferredSize(new Dimension(350, 200));
        tt_Panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel MaPN_Panel = new JPanel();
        MaPN_Panel.setPreferredSize(new Dimension(350, 40));
        JLabel MaPN_Label = new JLabel("Mã phiếu nhập");
        MaPN_Label.setPreferredSize(new Dimension(150, 40));
        JTextField MaPN_Field = new JTextField();
        MaPN_Field.setPreferredSize(new Dimension(100, 30));
        MaPN_Field.setEditable(false);
        String maPhieuNhap = nhapHangBLL.taoMaPhieuNhapMoi();
        MaPN_Field.setText(maPhieuNhap);
        phieuNhap.setMa_phieu_nhap(maPhieuNhap);
        MaPN_Panel.add(MaPN_Label);
        MaPN_Panel.add(MaPN_Field);

        JPanel MaNCC_Panel = new JPanel();
        MaNCC_Panel.setPreferredSize(new Dimension(350, 40));
        JLabel MaNCC_Label = new JLabel("Mã nhà cung cấp");
        MaNCC_Label.setPreferredSize(new Dimension(150, 40));
        JTextField MaNCC_Field = new JTextField();
        MaNCC_Field.setPreferredSize(new Dimension(100, 30));
        MaNCC_Field.setEditable(false);
        MaNCC_Field.setText(phieuNhap.getMa_nha_cung_cap());
        MaNCC_Panel.add(MaNCC_Label);
        MaNCC_Panel.add(MaNCC_Field);

        JPanel Ngay_Panel = new JPanel();
        Ngay_Panel.setPreferredSize(new Dimension(350, 40));
        JLabel Ngay_Label = new JLabel("Ngày nhập");
        Ngay_Label.setPreferredSize(new Dimension(150, 40));
        JTextField Ngay_Field = new JTextField();
        LocalDate today = LocalDate.now();
        String ngayText = today.getDayOfMonth() + " - " + today.getMonthValue() + " - " + today.getYear();
        Ngay_Field.setText(ngayText);
        Ngay_Field.setEditable(false);
        Ngay_Field.setPreferredSize(new Dimension(100, 30));
        phieuNhap.setNgay_nhap(Date.valueOf(today));
        Ngay_Panel.add(Ngay_Label);
        Ngay_Panel.add(Ngay_Field);

        JPanel Tongtien_Panel = new JPanel();
        Tongtien_Panel.setPreferredSize(new Dimension(350, 40));
        JLabel Tongtien_Label = new JLabel("Tổng tiền");
        Tongtien_Label.setPreferredSize(new Dimension(150, 40));
        JTextField Tongtien_Field = new JTextField();
        Tongtien_Field.setPreferredSize(new Dimension(100, 30));
        Tongtien_Field.setEditable(false);
        Tongtien_Field.setText(Integer.toString(phieuNhap.getTong_tien()));
        Tongtien_Panel.add(Tongtien_Label);
        Tongtien_Panel.add(Tongtien_Field);

        tt_Panel.add(MaPN_Panel);
        tt_Panel.add(MaNCC_Panel);
        tt_Panel.add(Ngay_Panel);
        tt_Panel.add(Tongtien_Panel);

        JPanel ds_Panel = new JPanel(new BorderLayout());
        ds_Panel.setPreferredSize(new Dimension(350, 200));
        ds_Panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JTable ds_Table = new JTable();
        ds_Table.setModel(model);
        JScrollPane ds_ScrollPane = new JScrollPane(ds_Table);
        ds_Panel.add(ds_ScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton Xacnhan_Button = new JButton("Xác nhận");
        Xacnhan_Button.setFont(new Font("Arial", Font.BOLD, 20));
        Xacnhan_Button.setPreferredSize(new Dimension(200, 40));
        Xacnhan_Button.setBackground(new Color(255, 128, 0));
        Xacnhan_Button.setForeground(Color.WHITE);
        buttonPanel.add(Xacnhan_Button);

        add(tt_Panel, BorderLayout.NORTH);
        add(ds_Panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        Xacnhan_Button.addActionListener(e -> {
            // Gán ma_phieu_nhap cho dsChiTiet
            for (ChiTietPhieuNhap_DTO chiTiet : dsChiTiet) {
                chiTiet.setMa_phieu_nhap(phieuNhap.getMa_phieu_nhap());
            }
        
            // Lưu dữ liệu vào CSDL
            boolean success = nhapHangBLL.luuPhieuNhap(phieuNhap, dsChiTiet);
            if (success) {
                JOptionPane.showMessageDialog(this, "Lưu phiếu nhập thành công!");
                nhapHangGUI.resetPhieuNhap();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Lưu phiếu nhập thất bại. Vui lòng kiểm tra lại thông tin hoặc kết nối CSDL.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}