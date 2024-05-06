package banhang.quanlythucpham.dvl;

import java.util.List;

import banhang.quanlythucpham.tdl.GioHang;

public interface DviGioHang {
    // Đọc dữ liệu nhiều bản ghi // getAllThucThe();
    List<GioHang> dsGioHang();

    List<GioHang> dsGioHangByUser(int id_User);

    void deleteGioHangById(int id);

    void deleteAllGioHangByUserId(int id);

    GioHang sua(int proId, int qty);

    void luuGioHang(GioHang gh);

}