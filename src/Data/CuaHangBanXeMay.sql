--drop database CuaHangBanXeMay;
create database CuaHangBanXeMay;
use CuaHangBanXeMay;
go
-- Bảng LoaiSanPham


CREATE TABLE LoaiSanPham (
    ma_loai VARCHAR(10) PRIMARY KEY,
    ten_loai VARCHAR(50) NOT NULL
);

-- Bảng SanPham
CREATE TABLE SanPham (
    ma_spham VARCHAR(10) PRIMARY KEY,
    ten_spham VARCHAR(100) NOT NULL,
    xuat_xu VARCHAR(50),
    so_luong_ton INT NOT NULL DEFAULT 0, -- Lưu trữ số lượng tồn kho
    gia_ban INT NOT NULL,
    rang_thai VARCHAR(20),
    ma_loai VARCHAR(10),
    FOREIGN KEY (ma_loai) REFERENCES LoaiSanPham(ma_loai)
);

-- Bảng KhachHang
CREATE TABLE KhachHang (
    ma_khach_hang VARCHAR(10) PRIMARY KEY,
    ten VARCHAR(100) NOT NULL,
    sdt VARCHAR(15),
    email VARCHAR(100),
    dia_chi VARCHAR(255)
);

-- Bảng NhanVien
CREATE TABLE NhanVien (
    ma_nhan_vien VARCHAR(10) PRIMARY KEY,
    ten VARCHAR(100) NOT NULL,
    chuc_vu VARCHAR(50),
    so_dien_thoai VARCHAR(15),
    muc_luong INT,
    gioi_tinh VARCHAR(10),
    dia_chi VARCHAR(255)
);

-- Bảng tài khoản
CREATE TABLE TaiKhoan (
    ma_nhan_vien VARCHAR(10) PRIMARY KEY,
    ten_tai_khoan VARCHAR(50) UNIQUE NOT NULL,
    mat_khau VARCHAR(50) NOT NULL,
    FOREIGN KEY (ma_nhan_vien) REFERENCES NhanVien(ma_nhan_vien)
);


-- Bảng NhaCungCap
CREATE TABLE NhaCungCap (
    ma_nha_cung_cap VARCHAR(10) PRIMARY KEY,
    ten VARCHAR(100) NOT NULL,
    dia_chi VARCHAR(255),
    email VARCHAR(100),
    quoc_gia VARCHAR(50)
);

-- Bảng PhieuNhap
CREATE TABLE PhieuNhap (
    ma_phieu_nhap VARCHAR(10) PRIMARY KEY,
    ma_nha_cung_cap VARCHAR(10),
    ngay_nhap DATE NOT NULL,
    tong_tien INT NOT NULL,
    FOREIGN KEY (ma_nha_cung_cap) REFERENCES NhaCungCap(ma_nha_cung_cap)
);

-- Bảng ChiTietPhieuNhap
CREATE TABLE ChiTietPhieuNhap (
    ma_phieu_nhap VARCHAR(10),
    ma_spham VARCHAR(10),
    gia_nhap INT NOT NULL,
    so_luong_nhap INT NOT NULL,
    thanh_tien INT NOT NULL,
    PRIMARY KEY (ma_phieu_nhap, ma_spham),
    FOREIGN KEY (ma_phieu_nhap) REFERENCES PhieuNhap(ma_phieu_nhap),
    FOREIGN KEY (ma_spham) REFERENCES SanPham(ma_spham)
);


-- Bảng XeMay
CREATE TABLE XeMay (
    ma_xe VARCHAR(10) PRIMARY KEY,
    dung_tich_xi_lanh INT,
    bao_hanh INT,
    nam_san_xuat INT
);

-- Bảng PhuTung
CREATE TABLE PhuTung (
    ma_phu_tung VARCHAR(10) PRIMARY KEY,
    chat_lieu VARCHAR(50)
);

-- Bảng HoaDonBan
CREATE TABLE HoaDonBan (
    ma_hoa_don_ban VARCHAR(10) PRIMARY KEY,
    ma_khach_hang VARCHAR(10),
    ma_nhan_vien VARCHAR(10),
    ngay_xuat DATE NOT NULL,
    tong_tien INT NOT NULL,
    trang_thai VARCHAR(20),
    FOREIGN KEY (ma_khach_hang) REFERENCES KhachHang(ma_khach_hang),
    FOREIGN KEY (ma_nhan_vien) REFERENCES NhanVien(ma_nhan_vien)
);

-- Bảng ChiTietHoaDonBan
CREATE TABLE ChiTietHoaDonBan (
    ma_hoa_don_ban VARCHAR(10),
    ma_spham VARCHAR(10),
    so_luong INT NOT NULL,
    don_gia INT NOT NULL,
    PRIMARY KEY (ma_hoa_don_ban, ma_spham),
    FOREIGN KEY (ma_hoa_don_ban) REFERENCES HoaDonBan(ma_hoa_don_ban),
    FOREIGN KEY (ma_spham) REFERENCES SanPham(ma_spham)
);

-- Bảng BaoHanh
CREATE TABLE BaoHanh (
    ma_bao_hanh VARCHAR(10) PRIMARY KEY,
    ma_xe VARCHAR(10),
    ngay_bat_dau DATE NOT NULL,
    ngay_ket_thuc DATE NOT NULL,
    FOREIGN KEY (ma_xe) REFERENCES XeMay(ma_xe)
);

ALTER TABLE XeMay ADD CONSTRAINT FK_XeMay_SanPham FOREIGN KEY (ma_xe) REFERENCES SanPham(ma_spham);
ALTER TABLE PhuTung ADD CONSTRAINT FK_PhuTung_SanPham FOREIGN KEY (ma_phu_tung) REFERENCES SanPham(ma_spham);
