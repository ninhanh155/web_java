package banhang.quanlythucpham.tdl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class KhachHang
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String tenDayDu;
    private String email;
    private String matKhau;
    private String xacNhanMatKhau;
    private String resetPasswordToken;

    public String getMatKhau() {
        return matKhau;
    }
  
}// end class