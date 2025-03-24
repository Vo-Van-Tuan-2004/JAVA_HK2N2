package OOP;

public interface CheckDate {

    static boolean  kiemTraNgayHopLe(int ngay, int thang, int nam){
        if (thang < 1 || thang > 12 || ngay < 1) {
            return false;
        }
        int[] soNgayTrongThang = {31, laNamNhuan(nam) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return ngay <= soNgayTrongThang[thang - 1];
    }
    static boolean laNamNhuan(int nam){
        return (nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0);
    }
}
