package banhang.quanlythucpham.dvl;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banhang.quanlythucpham.kdl.KdlGioHang;
import banhang.quanlythucpham.kdl.KdlKhachHang;
import banhang.quanlythucpham.tdl.GioHang;

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

    public void themgiohang(GioHang item)
    {
        GioHang GioHang = maps.get(item.getIdSanPham());

        if(GioHang == null)
        {
            maps.put(item.getIdSanPham(),item);
        }
        else
        {
            GioHang.setSoLuong(GioHang.getSoLuong()+1);
        }
        // this.kdl.save(item);
    }

    
    
    @Override
    public void xoatungsanpham(int id)
    {
        maps.remove(id);
        // this.kdl.save(GioHang);
    }


    @Override
    public GioHang sua(int proId , int qty)
    {
        GioHang GioHang = maps.get(proId);
        GioHang.setSoLuong(qty);
        this.kdl.save(GioHang);
        return GioHang;
    }


    @Override
    public void xoatatca()
    {
        maps.clear();
    }


    @Override
    public Collection<GioHang> getdlgiohang()
    {
        return maps.values();   
    }
    
    @Override
    public int getSoLuong()
    {
            return maps.values().size();
    }


    @Override
    public double getGia()
    {
        return maps.values().stream().mapToDouble(item -> item.getSoLuong() * item.getGia()).sum();
    }

}
