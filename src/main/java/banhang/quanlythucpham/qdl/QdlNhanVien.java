package banhang.quanlythucpham.qdl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import banhang.quanlythucpham.dvl.DvlNhanVien;
import banhang.quanlythucpham.tdl.NhanVien;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class QdlNhanVien 
{
    @Autowired
    private DvlNhanVien dvl; 

   
    @GetMapping({
            "/admin/nhanvien",
            "/admin/nhanvien/duyet"
    })
    public String getDuyet(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
                request.getSession().setAttribute("LOCATION","/admin/nhanvien/duyet");
            return "redirect:/admin/dangnhap";
        }
        session = request.getSession();
        int userId = (int) session.getAttribute("ADMIN_USER_ID");
        NhanVien nv = dvl.tìmNhanVienTheoId(userId);

        System.out.println("bao" + nv.getTenDayDu());
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<NhanVien> list = dvl.duyệtNhanVien();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/nhanvien/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

    @GetMapping("/admin/nhanvien/them")
    public String getThem(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
                request.getSession().setAttribute("LOCATION","/admin/nhanvien/them");
            return "redirect:/admin/dangnhap";
        }
        NhanVien dl = new NhanVien();
        
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/nhanvien/them.html"); 
        
        return "QuanTri/layout.html"; 
    }

    @GetMapping("/admin/nhanvien/sua")
    public String getSua(Model model, @RequestParam("id") int id, HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/nhanvien/sua"+id);
            return "redirect:/admin/dangnhap";
        }
       
        NhanVien dl = dvl.xemNhanVien(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);
        
        // Nội dung riêng của trang...~
        model.addAttribute("content", "QuanTri/nhanvien/sua.html"); // sua.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/nhanvien/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id, HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/nhanvien/xoa"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        NhanVien dl = dvl.tìmNhanVienTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        model.addAttribute("content", "QuanTri/nhanvien/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/nhanvien/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id, HttpServletRequest request, HttpSession session) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/nhanvien/xem/"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        NhanVien dl = dvl.xemNhanVien(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        model.addAttribute("content", "QuanTri/nhanvien/xem.html"); 

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html";    // QuanTri/layout.html
    }

    @PostMapping("/admin/nhanvien/them")
    public String postThem(@ModelAttribute("NhanVien") NhanVien dl, RedirectAttributes redirectAttributes) {
        
        var inputPassword = dl.getMatKhau();
        var hash = BCrypt.hashpw(inputPassword, BCrypt.gensalt(12));
        dl.setMatKhau(hash);

        dvl.lưuNhanVien(dl);

        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã thêm mới thành công !");

        return "redirect:/admin/nhanvien/duyet";
    }

    @GetMapping("/admin/dangnhap")
    public String getDangNhap(Model model) {

        model.addAttribute("dl", new NhanVien());
        
        model.addAttribute("content", "QuanTri/nhanvien/dangnhap.html");
        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/nhanvien/dangnhap.html"; // layout.html
    }


    @PostMapping("/admin/dangnhap")
    public String postDangNhap(Model model, 
    RedirectAttributes redirectAttributes, 
    @RequestParam("Email") String Email, 
    @RequestParam("MatKhau") String MatKhau,
    HttpServletRequest request,
    HttpSession session) 
    {
         String old_password=null;
        
        if(dvl.tồnTạiEmail(Email))
        { 
            var old_dl = dvl.tìmNhanVienTheoEmail(Email) ;
            System.out.println(old_dl.getEmail());
            old_password = old_dl.getMatKhau();
            // So sánh mật khẩu trên Form và trong MySQL
        // xem có khớp không
            var mật_khẩu_khớp = BCrypt.checkpw(MatKhau, old_password);

            if// nếu
            (mật_khẩu_khớp){
                System.out.println("\n Đúng tài khoản, đăng nhập thành công");
                request.getSession().setAttribute("ADMIN_USER_LOGGED", old_dl.getEmail());
                request.getSession().setAttribute("ADMIN_USER_ID", old_dl.getId());
                request.getSession().setAttribute("ADMIN_USER_IMG",old_dl.getAnhNhanVien());
                request.getSession().setAttribute("ADMIN_USER_NAME",old_dl.getTenDayDu());
                request.getSession().setAttribute("ADMIN_USER_COMMENT",old_dl.getMoTaNhanVien());


            }else{
                System.out.println("\n Sai mật khẩu");
                // Gửi thông báo thành công từ view Add/Edit sang view List
                redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Sai mật khẩu !");
                return "redirect:/admin/dangnhap";
            }
        }
        else {
            System.out.println("\n Không tồn tại tên đăng nhập");
            // Gửi thông báo thành công từ view Add/Edit sang view List
            redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Sai tên đăng nhập !");
            return "redirect:/admin/dangnhap";
        }

        return "redirect:"+(String)session.getAttribute("LOCATION");
        // return "redirect:/admin";
    }

    @GetMapping("/admin/nhanvien/dangthoat")
    public String getDangThoat(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/admin";
    }

  
    @PostMapping("/admin/nhanvien/sua")
    public String postSua(@ModelAttribute("NhanVien") NhanVien dl, RedirectAttributes redirectAttributes) {

        var inputPassword = dl.getMatKhau();
        var hash = BCrypt.hashpw(inputPassword, BCrypt.gensalt(12));
        dl.setMatKhau(hash);
        dvl.lưuNhanVien(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã sửa thành công !");
        return "redirect:/admin/nhanvien/duyet";
    }
    
    @PostMapping("/admin/nhanvien/xoa")
    public String postXoa(Model model, @RequestParam("Id") int id) // request param phải khớp với name="Id" của thẻ html input
    {
        // Xoá dữ liệu
        this.dvl.xóaNhanVien(id);

        // Điều hướng sang trang giao diện
        return "redirect:/admin/nhanvien/duyet";
    }

    @GetMapping("/admin/nhanvien/taikhoan")
    public String getTaiKhoan(Model model, HttpSession session, HttpServletRequest request) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/nhanvien/taikhoan");
            return "redirect:/admin/dangnhap";
        }
        String email = (String) session.getAttribute("ADMIN_USER_LOGGED");
        if (email != null) {
            NhanVien dl = dvl.tìmNhanVienTheoEmail(email);
            model.addAttribute("tknv", dl);
            model.addAttribute("content", "QuanTri/nhanvien/taikhoan.html");
            return "QuanTri/layout.html";
        } else {
            return "redirect:/admin/dangnhap"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }
    }


}// end class
