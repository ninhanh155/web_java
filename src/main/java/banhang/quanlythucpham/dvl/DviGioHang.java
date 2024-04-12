package banhang.quanlythucpham.dvl;


import java.util.Collection;
import java.util.List;

import banhang.quanlythucpham.tdl.GioHang;

// Giao diện hàm chức năng của lớp Dịch Vụ
public interface DviGioHang 
{
    // Đọc dữ liệu nhiều bản ghi // getAllThucThe();
    List<GioHang> dsGioHang();
    // public void add(CartItem item);
    public void xoatungsanpham(int id);
    GioHang sua(int proId , int qty);
    public void xoatatca();
    Collection<GioHang> getdlgiohang();
    int getSoLuong();
    double getGia();
}