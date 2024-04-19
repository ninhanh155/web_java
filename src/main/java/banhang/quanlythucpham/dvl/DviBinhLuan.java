package banhang.quanlythucpham.dvl;

import java.util.List;

import banhang.quanlythucpham.tdl.BinhLuan;

// Giao diện hàm chức năng của lớp Dịch Vụ
public interface DviBinhLuan 
{
    List<BinhLuan> dsBinhLuan();// getAllThucThe();

    List<BinhLuan> dsBinhLuan_thuoc_SanPham(int idSanPham);
    // Đọc dữ liệu của một bản ghi
    BinhLuan tìmBinhLuanTheoMa(int id);// getThucTheById(long id);

    // Lưu dữ liệu của 1 bản ghi vào db
    void lưuBinhLuan(BinhLuan bl);

    // Xoá 1 dòng bản ghi dữ liệu
    void xóaBinhLuan(int id);
}