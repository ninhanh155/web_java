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
public class QuangCao
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int Id;

    private String tenAnh;

    private String anhQuangCao;
    @Transient
    private MultipartFile fileAnh;
    
    public MultipartFile getFileAnh(){
      return fileAnh;
    }
    
}// end class