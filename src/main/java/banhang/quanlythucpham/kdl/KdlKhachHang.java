package banhang.quanlythucpham.kdl;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import banhang.quanlythucpham.tdl.KhachHang;

public interface KdlKhachHang extends JpaRepository<KhachHang, Integer>
{
    List<KhachHang> findByEmail(String email);
    Boolean existsByEmail(String email);
    KhachHang findOneByEmail(String email);

    @Query("SELECT c FROM KhachHang c WHERE c.email = ?1")
    KhachHang findEmail(String email); 
     
    KhachHang findByResetPasswordToken(String token);

    @Query ("SELECT COUNT(*) FROM KhachHang")
    Integer tongKhachHang();
}