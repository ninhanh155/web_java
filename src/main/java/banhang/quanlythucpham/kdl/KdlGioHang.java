package banhang.quanlythucpham.kdl;


import org.springframework.data.jpa.repository.JpaRepository;

import banhang.quanlythucpham.tdl.GioHang;

public interface KdlGioHang extends JpaRepository<GioHang, Integer>
{

}