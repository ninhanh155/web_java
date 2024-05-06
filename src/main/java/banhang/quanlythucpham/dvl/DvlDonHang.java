package banhang.quanlythucpham.dvl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banhang.quanlythucpham.kdl.KdlDonHang;
import banhang.quanlythucpham.tdl.DonHang;
@Service
public class DvlDonHang implements DviDonHang {
    @Autowired
    private KdlDonHang kdl;// kho dữ liệu;

    @Override
    public DonHang luu(DonHang donHang)
    {
        return kdl.save(donHang);
    }

    @Override
    public List<DonHang> dsDonHang() // getAllDonHang()
    {
        List<DonHang> listDH = kdl.findAll(); 
        return listDH;
    }

    @Override
    public
    List<DonHang> getDonHangByMaKhacHang(int id)
    {
        return kdl.findByMaKhachHang(id);
    }


    @Override
    public List<DonHang> duyệtDonHang() {
        return kdl.findAll();
    }

    @Override
    public DonHang tìmDonHangTheoId(int id)//
    {
        DonHang dl = null;

        Optional<DonHang> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            
        }
        

        return dl;

    }
    public double tong()
    {
        double tongGia = 0;
        return tongGia;
    }

    @Override
    public DonHang xemDonHang(int id)//
    {

        DonHang dl = null;

        Optional<DonHang> optional = kdl.findById(id);

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
    public void lưuDonHang(DonHang dl) {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }

    @Override
    public void xóaDonHang(int id) {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

    public List<Object[]> doanhthu_theongay(){
        return kdl.calculateRevenueByDay();
    }

    public long doanhthu(){
        return kdl.tongDoanhThu();
    }

    public Integer tongSLDonHang(){
        return kdl.tongDonHang();
    }

    @Override
    public DonHang xacNhanDonHang(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'xacNhanDonHang'");
    }

    @Override
    public void huyDonHang(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'huyDonHang'");
    }
    

}

