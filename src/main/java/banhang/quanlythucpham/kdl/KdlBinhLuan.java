package banhang.quanlythucpham.kdl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import banhang.quanlythucpham.tdl.BinhLuan;

public interface KdlBinhLuan extends JpaRepository<BinhLuan, Integer>
{

    @Query("SELECT noiDung FROM BinhLuan noiDung WHERE noiDung.maSanPham = ?1")
    List<BinhLuan> findByMaSanPham(int idSanPham);
}