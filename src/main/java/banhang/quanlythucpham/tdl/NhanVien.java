package banhang.quanlythucpham.tdl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NhanVien
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int Id;

    private String tenDayDu;
    private String email;
    private String matKhau;
    private String dienThoai;
    private String anhNhanVien;
    @Transient
    private MultipartFile fileNv;
    
    public MultipartFile getFileNv(){
      return fileNv;
    }
    private LocalDate ngayTaoNV;

    public String getNgayTaoVi() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.ngayTaoNV);
    }
    private String moTaNhanVien;

    public Boolean KhongHopLe() {
        var khl = false;

        return khl;
    }

}// end class
