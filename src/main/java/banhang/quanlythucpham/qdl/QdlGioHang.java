package banhang.quanlythucpham.qdl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import banhang.quanlythucpham.dvl.DvlGioHang;
import banhang.quanlythucpham.dvl.DvlKhachHang;
import banhang.quanlythucpham.dvl.DvlSanPham;
import banhang.quanlythucpham.tdl.GioHang;
import banhang.quanlythucpham.tdl.KhachHang;
import banhang.quanlythucpham.tdl.SanPham;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class QdlGioHang 
{
    @Autowired
    private DvlGioHang dvl; // cung cấp các dịch vụ thao tác dữ liệu

    
    @Autowired
    private DvlKhachHang dvl_kh; // cung cấp các dịch vụ thao tác dữ liệu

    
    @Autowired
    private DvlSanPham dvl_sp;
    
    
    
    @GetMapping({
            "/shopping-cart/views"
    })
    public String getDuyet(Model model,
     HttpServletRequest request,
     RedirectAttributes redirectAttributes) 
    {
        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", dvl.getdlgiohang());
        model.addAttribute("total", dvl.getGia());
        model.addAttribute("content","giohang/cart-item.html");


        // Nội dung riêng của trang...
        model.addAttribute("content", "KhachHang/giohang.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "KhachHang/layout.html";
    }

    @GetMapping({"/add_to_cart/{id}"})
    public String addToCart(@PathVariable("id") Integer id,
    Model model,
    HttpServletRequest request,
    RedirectAttributes redirectAttributes
    )
    {
        HttpSession session = request.getSession();
        int UserId = (int) session.getAttribute("USER_ID");
        KhachHang kh = dvl_kh.tìmKhachHangTheoId(UserId);
        SanPham sp = dvl_sp.tìmSanPhamTheoId(id);

        GioHang item = new GioHang();
        item.setMaKhachHang(kh.getId());
        item.setIdSanPham(sp.getId());
        item.setTenSanPham(sp.getTenSanPham());
        item.setGia(sp.getDonGia());
        item.setSoLuong(1);
        dvl.themgiohang(item);
        return "redirect:/shopping-cart/views";
    }

    @GetMapping("/shopping-cart/clear")
    public String clearCart()
    {
        dvl.xoatatca();
        return "redirect:/shopping-cart/views";
    }
    @GetMapping("/shopping-cart/remove/{id}")
    public String removeCart(@PathVariable("id") Integer id )
    {
        dvl.xoatungsanpham(id);
        return "redirect:/shopping-cart/views";
    }

    @PostMapping("/shopping-cart/update")
    public String sua(@RequestParam("id") Integer id,
    @RequestParam("soLuong") Integer quantity,
    HttpServletRequest request,
    RedirectAttributes redirectAttributes)
    {

        dvl.sua(id, quantity);
        return "redirect:/shopping-cart/views";

    }
    
}// end class