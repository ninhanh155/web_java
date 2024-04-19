package banhang.quanlythucpham.dvl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banhang.quanlythucpham.kdl.KdlBinhLuan;
import banhang.quanlythucpham.tdl.BinhLuan;

@Service
public class DvlBinhLuan implements DviBinhLuan
{
    @Autowired private KdlBinhLuan kdl;// kho dữ liệu;

    @Override public List<BinhLuan> dsBinhLuan() // getAllBinhLuan()
    {
  
        return kdl.findAll();
    }


    @Override public BinhLuan  tìmBinhLuanTheoMa(int id)// 
    {
        // return null;

        // return kdl.findById(id);

        BinhLuan dl = null;

        Optional<BinhLuan> optional = kdl.findById(id);

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


    @Override public List<BinhLuan> dsBinhLuan_thuoc_SanPham(int idSanPham)
    {
        return kdl.findByMaSanPham(idSanPham);
    }


    @Override
    public void lưuBinhLuan(BinhLuan dl)
    {
        this.kdl.save(dl);
    }

    @Override
    public void xóaBinhLuan(int id)
    {
        this.kdl.deleteById(id);
    }

}
