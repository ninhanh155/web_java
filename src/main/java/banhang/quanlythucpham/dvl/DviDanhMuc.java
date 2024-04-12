package banhang.quanlythucpham.dvl;

import java.util.List;

import banhang.quanlythucpham.tdl.DanhMuc;

// Giao diện hàm chức năng của lớp Dịch Vụ
public interface DviDanhMuc 
{
    // Đọc dữ liệu nhiều bản ghi // getAllThucThe();
    List<DanhMuc> dsDanhMuc();
    List<DanhMuc> duyệtDanhMuc();

    // Đọc dữ liệu của một bản ghi
    DanhMuc tìmDanhMucTheoId(int id);// getThucTheById( int id );
    DanhMuc xemDanhMuc( int id );

    // Lưu dữ liệu của 1 bản ghi vào db (thêm mới/cập nhật)
    void lưuDanhMuc( DanhMuc dl );

    // Xoá 1 dòng bản ghi dữ liệu
    void xóaDanhMuc(int id);

    // @todo
    // pagination
}
