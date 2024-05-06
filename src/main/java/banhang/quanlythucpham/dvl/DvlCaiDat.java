package banhang.quanlythucpham.dvl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banhang.quanlythucpham.kdl.KdlCaiDat;
import banhang.quanlythucpham.tdl.CaiDat;

@Service
public class DvlCaiDat implements DviCaiDat
{
    @Autowired private KdlCaiDat kdl;// kho dữ liệu;

    @Override public List<CaiDat> dsCaiDat() // getAllThucThe()
    {
        return kdl.findAll();
    }

    @Override public List<CaiDat>  duyệtCaiDat() 
    {
        return kdl.findAll();
    }

    @Override public CaiDat  tìmCaiDatTheoId(int id)// 
    {
        CaiDat dl = null;

        Optional<CaiDat> optional = kdl.findById(id);

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

    @Override public CaiDat xemCaiDat(int id)// 
    {

        CaiDat dl = null;

        Optional<CaiDat> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            
        }

        return dl;

    }

    @Override
    public void lưuCaiDat(CaiDat dl)
    {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }

    @Override
    public void xóaCaiDat(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

}

