package banhang.quanlythucpham.tdl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GioHang
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.AUTO) // Tăng tự động từ 1,2,3,...
    private int Id;

    // private int MaKhachHang;
    private String tenSanPham;
    private double gia;
    private int soLuong = 1;
    private int idSanPham;

    private int maKhachHang;
    @ManyToOne @JoinColumn(name="maKhachHang",insertable=false,updatable=false)
    private KhachHang kh;
   
    

}// end class