package banhang.quanlythucpham.qdl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import banhang.quanlythucpham.dvl.DvlDonHang;
import banhang.quanlythucpham.dvl.DvlKhachHang;
import banhang.quanlythucpham.dvl.DvlLienHe;
import banhang.quanlythucpham.dvl.DvlSanPham;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
public class QdlIndex {

    @Autowired
    private DvlSanPham dvl_sp;

    @Autowired
    private DvlDonHang dvl_dh;

    @Autowired
    private DvlLienHe dvl_lh;

    @Autowired
    private DvlKhachHang dvl_kh;

    @GetMapping({"/", "/trangchu"})
    public String indexAction(Model model, HttpSession session, HttpServletRequest request) {
        
        model.addAttribute("content", "KhachHang/index.html");

        return "KhachHang/layout.html"; 
    }

    @GetMapping({"/admin"})
    public String adminAction(Model model, HttpSession session, HttpServletRequest request) {

        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin");
            return "redirect:/admin/dangnhap";
        }
        model.addAttribute("dh", dvl_dh.duyệtDonHang());

<<<<<<< HEAD
        model.addAttribute("tongphanhoi", dvl_lh.tongPhanHoi());


        model.addAttribute("thongke", dvl_sp.thongKeSoLuongSanPhamTheoNhaSanXuat());

        model.addAttribute("doanh_thu", dvl_dh.doanhthu_theongay());

        model.addAttribute("tongdoanhthu", dvl_dh.doanhthu());

        model.addAttribute("tongdonhang", dvl_dh.tongSLDonHang());

        model.addAttribute("tongkhachhang", dvl_kh.sLKhachHang());


=======
>>>>>>> 4190f12894e89afedae1742272adcaee23e6eb7e
        model.addAttribute("content", "QuanTri/index.html");
        return "QuanTri/layout.html"; 

    }

}
