package banhang.quanlythucpham.dvl;

import java.util.List;

import banhang.quanlythucpham.tdl.CaiDat;

// Giao diện hàm chức năng của lớp Dịch Vụ
public interface DviCaiDat 
{
    // Đọc dữ liệu nhiều bản ghi // getAllThucThe();
    List<CaiDat> dsCaiDat();
    List<CaiDat> duyệtCaiDat();

    // Đọc dữ liệu của một bản ghi
    CaiDat tìmCaiDatTheoId(int id);// getThucTheById( int id );
    CaiDat xemCaiDat( int id );

    // Lưu dữ liệu của 1 bản ghi vào db (thêm mới/cập nhật)
    void lưuCaiDat( CaiDat dl );

    // Xoá 1 dòng bản ghi dữ liệu
    void xóaCaiDat(int id);

    // @todo
    // pagination
}
