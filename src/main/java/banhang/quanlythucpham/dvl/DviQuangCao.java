package banhang.quanlythucpham.dvl;



// package tên_công_ty.tên_đề_tài.tdl;

import java.util.List;

import banhang.quanlythucpham.tdl.QuangCao;

// Giao diện hàm chức năng của lớp Dịch Vụ
public interface DviQuangCao 
{
    // Đọc dữ liệu nhiều bản ghi // getAllThucThe();
    List<QuangCao> dsQuangCao();
    List<QuangCao> duyệtQuangCao();

    // Đọc dữ liệu của một bản ghi
    QuangCao tìmQuangCaoTheoId(int id);// getThucTheById( int id );
    QuangCao xemQuangCao( int id );

    // Lưu dữ liệu của 1 bản ghi vào db (thêm mới/cập nhật)
    void lưuQuangCao( QuangCao dl );

    // Xoá 1 dòng bản ghi dữ liệu
    void xóaQuangCao(int id);

    // @todo
    // pagination
}