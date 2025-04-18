﻿use CuaHangBanXeMay;
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
INSERT INTO SanPham (ma_spham, ten_spham, xuat_xu, so_luong_ton, gia_ban, gia_nhap, rang_thai, ma_loai) VALUES
('SP01', 'Honda Air Blade 150', 'Nhật Bản', 10, 55000000, 50000000, 'Còn hàng', 'LSP01'),
('SP02', 'Yamaha Exciter 155', 'Nhật Bản', 15, 48000000, 43000000, 'Còn hàng', 'LSP03'),
('SP03', 'Honda Wave Alpha', 'Việt Nam', 20, 22000000, 20000000, 'Còn hàng', 'LSP02'),
('SP04', 'VinFast Klara', 'Việt Nam', 8, 40000000, 36000000, 'Còn hàng', 'LSP04'),
('SP05', 'Bánh xe Honda', 'Việt Nam', 50, 500000, 400000, 'Còn hàng', 'LSP05'),
('SP06', 'Động cơ Yamaha', 'Nhật Bản', 30, 2000000, 1800000, 'Còn hàng', 'LSP06'),
('SP07', 'Đèn LED xe máy', 'Trung Quốc', 40, 300000, 250000, 'Còn hàng', 'LSP07'),
('SP08', 'Phanh đĩa trước', 'Việt Nam', 25, 600000, 500000, 'Còn hàng', 'LSP08'),
('SP09', 'Honda SH 150i', 'Nhật Bản', 5, 90000000, 85000000, 'Còn hàng', 'LSP09'),
('SP10', 'Yamaha Sirius', 'Việt Nam', 18, 20000000, 18000000, 'Còn hàng', 'LSP10'),
('SP11', 'Suzuki Raider', 'Nhật Bản', 12, 50000000, 45000000, 'Còn hàng', 'LSP11'),
('SP12', 'Xe điện trẻ em Mini', 'Trung Quốc', 10, 5000000, 4500000, 'Còn hàng', 'LSP12'),
('SP13', 'Gương chiếu hậu', 'Việt Nam', 60, 100000, 80000, 'Còn hàng', 'LSP13'),
('SP14', 'Lốp Michelin', 'Pháp', 35, 800000, 700000, 'Còn hàng', 'LSP14'),
('SP15', 'Ắc quy GS', 'Việt Nam', 20, 400000, 350000, 'Còn hàng', 'LSP15'),
('SP16', 'Yên xe cao cấp', 'Việt Nam', 15, 700000, 600000, 'Còn hàng', 'LSP16'),
('SP17', 'Honda Vision', 'Nhật Bản', 10, 35000000, 32000000, 'Còn hàng', 'LSP01'),
('SP18', 'Yamaha R15', 'Nhật Bản', 7, 70000000, 65000000, 'Còn hàng', 'LSP03'),
('SP19', 'Honda Winner X', 'Việt Nam', 9, 48000000, 43000000, 'Còn hàng', 'LSP03'),
('SP20', 'Phụ tùng ống xả', 'Trung Quốc', 25, 1000000, 900000, 'Còn hàng', 'LSP20');

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
INSERT INTO NhanVien (ma_nhan_vien, ten, chuc_vu, so_dien_thoai, username, password, muc_luong, gioi_tinh, dia_chi) VALUES
('NV01', 'Nguyen Van X', 'Quản lý', '0912345601', 'nvx', 'pass123', 15000000, 'Nam', '123 Nguyen Hue, Q1, TP.HCM'),
('NV02', 'Tran Thi Y', 'Nhân viên bán hàng', '0912345602', 'tty', 'pass123', 8000000, 'Nữ', '45 Tran Hung Dao, Q5, TP.HCM'),
('NV03', 'Le Van Z', 'Nhân viên kỹ thuật', '0912345603', 'lvz', 'pass123', 9000000, 'Nam', '78 Nguyen Trai, Q3, TP.HCM'),
('NV04', 'Pham Thi W', 'Kế toán', '0912345604', 'ptw', 'pass123', 10000000, 'Nữ', '12 Vo Van Tan, Q3, TP.HCM'),
('NV05', 'Hoang Van V', 'Nhân viên bán hàng', '0912345605', 'hvv', 'pass123', 8000000, 'Nam', '56 Ly Thuong Kiet, Q10, TP.HCM'),
('NV06', 'Nguyen Thi U', 'Nhân viên kỹ thuật', '0912345606', 'ntu', 'pass123', 9000000, 'Nữ', '89 Pham Van Dong, Go Vap, TP.HCM'),
('NV07', 'Tran Van T', 'Quản lý', '0912345607', 'tvt', 'pass123', 15000000, 'Nam', '23 Nguyen Thi Minh Khai, Q1, TP.HCM'),
('NV08', 'Le Thi S', 'Nhân viên bán hàng', '0912345608', 'lts', 'pass123', 8000000, 'Nữ', '67 Le Thanh Ton, Q1, TP.HCM'),
('NV09', 'Pham Van R', 'Nhân viên kỹ thuật', '0912345609', 'pvr', 'pass123', 9000000, 'Nam', '90 Cach Mang Thang Tam, Q3, TP.HCM'),
('NV10', 'Hoang Thi Q', 'Kế toán', '0912345610', 'htq', 'pass123', 10000000, 'Nữ', '34 Nguyen Hue, Q1, TP.HCM'),
('NV11', 'Nguyen Van P', 'Nhân viên bán hàng', '0912345611', 'nvp', 'pass123', 8000000, 'Nam', '56 Tran Phu, Q5, TP.HCM'),
('NV12', 'Tran Thi O', 'Nhân viên kỹ thuật', '0912345612', 'tto', 'pass123', 9000000, 'Nữ', '78 Hai Ba Trung, Q1, TP.HCM'),
('NV13', 'Le Van N', 'Quản lý', '0912345613', 'lvn', 'pass123', 15000000, 'Nam', '12 Ton Duc Thang, Q1, TP.HCM'),
('NV14', 'Pham Thi M', 'Nhân viên bán hàng', '0912345614', 'ptm', 'pass123', 8000000, 'Nữ', '45 Ly Tu Trong, Q1, TP.HCM'),
('NV15', 'Hoang Van L', 'Nhân viên kỹ thuật', '0912345615', 'hvl', 'pass123', 9000000, 'Nam', '67 Nguyen Dinh Chieu, Q3, TP.HCM'),
('NV16', 'Nguyen Thi K', 'Kế toán', '0912345616', 'ntk', 'pass123', 10000000, 'Nữ', '89 Le Dai Hanh, Q11, TP.HCM'),
('NV17', 'Tran Van I', 'Nhân viên bán hàng', '0912345617', 'tvi', 'pass123', 8000000, 'Nam', '23 Hoang Sa, Q1, TP.HCM'),
('NV18', 'Le Thi H', 'Nhân viên kỹ thuật', '0912345618', 'lth', 'pass123', 9000000, 'Nữ', '56 Truong Sa, Q3, TP.HCM'),
('NV19', 'Pham Van G', 'Quản lý', '0912345619', 'pvg', 'pass123', 15000000, 'Nam', '78 Dien Bien Phu, Q3, TP.HCM'),
('NV20', 'Hoang Thi F', 'Nhân viên bán hàng', '0912345620', 'htf', 'pass123', 8000000, 'Nữ', '12 Pasteur, Q1, TP.HCM');

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
INSERT INTO ChiTietPhieuNhap (ma_cht_hoa_don_nhap, ma_phieu_nhap, ma_spham, thanh_tien, so_luong_nhap) VALUES
('CTPN01', 'PN01', 'SP01', 500000000, 10),
('CTPN02', 'PN02', 'SP02', 430000000, 10),
('CTPN03', 'PN03', 'SP04', 360000000, 10),
('CTPN04', 'PN04', 'SP11', 450000000, 10),
('CTPN05', 'PN05', 'SP05', 20000000, 50),
('CTPN06', 'PN06', 'SP06', 18000000, 10),
('CTPN07', 'PN07', 'SP09', 850000000, 10),
('CTPN08', 'PN08', 'SP18', 650000000, 10),
('CTPN09', 'PN09', 'SP14', 35000000, 50),
('CTPN10', 'PN10', 'SP15', 17500000, 50),
('CTPN11', 'PN11', 'SP17', 320000000, 10),
('CTPN12', 'PN12', 'SP02', 430000000, 10),
('CTPN13', 'PN13', 'SP04', 360000000, 10),
('CTPN14', 'PN14', 'SP11', 450000000, 10),
('CTPN15', 'PN15', 'SP05', 20000000, 50),
('CTPN16', 'PN16', 'SP06', 18000000, 10),
('CTPN17', 'PN17', 'SP09', 850000000, 10),
('CTPN18', 'PN18', 'SP18', 650000000, 10),
('CTPN19', 'PN19', 'SP14', 35000000, 50),
('CTPN20', 'PN20', 'SP15', 17500000, 50);

-- Dữ liệu cho bảng XeMay
INSERT INTO XeMay (ma_xe, dung_tich_xi_lanh, bao_hanh, nam_san_xuat) VALUES
('XM01', 150, 24, 2023),
('XM02', 155, 24, 2023),
('XM03', 110, 12, 2022),
('XM04', 0, 12, 2023),
('XM05', 150, 24, 2024),
('XM06', 110, 12, 2022),
('XM07', 155, 24, 2023),
('XM08', 150, 24, 2024),
('XM09', 155, 24, 2023),
('XM10', 0, 12, 2023),
('XM11', 150, 24, 2023),
('XM12', 155, 24, 2023),
('XM13', 110, 12, 2022),
('XM14', 0, 12, 2023),
('XM15', 150, 24, 2024),
('XM16', 110, 12, 2022),
('XM17', 155, 24, 2023),
('XM18', 150, 24, 2024),
('XM19', 155, 24, 2023),
('XM20', 0, 12, 2023);

-- Dữ liệu cho bảng PhuTung
INSERT INTO PhuTung (ma_phu_tung, chat_lieu) VALUES
('PT01', 'Cao su'),
('PT02', 'Kim loại'),
('PT03', 'Nhựa'),
('PT04', 'Thép'),
('PT05', 'Cao su'),
('PT06', 'Kim loại'),
('PT07', 'Nhựa'),
('PT08', 'Thép'),
('PT09', 'Cao su'),
('PT10', 'Kim loại'),
('PT11', 'Nhựa'),
('PT12', 'Thép'),
('PT13', 'Cao su'),
('PT14', 'Kim loại'),
('PT15', 'Nhựa'),
('PT16', 'Thép'),
('PT17', 'Cao su'),
('PT18', 'Kim loại'),
('PT19', 'Nhựa'),
('PT20', 'Thép');

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
INSERT INTO ChiTietHoaDonBan (ma_cht_hoa_don_ban, ma_hoa_don_ban, ma_spham, so_luong, don_gia) VALUES
('CTHDB01', 'HDB01', 'SP01', 1, 55000000),
('CTHDB02', 'HDB02', 'SP02', 1, 48000000),
('CTHDB03', 'HDB03', 'SP03', 1, 22000000),
('CTHDB04', 'HDB04', 'SP04', 1, 40000000),
('CTHDB05', 'HDB05', 'SP05', 1, 500000),
('CTHDB06', 'HDB06', 'SP06', 1, 2000000),
('CTHDB07', 'HDB07', 'SP07', 1, 300000),
('CTHDB08', 'HDB08', 'SP08', 1, 600000),
('CTHDB09', 'HDB09', 'SP09', 1, 90000000),
('CTHDB10', 'HDB10', 'SP10', 1, 20000000),
('CTHDB11', 'HDB11', 'SP11', 1, 50000000),
('CTHDB12', 'HDB12', 'SP12', 1, 5000000),
('CTHDB13', 'HDB13', 'SP13', 1, 100000),
('CTHDB14', 'HDB14', 'SP14', 1, 800000),
('CTHDB15', 'HDB15', 'SP15', 1, 400000),
('CTHDB16', 'HDB16', 'SP16', 1, 700000),
('CTHDB17', 'HDB17', 'SP17', 1, 35000000),
('CTHDB18', 'HDB18', 'SP18', 1, 70000000),
('CTHDB19', 'HDB19', 'SP19', 1, 48000000),
('CTHDB20', 'HDB20', 'SP20', 1, 1000000);

-- Dữ liệu cho bảng BaoHanh
INSERT INTO BaoHanh (ma_bao_hanh, ma_xe, ngay_bat_dau, ngay_ket_thuc) VALUES
('BH01', 'XM01', '2025-02-01', '2027-02-01'),
('BH02', 'XM02', '2025-02-02', '2027-02-02'),
('BH03', 'XM03', '2025-02-03', '2026-02-03'),
('BH04', 'XM04', '2025-02-04', '2026-02-04'),
('BH05', 'XM05', '2025-02-05', '2027-02-05'),
('BH06', 'XM06', '2025-02-06', '2026-02-06'),
('BH07', 'XM07', '2025-02-07', '2027-02-07'),
('BH08', 'XM08', '2025-02-08', '2027-02-08'),
('BH09', 'XM09', '2025-02-09', '2027-02-09'),
('BH10', 'XM10', '2025-02-10', '2026-02-10'),
('BH11', 'XM11', '2025-02-11', '2027-02-11'),
('BH12', 'XM12', '2025-02-12', '2027-02-12'),
('BH13', 'XM13', '2025-02-13', '2026-02-13'),
('BH14', 'XM14', '2025-02-14', '2026-02-14'),
('BH15', 'XM15', '2025-02-15', '2027-02-15'),
('BH16', 'XM16', '2025-02-16', '2026-02-16'),
('BH17', 'XM17', '2025-02-17', '2027-02-17'),
('BH18', 'XM18', '2025-02-18', '2027-02-18'),
('BH19', 'XM19', '2025 imun-02-19', '2027-02-19'),
('BH20', 'XM20', '2025-02-20', '2026-02-20');