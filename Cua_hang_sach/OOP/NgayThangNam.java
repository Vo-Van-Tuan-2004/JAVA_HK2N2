package OOP;


public class NgayThangNam implements CheckDate{
    int ngay;
    int thang;
    int nam;

    public NgayThangNam(int day, int month, int year) {
        if (CheckDate.kiemTraNgayHopLe(day, month, year)) {
            ngay = day;
            thang = month;
            nam = year;
        } else {
            throw new IllegalArgumentException("Ngày, tháng hoặc năm không hợp lệ.");
        }
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int day) {
        if (CheckDate.kiemTraNgayHopLe(day, thang, nam)) {
            ngay = day;
        } else {
            throw new IllegalArgumentException("Ngay khong hop le.");
        }
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int month) {
        if (CheckDate.kiemTraNgayHopLe(ngay, month, nam)) {
            thang = month;
        } else {
            throw new IllegalArgumentException("Thang khong hop le.");
        }
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int year) {
        if (CheckDate.kiemTraNgayHopLe(ngay, thang, year)) {
            nam = year;
        } else {
            throw new IllegalArgumentException("Nam khong hop le.");
        }
    }

    // @Override
    // public boolean laNamNhuan(int nam) {
    //     return (nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0);
    // }

    // @Override
    // public boolean kiemTraNgayHopLe(int ngay, int thang, int nam) {
    //     if (thang < 1 || thang > 12 || ngay < 1) {
    //         return false;
    //     }
    //     int[] soNgayTrongThang = {31, laNamNhuan(nam) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    //     return ngay <= soNgayTrongThang[thang - 1];
    // }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d",ngay, thang, nam);
    }
}
