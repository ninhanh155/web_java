package banhang.quanlythucpham.kdl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import banhang.quanlythucpham.tdl.NhanVien;

public interface KdlNhanVien extends JpaRepository<NhanVien, Integer>
{
    List<NhanVien> findByEmail(String email);
    Boolean existsByEmail(String email);
    NhanVien findOneByEmail(String email);
}