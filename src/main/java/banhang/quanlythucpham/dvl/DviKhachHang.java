package banhang.quanlythucpham.dvl;

import java.util.List;

import banhang.quanlythucpham.tdl.KhachHang;

// Giao diện hàm chức năng của lớp Dịch Vụ
public interface DviKhachHang 
{
    // Đọc dữ liệu nhiều bản ghi // getAllThucThe();
    List<KhachHang> dsKhachHang();
    List<KhachHang> duyệtKhachHang();

    // Đọc dữ liệu của một bản ghi
    KhachHang tìmKhachHangTheoId(int id);// getThucTheById( int id );
    KhachHang xemKhachHang( int id );
    KhachHang tìmKhachHangTheoEmail(String mail);

    // Boolean tồnTạiTênĐăngNhập(String tdn);
    Boolean tồnTạiEmail(String mail);
    // Lưu dữ liệu của 1 bản ghi vào db (thêm mới/cập nhật)
    void lưuKhachHang( KhachHang dl );

    // Xoá 1 dòng bản ghi dữ liệu
    void xóaKhachHang(int id);

    // @todo
    // pagination
}