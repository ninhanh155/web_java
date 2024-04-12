package banhang.quanlythucpham.dvl;


import java.util.List;

import banhang.quanlythucpham.tdl.SanPham;

// Giao diện hàm chức năng của lớp Dịch Vụ
public interface DviSanPham 
{
    // Đọc dữ liệu nhiều bản ghi // getAllThucThe();
    List<SanPham> dsSanPham();
    List<SanPham> duyệtSanPham();

    // Đọc dữ liệu của một bản ghi
    SanPham tìmSanPhamTheoId(int id);// getThucTheById( int id );
    SanPham xemSanPham( int id );

    // Lưu dữ liệu của 1 bản ghi vào db (thêm mới/cập nhật)
    void lưuSanPham( SanPham dl );

    // Xoá 1 dòng bản ghi dữ liệu
    void xóaSanPham(int id);

    // @todo
    // pagination
}