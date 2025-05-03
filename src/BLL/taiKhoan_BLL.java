package BLL;

import DAO.taiKhoan_DAL;
import DTO.taiKhoan_DTO;
import java.util.List;

public class taiKhoan_BLL {
    private taiKhoan_DAL taiKhoan_DAL;

    public taiKhoan_BLL() throws Exception {
        taiKhoan_DAL = new taiKhoan_DAL();
    }

    public boolean registerUser(String maNhanVien, String tenTaiKhoan, String matKhau) {
        // Use the provided employee code directly without generating random code
        try {
            if (!taiKhoan_DAL.maNhanVienExists(maNhanVien)) {
                System.out.println("Mã nhân viên không tồn tại");
                return false; // Employee code does not exist
            }
            if (taiKhoan_DAL.maNhanVienExists(maNhanVien)) {
                System.out.println("Mã tài khoản đã tồn tại");
                return false; // Account code already exists
            }
            taiKhoan_DTO user = new taiKhoan_DTO(maNhanVien, tenTaiKhoan, matKhau);
            taiKhoan_DAL.register(user);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi đăng ký tài khoản: " + e.getMessage());
            return false;
        }
    }

    public boolean isEmployeeIdExists(String maNhanVien) {
        return taiKhoan_DAL.maNhanVienExists(maNhanVien);
    }

    // Remove generateRandomMaNhanVien method as it is no longer needed

    public boolean loginUser(String tenTaiKhoan, String matKhau){
        return taiKhoan_DAL.login(tenTaiKhoan, matKhau) != null;
    }

    // New method to get all accounts
    public List<taiKhoan_DTO> getAllAccounts() {
        return taiKhoan_DAL.getAllAccounts();
    }

    // New method to update an account
    public boolean updateAccount(String taiKhoan, String tenTaiKhoanMoi, String matKhauMoi) {
        // Fetch existing account to get current data
        taiKhoan_DTO existingAccount = null;
        List<taiKhoan_DTO> accounts = taiKhoan_DAL.getAllAccounts();
        for (taiKhoan_DTO acc : accounts) {
            if (acc.getMaTaiKhoan().equals(taiKhoan)) {
                existingAccount = acc;
                break;
            }
        }
        if (existingAccount == null) {
            return false; // account not found
        }
        existingAccount.setTenTaiKhoan(tenTaiKhoanMoi);
        existingAccount.setMatKhau(matKhauMoi);
        return taiKhoan_DAL.updateAccount(existingAccount);
    }

    public boolean deleteAccount(String taiKhoan) {
        return taiKhoan_DAL.deleteAccount(taiKhoan);
    }

    // New method to search accounts by keyword
    public List<taiKhoan_DTO> searchAccounts(String keyword) {
        return taiKhoan_DAL.searchAccounts(keyword);
    }
}
