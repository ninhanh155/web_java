package banhang.quanlythucpham.qdl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import banhang.quanlythucpham.dvl.DvlDanhMuc;
import banhang.quanlythucpham.dvl.DvlKhachHang;
import banhang.quanlythucpham.dvl.DvlLienHe;
import banhang.quanlythucpham.dvl.DvlQuangCao;
import banhang.quanlythucpham.dvl.DvlSanPham;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class QdlIndex {

    // @Autowired
    // private DvlDanhMuc dm;

    // @Autowired
    // private DvlSanPham sp;

    // @Autowired
    // private DvlQuangCao qc;

    // @Autowired
    // private DvlKhachHang kh;

    // @Autowired
    // private DvlLienHe lh;
    
    @GetMapping({"/", "/trangchu"})
    public String indexAction(Model model, HttpSession session, HttpServletRequest request) {
        
        // model.addAttribute("dm", this.dm.dsDanhMuc());
        // model.addAttribute("sp", this.sp.dsSanPham());
        // model.addAttribute("qc", this.qc.dsQuangCao());
        // model.addAttribute("kh", this.kh.dsKhachHang());
        // model.addAttribute("lh", this.lh.dsLienHe());
        
        // if(session.getAttribute("USER_LOGGED")==null)
        // {
        //     request.getSession().setAttribute("LOCATION","/trangchu");
        //     return "redirect:/dangnhap";
        // }

        return "KhachHang/index.html"; // Tên template được trả về
    }

    @GetMapping({"/admin"})
    public String adminAction(Model model, HttpSession session, HttpServletRequest request) {

        if(session.getAttribute("USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin");
            return "redirect:/admin/dangnhap";
        }
        return "QuanTri/index.html"; // Tên template được trả về
    }

}
