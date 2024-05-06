package banhang.quanlythucpham.tdl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//package congty.tdl;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DonHang {
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int Id;

    public DonHang() {
        this.trangThai = "Đang xử lý";
        this.ngayDat = LocalDateTime.now();
    }

    private String ten;

    private String diaChi;

    private String soDienThoai;

    @OneToMany
    private List<GioHang> dsGioHang;

    private LocalDateTime ngayDat;

    public String getNgayDatVi() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.ngayDat);
    }

    private long tongTien;

    // 1: Đang xử lý, 2: Xác nhận, 3: Đang chuẩn bị, 4: Đang giao hàng, 5: Giao hàng
    // thành công, 6: Giao hàng thất bại
    private String trangThai;

    private int maKhachHang;
    @ManyToOne
    @JoinColumn(name = "maKhachHang", insertable = false, updatable = false)
    private KhachHang khachHang;
}
