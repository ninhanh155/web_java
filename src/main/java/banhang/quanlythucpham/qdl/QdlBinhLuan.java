package banhang.quanlythucpham.qdl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import banhang.quanlythucpham.dvl.DvlBinhLuan;
import banhang.quanlythucpham.dvl.DvlKhachHang;
import banhang.quanlythucpham.dvl.DvlSanPham;
import banhang.quanlythucpham.tdl.BinhLuan;
import banhang.quanlythucpham.tdl.KhachHang;
import banhang.quanlythucpham.tdl.SanPham;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class QdlBinhLuan 
{
    @Autowired
    private DvlBinhLuan dvl; 

    @Autowired
    private DvlKhachHang dvl_kh; 

    @Autowired
    private DvlSanPham dvl_sp; 
    
    @PostMapping("/khachhang/binhluan")
    public String getThem(Model model ,@RequestParam("id") int id, 
    @RequestParam("binhluan") String binhluan,
    HttpServletRequest request,
    RedirectAttributes redirectAttributes, HttpSession session) {
        if(session.getAttribute("USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/chitietsanpham/"+id);
            return "redirect:/dangnhap";
        }
        
        session = request.getSession();
        int userId = (int) session.getAttribute("USER_ID");

        KhachHang kh = dvl_kh.tìmKhachHangTheoId(userId);

        SanPham sp = dvl_sp.tìmSanPhamTheoId(id);

        System.out.println("id user" + kh.getId() + "id sanpham : "+ sp.getId());


        BinhLuan bl = new BinhLuan();
        bl.setMaKhachHang(kh.getId());
        bl.setMaSanPham(sp.getId());
        bl.setNoiDung(binhluan);
        dvl.lưuBinhLuan(bl);

        // Nội dung riêng của trang...
        model.addAttribute("content", "KhachHang/chitietsanpham.html"); 

        return "redirect:/chitietsanpham/" + id;
    }
}