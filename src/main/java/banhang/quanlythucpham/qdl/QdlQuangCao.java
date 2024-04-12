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

import banhang.quanlythucpham.dvl.DvlQuangCao;
import banhang.quanlythucpham.tdl.QuangCao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class QdlQuangCao 
{
    @Autowired
    private DvlQuangCao dvl; // cung cấp các dịch vụ thao tác dữ liệu

    
    @GetMapping({
            "/admin/quangcao",
            "/admin/quangcao/duyet"
    })
    public String getDuyet(Model model, HttpSession session, HttpServletRequest request) 
    {
        // if(session.getAttribute("USER_LOGGED")==null)
        // {
        //     request.getSession().setAttribute("LOCATION","/admin/quangcao/duyet");
        //     return "redirect:/admin/dangnhap";
        // }
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<QuangCao> list = dvl.duyệtQuangCao();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/quangcao/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    @GetMapping("/admin/quangcao/them")
    public String getThem(Model model, HttpSession session, HttpServletRequest request) 
    {
        // if(session.getAttribute("USER_LOGGED")==null)
        // {
        //     request.getSession().setAttribute("LOCATION","/admin/quangcao/them");
        //     return "redirect:/admin/dangnhap";
        // }
        QuangCao dl = new QuangCao();

        // Gửi đối tượng dữ liệu sang bên view
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/quangcao/them.html"); 
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    // @GetMapping("/admin/quangcao/sua/{id}")
    @GetMapping("/admin/quangcao/sua")
    public String getSua(Model model, @RequestParam("id") int id) {

        QuangCao dl = dvl.xemQuangCao(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // Hiển thị giao diện view html
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/quangcao/sua.html"); // sua.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/quangcao/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id) {
        // Lấy ra bản ghi theo id
        QuangCao dl = dvl.tìmQuangCaoTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/quangcao/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/quangcao/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id) 
    {
        // Lấy ra bản ghi theo id
        QuangCao dl = dvl.xemQuangCao(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/quangcao/xem.html"); 

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html";    // QuanTri/layout.html
    }

    @PostMapping("/admin/quangcao/them")
    public String postThem(@ModelAttribute("QuangCao") QuangCao dl, RedirectAttributes redirectAttributes) {
        // System.out.print("save action...");
        dvl.lưuQuangCao(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã thêm mới thành công !");

        return "redirect:/admin/quangcao/duyet";
    }

    
  
    @PostMapping("/admin/quangcao/sua")
    public String postSua(@ModelAttribute("QuangCao") QuangCao dl, RedirectAttributes redirectAttributes) {
        dvl.lưuQuangCao(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã sửa thành công !");

        return "redirect:/admin/quangcao/duyet";
    }

    
    @PostMapping("/admin/quangcao/xoa")
    public String postXoa(Model model, @RequestParam("Id") int id) 
    {
        // Xoá dữ liệu
        this.dvl.xóaQuangCao(id);

        // Điều hướng sang trang giao diện
        return "redirect:/admin/quangcao/duyet";
    }



}
