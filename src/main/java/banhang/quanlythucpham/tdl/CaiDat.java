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
public class CaiDat
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int Id;

    private String khoa;
    private String giaTri;
    
    public Boolean KhongHopLe() {
        var khl = false;

        return khl;
    }

}// end class