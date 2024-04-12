package banhang.quanlythucpham.dvl;


import java.util.List;

import banhang.quanlythucpham.tdl.NhanVien;

// Giao diện hàm chức năng của lớp Dịch Vụ
public interface DviNhanVien 
{
    // Đọc dữ liệu nhiều bản ghi // getAllThucThe();
    List<NhanVien> dsNhanVien();
    List<NhanVien> duyệtNhanVien();

    // Đọc dữ liệu của một bản ghi
    NhanVien tìmNhanVienTheoId(int id);// getThucTheById( int id );
    NhanVien xemNhanVien( int id );
    NhanVien tìmNhanVienTheoEmail(String mail);

    // Boolean tồnTạiTênĐăngNhập(String tdn);
    Boolean tồnTạiEmail(String mail);
    // Lưu dữ liệu của 1 bản ghi vào db (thêm mới/cập nhật)
    void lưuNhanVien( NhanVien dl );

    // Xoá 1 dòng bản ghi dữ liệu
    void xóaNhanVien(int id);

    // @todo
    // pagination
}
