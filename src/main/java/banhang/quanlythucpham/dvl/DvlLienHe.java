package banhang.quanlythucpham.dvl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banhang.quanlythucpham.kdl.KdlLienHe;
import banhang.quanlythucpham.tdl.LienHe;

@Service
public class DvlLienHe implements DviLienHe
{
    @Autowired private KdlLienHe kdl;// kho dữ liệu;

    @Override public List<LienHe> dsLienHe() // getAllThucThe()
    {
  
        // return null;

        // mã bởi lập trình viên:
        return kdl.findAll();
    }

    @Override public List<LienHe>  duyệtLienHe() 
    {
        return kdl.findAll();
    }

    @Override public LienHe  tìmLienHeTheoId(int id)// 
    {

        LienHe dl = null;

        Optional<LienHe> optional = kdl.findById(id);

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

    @Override public LienHe xemLienHe(int id)// 
    {

        LienHe dl = null;

        Optional<LienHe> optional = kdl.findById(id);

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
    public void lưuLienHe(LienHe dl)
    {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }
    public Integer tongPhanHoi(){
        return kdl.tongLienHe();
    }

    @Override
    public void xóaLienHe(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

}
