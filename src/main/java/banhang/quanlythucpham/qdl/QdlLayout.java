package banhang.quanlythucpham.qdl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import banhang.quanlythucpham.dvl.DvlBinhLuan;
import banhang.quanlythucpham.dvl.DvlCaiDat;
import banhang.quanlythucpham.dvl.DvlDanhMuc;
import banhang.quanlythucpham.dvl.DvlGioHang;
import banhang.quanlythucpham.dvl.DvlKhachHang;
import banhang.quanlythucpham.dvl.DvlLienHe;
import banhang.quanlythucpham.dvl.DvlQuangCao;
import banhang.quanlythucpham.dvl.DvlSanPham;
import banhang.quanlythucpham.tdl.BinhLuan;
import banhang.quanlythucpham.tdl.DanhMuc;
import banhang.quanlythucpham.tdl.GioHang;
import banhang.quanlythucpham.tdl.KhachHang;
import banhang.quanlythucpham.tdl.LienHe;
import banhang.quanlythucpham.tdl.QuangCao;
import banhang.quanlythucpham.tdl.SanPham;


@ControllerAdvice
public class QdlLayout
{
   @Autowired
    DvlCaiDat dvlCaiDat;

    
    @ModelAttribute("caidat")
    public HashMap<String, String> getCaiDat() {
        var map = new HashMap<String, String>();

        var list = dvlCaiDat.duyệtCaiDat();
        for(var obj : list){
            map.put(obj.getKhoa(), obj.getGiaTri());
        }
        return map;
    }

    
    @ControllerAdvice
    public class LayoutAdvice {
        @Autowired
        private DvlDanhMuc dm;

        @Autowired
        private DvlSanPham sp;

        @Autowired
        private DvlQuangCao qc;

        @Autowired
        private DvlLienHe lh;

        @Autowired
        private DvlGioHang gh;

        @Autowired
        private DvlKhachHang kh;

        @Autowired
        private DvlBinhLuan bl;

        @ModelAttribute("dm")
        public List<DanhMuc> getDanhMuc() {
            return this.dm.dsDanhMuc();
        }

        @ModelAttribute("sp")
        public List<SanPham> getSanPham() {
            return this.sp.dsSanPham();
        }

        @ModelAttribute("qc")
        public List<QuangCao> getQuangCao() {
            return this.qc.dsQuangCao();
        }

        @ModelAttribute("lh")
        public List<LienHe> getLienHe() {
            return this.lh.dsLienHe();
        }

        // @ModelAttribute("gh")
        // public List<GioHang> getdlgiohang(){

        //     return this.gh.dsGioHang();
        // }

        @ModelAttribute("kh")
        public List<KhachHang> getKhachHang(){
            return this.kh.dsKhachHang();
        }

        @ModelAttribute("bl")
        public List<BinhLuan> getBinhLuan(){
            return this.bl.dsBinhLuan();
        }

    }


}