package banhang.quanlythucpham.dvl;



// package tên_công_ty.tên_đề_tài.tdl;

import java.util.List;

import banhang.quanlythucpham.tdl.NhaSanXuat;

// Giao diện hàm chức năng của lớp Dịch Vụ
public interface DviNhaSanXuat 
{
    // Đọc dữ liệu nhiều bản ghi // getAllThucThe();
    List<NhaSanXuat> dsNhaSanXuat();
    List<NhaSanXuat> duyệtNhaSanXuat();

    // Đọc dữ liệu của một bản ghi
    NhaSanXuat tìmNhaSanXuatTheoId(int id);// getThucTheById( int id );
    NhaSanXuat xemNhaSanXuat( int id );

    // Lưu dữ liệu của 1 bản ghi vào db (thêm mới/cập nhật)
    void lưuNhaSanXuat( NhaSanXuat dl );

    // Xoá 1 dòng bản ghi dữ liệu
    void xóaNhaSanXuat(int id);

    // @todo
    // pagination
}