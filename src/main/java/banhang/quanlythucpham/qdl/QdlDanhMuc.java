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
import banhang.quanlythucpham.tdl.DanhMuc;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class QdlDanhMuc 
{
    @Autowired
    private DvlDanhMuc dvl; // cung cấp các dịch vụ thao tác dữ liệu

    // @Autowired
    // private DvlSanPham dvlSanPham;
    
    @GetMapping({
        "/danhmuc"
    })
    public String getDanhMuc(Model model) 
    {
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<DanhMuc> list = dvl.duyệtDanhMuc();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "KhachHang/danhmuc.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "KhachHang/layout.html"; 
}
    @GetMapping({
            "/admin/danhmuc/duyet"
    })
    public String getDuyet(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/danhmuc/duyet");
            return "redirect:/admin/dangnhap";
        }
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<DanhMuc> list = dvl.duyệtDanhMuc();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/danhmuc/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    @GetMapping("/admin/danhmuc/them")
    public String getThem(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/danhmuc/them");
            return "redirect:/admin/dangnhap";
        }
        DanhMuc dl = new DanhMuc();

        // Gửi đối tượng dữ liệu sang bên view
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);
        // model.addAttribute("dssp", this.dvlSanPham.dsSanPham());
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/danhmuc/them.html"); 

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    // @GetMapping("/danhmuc/sua/{id}")
    @GetMapping("/admin/danhmuc/sua")
    public String getSua(Model model, @RequestParam("id") int id, HttpSession session, HttpServletRequest request) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/danhmuc/sua"+id);
            return "redirect:/admin/dangnhap";
        }

        DanhMuc dl = dvl.xemDanhMuc(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);
        // model.addAttribute("dssp", this.dvlSanPham.dsSanPham());

        // Hiển thị giao diện view html
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/danhmuc/sua.html"); // sua.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // layout.html
    }

    @GetMapping("/admin/danhmuc/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id, HttpSession session, HttpServletRequest request) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/danhmuc/xoa"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        DanhMuc dl = dvl.tìmDanhMucTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/danhmuc/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // layout.html
    }

    @GetMapping("/admin/danhmuc/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/danhmuc/xem/"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        DanhMuc dl = dvl.xemDanhMuc(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/danhmuc/xem.html"); 

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html";    // layout.html
    }

    @PostMapping("/admin/danhmuc/them")
    public String postThem(@ModelAttribute("DanhMuc") DanhMuc dl, RedirectAttributes redirectAttributes) {
        // System.out.print("save action...");
        dvl.lưuDanhMuc(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã thêm mới thành công !");

        return "redirect:/admin/danhmuc/duyet";
    }

    
    @PostMapping("/admin/danhmuc/sua")
    public String postSua(@ModelAttribute("DanhMuc") DanhMuc dl, RedirectAttributes redirectAttributes) {
        dvl.lưuDanhMuc(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã sửa thành công !");

        return "redirect:/admin/danhmuc/duyet";
    }

    @PostMapping("/admin/danhmuc/xoa")
    public String postXoa(Model model, @RequestParam("Id") int id) // request param phải khớp với name="Id" của thẻ html input
    {
        // Xoá dữ liệu
        this.dvl.xóaDanhMuc(id);

        // Điều hướng sang trang giao diện
        return "redirect:/admin/danhmuc/duyet";
    }

}// end class
