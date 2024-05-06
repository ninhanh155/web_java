package banhang.quanlythucpham.qdl;

import java.net.http.HttpRequest;
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

import banhang.quanlythucpham.dvl.DvlLienHe;
import banhang.quanlythucpham.tdl.LienHe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class QdlLienHe 
{
    @Autowired
    private DvlLienHe dvl; // cung cấp các dịch vụ thao tác dữ liệu


    @GetMapping({"/lienhe"})
    public String getLienHe(Model model) 
    {
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<LienHe> list = dvl.duyệtLienHe();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "KhachHang/lienhe.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "KhachHang/layout.html"; 
    }
    
    @GetMapping({
            "/admin/lienhe",
            "/admin/lienhe/duyet"
    })
    public String getDuyet(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/lienhe/duyet");
            return "redirect:/admin/dangnhap";
        }
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<LienHe> list = dvl.duyệtLienHe();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/lienhe/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    @GetMapping("/admin/lienhe/them")
    public String getThem(Model model, HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/lienhe/them");
            return "redirect:/admin/dangnhap";
        }
        LienHe dl = new LienHe();

        // Gửi đối tượng dữ liệu sang bên view
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/lienhe/them.html"); 
        
        return "QuanTri/layout.html"; 
    }

    @GetMapping("/lienhe/camon")
    public String getlienhethanhcong(Model model) {

        model.addAttribute("content", "KhachHang/lienhethanhcong.html");
        
        return "KhachHang/layout.html";
    }

    // @GetMapping("/lienhe/sua/{id}")
    @GetMapping("/admin/lienhe/sua")
    public String getSua(Model model, @RequestParam("id") int id, HttpSession session, HttpServletRequest request) {
        // Lấy ra bản ghi theo id
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/lienhe/sua"+id);
            return "redirect:/admin/dangnhap";
        }
        LienHe dl = dvl.xemLienHe(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị giao diện view html
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/lienhe/sua.html"); // sua.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/lienhe/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id, HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/lienhe/xoa"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        LienHe dl = dvl.tìmLienHeTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/lienhe/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/lienhe/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id, HttpServletRequest request, HttpSession session) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/lienhe/xem/"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        LienHe dl = dvl.xemLienHe(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/lienhe/xem.html"); 

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html";    // QuanTri/layout.html
    }

    @PostMapping("/admin/lienhe/them")
    public String postThem(@ModelAttribute("LienHe") LienHe dl, RedirectAttributes redirectAttributes) {
        // System.out.print("save action...");
        dvl.lưuLienHe(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã thêm mới thành công !");

        return "redirect:/lienhe/camon"; 
    }
  
    @PostMapping("/admin/lienhe/sua")
    public String postSua(@ModelAttribute("LienHe") LienHe dl, RedirectAttributes redirectAttributes) {
        dvl.lưuLienHe(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã sửa thành công !");

        return "redirect:/lienhe/duyet";
    }
    
    @PostMapping("/admin/lienhe/xoa")
    public String postXoa(Model model, @RequestParam("Id") int id) // request param phải khớp với name="Id" của thẻ html input
    {
        // Xoá dữ liệu
        this.dvl.xóaLienHe(id);

        // Điều hướng sang trang giao diện
        return "redirect:/lienhe/duyet";
    }




}// end class
