package banhang.quanlythucpham.kdl;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import banhang.quanlythucpham.tdl.GioHang;
import banhang.quanlythucpham.tdl.SanPham;
import jakarta.transaction.Transactional;

import java.util.List;


public interface KdlGioHang extends JpaRepository<GioHang, Integer>
{

    List<GioHang> findByMaKhachHangAndStatusTrue(int maKhachHang);

    @Query("DELETE FROM GioHang gh WHERE gh.maKhachHang = :maKhachHang")
    void deleteAllByMaKhachHangAndStatusTrue(int maKhachHang);
}