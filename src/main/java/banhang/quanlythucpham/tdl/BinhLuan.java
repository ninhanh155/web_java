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
public class BinhLuan
{
  @Id // Khóa chính
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
  private int Id;

  private String noiDung;
  
  private int maKhachHang;
  @ManyToOne @JoinColumn(name="maKhachHang",insertable=false,updatable=false)
  private KhachHang kh;
 
  private int maSanPham;
  @ManyToOne @JoinColumn(name="maSanPham",insertable=false,updatable=false)
  private SanPham sp;

}// end class
