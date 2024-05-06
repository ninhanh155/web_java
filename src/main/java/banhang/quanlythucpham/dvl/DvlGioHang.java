package banhang.quanlythucpham.dvl;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banhang.quanlythucpham.kdl.KdlGioHang;
import banhang.quanlythucpham.kdl.KdlKhachHang;
import banhang.quanlythucpham.tdl.GioHang;
import banhang.quanlythucpham.tdl.SanPham;

@Service
public class DvlGioHang implements DviGioHang
{
    @Autowired KdlGioHang kdl;

    @Autowired KdlKhachHang kdl_nv;
    @Autowired DvlSanPham dvl_sp;

    @Autowired DviKhachHang dvl_nv;

    Map<Integer , GioHang> maps = new HashMap<>();
    
    @Override
    public List<GioHang>  dsGioHang() 
    {
        // GioHang GioHang = maps.get(item.getMaKhachHang());
        return kdl.findAll();
    }
    @Override
    public List<GioHang> dsGioHangByUser(int id_User) 
    {
        return kdl.findByMaKhachHangAndStatusTrue(id_User);
    }


    @Override
    public void deleteGioHangById(int idGioHang)
    {
        this.kdl.deleteById(idGioHang);
    }

    @Override
    public void deleteAllGioHangByUserId(int maKhachHang)
    {
        this.kdl.deleteAllByMaKhachHangAndStatusTrue(maKhachHang);
    }


    
    public void themgiohang(GioHang item)
    {
        GioHang GioHang = maps.get(item.getSp().getId());

        if(GioHang == null)
        {
            maps.put(item.getSp().getId(),item);
        }
        else
        {
            GioHang.setSoLuong(GioHang.getSoLuong()+1);
        }
        this.kdl.save(item);
    }

    
    
    // @Override
    // public void xoatungsanpham(int id)
    // {
    //     maps.remove(id);
    //     // this.kdl.save(GioHang);
    // }


    @Override
    public GioHang sua(int idGioHang , int qty)
    {
        Optional<GioHang> optionalGioHang = kdl.findById(idGioHang);
        GioHang gioHang = null;
        if(optionalGioHang.isPresent()) {
            gioHang = optionalGioHang.get();
        }

        gioHang.setSoLuong(qty);
        this.kdl.save(gioHang);
        return gioHang;
    }


    @Override
    public void luuGioHang(GioHang gioHang)
    {
        this.kdl.save(gioHang);
    }




    // @Override
    // public void xoatatca()
    // {
    //     maps.clear();
    // }


    // @Override
    // public Collection<GioHang> getdlgiohang()
    // {
    //     return maps.values();   
    // }
    
    // @Override
    // public int getSoLuong()
    // {
    //         return maps.values().size();
    // }


    // @Override
    // public double getGia()
    // {
    //     return maps.values().stream().mapToDouble(item -> item.getSoLuong() * item.getGia()).sum();
    // }

}
