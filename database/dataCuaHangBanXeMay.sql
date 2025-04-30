-- Insert data into LoaiSanPham
INSERT INTO LoaiSanPham (ma_loai, ten_loai) VALUES
('LSP01', 'Xe May'),
('LSP02', 'Phu Tung'),
('LSP03', 'Xe Dien'),
('LSP04', 'Phu Kien'),
('LSP05', 'Xe Moto'),
('LSP06', 'Lop Xe'),
('LSP07', 'Ac Quy'),
('LSP08', 'Dau Nhot'),
('LSP09', 'Den Xe'),
('LSP10', 'Gương Xe'),
('LSP11', 'Bugi'),
('LSP12', 'Phanh Xe'),
('LSP13', 'Xich Xe'),
('LSP14', 'Binh Xang'),
('LSP15', 'Yen Xe');

-- Insert data into SanPham
INSERT INTO SanPham (ma_san_pham, ma_loai, ten_san_pham, xuat_xu, so_luong_ton, gia_ban, gia_nhap, trang_thai, mau_sac, trong_luong, bao_hanh, thuong_hieu) VALUES
('SP001', 'LSP01', 'Honda Wave Alpha', 'Viet Nam', 20, 20000000, 18000000, 'active', 'Do', '100kg', '24 thang', 'Honda'),
('SP002', 'LSP01', 'Yamaha Sirius', 'Viet Nam', 15, 22000000, 20000000, 'active', 'Xanh', '110kg', '24 thang', 'Yamaha'),
('SP003', 'LSP03', 'VinFast Klara', 'Viet Nam', 10, 35000000, 32000000, 'active', 'Trang', '95kg', '36 thang', 'VinFast'),
('SP004', 'LSP05', 'Honda CB150R', 'Thai Lan', 5, 70000000, 65000000, 'active', 'Den', '135kg', '24 thang', 'Honda'),
('SP005', 'LSP02', 'Lop Michelin 80/90', 'Phap', 50, 500000, 450000, 'active', 'Den', '2kg', '12 thang', 'Michelin'),
('SP006', 'LSP06', 'Lop Dunlop 90/90', 'Nhat Ban', 40, 600000, 550000, 'active', 'Den', '2.5kg', '12 thang', 'Dunlop'),
('SP007', 'LSP07', 'Ac Quy GS 12V', 'Viet Nam', 30, 800000, 750000, 'active', 'Xam', '4kg', '12 thang', 'GS'),
('SP008', 'LSP08', 'Dau Nhot Castrol 1L', 'Anh', 60, 150000, 130000, 'active', NULL, '1kg', '6 thang', 'Castrol'),
('SP009', 'LSP09', 'Den Pha LED', 'Trung Quoc', 25, 300000, 280000, 'active', 'Trang', '0.5kg', '6 thang', 'Osram'),
('SP010', 'LSP10', 'Guong Yamaha', 'Viet Nam', 100, 100000, 90000, 'active', 'Den', '0.3kg', '6 thang', 'Yamaha'),
('SP011', 'LSP11', 'Bugi NGK', 'Nhat Ban', 80, 50000, 45000, 'active', NULL, '0.05kg', '6 thang', 'NGK'),
('SP012', 'LSP12', 'Phanh Dia Nissin', 'Nhat Ban', 20, 1200000, 1100000, 'active', 'Bac', '1kg', '12 thang', 'Nissin'),
('SP013', 'LSP13', 'Xich DID', 'Nhat Ban', 35, 400000, 380000, 'active', 'Bac', '0.8kg', '12 thang', 'DID'),
('SP014', 'LSP14', 'Binh Xang Honda', 'Viet Nam', 10, 1500000, 1400000, 'active', 'Bac', '3kg', '12 thang', 'Honda'),
('SP015', 'LSP15', 'Yen Xe Wave', 'Viet Nam', 15, 700000, 650000, 'active', 'Den', '2kg', '12 thang', 'Honda');

-- Insert data into KhachHang
INSERT INTO KhachHang (ma_khach_hang, ten, sdt, email, dia_chi) VALUES
('KH001', 'Nguyen Van An', '0901234567', 'an.nguyen@gmail.com', '123 Le Loi, Q1, TPHCM'),
('KH002', 'Tran Thi Bich', '0912345678', 'bich.tran@gmail.com', '45 Nguyen Hue, Q1, TPHCM'),
('KH003', 'Le Van Cuong', '0923456789', NULL, '67 Tran Hung Dao, Q5, TPHCM'),
('KH004', 'Pham Thi Dung', '0934567890', 'dung.pham@gmail.com', '89 Vo Van Tan, Q3, TPHCM'),
('KH005', 'Hoang Van Em', '0945678901', NULL, '12 Ly Tu Trong, Q1, TPHCM'),
('KH006', 'Vo Thi Phuong', '0956789012', 'phuong.vo@gmail.com', '34 Nguyen Thi Minh Khai, Q1, TPHCM'),
('KH007', 'Bui Van Giang', '0967890123', NULL, '56 Hoang Dieu, Q4, TPHCM'),
('KH008', 'Dang Thi Hoa', '0978901234', 'hoa.dang@gmail.com', '78 Cach Mang Thang 8, Q3, TPHCM'),
('KH009', 'Nguyen Van Hung', '0989012345', NULL, '90 Nguyen Trai, Q5, TPHCM'),
('KH010', 'Tran Van Khanh', '0990123456', 'khanh.tran@gmail.com', '112 Le Dai Hanh, Q11, TPHCM'),
('KH011', 'Le Thi Lan', '0901239876', NULL, '134 Nguyen Van Cu, Q5, TPHCM'),
('KH012', 'Pham Van Minh', '0912348765', 'minh.pham@gmail.com', '156 Ton Duc Thang, Q1, TPHCM'),
('KH013', 'Hoang Thi Ngoc', '0923457654', NULL, '178 Dien Bien Phu, Q3, TPHCM'),
('KH014', 'Vo Van Phuc', '0934566543', 'phuc.vo@gmail.com', '200 Hai Ba Trung, Q1, TPHCM'),
('KH015', 'Bui Thi Quynh', '0945675432', NULL, '222 Ly Thai To, Q10, TPHCM');

-- Insert data into NhanVien
INSERT INTO NhanVien (ma_nhan_vien, ten, chuc_vu, so_dien_thoai, username, password, muc_luong, gioi_tinh, dia_chi) VALUES
('NV001', 'Nguyen Van Long', 'Quan Ly', '0901112233', 'longnv', 'hashed_password_1', 15000000, 'Nam', '123 Nguyen Hue, Q1, TPHCM'),
('NV002', 'Tran Thi Mai', 'Nhan Vien Ban Hang', '0912223344', 'mainv', 'hashed_password_2', 8000000, 'Nu', '45 Tran Hung Dao, Q5, TPHCM'),
('NV003', 'Le Van Nam', 'Ky Thuat Vien', '0923334455', 'namnv', 'hashed_password_3', 10000000, 'Nam', '67 Vo Van Tan, Q3, TPHCM'),
('NV004', 'Pham Thi Oanh', 'Ke Toan', '0934445566', 'oanhnv', 'hashed_password_4', 12000000, 'Nu', '89 Ly Tu Trong, Q1, TPHCM'),
('NV005', 'Hoang Van Phu', 'Nhan Vien Ban Hang', '0945556677', 'phunv', 'hashed_password_5', 8000000, 'Nam', '12 Hoang Dieu, Q4, TPHCM'),
('NV006', 'Vo Thi Quy', 'Nhan Vien Ban Hang', '0956667788', 'quynv', 'hashed_password_6', 8000000, 'Nu', '34 Cach Mang Thang 8, Q3, TPHCM'),
('NV007', 'Bui Van Son', 'Ky Thuat Vien', '0967778899', 'sonnv', 'hashed_password_7', 10000000, 'Nam', '56 Nguyen Trai, Q5, TPHCM'),
('NV008', 'Dang Thi Thuy', 'Nhan Vien Ban Hang', '0978889900', 'thuynv', 'hashed_password_8', 8000000, 'Nu', '78 Le Dai Hanh, Q11, TPHCM'),
('NV009', 'Nguyen Van Tuan', 'Quan Ly Kho', '0989990011', 'tuannv', 'hashed_password_9', 11000000, 'Nam', '90 Dien Bien Phu, Q3, TPHCM'),
('NV010', 'Tran Van Vinh', 'Ky Thuat Vien', '0990001122', 'vinhnv', 'hashed_password_10', 10000000, 'Nam', '112 Hai Ba Trung, Q1, TPHCM'),
('NV011', 'Le Thi Yen', 'Nhan Vien Ban Hang', '0901113344', 'yennv', 'hashed_password_11', 8000000, 'Nu', '134 Ly Thai To, Q10, TPHCM'),
('NV012', 'Pham Van Duc', 'Ky Thuat Vien', '0912224455', 'ducnv', 'hashed_password_12', 10000000, 'Nam', '156 Nguyen Van Cu, Q5, TPHCM'),
('NV013', 'Hoang Thi Kim', 'Ke Toan', '0923335566', 'kimnv', 'hashed_password_13', 12000000, 'Nu', '178 Ton Duc Thang, Q1, TPHCM'),
('NV014', 'Vo Van Hao', 'Nhan Vien Ban Hang', '0934446677', 'haonv', 'hashed_password_14', 8000000, 'Nam', '200 Nguyen Thi Minh Khai, Q1, TPHCM'),
('NV015', 'Bui Thi Lan', 'Nhan Vien Ban Hang', '0945557788', 'lannv', 'hashed_password_15', 8000000, 'Nu', '222 Le Loi, Q1, TPHCM');

-- Insert data into NhaCungCap
INSERT INTO NhaCungCap (ma_nha_cung_cap, ten, dia_chi, email, quoc_gia) VALUES
('NCC01', 'Honda Viet Nam', 'KCN Phuc Dien, Vinh Phuc', 'contact@honda.vn', 'Viet Nam'),
('NCC02', 'Yamaha Viet Nam', 'KCN Nomura, Hai Phong', 'contact@yamaha.vn', 'Viet Nam'),
('NCC03', 'VinFast', 'KCN Dinh Vu, Hai Phong', 'contact@vinfast.vn', 'Viet Nam'),
('NCC04', 'Michelin Viet Nam', '123 Tran Hung Dao, Q1, TPHCM', 'contact@michelin.vn', 'Viet Nam'),
('NCC05', 'Dunlop Nhat Ban', 'Tokyo, Japan', 'contact@dunlop.jp', 'Nhat Ban'),
('NCC06', 'GS Viet Nam', 'KCN Long Thanh, Dong Nai', 'contact@gs.vn', 'Viet Nam'),
('NCC07', 'Castrol Anh', 'London, UK', 'contact@castrol.uk', 'Anh'),
('NCC08', 'Osram Trung Quoc', 'Shanghai, China', 'contact@osram.cn', 'Trung Quoc'),
('NCC09', 'NGK Nhat Ban', 'Nagoya, Japan', 'contact@ngk.jp', 'Nhat Ban'),
('NCC10', 'Nissin Nhat Ban', 'Tokyo, Japan', 'contact@nissin.jp', 'Nhat Ban'),
('NCC11', 'DID Nhat Ban', 'Osaka, Japan', 'contact@did.jp', 'Nhat Ban'),
('NCC12', 'Honda Thai Lan', 'Bangkok, Thailand', 'contact@honda.th', 'Thai Lan'),
('NCC13', 'Phu Tung Viet Nam', '456 Le Loi, Q1, TPHCM', 'contact@phutung.vn', 'Viet Nam'),
('NCC14', 'Ac Quy Viet Nam', 'KCN Tan Binh, TPHCM', 'contact@acquy.vn', 'Viet Nam'),
('NCC15', 'Den Xe Trung Quoc', 'Guangzhou, China', 'contact@denxe.cn', 'Trung Quoc');

-- Insert data into PhieuNhap
INSERT INTO PhieuNhap (ma_phieu_nhap, ma_nha_cung_cap, ngay_nhap, tong_tien) VALUES
('PN001', 'NCC01', '2025-01-01', 360000000),
('PN002', 'NCC02', '2025-01-02', 400000000),
('PN003', 'NCC03', '2025-01-03', 320000000),
('PN004', 'NCC04', '2025-01-04', 22500000),
('PN005', 'NCC05', '2025-01-05', 22000000),
('PN006', 'NCC06', '2025-01-06', 22500000),
('PN007', 'NCC07', '2025-01-07', 7800000),
('PN008', 'NCC08', '2025-01-08', 7000000),
('PN009', 'NCC09', '2025-01-09', 3600000),
('PN010', 'NCC10', '2025-01-10', 9000000),
('PN011', 'NCC11', '2025-01-11', 13300000),
('PN012', 'NCC12', '2025-01-12', 130000000),
('PN013', 'NCC13', '2025-01-13', 14000000),
('PN014', 'NCC14', '2025-01-14', 21000000),
('PN015', 'NCC15', '2025-01-15', 8400000);

-- Insert data into ChiTietPhieuNhap
INSERT INTO ChiTietPhieuNhap (ma, ma_phieu_nhap, ma_san_pham, so_luong_nhap, thanh_tien) VALUES
('CTPN001', 'PN001', 'SP001', 20, 360000000),
('CTPN002', 'PN002', 'SP002', 20, 400000000),
('CTPN003', 'PN003', 'SP003', 10, 320000000),
('CTPN004', 'PN004', 'SP005', 50, 22500000),
('CTPN005', 'PN005', 'SP006', 40, 22000000),
('CTPN006', 'PN006', 'SP007', 30, 22500000),
('CTPN007', 'PN007', 'SP008', 60, 7800000),
('CTPN008', 'PN008', 'SP009', 25, 7000000),
('CTPN009', 'PN009', 'SP011', 80, 3600000),
('CTPN010', 'PN010', 'SP010', 100, 9000000),
('CTPN011', 'PN011', 'SP013', 35, 13300000),
('CTPN012', 'PN012', 'SP004', 2, 130000000),
('CTPN013', 'PN013', 'SP014', 10, 14000000),
('CTPN014', 'PN014', 'SP007', 30, 22500000),
('CTPN015', 'PN015', 'SP009', 30, 8400000);

-- Insert data into XeMay
INSERT INTO XeMay (ma_xe, dung_tich_xi_lanh, nam_san_xuat) VALUES
('SP001', 110, 2024),
('SP002', 110, 2024),
('SP003', 0, 2024), -- Xe dien, dung tich xi lanh = 0
('SP004', 150, 2023),
('SP016', 125, 2024),
('SP017', 135, 2023),
('SP018', 150, 2024),
('SP019', 110, 2023),
('SP020', 0, 2024), -- Xe dien
('SP021', 250, 2023),
('SP022', 175, 2024),
('SP023', 110, 2023),
('SP024', 125, 2024),
('SP025', 150, 2023),
('SP026', 0, 2024); -- Xe dien

-- Insert data into PhuTung
INSERT INTO PhuTung (ma_phu_tung, chat_lieu) VALUES
('SP005', 'Cao Su'),
('SP006', 'Cao Su'),
('SP007', 'Kim Loai'),
('SP008', 'Dau'),
('SP009', 'Nhua'),
('SP010', 'Kim Loai'),
('SP011', 'Kim Loai'),
('SP012', 'Kim Loai'),
('SP013', 'Thep'),
('SP014', 'Kim Loai'),
('SP015', 'Da'),
('SP027', 'Nhua'),
('SP028', 'Cao Su'),
('SP029', 'Kim Loai'),
('SP030', 'Thep');

-- Insert data into HoaDonBan
INSERT INTO HoaDonBan (ma_hoa_don_ban, ma_khach_hang, ma_nhan_vien, ngay_xuat, tong_tien, trang_thai) VALUES
('HDB001', 'KH001', 'NV002', '2025-02-01', 20000000, 'completed'),
('HDB002', 'KH002', 'NV005', '2025-02-02', 22000000, 'completed'),
('HDB003', 'KH003', 'NV006', '2025-02-03', 500000, 'completed'),
('HDB004', 'KH004', 'NV008', '2025-02-04', 600000, 'completed'),
('HDB005', 'KH005', 'NV011', '2025-02-05', 800000, 'completed'),
('HDB006', 'KH006', 'NV014', '2025-02-06', 150000, 'completed'),
('HDB007', 'KH007', 'NV015', '2025-02-07', 300000, 'completed'),
('HDB008', 'KH008', 'NV002', '2025-02-08', 70000000, 'completed'),
('HDB009', 'KH009', 'NV005', '2025-02-09', 100000, 'completed'),
('HDB010', 'KH010', 'NV006', '2025-02-10', 1200000, 'completed'),
('HDB011', 'KH011', 'NV008', '2025-02-11', 400000, 'completed'),
('HDB012', 'KH012', 'NV011', '2025-02-12', 1500000, 'completed'),
('HDB013', 'KH013', 'NV014', '2025-02-13', 700000, 'completed'),
('HDB014', 'KH014', 'NV015', '2025-02-14', 50000, 'completed'),
('HDB015', 'KH015', 'NV002', '2025-02-15', 35000000, 'completed');

-- Insert data into ChiTietHoaDonBan
INSERT INTO ChiTietHoaDonBan (ma, ma_hoa_don_ban, ma_san_pham, so_luong, don_gia) VALUES
('CTHDB001', 'HDB001', 'SP001', 1, 20000000),
('CTHDB002', 'HDB002', 'SP002', 1, 22000000),
('CTHDB003', 'HDB003', 'SP005', 1, 500000),
('CTHDB004', 'HDB004', 'SP006', 1, 600000),
('CTHDB005', 'HDB005', 'SP007', 1, 800000),
('CTHDB006', 'HDB006', 'SP008', 1, 150000),
('CTHDB007', 'HDB007', 'SP009', 1, 300000),
('CTHDB008', 'HDB008', 'SP004', 1, 70000000),
('CTHDB009', 'HDB009', 'SP010', 1, 100000),
('CTHDB010', 'HDB010', 'SP012', 1, 1200000),
('CTHDB011', 'HDB011', 'SP013', 1, 400000),
('CTHDB012', 'HDB012', 'SP014', 1, 1500000),
('CTHDB013', 'HDB013', 'SP015', 1, 700000),
('CTHDB014', 'HDB014', 'SP011', 1, 50000),
('CTHDB015', 'HDB015', 'SP003', 1, 35000000);

-- Insert data into BaoHanh
INSERT INTO BaoHanh (ma_bao_hanh, ma_san_pham, ngay_bat_dau, ngay_ket_thuc) VALUES
('BH001', 'SP001', '2025-02-01', '2027-02-01'),
('BH002', 'SP002', '2025-02-02', '2027-02-02'),
('BH003', 'SP003', '2025-02-15', '2028-02-15'),
('BH004', 'SP004', '2025-02-08', '2027-02-08'),
('BH005', 'SP005', '2025-02-03', '2026-02-03'),
('BH006', 'SP006', '2025-02-04', '2026-02-04'),
('BH007', 'SP007', '2025-02-05', '2026-02-05'),
('BH008', 'SP008', '2025-02-06', '2025-08-06'),
('BH009', 'SP009', '2025-02-07', '2025-08-07'),
('BH010', 'SP010', '2025-02-09', '2025-08-09'),
('BH011', 'SP011', '2025-02-14', '2025-08-14'),
('BH012', 'SP012', '2025-02-10', '2026-02-10'),
('BH013', 'SP013', '2025-02-11', '2026-02-11'),
('BH014', 'SP014', '2025-02-12', '2026-02-12'),
('BH015', 'SP015', '2025-02-13', '2026-02-13');