package GUI;

import DAO.BaoCao_DAO;
import DTO.BaoCao_DTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BaoCao_GUI extends JPanel {
    private JTable tblDoanhThu;
    private BaoCao_DAO baoCaoDAO = new BaoCao_DAO();

    public BaoCao_GUI() {
        setLayout(new BorderLayout()); 

        tblDoanhThu = new JTable();
        add(new JScrollPane(tblDoanhThu), BorderLayout.CENTER);  
        loadDoanhThu();  
    }

    private void loadDoanhThu() {
        List<BaoCao_DTO> list = baoCaoDAO.getDoanhThuTheoThang();  

        // Tạo DefaultTableModel để quản lý dữ liệu bảng
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tháng");
        model.addColumn("Doanh thu");

        // Thêm dữ liệu vào bảng
        for (BaoCao_DTO dto : list) {
            model.addRow(new Object[]{dto.getThang(), dto.getTongDoanhThu()});
        }

        tblDoanhThu.setModel(model); \
    }
}
