package banhang.quanlythucpham.dvl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banhang.quanlythucpham.kdl.KdlDanhMuc;
import banhang.quanlythucpham.tdl.DanhMuc;

@Service
public class DvlDanhMuc implements DviDanhMuc
{
    @Autowired private KdlDanhMuc kdl;// kho dữ liệu;

    @Override public List<DanhMuc> dsDanhMuc() // getAllThucThe()
    {
  
        // return null;

        // mã bởi lập trình viên:
        return kdl.findAll();
    }

    @Override public List<DanhMuc>  duyệtDanhMuc() 
    {
        return kdl.findAll();
    }

    @Override public DanhMuc  tìmDanhMucTheoId(int id)// 
    {

        DanhMuc dl = null;

        Optional<DanhMuc> optional = kdl.findById(id);

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

    @Override public DanhMuc xemDanhMuc(int id)// 
    {

        DanhMuc dl = null;

        Optional<DanhMuc> optional = kdl.findById(id);

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
    public void lưuDanhMuc(DanhMuc dl)
    {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }

    @Override
    public void xóaDanhMuc(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

}
