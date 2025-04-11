
-- Tạo cơ sở dữ liệu
CREATE DATABASE QuanLyBanXeMay;
GO

USE QuanLyBanXeMay;
GO

-- Bảng HangSanXuat
CREATE TABLE HangSanXuat (
    MaHang VARCHAR(10) PRIMARY KEY,
    TenHang NVARCHAR(100) NOT NULL,
    QuocGia NVARCHAR(50)
);

-- Bảng XeMay
CREATE TABLE XeMay (
    MaXe VARCHAR(10) PRIMARY KEY,
    TenXe NVARCHAR(100) NOT NULL,
    MauSac NVARCHAR(30),
    GiaBan DECIMAL(18, 2),
    SoLuong INT,
    MaHang VARCHAR(10),
    FOREIGN KEY (MaHang) REFERENCES HangSanXuat(MaHang)
);

-- Bảng KhachHang
CREATE TABLE KhachHang (
    MaKH VARCHAR(10) PRIMARY KEY,
    TenKH NVARCHAR(100),
    DiaChi NVARCHAR(200),
    SoDienThoai VARCHAR(15)
);

-- Bảng NhanVien
CREATE TABLE NhanVien (
    MaNV VARCHAR(10) PRIMARY KEY,
    TenNV NVARCHAR(100),
    ChucVu NVARCHAR(50),
    SoDienThoai VARCHAR(15)
);

-- Bảng HoaDon
CREATE TABLE HoaDon (
    MaHD VARCHAR(10) PRIMARY KEY,
    NgayLap DATE,
    MaKH VARCHAR(10),
    MaNV VARCHAR(10),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

-- Bảng ChiTietHoaDon
CREATE TABLE ChiTietHoaDon (
    MaHD VARCHAR(10),
    MaXe VARCHAR(10),
    SoLuong INT,
    DonGia DECIMAL(18, 2),
    PRIMARY KEY (MaHD, MaXe),
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
    FOREIGN KEY (MaXe) REFERENCES XeMay(MaXe)
);

-- Dữ liệu mẫu

-- HangSanXuat
INSERT INTO HangSanXuat (MaHang, TenHang, QuocGia)
VALUES 
('HON', 'Honda', 'Nhật Bản'),
('YAM', 'Yamaha', 'Nhật Bản'),
('SYM', 'SYM', 'Đài Loan'),
('SUZ', 'Suzuki', 'Nhật Bản');

-- XeMay
INSERT INTO XeMay (MaXe, TenXe, MauSac, GiaBan, SoLuong, MaHang)
VALUES 
('X001', 'Honda Wave Alpha', 'Đen', 18000000, 10, 'HON'),
('X002', 'Yamaha Sirius', 'Trắng', 21000000, 8, 'YAM'),
('X003', 'Suzuki Raider', 'Xanh', 45000000, 5, 'SUZ'),
('X004', 'SYM Galaxy', 'Đỏ', 19000000, 6, 'SYM');

-- KhachHang
INSERT INTO KhachHang (MaKH, TenKH, DiaChi, SoDienThoai)
VALUES 
('KH01', N'Nguyễn Văn A', N'Hà Nội', '0912345678'),
('KH02', N'Trần Thị B', N'Hồ Chí Minh', '0987654321'),
('KH03', N'Lê Văn C', N'Đà Nẵng', '0933222111');

-- NhanVien
INSERT INTO NhanVien (MaNV, TenNV, ChucVu, SoDienThoai)
VALUES 
('NV01', N'Phạm Văn D', N'Nhân viên bán hàng', '0909090909'),
('NV02', N'Ngô Thị E', N'Quản lý', '0977979797');

-- HoaDon
INSERT INTO HoaDon (MaHD, NgayLap, MaKH, MaNV)
VALUES 
('HD01', '2025-04-10', 'KH01', 'NV01'),
('HD02', '2025-04-11', 'KH02', 'NV02');

-- ChiTietHoaDon
INSERT INTO ChiTietHoaDon (MaHD, MaXe, SoLuong, DonGia)
VALUES 
('HD01', 'X001', 1, 18000000),
('HD01', 'X002', 1, 21000000),
('HD02', 'X003', 1, 45000000);


-- Bảng PhieuNhap
CREATE TABLE PhieuNhap (
    MaPN VARCHAR(10) PRIMARY KEY,
    NgayNhap DATE,
    MaNV VARCHAR(10),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

-- Bảng ChiTietPhieuNhap
CREATE TABLE ChiTietPhieuNhap (
    MaPN VARCHAR(10),
    MaXe VARCHAR(10),
    SoLuong INT,
    DonGia DECIMAL(18, 2),
    PRIMARY KEY (MaPN, MaXe),
    FOREIGN KEY (MaPN) REFERENCES PhieuNhap(MaPN),
    FOREIGN KEY (MaXe) REFERENCES XeMay(MaXe)
);

-- Dữ liệu mẫu cho PhieuNhap
INSERT INTO PhieuNhap (MaPN, NgayNhap, MaNV)
VALUES 
('PN01', '2025-04-01', 'NV02'),
('PN02', '2025-04-05', 'NV01');

-- Dữ liệu mẫu cho ChiTietPhieuNhap
INSERT INTO ChiTietPhieuNhap (MaPN, MaXe, SoLuong, DonGia)
VALUES 
('PN01', 'X001', 5, 16000000),
('PN01', 'X002', 3, 19000000),
('PN02', 'X004', 4, 17000000);
