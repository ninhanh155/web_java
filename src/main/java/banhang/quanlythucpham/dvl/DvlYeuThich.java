package banhang.quanlythucpham.dvl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banhang.quanlythucpham.kdl.KdlYeuThich;
import banhang.quanlythucpham.tdl.SanPham;
import banhang.quanlythucpham.tdl.YeuThich;

@Service
public class DvlYeuThich implements DviYeuThich
{
    @Autowired private KdlYeuThich kdl;// kho dữ liệu;

    @Override public List<SanPham> dsYeuThich(int user_id) // getAllYeuThich()
    {
  
<<<<<<< HEAD
     
=======
        // return null;

        // mã bởi lập trình viên:
>>>>>>> 4190f12894e89afedae1742272adcaee23e6eb7e
        return kdl.ds_yeu_thich_by_user(user_id);
    }
    // đổ tất cả các sản phẩm được yêu thích của khách hàng

    

    @Override public YeuThich  tìmYeuThichTheoId(int id)// 
    {


        YeuThich dl = null;

        Optional<YeuThich> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            //throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung !");
        }

        return dl;

    }



    @Override
    public void lưuYeuThich(YeuThich dl)
    {
        this.kdl.save(dl);
    }

    @Override
    public void xóaYeuThich(int id_product ,  int id_user)
    {
        this.kdl.remove_product_wishlist(id_product,id_user);
    }

    @Override
    public boolean kiemtra(int id_product, int id_user){
        return kdl.kiemtrasanpham(id_product, id_user);
    }
}
