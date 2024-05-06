package banhang.quanlythucpham.kdl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import banhang.quanlythucpham.tdl.SanPham;
import banhang.quanlythucpham.tdl.YeuThich;
import jakarta.transaction.Transactional;

public interface KdlYeuThich extends JpaRepository<YeuThich, Integer>
{

    @Transactional
    @Modifying
    @Query("DELETE FROM YeuThich WHERE maKhachHang = ?2 AND maSanPham = ?1")
    void remove_product_wishlist(int idSanPham , int id_user);

    @Query("SELECT COUNT(w) > 0 FROM YeuThich w WHERE w.maSanPham =?1 AND w.maKhachHang =?2")
    boolean kiemtrasanpham(int id_product, int id_user);

    @Query("SELECT sp FROM YeuThich w JOIN SanPham sp ON w.maSanPham = sp.Id WHERE w.maKhachHang = ?1")
    List<SanPham> ds_yeu_thich_by_user(int id_User);
    
}