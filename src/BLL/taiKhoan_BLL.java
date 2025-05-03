package BLL;

import DAO.taiKhoan_DAL;
import DTO.taiKhoan_DTO;
import java.util.List;

public class taiKhoan_BLL {
    private taiKhoan_DAL taiKhoan_DAL;

    public taiKhoan_BLL() throws Exception {
        taiKhoan_DAL = new taiKhoan_DAL();
    }

    public boolean registerUser(String tenTaiKhoan, String matKhau) {
        try {
            if (taiKhoan_DAL.tenTaiKhoanExists(tenTaiKhoan)) {
                System.out.println("Tên tài khoản đã tồn tại");
                return false;
            }
            taiKhoan_DTO user = new taiKhoan_DTO(null, tenTaiKhoan, matKhau, "Nhân viên");
            return taiKhoan_DAL.register(user);
        } catch (Exception e) {
            System.out.println("Lỗi đăng ký tài khoản: " + e.getMessage());
            return false;
        }
    }

    public boolean loginUser(String tenTaiKhoan, String matKhau) {
        return taiKhoan_DAL.login(tenTaiKhoan, matKhau) != null;
    }

    public List<taiKhoan_DTO> getAllAccounts() {
        return taiKhoan_DAL.getAllAccounts();
    }

    public boolean updateAccount(String maNhanVien, String tenTaiKhoanMoi, String matKhauMoi) {
        taiKhoan_DTO existingAccount = taiKhoan_DAL.getAccountByMaNhanVien(maNhanVien);
        if (existingAccount == null) {
            return false;
        }
        if (!existingAccount.getTenTaiKhoan().equals(tenTaiKhoanMoi) && taiKhoan_DAL.tenTaiKhoanExists(tenTaiKhoanMoi)) {
            System.out.println("Tên tài khoản mới đã tồn tại");
            return false;
        }
        existingAccount.setTenTaiKhoan(tenTaiKhoanMoi);
        existingAccount.setMatKhau(matKhauMoi);
        return taiKhoan_DAL.updateAccount(existingAccount);
    }

    public boolean deleteAccount(String maNhanVien) {
        return taiKhoan_DAL.deleteAccount(maNhanVien);
    }

    public List<taiKhoan_DTO> searchAccounts(String keyword) {
        return taiKhoan_DAL.searchAccounts(keyword);
    }
}