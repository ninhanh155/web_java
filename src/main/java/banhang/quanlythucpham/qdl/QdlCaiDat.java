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

import banhang.quanlythucpham.dvl.DvlCaiDat;
import banhang.quanlythucpham.tdl.CaiDat;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class QdlCaiDat 
{
    @Autowired
    private DvlCaiDat dvl; // cung cấp các dịch vụ thao tác dữ liệu

    //@Autowired
    //private DvlBảngNgoại dvlBangNgoai; // cung cấp các dịch vụ thao tác dữ liệu

    
    @GetMapping({
            "/admin/caidat",
            "/admin/caidat/duyet"
    })
    public String getDuyet(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/caidat/duyet");
            return "redirect:/admin/dangnhap";
        }

        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<CaiDat> list = dvl.duyệtCaiDat();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/caidat/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    @GetMapping("/admin/caidat/them")
    public String getThem(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/caidat/them");
            return "redirect:/admin/dangnhap";
        }
        CaiDat dl = new CaiDat();

        // Gửi đối tượng dữ liệu sang bên view
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/caidat/them.html"); 
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    @GetMapping("/admin/caidat/sua")
    public String getSua(Model model, @RequestParam("id") int id, HttpSession session, HttpServletRequest request) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/caidat/sua"+id);
            return "redirect:/admin/dangnhap";
        }
        CaiDat dl = dvl.xemCaiDat(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // Hiển thị giao diện view html
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/caidat/sua.html"); // sua.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/caidat/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id, HttpSession session, HttpServletRequest request) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/caidat/xoa"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        CaiDat dl = dvl.tìmCaiDatTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);
        
        model.addAttribute("content", "QuanTri/caidat/xoa.html"); 

        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/caidat/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id, HttpSession session, HttpServletRequest request) 
    {
       if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/caidat/xem/"+id);
            return "redirect:/admin/dangnhap";
        }
        CaiDat dl = dvl.xemCaiDat(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/caidat/xem.html"); 

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html";    // QuanTri/layout.html
    }

    @PostMapping("/admin/caidat/them")
    public String postThem(@ModelAttribute("CaiDat") CaiDat dl, RedirectAttributes redirectAttributes) {
        // System.out.print("save action...");
        dvl.lưuCaiDat(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã thêm mới thành công !");

        return "redirect:/admin/caidat/duyet";
    }

 
    @PostMapping("/admin/caidat/sua")
    public String postSua(@ModelAttribute("CaiDat") CaiDat dl, RedirectAttributes redirectAttributes) {
        dvl.lưuCaiDat(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã sửa thành công !");

        return "redirect:/admin/caidat/duyet";
    }

    @PostMapping("/admin/caidat/xoa")
    public String postXoa(Model model, @RequestParam("Id") int id) // request param phải khớp với name="Id" của thẻ html input
    {
        // Xoá dữ liệu
        this.dvl.xóaCaiDat(id);

        // Điều hướng sang trang giao diện
        return "redirect:/admin/caidat/duyet";
    }



}// end class

