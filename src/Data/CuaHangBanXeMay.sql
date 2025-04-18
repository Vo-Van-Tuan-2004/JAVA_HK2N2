
create database CuaHangBanXeMay;
use CuaHangBanXeMay;

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
    trang_thai VARCHAR(20),
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

use CuaHangBanXeMay;
go
-- Dữ liệu cho bảng LoaiSanPham
INSERT INTO LoaiSanPham (ma_loai, ten_loai) VALUES
('LSP01', 'Xe tay ga'),
('LSP02', 'Xe số'),
('LSP03', 'Xe côn tay'),
('LSP04', 'Xe điện'),
('LSP05', 'Phụ tùng bánh xe'),
('LSP06', 'Phụ tùng động cơ'),
('LSP07', 'Phụ tùng đèn'),
('LSP08', 'Phụ tùng phanh'),
('LSP09', 'Xe tay ga cao cấp'),
('LSP10', 'Xe số phổ thông'),
('LSP11', 'Xe côn tay thể thao'),
('LSP12', 'Xe điện trẻ em'),
('LSP13', 'Phụ tùng gương'),
('LSP14', 'Phụ tùng lốp'),
('LSP15', 'Phụ tùng ắc quy'),
('LSP16', 'Phụ tùng yên xe'),
('LSP17', 'Xe tay ga nhập khẩu'),
('LSP18', 'Xe số nhập khẩu'),
('LSP19', 'Xe côn tay nhập khẩu'),
('LSP20', 'Phụ tùng khác');

-- Dữ liệu cho bảng SanPham
INSERT INTO SanPham (ma_spham, ten_spham, xuat_xu, so_luong_ton, gia_ban, trang_thai, ma_loai) VALUES
('SP01', 'Honda Air Blade 150', 'Nhật Bản', 10, 55000000, 'Còn hàng', 'LSP01'),
('SP02', 'Yamaha Exciter 155', 'Nhật Bản', 15, 48000000, 'Còn hàng', 'LSP03'),
('SP03', 'Honda Wave Alpha', 'Việt Nam', 20, 22000000, 'Còn hàng', 'LSP02'),
('SP04', 'VinFast Klara', 'Việt Nam', 8, 40000000, 'Còn hàng', 'LSP04'),
('SP05', 'Bánh xe Honda', 'Việt Nam', 50, 500000, 'Còn hàng', 'LSP05'),
('SP06', 'Động cơ Yamaha', 'Nhật Bản', 30, 2000000, 'Còn hàng', 'LSP06'),
('SP07', 'Đèn LED xe máy', 'Trung Quốc', 40, 300000, 'Còn hàng', 'LSP07'),
('SP08', 'Phanh đĩa trước', 'Việt Nam', 25, 600000, 'Còn hàng', 'LSP08'),
('SP09', 'Honda SH 150i', 'Nhật Bản', 5, 90000000, 'Còn hàng', 'LSP09'),
('SP10', 'Yamaha Sirius', 'Việt Nam', 18, 20000000, 'Còn hàng', 'LSP10'),
('SP11', 'Suzuki Raider', 'Nhật Bản', 12, 50000000, 'Còn hàng', 'LSP11'),
('SP12', 'Xe điện trẻ em Mini', 'Trung Quốc', 10, 5000000, 'Còn hàng', 'LSP12'),
('SP13', 'Gương chiếu hậu', 'Việt Nam', 60, 100000, 'Còn hàng', 'LSP13'),
('SP14', 'Lốp Michelin', 'Pháp', 35, 800000, 'Còn hàng', 'LSP14'),
('SP15', 'Ắc quy GS', 'Việt Nam', 20, 400000, 'Còn hàng', 'LSP15'),
('SP16', 'Yên xe cao cấp', 'Việt Nam', 15, 700000, 'Còn hàng', 'LSP16'),
('SP17', 'Honda Vision', 'Nhật Bản', 10, 35000000, 'Còn hàng', 'LSP01'),
('SP18', 'Yamaha R15', 'Nhật Bản', 7, 70000000, 'Còn hàng', 'LSP03'),
('SP19', 'Honda Winner X', 'Việt Nam', 9, 48000000, 'Còn hàng', 'LSP03'),
('SP20', 'Phụ tùng ống xả', 'Trung Quốc', 25, 1000000, 'Còn hàng', 'LSP20');


-- Dữ liệu cho bảng KhachHang
INSERT INTO KhachHang (ma_khach_hang, ten, sdt, email, dia_chi) VALUES
('KH01', 'Nguyen Van A', '0901234561', 'nva@gmail.com', '123 Le Loi, Q1, TP.HCM'),
('KH02', 'Tran Thi B', '0901234562', 'ttb@gmail.com', '45 Tran Hung Dao, Q5, TP.HCM'),
('KH03', 'Le Van C', '0901234563', 'lvc@gmail.com', '78 Nguyen Trai, Q3, TP.HCM'),
('KH04', 'Pham Thi D', '0901234564', 'ptd@gmail.com', '12 Vo Van Tan, Q3, TP.HCM'),
('KH05', 'Hoang Van E', '0901234565', 'hve@gmail.com', '56 Ly Thuong Kiet, Q10, TP.HCM'),
('KH06', 'Nguyen Thi F', '0901234566', 'ntf@gmail.com', '89 Pham Van Dong, Go Vap, TP.HCM'),
('KH07', 'Tran Van G', '0901234567', 'tvg@gmail.com', '23 Nguyen Thi Minh Khai, Q1, TP.HCM'),
('KH08', 'Le Thi H', '0901234568', 'lth@gmail.com', '67 Le Thanh Ton, Q1, TP.HCM'),
('KH09', 'Pham Van I', '0901234569', 'pvi@gmail.com', '90 Cach Mang Thang Tam, Q3, TP.HCM'),
('KH10', 'Hoang Thi K', '0901234570', 'htk@gmail.com', '34 Nguyen Hue, Q1, TP.HCM'),
('KH11', 'Nguyen Van L', '0901234571', 'nvl@gmail.com', '56 Tran Phu, Q5, TP.HCM'),
('KH12', 'Tran Thi M', '0901234572', 'ttm@gmail.com', '78 Hai Ba Trung, Q1, TP.HCM'),
('KH13', 'Le Van N', '0901234573', 'lvn@gmail.com', '12 Ton Duc Thang, Q1, TP.HCM'),
('KH14', 'Pham Thi O', '0901234574', 'pto@gmail.com', '45 Ly Tu Trong, Q1, TP.HCM'),
('KH15', 'Hoang Van P', '0901234575', 'hvp@gmail.com', '67 Nguyen Dinh Chieu, Q3, TP.HCM'),
('KH16', 'Nguyen Thi Q', '0901234576', 'ntq@gmail.com', '89 Le Dai Hanh, Q11, TP.HCM'),
('KH17', 'Tran Van R', '0901234577', 'tvr@gmail.com', '23 Hoang Sa, Q1, TP.HCM'),
('KH18', 'Le Thi S', '0901234578', 'lts@gmail.com', '56 Truong Sa, Q3, TP.HCM'),
('KH19', 'Pham Van T', '0901234579', 'pvt@gmail.com', '78 Dien Bien Phu, Q3, TP.HCM'),
('KH20', 'Hoang Thi U', '0901234580', 'htu@gmail.com', '12 Pasteur, Q1, TP.HCM');

-- Dữ liệu cho bảng NhanVien
INSERT INTO NhanVien (ma_nhan_vien, ten, chuc_vu, so_dien_thoai, muc_luong, gioi_tinh, dia_chi) VALUES
('NV01', 'Nguyen Van X', 'Quản lý', '0912345601', 15000000, 'Nam', '123 Nguyen Hue, Q1, TP.HCM'),
('NV02', 'Tran Thi Y', 'Nhân viên bán hàng', '0912345602', 8000000, 'Nữ', '45 Tran Hung Dao, Q5, TP.HCM'),
('NV03', 'Le Van Z', 'Nhân viên kỹ thuật', '0912345603', 9000000, 'Nam', '78 Nguyen Trai, Q3, TP.HCM'),
('NV04', 'Pham Thi W', 'Kế toán', '0912345604', 10000000, 'Nữ', '12 Vo Van Tan, Q3, TP.HCM'),
('NV05', 'Hoang Van V', 'Nhân viên bán hàng', '0912345605', 8000000, 'Nam', '56 Ly Thuong Kiet, Q10, TP.HCM'),
('NV06', 'Nguyen Thi U', 'Nhân viên kỹ thuật', '0912345606', 9000000, 'Nữ', '89 Pham Van Dong, Go Vap, TP.HCM'),
('NV07', 'Tran Van T', 'Quản lý', '0912345607', 15000000, 'Nam', '23 Nguyen Thi Minh Khai, Q1, TP.HCM'),
('NV08', 'Le Thi S', 'Nhân viên bán hàng', '0912345608', 8000000, 'Nữ', '67 Le Thanh Ton, Q1, TP.HCM'),
('NV09', 'Pham Van R', 'Nhân viên kỹ thuật', '0912345609', 9000000, 'Nam', '90 Cach Mang Thang Tam, Q3, TP.HCM'),
('NV10', 'Hoang Thi Q', 'Kế toán', '0912345610', 10000000, 'Nữ', '34 Nguyen Hue, Q1, TP.HCM'),
('NV11', 'Nguyen Van P', 'Nhân viên bán hàng', '0912345611', 8000000, 'Nam', '56 Tran Phu, Q5, TP.HCM'),
('NV12', 'Tran Thi O', 'Nhân viên kỹ thuật', '0912345612', 9000000, 'Nữ', '78 Hai Ba Trung, Q1, TP.HCM'),
('NV13', 'Le Van N', 'Quản lý', '0912345613', 15000000, 'Nam', '12 Ton Duc Thang, Q1, TP.HCM'),
('NV14', 'Pham Thi M', 'Nhân viên bán hàng', '0912345614', 8000000, 'Nữ', '45 Ly Tu Trong, Q1, TP.HCM'),
('NV15', 'Hoang Van L', 'Nhân viên kỹ thuật', '0912345615',  9000000, 'Nam', '67 Nguyen Dinh Chieu, Q3, TP.HCM'),
('NV16', 'Nguyen Thi K', 'Kế toán', '0912345616', 10000000, 'Nữ', '89 Le Dai Hanh, Q11, TP.HCM'),
('NV17', 'Tran Van I', 'Nhân viên bán hàng', '0912345617', 8000000, 'Nam', '23 Hoang Sa, Q1, TP.HCM'),
('NV18', 'Le Thi H', 'Nhân viên kỹ thuật', '0912345618', 9000000, 'Nữ', '56 Truong Sa, Q3, TP.HCM'),
('NV19', 'Pham Van G', 'Quản lý', '0912345619', 15000000, 'Nam', '78 Dien Bien Phu, Q3, TP.HCM'),
('NV20', 'Hoang Thi F', 'Nhân viên bán hàng', '0912345620', 8000000, 'Nữ', '12 Pasteur, Q1, TP.HCM');

-- Dữ liệu cho bảng NhaCungCap
INSERT INTO NhaCungCap (ma_nha_cung_cap, ten, dia_chi, email, quoc_gia) VALUES
('NCC01', 'Honda Việt Nam', 'KCN Phuc Dien, Ha Noi', 'honda.vn@gmail.com', 'Việt Nam'),
('NCC02', 'Yamaha Việt Nam', 'KCN Long Duc, Dong Nai', 'yamaha.vn@gmail.com', 'Việt Nam'),
('NCC03', 'VinFast', 'KCN Dinh Vu, Hai Phong', 'vinfast@gmail.com', 'Việt Nam'),
('NCC04', 'Suzuki Việt Nam', 'KCN Bien Hoa, Dong Nai', 'suzuki.vn@gmail.com', 'Việt Nam'),
('NCC05', 'Công ty Phụ tùng ABC', '123 Nguyen Van Cu, Q5, TP.HCM', 'abc@gmail.com', 'Việt Nam'),
('NCC06', 'Công ty Phụ tùng XYZ', '456 Le Loi, Q1, TP.HCM', 'xyz@gmail.com', 'Việt Nam'),
('NCC07', 'Honda Nhật Bản', 'Tokyo, Japan', 'honda.jp@gmail.com', 'Nhật Bản'),
('NCC08', 'Yamaha Nhật Bản', 'Osaka, Japan', 'yamaha.jp@gmail.com', 'Nhật Bản'),
('NCC09', 'Michelin Việt Nam', 'KCN Tan Binh, TP.HCM', 'michelin.vn@gmail.com', 'Việt Nam'),
('NCC10', 'GS Battery', 'KCN Tan Thuan, TP.HCM', 'gsbattery@gmail.com', 'Việt Nam'),
('NCC11', 'Công ty Phụ tùng DEF', '789 Tran Hung Dao, Q5, TP.HCM', 'def@gmail.com', 'Việt Nam'),
('NCC12', 'Công ty Phụ tùng GHI', '234 Nguyen Trai, Q3, TP.HCM', 'ghi@gmail.com', 'Việt Nam'),
('NCC13', 'Suzuki Nhật Bản', 'Nagoya, Japan', 'suzuki.jp@gmail.com', 'Nhật Bản'),
('NCC14', 'Công ty Phụ tùng JKL', '567 Vo Van Tan, Q3, TP.HCM', 'jkl@gmail.com', 'Việt Nam'),
('NCC15', 'Công ty Phụ tùng MNO', '890 Ly Thuong Kiet, Q10, TP.HCM', 'mno@gmail.com', 'Việt Nam'),
('NCC16', 'Công ty Phụ tùng PQR', '123 Pham Van Dong, Go Vap, TP.HCM', 'pqr@gmail.com', 'Việt Nam'),
('NCC17', 'Công ty Phụ tùng STU', '456 Nguyen Thi Minh Khai, Q1, TP.HCM', 'stu@gmail.com', 'Việt Nam'),
('NCC18', 'Công ty Phụ tùng VWX', '789 Le Thanh Ton, Q1, TP.HCM', 'vwx@gmail.com', 'Việt Nam'),
('NCC19', 'Công ty Phụ tùng YZA', '234 Cach Mang Thang Tam, Q3, TP.HCM', 'yza@gmail.com', 'Việt Nam'),
('NCC20', 'Công ty Phụ tùng BCD', '567 Nguyen Hue, Q1, TP.HCM', 'bcd@gmail.com', 'Việt Nam');

-- Dữ liệu cho bảng PhieuNhap
INSERT INTO PhieuNhap (ma_phieu_nhap, ma_nha_cung_cap, ngay_nhap, tong_tien) VALUES
('PN01', 'NCC01', '2025-01-01', 500000000),
('PN02', 'NCC02', '2025-01-02', 430000000),
('PN03', 'NCC03', '2025-01-03', 360000000),
('PN04', 'NCC04', '2025-01-04', 450000000),
('PN05', 'NCC05', '2025-01-05', 20000000),
('PN06', 'NCC06', '2025-01-06', 18000000),
('PN07', 'NCC07', '2025-01-07', 850000000),
('PN08', 'NCC08', '2025-01-08', 650000000),
('PN09', 'NCC09', '2025-01-09', 35000000),
('PN10', 'NCC10', '2025-01-10', 17500000),
('PN11', 'NCC01', '2025-01-11', 320000000),
('PN12', 'NCC02', '2025-01-12', 430000000),
('PN13', 'NCC03', '2025-01-13', 360000000),
('PN14', 'NCC04', '2025-01-14', 450000000),
('PN15', 'NCC05', '2025-01-15', 20000000),
('PN16', 'NCC06', '2025-01-16', 18000000),
('PN17', 'NCC07', '2025-01-17', 850000000),
('PN18', 'NCC08', '2025-01-18', 650000000),
('PN19', 'NCC09', '2025-01-19', 35000000),
('PN20', 'NCC10', '2025-01-20', 17500000);

-- Dữ liệu cho bảng ChiTietPhieuNhap
INSERT INTO ChiTietPhieuNhap (ma_phieu_nhap, ma_spham, gia_nhap, so_luong_nhap, thanh_tien) VALUES
('PN01', 'SP01', 50000000, 10, 500000000),
('PN02', 'SP02', 43000000, 10, 430000000),
('PN03', 'SP04', 36000000, 10, 360000000),
('PN04', 'SP11', 45000000, 10, 450000000),
('PN05', 'SP05', 200000, 50, 10000000),
('PN06', 'SP06', 1800000, 10, 18000000),
('PN07', 'SP09', 85000000, 10, 850000000),
('PN08', 'SP18', 65000000, 10, 650000000),
('PN09', 'SP14', 700000, 50, 35000000),
('PN10', 'SP15', 350000, 50, 17500000),
('PN11', 'SP17', 32000000, 10, 320000000),
('PN12', 'SP02', 43000000, 10, 430000000),
('PN13', 'SP04', 36000000, 10, 360000000),
('PN14', 'SP11', 45000000, 10, 450000000),
('PN15', 'SP05', 200000, 50, 10000000),
('PN16', 'SP06', 1800000, 10, 18000000),
('PN17', 'SP09', 85000000, 10, 850000000),
('PN18', 'SP18', 65000000, 10, 650000000),
('PN19', 'SP14', 700000, 50, 35000000),
('PN20', 'SP15', 350000, 50, 17500000);


-- Dữ liệu cho bảng XeMay
INSERT INTO XeMay (ma_xe, dung_tich_xi_lanh, bao_hanh, nam_san_xuat) VALUES
('SP01', 150, 24, 2023),
('SP02', 155, 24, 2023),
('SP03', 110, 12, 2022),
('SP04', 0, 12, 2023),
('SP05', 150, 24, 2024),
('SP06', 110, 12, 2022),
('SP07', 155, 24, 2023),
('SP08', 150, 24, 2024),
('SP09', 155, 24, 2023),
('SP10', 0, 12, 2023),
('SP11', 150, 24, 2023),
('SP12', 155, 24, 2023),
('SP13', 110, 12, 2022),
('SP14', 0, 12, 2023),
('SP15', 150, 24, 2024),
('SP16', 110, 12, 2022),
('SP17', 155, 24, 2023),
('SP18', 150, 24, 2024),
('SP19', 155, 24, 2023),
('SP20', 0, 12, 2023);


-- Dữ liệu cho bảng PhuTung
-- Dữ liệu cho bảng PhuTung
INSERT INTO PhuTung (ma_phu_tung, chat_lieu) VALUES
('SP01', 'Cao su'),
('SP02', 'Kim loại'),
('SP03', 'Nhựa'),
('SP04', 'Thép'),
('SP05', 'Cao su'),
('SP06', 'Kim loại'),
('SP07', 'Nhựa'),
('SP08', 'Thép'),
('SP09', 'Cao su'),
('SP10', 'Kim loại'),
('SP11', 'Nhựa'),
('SP12', 'Thép'),
('SP13', 'Cao su'),
('SP14', 'Kim loại'),
('SP15', 'Nhựa'),
('SP16', 'Thép'),
('SP17', 'Cao su'),
('SP18', 'Kim loại'),
('SP19', 'Nhựa'),
('SP20', 'Thép');


-- Dữ liệu cho bảng HoaDonBan
INSERT INTO HoaDonBan (ma_hoa_don_ban, ma_khach_hang, ma_nhan_vien, ngay_xuat, tong_tien, trang_thai) VALUES
('HDB01', 'KH01', 'NV01', '2025-02-01', 55000000, 'Hoàn thành'),
('HDB02', 'KH02', 'NV02', '2025-02-02', 48000000, 'Hoàn thành'),
('HDB03', 'KH03', 'NV03', '2025-02-03', 22000000, 'Hoàn thành'),
('HDB04', 'KH04', 'NV04', '2025-02-04', 40000000, 'Hoàn thành'),
('HDB05', 'KH05', 'NV05', '2025-02-05', 500000, 'Hoàn thành'),
('HDB06', 'KH06', 'NV06', '2025-02-06', 2000000, 'Hoàn thành'),
('HDB07', 'KH07', 'NV07', '2025-02-07', 300000, 'Hoàn thành'),
('HDB08', 'KH08', 'NV08', '2025-02-08', 600000, 'Hoàn thành'),
('HDB09', 'KH09', 'NV09', '2025-02-09', 90000000, 'Hoàn thành'),
('HDB10', 'KH10', 'NV10', '2025-02-10', 20000000, 'Hoàn thành'),
('HDB11', 'KH11', 'NV11', '2025-02-11', 50000000, 'Hoàn thành'),
('HDB12', 'KH12', 'NV12', '2025-02-12', 5000000, 'Hoàn thành'),
('HDB13', 'KH13', 'NV13', '2025-02-13', 100000, 'Hoàn thành'),
('HDB14', 'KH14', 'NV14', '2025-02-14', 800000, 'Hoàn thành'),
('HDB15', 'KH15', 'NV15', '2025-02-15', 400000, 'Hoàn thành'),
('HDB16', 'KH16', 'NV16', '2025-02-16', 700000, 'Hoàn thành'),
('HDB17', 'KH17', 'NV17', '2025-02-17', 35000000, 'Hoàn thành'),
('HDB18', 'KH18', 'NV18', '2025-02-18', 70000000, 'Hoàn thành'),
('HDB19', 'KH19', 'NV19', '2025-02-19', 48000000, 'Hoàn thành'),
('HDB20', 'KH20', 'NV20', '2025-02-20', 1000000, 'Hoàn thành');

-- Dữ liệu cho bảng ChiTietHoaDonBan
INSERT INTO ChiTietHoaDonBan (ma_hoa_don_ban, ma_spham, so_luong, don_gia) VALUES
('HDB01', 'SP01', 1, 55000000),
('HDB02', 'SP02', 1, 48000000),
('HDB03', 'SP03', 1, 22000000),
('HDB04', 'SP04', 1, 40000000),
('HDB05', 'SP05', 1, 500000),
('HDB06', 'SP06', 1, 2000000),
('HDB07', 'SP07', 1, 300000),
('HDB08', 'SP08', 1, 600000),
('HDB09', 'SP09', 1, 90000000),
('HDB10', 'SP10', 1, 20000000),
('HDB11', 'SP11', 1, 50000000),
('HDB12', 'SP12', 1, 5000000),
('HDB13', 'SP13', 1, 100000),
('HDB14', 'SP14', 1, 800000),
('HDB15', 'SP15', 1, 400000),
('HDB16', 'SP16', 1, 700000),
('HDB17', 'SP17', 1, 35000000),
('HDB18', 'SP18', 1, 70000000),
('HDB19', 'SP19', 1, 48000000),
('HDB20', 'SP20', 1, 1000000);


-- Dữ liệu cho bảng BaoHanh
INSERT INTO BaoHanh (ma_bao_hanh, ma_xe, ngay_bat_dau, ngay_ket_thuc) VALUES
('BH01', 'SP01', '2025-02-01', '2027-02-01'),
('BH02', 'SP02', '2025-02-02', '2027-02-02'),
('BH03', 'SP03', '2025-02-03', '2026-02-03'),
('BH04', 'SP04', '2025-02-04', '2026-02-04'),
('BH05', 'SP05', '2025-02-05', '2027-02-05'),
('BH06', 'SP06', '2025-02-06', '2026-02-06'),
('BH07', 'SP07', '2025-02-07', '2027-02-07'),
('BH08', 'SP08', '2025-02-08', '2027-02-08'),
('BH09', 'SP09', '2025-02-09', '2027-02-09'),
('BH10', 'SP10', '2025-02-10', '2026-02-10'),
('BH11', 'SP11', '2025-02-11', '2027-02-11'),
('BH12', 'SP12', '2025-02-12', '2027-02-12'),
('BH13', 'SP13', '2025-02-13', '2026-02-13'),
('BH14', 'SP14', '2025-02-14', '2026-02-14'),
('BH15', 'SP15', '2025-02-15', '2027-02-15'),
('BH16', 'SP16', '2025-02-16', '2026-02-16'),
('BH17', 'SP17', '2025-02-17', '2027-02-17'),
('BH18', 'SP18', '2025-02-18', '2027-02-18'),
('BH19', 'SP19', '2025-02-19', '2027-02-19'),
('BH20', 'SP20', '2025-02-20', '2026-02-20');


--Dữ liệu bảng tài khoản
INSERT INTO TaiKhoan (ma_nhan_vien, ten_tai_khoan, mat_khau)
VALUES 
('NV01', 'admin', 'admin123'),
('NV02', 'lethanh', 'thanh456'),
('NV03', 'ngocanh', 'anh789'),
('NV04', 'tranminh', 'minh321');

