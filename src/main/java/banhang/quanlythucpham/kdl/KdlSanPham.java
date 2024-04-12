package banhang.quanlythucpham.kdl;


import org.springframework.data.jpa.repository.JpaRepository;

import banhang.quanlythucpham.tdl.SanPham;

public interface KdlSanPham extends JpaRepository<SanPham, Integer>
{

}