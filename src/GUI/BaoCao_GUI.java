package GUI;
import DAO.BaoCao_DAO;          
import DTO.BaoCao_DTO; 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;      

public class BaoCao_GUI 
    private JTable tblDoanhThu;
    private BaoCao_DAO baoCaoDAO = new BaoCao_DAO();

    public BaoCao_GUI() {
        setTitle("Thống kê doanh thu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tblDoanhThu = new JTable();
        add(new JScrollPane(tblDoanhThu), BorderLayout.CENTER);

        loadDoanhThu();
    }

    private void loadDoanhThu() {
        List<BaoCao_DTO> list = baoCaoDAO.getDoanhThuTheoThang();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tháng");
        model.addColumn("Doanh thu");

        for (BaoCao_DTO dto : list) {
            model.addRow(new Object[]{dto.getThang(), dto.getTongDoanhThu()});
        }

        tblDoanhThu.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BaoCao_GUI().setVisible(true);
        });
    }
}
