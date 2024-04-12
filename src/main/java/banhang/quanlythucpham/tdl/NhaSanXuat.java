package banhang.quanlythucpham.tdl;

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
public class NhaSanXuat
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int Id;

    private String tenNhaSanXuat;
    
    private String anhNhaSanXuat;
    @Transient
    private MultipartFile file;
    
    public MultipartFile getFile(){
      return file;
    }

    public Boolean KhongHopLe() {
        var khl = false;

        // if (this.Ten.length() < 2) {
        //     khl = true;
        //     System.out.print("\n Lỗi->Tên phải từ 2 kí tự trở lên: ");
        // }

        // if (this.Ten.length() > 22) {
        //     khl = true;
        //     System.out.print("\n Lỗi->Tên phải không quá 22 kí tự. ");
        // }

        return khl;
    }

}// end class