package banhang.quanlythucpham.tdl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GioHang
{

    public GioHang()
    {
        this.status = true;
    }

    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.AUTO) // Tăng tự động từ 1,2,3,...
    private int Id;

    private int soLuong = 1;
    
    @ManyToOne 
    @JoinColumn(name="maSanPham")
    private SanPham sp;

    private int maKhachHang;
    @ManyToOne @JoinColumn(name="maKhachHang",insertable=false,updatable=false)
    private KhachHang kh;

    private boolean status;

    private String getStatus()
    {
        return this.status ? "chưa thanh toán":" đã thanh toán";
    }
}// end class