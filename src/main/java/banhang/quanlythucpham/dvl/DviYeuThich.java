package banhang.quanlythucpham.dvl;


import java.util.List;

import banhang.quanlythucpham.tdl.SanPham;
import banhang.quanlythucpham.tdl.YeuThich;

// Giao diện hàm chức năng của lớp Dịch Vụ
public interface DviYeuThich 
{
    List<SanPham> dsYeuThich(int userId);
    YeuThich tìmYeuThichTheoId(int id);

    void lưuYeuThich(YeuThich item);

    void xóaYeuThich(int id_product ,  int id_user);

    boolean kiemtra(int id_product, int id_user);
}