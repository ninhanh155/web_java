package banhang.quanlythucpham.tdl;


import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SanPham
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int Id;

    private String tenSanPham;
    private int donGia;
    private String moTa;
    private String anhSanPham;
    private boolean trangThai;
    private boolean banChay;
    private boolean noiBat;


  // private String Anh; // địa chỉ ảnh Online
    private String maNhaSanXuat;
    @ManyToOne @JoinColumn(name="maNhaSanXuat",insertable=false,updatable=false)
    private NhaSanXuat nsx;

    private String maDanhMuc;
    @ManyToOne @JoinColumn(name="maDanhMuc",insertable=false,updatable=false)
    private DanhMuc dm;

    @Transient
    private MultipartFile fileSp;
    
    public MultipartFile getFileSp(){
      return fileSp;
    }
    public Boolean KhongHopLe() {
        var khl = false;

        

        return khl;
    }

}// end class