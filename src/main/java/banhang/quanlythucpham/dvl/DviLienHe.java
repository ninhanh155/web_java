package banhang.quanlythucpham.dvl;

import java.util.List;

import banhang.quanlythucpham.tdl.LienHe;

// Giao diện hàm chức năng của lớp Dịch Vụ
public interface DviLienHe 
{
    // Đọc dữ liệu nhiều bản ghi // getAllThucThe();
    List<LienHe> dsLienHe();
    List<LienHe> duyệtLienHe();

    // Đọc dữ liệu của một bản ghi
    LienHe tìmLienHeTheoId(int id);// getThucTheById( int id );
    LienHe xemLienHe( int id );

    // Lưu dữ liệu của 1 bản ghi vào db (thêm mới/cập nhật)
    void lưuLienHe( LienHe dl );

    // Xoá 1 dòng bản ghi dữ liệu
    void xóaLienHe(int id);

}