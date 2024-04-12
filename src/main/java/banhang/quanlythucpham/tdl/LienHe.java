package banhang.quanlythucpham.tdl;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LienHe
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int Id;

    private String tenKhach;
    private String email;
    private String soDienThoai;
    private String tieuDe;
    private String phanHoi;
    private LocalDate ngayPhanHoi;
    
    public String getNgayPhanHoiVi() {
      return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.ngayPhanHoi);
  }

}// end class