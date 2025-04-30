-- Create NhanVien table
CREATE TABLE NhanVien (
    MaNV VARCHAR(10) PRIMARY KEY,
    TenNV NVARCHAR(100) NOT NULL,
    SoDienThoai VARCHAR(10) NOT NULL,
    DiaChi NVARCHAR(200),
    ChucVu NVARCHAR(50) NOT NULL,
    Luong DECIMAL(12,0) NOT NULL,
    CONSTRAINT CK_MaNV CHECK (MaNV LIKE 'NV%'),
    CONSTRAINT CK_SoDienThoai CHECK (SoDienThoai LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
    CONSTRAINT CK_Luong CHECK (Luong >= 0)
);

-- Insert sample data
INSERT INTO NhanVien (MaNV, TenNV, SoDienThoai, DiaChi, ChucVu, Luong)
VALUES 
    ('NV01', N'Nguyễn Văn An', '0123456789', N'123 Nguyễn Văn Cừ, Q.5, TP.HCM', N'Quản lý', 15000000),
    ('NV02', N'Trần Thị Bình', '0987654321', N'456 Lê Lợi, Q.1, TP.HCM', N'Nhân viên bán hàng', 8000000),
    ('NV03', N'Lê Văn Cường', '0369852147', N'789 Võ Văn Tần, Q.3, TP.HCM', N'Nhân viên kỹ thuật', 10000000),
    ('NV04', N'Phạm Thị Dung', '0741852963', N'321 Nguyễn Thị Minh Khai, Q.1, TP.HCM', N'Nhân viên bán hàng', 8000000),
    ('NV05', N'Hoàng Văn Em', '0258963147', N'654 Điện Biên Phủ, Q.Bình Thạnh, TP.HCM', N'Nhân viên kỹ thuật', 10000000); 