package banhang.quanlythucpham.kdl;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import banhang.quanlythucpham.tdl.SanPham;

public interface KdlSanPham extends JpaRepository<SanPham, Integer>
{
    @Query("SELECT b FROM SanPham b WHERE b.maDanhMuc = ?1")
    List<SanPham> findByMaDanhMuc(int idSanPham);

<<<<<<< HEAD
    @Query("SELECT b FROM SanPham b WHERE b.tenSanPham LIKE  ?1% ")
    List<SanPham> findbynameproduct(String tensp);

    @Query("SELECT sp.nsx.tenNhaSanXuat, COUNT(sp) AS soLuongSanPham FROM SanPham sp GROUP BY sp.nsx.tenNhaSanXuat")
    List<Object[]> thongKeSoLuongSanPhamTheoNhaSanXuat();

=======
    
>>>>>>> 4190f12894e89afedae1742272adcaee23e6eb7e
}
