package BLL;

import DAL.taiKhoan_DAL;
import DTO.taiKhoan_DTO;
import java.util.List;
import java.util.Random;

public class taiKhoan_BLL {
    private taiKhoan_DAL taiKhoan_DAL;

    public taiKhoan_BLL() throws Exception {
        taiKhoan_DAL = new taiKhoan_DAL();
    }

    public boolean registerUser(String tenTaiKhoan, String matKhau) throws Exception {
        String maNhanVien;
        do {
            maNhanVien = generateRandomMaNhanVien();
        } while (taiKhoan_DAL.maNhanVienExists(maNhanVien));

        taiKhoan_DTO user = new taiKhoan_DTO(maNhanVien, tenTaiKhoan, matKhau);
        return taiKhoan_DAL.register(user);
    }

    private String generateRandomMaNhanVien() {
        Random rand = new Random();
        int num = rand.nextInt(90000) + 10000; // 5 digit number
        return "NV" + num;
    }

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
