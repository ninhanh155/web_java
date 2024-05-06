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

import banhang.quanlythucpham.dvl.DvlNhaSanXuat;
import banhang.quanlythucpham.tdl.NhaSanXuat;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class QdlNhaSanXuat 
{
    @Autowired
    private DvlNhaSanXuat dvl; // cung cấp các dịch vụ thao tác dữ liệu

    
    @GetMapping({
            "/admin/nhasanxuat",
            "/admin/nhasanxuat/duyet"
    })
    public String getDuyet(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/nhasanxuat/duyet");
            return "redirect:/admin/dangnhap";
        }
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<NhaSanXuat> list = dvl.duyệtNhaSanXuat();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/nhasanxuat/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    @GetMapping("/admin/nhasanxuat/them")
    public String getThem(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/nhasanxuat/them");
            return "redirect:/admin/dangnhap";
        }
        NhaSanXuat dl = new NhaSanXuat();

        // Gửi đối tượng dữ liệu sang bên view
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/nhasanxuat/them.html"); 
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    // @GetMapping("/admin/nhasanxuat/sua/{id}")
    @GetMapping("/admin/nhasanxuat/sua")
    public String getSua(Model model, @RequestParam("id") int id, HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/nhasanxuat/sua"+id);
            return "redirect:/admin/dangnhap";
        }
        NhaSanXuat dl = dvl.xemNhaSanXuat(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // Hiển thị giao diện view html
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/nhasanxuat/sua.html"); // sua.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/nhasanxuat/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id, HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/nhasanxuat/xoa"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        NhaSanXuat dl = dvl.tìmNhaSanXuatTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/nhasanxuat/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/nhasanxuat/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id, HttpServletRequest request, HttpSession session) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/nhasanxuat/xem/"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        NhaSanXuat dl = dvl.xemNhaSanXuat(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/nhasanxuat/xem.html"); 

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html";    // QuanTri/layout.html
    }

    @PostMapping("/admin/nhasanxuat/them")
    public String postThem(@ModelAttribute("NhaSanXuat") NhaSanXuat dl, RedirectAttributes redirectAttributes) {
        // System.out.print("save action...");
        dvl.lưuNhaSanXuat(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã thêm mới thành công !");

        return "redirect:/admin/nhasanxuat/duyet";
    }

  
    @PostMapping("/admin/nhasanxuat/sua")
    public String postSua(@ModelAttribute("NhaSanXuat") NhaSanXuat dl, RedirectAttributes redirectAttributes) {
        dvl.lưuNhaSanXuat(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã sửa thành công !");

        return "redirect:/admin/nhasanxuat/duyet";
    }

    
    @PostMapping("/admin/nhasanxuat/xoa")
    public String postXoa(Model model, @RequestParam("Id") int id) 
    {
        // Xoá dữ liệu
        this.dvl.xóaNhaSanXuat(id);

        // Điều hướng sang trang giao diện
        return "redirect:/admin/nhasanxuat/duyet";
    }



}// end class

