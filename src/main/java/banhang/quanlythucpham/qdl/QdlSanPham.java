package banhang.quanlythucpham.qdl;

import java.util.List;

// Thư viện web: Java Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import banhang.quanlythucpham.dvl.DvlDanhMuc;
import banhang.quanlythucpham.dvl.DvlNhaSanXuat;
import banhang.quanlythucpham.dvl.DvlSanPham;
import banhang.quanlythucpham.tdl.SanPham;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class QdlSanPham 
{
    @Autowired
    private DvlSanPham dvl; // cung cấp các dịch vụ thao tác dữ liệu

    @Autowired
    private DvlNhaSanXuat dvlNhaSanXuat;

    @Autowired
    private DvlDanhMuc dvlDanhMuc;

    
    @GetMapping({
            "/admin/sanpham",
            "/admin/sanpham/duyet"
    })
    public String getDuyet(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/sanpham/duyet");
            return "redirect:/admin/dangnhap";
        }
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<SanPham> list = dvl.duyệtSanPham();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/sanpham/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    @GetMapping("/admin/sanpham/them")
    public String getThem(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/sanpham/them");
            return "redirect:/admin/dangnhap";
        }
        SanPham dl = new SanPham();

        // Gửi đối tượng dữ liệu sang bên view
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);

        model.addAttribute("dsnsx", this.dvlNhaSanXuat.dsNhaSanXuat()); 
        model.addAttribute("dsdm", this.dvlDanhMuc.dsDanhMuc());

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/sanpham/them.html"); 
      

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    // @GetMapping("/admin/sanpham/sua/{id}")
    @GetMapping("/admin/sanpham/sua")
    public String getSua(Model model, @RequestParam("id") int id, HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/sanpham/sua"+id);
            return "redirect:/admin/dangnhap";
        }
        SanPham dl = dvl.xemSanPham(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);
        model.addAttribute("dsnsx", this.dvlNhaSanXuat.dsNhaSanXuat());
        model.addAttribute("dsdm", this.dvlDanhMuc.dsDanhMuc());
        // Hiển thị giao diện view html
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/sanpham/sua.html"); // sua.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    
    @GetMapping("/admin/sanpham/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id, HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/sanpham/xoa"+id);
            return "redirect:/admin/dangnhap";
        }        
        SanPham dl = dvl.tìmSanPhamTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);
        model.addAttribute("dsnsx", this.dvlNhaSanXuat.dsNhaSanXuat()); 
        model.addAttribute("dsdm", this.dvlDanhMuc.dsDanhMuc());
        
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/sanpham/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/sanpham/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id, HttpServletRequest request, HttpSession session) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/sanpham/xem"+id);
            return "redirect:/admin/dangnhap";
        }
        SanPham dl = dvl.xemSanPham(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        model.addAttribute("content", "QuanTri/sanpham/xem.html"); 

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html";    // QuanTri/layout.html
    }

    @PostMapping("/admin/sanpham/them")
    public String postThem(@ModelAttribute("SanPham") SanPham dl, RedirectAttributes redirectAttributes) {
        // System.out.print("save action...");
        dvl.lưuSanPham(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã thêm mới thành công !");

        return "redirect:/admin/sanpham/duyet";
    }


    @PostMapping("/admin/sanpham/sua")
    public String postSua(@ModelAttribute("SanPham") SanPham dl, RedirectAttributes redirectAttributes) {
        dvl.lưuSanPham(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã sửa thành công !");

        return "redirect:/admin/sanpham/duyet";
    }

   
    @PostMapping("/admin/sanpham/xoa")
    public String postXoa(Model model, @RequestParam("Id") int id) // request param phải khớp với name="Id" của thẻ html input
    {
        // Xoá dữ liệu
        this.dvl.xóaSanPham(id);

        // Điều hướng sang trang giao diện
        return "redirect:/admin/sanpham/duyet";
    }


}// end class

