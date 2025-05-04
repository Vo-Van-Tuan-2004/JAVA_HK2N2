-- Xóa cơ sở dữ liệu nếu đã tồn tại (được comment để tránh xóa nhầm)
 use master
 DROP DATABASE CuaHangBanXeMay;
 GO

-- Tạo cơ sở dữ liệu với collation hỗ trợ tiếng Việt
CREATE DATABASE CuaHangBanXeMay
COLLATE Vietnamese_CI_AS;
GO

-- Sử dụng cơ sở dữ liệu
USE CuaHangBanXeMay;
GO

-- Bảng LoaiSanPham
CREATE TABLE LoaiSanPham (
    ma_loai VARCHAR(10) PRIMARY KEY,
    ten_loai NVARCHAR(50) COLLATE Vietnamese_CI_AS NOT NULL
);

-- Bảng SanPham
CREATE TABLE SanPham (
    ma_spham VARCHAR(10) PRIMARY KEY,
    ten_spham NVARCHAR(100) COLLATE Vietnamese_CI_AS NOT NULL,
    xuat_xu NVARCHAR(50) COLLATE Vietnamese_CI_AS,
    so_luong_ton INT NOT NULL DEFAULT 0, -- Lưu trữ số lượng tồn kho
    gia_ban INT NOT NULL,
    trang_thai NVARCHAR(20) COLLATE Vietnamese_CI_AS,
    ma_loai VARCHAR(10),
    FOREIGN KEY (ma_loai) REFERENCES LoaiSanPham(ma_loai)
);

-- Bảng KhachHang
CREATE TABLE KhachHang (
    ma_khach_hang VARCHAR(10) PRIMARY KEY,
    ten NVARCHAR(100) COLLATE Vietnamese_CI_AS NOT NULL,
    sdt VARCHAR(15),
    email VARCHAR(100),
    dia_chi NVARCHAR(255) COLLATE Vietnamese_CI_AS
);

-- Bảng NhanVien
CREATE TABLE NhanVien (
    ma_nhan_vien VARCHAR(10) PRIMARY KEY,
    ten NVARCHAR(100) COLLATE Vietnamese_CI_AS NOT NULL,
    chuc_vu NVARCHAR(50) COLLATE Vietnamese_CI_AS,
    so_dien_thoai VARCHAR(15),
    muc_luong INT,
    gioi_tinh NVARCHAR(10) COLLATE Vietnamese_CI_AS,
    dia_chi NVARCHAR(255) COLLATE Vietnamese_CI_AS
);

-- Bảng TaiKhoan
CREATE TABLE TaiKhoan (
    ma_nhan_vien VARCHAR(10) PRIMARY KEY,
    ten_tai_khoan NVARCHAR(50) COLLATE Vietnamese_CI_AS UNIQUE NOT NULL,
    mat_khau VARCHAR(50) NOT NULL,
    FOREIGN KEY (ma_nhan_vien) REFERENCES NhanVien(ma_nhan_vien)
);

-- Bảng NhaCungCap
CREATE TABLE NhaCungCap (
    ma_nha_cung_cap VARCHAR(10) PRIMARY KEY,
    ten NVARCHAR(100) COLLATE Vietnamese_CI_AS NOT NULL,
    dia_chi NVARCHAR(255) COLLATE Vietnamese_CI_AS,
    email VARCHAR(100),
    quoc_gia NVARCHAR(50) COLLATE Vietnamese_CI_AS
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
    chat_lieu NVARCHAR(50) COLLATE Vietnamese_CI_AS
);

-- Bảng HoaDonBan
CREATE TABLE HoaDonBan (
    ma_hoa_don_ban VARCHAR(10) PRIMARY KEY,
    ma_khach_hang VARCHAR(10),
    ma_nhan_vien VARCHAR(10),
    ngay_xuat DATE NOT NULL,
    tong_tien INT NOT NULL,
    trang_thai NVARCHAR(20) COLLATE Vietnamese_CI_AS,
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

-- Thêm ràng buộc khóa ngoại
ALTER TABLE XeMay ADD CONSTRAINT FK_XeMay_SanPham FOREIGN KEY (ma_xe) REFERENCES SanPham(ma_spham);
ALTER TABLE PhuTung ADD CONSTRAINT FK_PhuTung_SanPham FOREIGN KEY (ma_phu_tung) REFERENCES SanPham(ma_spham);

-- Dữ liệu cho bảng LoaiSanPham
INSERT INTO LoaiSanPham (ma_loai, ten_loai) VALUES
('LSP01', N'Xe tay ga'),
('LSP02', N'Xe số'),
('LSP03', N'Xe côn tay'),
('LSP04', N'Xe điện'),
('LSP05', N'Phụ tùng bánh xe'),
('LSP06', N'Phụ tùng động cơ'),
('LSP07', N'Phụ tùng đèn'),
('LSP08', N'Phụ tùng phanh'),
('LSP09', N'Xe tay ga cao cấp'),
('LSP10', N'Xe số phổ thông'),
('LSP11', N'Xe côn tay thể thao'),
('LSP12', N'Xe điện trẻ em'),
('LSP13', N'Phụ tùng gương'),
('LSP14', N'Phụ tùng lốp'),
('LSP15', N'Phụ tùng ắc quy'),
('LSP16', N'Phụ tùng yên xe'),
('LSP17', N'Xe tay ga nhập khẩu'),
('LSP18', N'Xe số nhập khẩu'),
('LSP19', N'Xe côn tay nhập khẩu'),
('LSP20', N'Phụ tùng khác');

-- Dữ liệu cho bảng SanPham
INSERT INTO SanPham (ma_spham, ten_spham, xuat_xu, so_luong_ton, gia_ban, trang_thai, ma_loai) VALUES
('SP01', N'Honda Air Blade 150', N'Nhật Bản', 10, 55000000, N'Còn hàng', 'LSP01'),
('SP02', N'Yamaha Exciter 155', N'Nhật Bản', 15, 48000000, N'Còn hàng', 'LSP03'),
('SP03', N'Honda Wave Alpha', N'Việt Nam', 20, 22000000, N'Còn hàng', 'LSP02'),
('SP04', N'VinFast Klara', N'Việt Nam', 8, 40000000, N'Còn hàng', 'LSP04'),
('SP05', N'Bánh xe Honda', N'Việt Nam', 50, 500000, N'Còn hàng', 'LSP05'),
('SP06', N'Động cơ Yamaha', N'Nhật Bản', 30, 2000000, N'Còn hàng', 'LSP06'),
('SP07', N'Đèn LED xe máy', N'Trung Quốc', 40, 300000, N'Còn hàng', 'LSP07'),
('SP08', N'Phanh đĩa trước', N'Việt Nam', 25, 600000, N'Còn hàng', 'LSP08'),
('SP09', N'Honda SH 150i', N'Nhật Bản', 5, 90000000, N'Còn hàng', 'LSP09'),
('SP10', N'Yamaha Sirius', N'Việt Nam', 18, 20000000, N'Còn hàng', 'LSP10'),
('SP11', N'Suzuki Raider', N'Nhật Bản', 12, 50000000, N'Còn hàng', 'LSP11'),
('SP12', N'Xe điện trẻ em Mini', N'Trung Quốc', 10, 5000000, N'Còn hàng', 'LSP12'),
('SP13', N'Gương chiếu hậu', N'Việt Nam', 60, 100000, N'Còn hàng', 'LSP13'),
('SP14', N'Lốp Michelin', N'Pháp', 35, 800000, N'Còn hàng', 'LSP14'),
('SP15', N'Ắc quy GS', N'Việt Nam', 20, 400000, N'Còn hàng', 'LSP15'),
('SP16', N'Yên xe cao cấp', N'Việt Nam', 15, 700000, N'Còn hàng', 'LSP16'),
('SP17', N'Honda Vision', N'Nhật Bản', 10, 35000000, N'Còn hàng', 'LSP01'),
('SP18', N'Yamaha R15', N'Nhật Bản', 7, 70000000, N'Còn hàng', 'LSP03'),
('SP19', N'Honda Winner X', N'Việt Nam', 9, 48000000, N'Còn hàng', 'LSP03'),
('SP20', N'Phụ tùng ống xả', N'Trung Quốc', 25, 1000000, N'Còn hàng', 'LSP20');

-- Dữ liệu cho bảng KhachHang
INSERT INTO KhachHang (ma_khach_hang, ten, sdt, email, dia_chi) VALUES
('KH01', N'Nguyen Van A', '0901234561', 'nva@gmail.com', N'123 Le Loi, Q1, TP.HCM'),
('KH02', N'Tran Thi B', '0901234562', 'ttb@gmail.com', N'45 Tran Hung Dao, Q5, TP.HCM'),
('KH03', N'Le Van C', '0901234563', 'lvc@gmail.com', N'78 Nguyen Trai, Q3, TP.HCM'),
('KH04', N'Pham Thi D', '0901234564', 'ptd@gmail.com', N'12 Vo Van Tan, Q3, TP.HCM'),
('KH05', N'Hoang Van E', '0901234565', 'hve@gmail.com', N'56 Ly Thuong Kiet, Q10, TP.HCM'),
('KH06', N'Nguyen Thi F', '0901234566', 'ntf@gmail.com', N'89 Pham Van Dong, Go Vap, TP.HCM'),
('KH07', N'Tran Van G', '0901234567', 'tvg@gmail.com', N'23 Nguyen Thi Minh Khai, Q1, TP.HCM'),
('KH08', N'Le Thi H', '0901234568', 'lth@gmail.com', N'67 Le Thanh Ton, Q1, TP.HCM'),
('KH09', N'Pham Van I', '0901234569', 'pvi@gmail.com', N'90 Cach Mang Thang Tam, Q3, TP.HCM'),
('KH10', N'Hoang Thi K', '0901234570', 'htk@gmail.com', N'34 Nguyen Hue, Q1, TP.HCM'),
('KH11', N'Nguyen Van L', '0901234571', 'nvl@gmail.com', N'56 Tran Phu, Q5, TP.HCM'),
('KH12', N'Tran Thi M', '0901234572', 'ttm@gmail.com', N'78 Hai Ba Trung, Q1, TP.HCM'),
('KH13', N'Le Van N', '0901234573', 'lvn@gmail.com', N'12 Ton Duc Thang, Q1, TP.HCM'),
('KH14', N'Pham Thi O', '0901234574', 'pto@gmail.com', N'45 Ly Tu Trong, Q1, TP.HCM'),
('KH15', N'Hoang Van P', '0901234575', 'hvp@gmail.com', N'67 Nguyen Dinh Chieu, Q3, TP.HCM'),
('KH16', N'Nguyen Thi Q', '0901234576', 'ntq@gmail.com', N'89 Le Dai Hanh, Q11, TP.HCM'),
('KH17', N'Tran Van R', '0901234577', 'tvr@gmail.com', N'23 Hoang Sa, Q1, TP.HCM'),
('KH18', N'Le Thi S', '0901234578', 'lts@gmail.com', N'56 Truong Sa, Q3, TP.HCM'),
('KH19', N'Pham Van T', '0901234579', 'pvt@gmail.com', N'78 Dien Bien Phu, Q3, TP.HCM'),
('KH20', N'Hoang Thi U', '0901234580', 'htu@gmail.com', N'12 Pasteur, Q1, TP.HCM');

-- Dữ liệu cho bảng NhanVien
INSERT INTO NhanVien (ma_nhan_vien, ten, chuc_vu, so_dien_thoai, muc_luong, gioi_tinh, dia_chi) VALUES
('NV01', N'Nguyen Van X', N'Quản lý', '0912345601', 15000000, N'Nam', N'123 Nguyen Hue, Q1, TP.HCM'),
('NV02', N'Tran Thi Y', N'Nhân viên bán hàng', '0912345602', 8000000, N'Nữ', N'45 Tran Hung Dao, Q5, TP.HCM'),
('NV03', N'Le Van Z', N'Nhân viên kỹ thuật', '0912345603', 9000000, N'Nam', N'78 Nguyen Trai, Q3, TP.HCM'),
('NV04', N'Pham Thi W', N'Kế toán', '0912345604', 10000000, N'Nữ', N'12 Vo Van Tan, Q3, TP.HCM'),
('NV05', N'Hoang Van V', N'Nhân viên bán hàng', '0912345605', 8000000, N'Nam', N'56 Ly Thuong Kiet, Q10, TP.HCM'),
('NV06', N'Nguyen Thi U', N'Nhân viên kỹ thuật', '0912345606', 9000000, N'Nữ', N'89 Pham Van Dong, Go Vap, TP.HCM'),
('NV07', N'Tran Van T', N'Quản lý', '0912345607', 15000000, N'Nam', N'23 Nguyen Thi Minh Khai, Q1, TP.HCM'),
('NV08', N'Le Thi S', N'Nhân viên bán hàng', '0912345608', 8000000, N'Nữ', N'67 Le Thanh Ton, Q1, TP.HCM'),
('NV09', N'Pham Van R', N'Nhân viên kỹ thuật', '0912345609', 9000000, N'Nam', N'90 Cach Mang Thang Tam, Q3, TP.HCM'),
('NV10', N'Hoang Thi Q', N'Kế toán', '0912345610', 10000000, N'Nữ', N'34 Nguyen Hue, Q1, TP.HCM'),
('NV11', N'Nguyen Van P', N'Nhân viên bán hàng', '0912345611', 8000000, N'Nam', N'56 Tran Phu, Q5, TP.HCM'),
('NV12', N'Tran Thi O', N'Nhân viên kỹ thuật', '0912345612', 9000000, N'Nữ', N'78 Hai Ba Trung, Q1, TP.HCM'),
('NV13', N'Le Van N', N'Quản lý', '0912345613', 15000000, N'Nam', N'12 Ton Duc Thang, Q1, TP.HCM'),
('NV14', N'Pham Thi M', N'Nhân viên bán hàng', '0912345614', 8000000, N'Nữ', N'45 Ly Tu Trong, Q1, TP.HCM'),
('NV15', N'Hoang Van L', N'Nhân viên kỹ thuật', '0912345615', 9000000, N'Nam', N'67 Nguyen Dinh Chieu, Q3, TP.HCM'),
('NV16', N'Nguyen Thi K', N'Kế toán', '0912345616', 10000000, N'Nữ', N'89 Le Dai Hanh, Q11, TP.HCM'),
('NV17', N'Tran Van I', N'Nhân viên bán hàng', '0912345617', 8000000, N'Nam', N'23 Hoang Sa, Q1, TP.HCM'),
('NV18', N'Le Thi H', N'Nhân viên kỹ thuật', '0912345618', 9000000, N'Nữ', N'56 Truong Sa, Q3, TP.HCM'),
('NV19', N'Pham Van G', N'Quản lý', '0912345619', 15000000, N'Nam', N'78 Dien Bien Phu, Q3, TP.HCM'),
('NV20', N'Hoang Thi F', N'Nhân viên bán hàng', '0912345620', 8000000, N'Nữ', N'12 Pasteur, Q1, TP.HCM');

-- Dữ liệu cho bảng NhaCungCap
INSERT INTO NhaCungCap (ma_nha_cung_cap, ten, dia_chi, email, quoc_gia) VALUES
('NCC01', N'Honda Việt Nam', N'KCN Phuc Dien, Ha Noi', 'honda.vn@gmail.com', N'Việt Nam'),
('NCC02', N'Yamaha Việt Nam', N'KCN Long Duc, Dong Nai', 'yamaha.vn@gmail.com', N'Việt Nam'),
('NCC03', N'VinFast', N'KCN Dinh Vu, Hai Phong', 'vinfast@gmail.com', N'Việt Nam'),
('NCC04', N'Suzuki Việt Nam', N'KCN Bien Hoa, Dong Nai', 'suzuki.vn@gmail.com', N'Việt Nam'),
('NCC05', N'Công ty Phụ tùng ABC', N'123 Nguyen Van Cu, Q5, TP.HCM', 'abc@gmail.com', N'Việt Nam'),
('NCC06', N'Công ty Phụ tùng XYZ', N'456 Le Loi, Q1, TP.HCM', 'xyz@gmail.com', N'Việt Nam'),
('NCC07', N'Honda Nhật Bản', N'Tokyo, Japan', 'honda.jp@gmail.com', N'Nhật Bản'),
('NCC08', N'Yamaha Nhật Bản', N'Osaka, Japan', 'yamaha.jp@gmail.com', N'Nhật Bản'),
('NCC09', N'Michelin Việt Nam', N'KCN Tan Binh, TP.HCM', 'michelin.vn@gmail.com', N'Việt Nam'),
('NCC10', N'GS Battery', N'KCN Tan Thuan, TP.HCM', 'gsbattery@gmail.com', N'Việt Nam'),
('NCC11', N'Công ty Phụ tùng DEF', N'789 Tran Hung Dao, Q5, TP.HCM', 'def@gmail.com', N'Việt Nam'),
('NCC12', N'Công ty Phụ tùng GHI', N'234 Nguyen Trai, Q3, TP.HCM', 'ghi@gmail.com', N'Việt Nam'),
('NCC13', N'Suzuki Nhật Bản', N'Nagoya, Japan', 'suzuki.jp@gmail.com', N'Nhật Bản'),
('NCC14', N'Công ty Phụ tùng JKL', N'567 Vo Van Tan, Q3, TP.HCM', 'jkl@gmail.com', N'Việt Nam'),
('NCC15', N'Công ty Phụ tùng MNO', N'890 Ly Thuong Kiet, Q10, TP.HCM', 'mno@gmail.com', N'Việt Nam'),
('NCC16', N'Công ty Phụ tùng PQR', N'123 Pham Van Dong, Go Vap, TP.HCM', 'pqr@gmail.com', N'Việt Nam'),
('NCC17', N'Công ty Phụ tùng STU', N'456 Nguyen Thi Minh Khai, Q1, TP.HCM', 'stu@gmail.com', N'Việt Nam'),
('NCC18', N'Công ty Phụ tùng VWX', N'789 Le Thanh Ton, Q1, TP.HCM', 'vwx@gmail.com', N'Việt Nam'),
('NCC19', N'Công ty Phụ tùng YZA', N'234 Cach Mang Thang Tam, Q3, TP.HCM', 'yza@gmail.com', N'Việt Nam'),
('NCC20', N'Công ty Phụ tùng BCD', N'567 Nguyen Hue, Q1, TP.HCM', 'bcd@gmail.com', N'Việt Nam');

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
INSERT INTO PhuTung (ma_phu_tung, chat_lieu) VALUES
('SP01', N'Cao su'),
('SP02', N'Kim loại'),
('SP03', N'Nhựa'),
('SP04', N'Thép'),
('SP05', N'Cao su'),
('SP06', N'Kim loại'),
('SP07', N'Nhựa'),
('SP08', N'Thép'),
('SP09', N'Cao su'),
('SP10', N'Kim loại'),
('SP11', N'Nhựa'),
('SP12', N'Thép'),
('SP13', N'Cao su'),
('SP14', N'Kim loại'),
('SP15', N'Nhựa'),
('SP16', N'Thép'),
('SP17', N'Cao su'),
('SP18', N'Kim loại'),
('SP19', N'Nhựa'),
('SP20', N'Thép');

-- Dữ liệu cho bảng HoaDonBan
INSERT INTO HoaDonBan (ma_hoa_don_ban, ma_khach_hang, ma_nhan_vien, ngay_xuat, tong_tien, trang_thai) VALUES
('HDB01', 'KH01', 'NV01', '2025-02-01', 55000000, N'Hoàn thành'),
('HDB02', 'KH02', 'NV02', '2025-02-02', 48000000, N'Hoàn thành'),
('HDB03', 'KH03', 'NV03', '2025-02-03', 22000000, N'Hoàn thành'),
('HDB04', 'KH04', 'NV04', '2025-02-04', 40000000, N'Hoàn thành'),
('HDB05', 'KH05', 'NV05', '2025-02-05', 500000, N'Hoàn thành'),
('HDB06', 'KH06', 'NV06', '2025-02-06', 2000000, N'Hoàn thành'),
('HDB07', 'KH07', 'NV07', '2025-02-07', 300000, N'Hoàn thành'),
('HDB08', 'KH08', 'NV08', '2025-02-08', 600000, N'Hoàn thành'),
('HDB09', 'KH09', 'NV09', '2025-02-09', 90000000, N'Hoàn thành'),
('HDB10', 'KH10', 'NV10', '2025-02-10', 20000000, N'Hoàn thành'),
('HDB11', 'KH11', 'NV11', '2025-02-11', 50000000, N'Hoàn thành'),
('HDB12', 'KH12', 'NV12', '2025-02-12', 5000000, N'Hoàn thành'),
('HDB13', 'KH13', 'NV13', '2025-02-13', 100000, N'Hoàn thành'),
('HDB14', 'KH14', 'NV14', '2025-02-14', 800000, N'Hoàn thành'),
('HDB15', 'KH15', 'NV15', '2025-02-15', 400000, N'Hoàn thành'),
('HDB16', 'KH16', 'NV16', '2025-02-16', 700000, N'Hoàn thành'),
('HDB17', 'KH17', 'NV17', '2025-02-17', 35000000, N'Hoàn thành'),
('HDB18', 'KH18', 'NV18', '2025-02-18', 70000000, N'Hoàn thành'),
('HDB19', 'KH19', 'NV19', '2025-02-19', 48000000, N'Hoàn thành'),
('HDB20', 'KH20', 'NV20', '2025-02-20', 1000000, N'Hoàn thành');

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

-- Dữ liệu cho bảng TaiKhoan
INSERT INTO TaiKhoan (ma_nhan_vien, ten_tai_khoan, mat_khau) VALUES
('NV01', N'admin', 'admin123'),
('NV02', N'lethanh', 'thanh456'),
('NV03', N'ngocanh', 'anh789'),
('NV04', N'tranminh', 'minh321');