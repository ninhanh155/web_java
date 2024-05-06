package banhang.quanlythucpham.kdl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import banhang.quanlythucpham.tdl.DonHang;

public interface KdlDonHang extends JpaRepository<DonHang, Integer>
{
    List<DonHang> findByMaKhachHang(int id);

    @Query ("SELECT DATE(d.ngayDat), SUM(d.tongTien) FROM DonHang d GROUP BY DATE(d.ngayDat)")
    List<Object[]> calculateRevenueByDay();

    @Query ("SELECT SUM(tongTien) FROM DonHang")
    long tongDoanhThu();

    @Query ("SELECT COUNT(*) FROM DonHang")
    Integer tongDonHang();
    
}
