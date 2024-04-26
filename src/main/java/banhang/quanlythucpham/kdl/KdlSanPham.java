package banhang.quanlythucpham.kdl;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import banhang.quanlythucpham.tdl.SanPham;

public interface KdlSanPham extends JpaRepository<SanPham, Integer>
{
    @Query("SELECT b FROM SanPham b WHERE b.maDanhMuc = ?1")
    List<SanPham> findByMaDanhMuc(int idSanPham);

    
}
