package banhang.quanlythucpham.qdl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import banhang.quanlythucpham.dvl.DvlBinhLuan;
import banhang.quanlythucpham.dvl.DvlKhachHang;
import banhang.quanlythucpham.dvl.DvlNhanVien;
import banhang.quanlythucpham.dvl.DvlSanPham;
import banhang.quanlythucpham.dvl.DvlYeuThich;
import banhang.quanlythucpham.tdl.BinhLuan;
import banhang.quanlythucpham.tdl.KhachHang;
import banhang.quanlythucpham.tdl.NhanVien;
import banhang.quanlythucpham.tdl.SanPham;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class QdlKhachHang 
{
    @Autowired
    private DvlKhachHang dvl; // cung cấp các dịch vụ thao tác dữ liệu

    @Autowired
    private DvlSanPham dvlSanPham;

    @Autowired
    private DvlBinhLuan dvlBinhLuan;

    @Autowired
    private DvlNhanVien dvlNhanVien;

    @Autowired
    private DvlKhachHang dvlKhachHang;

    @Autowired
    private DvlYeuThich dvlYeuThich;

    @Autowired 
    private JavaMailSender javamailsender;

    @GetMapping({
            "/admin/khachhang",
            "/admin/khachhang/duyet"
    })
    public String getDuyet(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/khachhang/duyet");
            return "redirect:/admin/dangnhap";
        }
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<KhachHang> list = dvl.duyệtKhachHang();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/khachhang/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }


    // @GetMapping("/admin/khachhang/sua/{id}")
    @GetMapping("/admin/khachhang/sua")
    public String getSua(Model model, @RequestParam("id") int id, HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/khachang/sua"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        KhachHang dl = dvl.xemKhachHang(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        model.addAttribute("content", "QuanTri/khachhang/sua.html"); // sua.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    
    @GetMapping("/admin/khachhang/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id, HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/khachhang/xoa"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        KhachHang dl = dvl.tìmKhachHangTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/khachhang/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @GetMapping("/admin/khachhang/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id, HttpServletRequest request, HttpSession session) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/khachhang/xem/"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        KhachHang dl = dvl.xemKhachHang(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/khachhang/xem.html"); 

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html";    // QuanTri/layout.html
    }

    @GetMapping("/khachhang/taikhoan")
    public String getTaiKhoan(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("USER_ID");
        if (userId != null) {
            KhachHang dl = dvl.tìmKhachHangTheoId(userId);
            if (dl != null) {
                model.addAttribute("tk", dl);
                model.addAttribute("content", "KhachHang/taikhoan.html");
                return "KhachHang/layout.html";
            } else {
                return "redirect:/dangnhap"; 
            }
        } else {
            return "redirect:/dangnhap"; 
        }
    }
    

    

    @GetMapping("/dangky")
    public String getThem(Model model) {

        KhachHang dl = new KhachHang();
        
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);

        // Nội dung riêng của trang...
        model.addAttribute("content", "KhachHang/dangki.html"); 
        
        return "KhachHang/layout.html"; 
    }
    
    @PostMapping("/dangky")
    public String postThem(@ModelAttribute("KhachHang") KhachHang dl, RedirectAttributes redirectAttributes, Model model, HttpSession session) {

        var inputPassword = dl.getMatKhau();
        var confirmPassword = dl.getXacNhanMatKhau();

        if(inputPassword.equals(confirmPassword)) {
            var hashInputPassword = BCrypt.hashpw(inputPassword, BCrypt.gensalt(10));
            var hashConfirmPassword = BCrypt.hashpw(confirmPassword, BCrypt.gensalt(10));

            dl.setMatKhau(hashInputPassword);
            dl.setXacNhanMatKhau(hashConfirmPassword);

            dvl.lưuKhachHang(dl);
            session.setAttribute("USER_ID", dl.getId());

            redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã đăng kí tài khoản thành công !");
            return "redirect:/trangchu";
        } else {
            // Add the attribute for the form to reload with previous input values
            model.addAttribute("dl", dl);
            redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Mật khẩu không khớp !");
            return "redirect:/dangky"; 
        }
    }


    @GetMapping("/dangnhap")
    public String getDangNhap(Model model) {

        KhachHang dl = new KhachHang();
        
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);
        
        model.addAttribute("content", "KhachHang/dangnhap.html");
        // ...được đặt vào bố cục chung của toàn website
        return "KhachHang/layout.html"; // layout.html
    }

    @PostMapping("/dangnhap")
    public String postDangNhap(
        Model model, 
        RedirectAttributes redirectAttributes, 
        @RequestParam("Email") String Email, 
        @RequestParam("MatKhau") String MatKhau, 
        HttpServletRequest request, HttpSession session) 
    {
        
        String old_password = null;
        
        if (dvl.tồnTạiEmail(Email)) {
            var old_dl = dvl.tìmKhachHangTheoEmail(Email);
            System.out.println(old_dl.getEmail());
            old_password = old_dl.getMatKhau();
            
            boolean mật_khẩu_khớp = BCrypt.checkpw(MatKhau, old_password);

            if (mật_khẩu_khớp) {
                System.out.println("\n Đúng tài khoản, đăng nhập thành công");
                request.getSession().setAttribute("USER_LOGGED", old_dl.getEmail());
                request.getSession().setAttribute("USER_NAME", old_dl.getTenDayDu());
                request.getSession().setAttribute("USER_ID", old_dl.getId());
                
            } else {
                System.out.println("\n Sai mật khẩu");
                redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Tài khoản hoặc mật khẩu không chính xác!");
                return "redirect:/dangnhap";
            }
        } else {
            System.out.println("\n Không tồn tại tên đăng nhập");
            redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Sai tên đăng nhập !");
            return "redirect:/dangnhap";
        }

        String location = (String) session.getAttribute("LOCATION");
        if (location != null) {
            return "redirect:" + location;
        } else {
            return "redirect:/trangchu";
        }
    }

    
    @GetMapping("/khachhang/doimatkhau")
    public String getDoiMatKhau(Model model) {


        model.addAttribute("content", "KhachHang/doimatkhau.html");

        // ...được đặt vào bố cục chung của toàn website
        return "KhachHang/layout.html"; // layout.html
    }
    
    @PostMapping("/khachhang/doimatkhau")
    public String postdoiMatKhau(
        @RequestParam("email") String email, 
        @RequestParam("matKhauCu") String matKhauCu,
        @RequestParam("matKhauMoi") String matKhauMoi,
        @RequestParam("xnMatKhauMoi") String xnMatKhauMoi,
        RedirectAttributes redirectAttributes) {
        
        if (!dvl.tồnTạiEmail(email)) {
            redirectAttributes.addFlashAttribute("THONG_BAO_LOI", "Email không tồn tại!");
            return "redirect:/khachhang/doimatkhau";
        }
    
        KhachHang KhachHang = dvl.tìmKhachHangTheoEmail(email);
        // Kiểm tra mật khẩu cũ có đúng không
        if (!BCrypt.checkpw(matKhauCu, KhachHang.getMatKhau())) {
            redirectAttributes.addFlashAttribute("THONG_BAO_LOI", "Mật khẩu cũ không đúng!");
            return "redirect:/khachhang/doimatkhau";
        }
    
        // Mã hóa mật khẩu mới và cập nhật vào CSDL
        String matKhauMoiMaHoa = BCrypt.hashpw(matKhauMoi, BCrypt.gensalt(12));
        KhachHang.setMatKhau(matKhauMoiMaHoa);
        dvl.lưuKhachHang(KhachHang);
    
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đổi mật khẩu thành công!");

        return "redirect:/khachhang/doimatkhauthanhcong";
        // return "redirect:/KhachHang/duyet";
        
    }
    
    @GetMapping("/khachhang/doimatkhauthanhcong")
    public String getDoiMatKhauthanhcong(Model model) {

        model.addAttribute("content", "KhachHang/thongbao_thanhcong.html");
        
        return "KhachHang/layout.html";
    }


    @GetMapping("/khachhang/dangthoat")
    public String getDangThoat(HttpServletRequest request) {

        request.getSession().invalidate();
        return "redirect:/";
    }

    @PostMapping("/admin/khachhang/sua")
    public String postSua(@ModelAttribute("KhachHang") KhachHang dl, RedirectAttributes redirectAttributes) {

        var inputPassword = dl.getMatKhau();
        var confirmPassword = dl.getXacNhanMatKhau();

        var hash = BCrypt.hashpw(inputPassword, BCrypt.gensalt(10));
        // String shorthash = hash.substring(0, 10);
        var hashpas = BCrypt.hashpw(confirmPassword, BCrypt.gensalt(10));
        // String shortHash = hashpas.substring(0, 10);
        dl.setMatKhau(hash);
        dl.setXacNhanMatKhau(hashpas);
        dvl.lưuKhachHang(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã sửa thành công !");

        return "redirect:/admin/khachhang/duyet";
    }
    
    @PostMapping("/admin/khachhang/xoa")
    public String postXoa(Model model, @RequestParam("Id") int id) // request param phải khớp với name="Id" của thẻ html input
    {
        // Xoá dữ liệu
        this.dvl.xóaKhachHang(id);

        // Điều hướng sang trang giao diện
        return "redirect:/admin/khachhang/duyet";
    }

    @GetMapping({"/sanpham"})
    public String getAllSanPham(Model model,@RequestParam(name = "pageNo" , defaultValue="1") Integer pageNo
    ) 
    {
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        Page<SanPham> list = dvlSanPham.findPaginated(pageNo,12);

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);
        model.addAttribute("currentPage", pageNo); // trang hiện tại
        model.addAttribute("totalPages", list.getTotalPages()); // tổng số trang
        model.addAttribute("totalItems", list.getTotalElements()); // tổng số phần tử tìm thấy
        // Nội dung riêng của trang...
        model.addAttribute("content", "KhachHang/sanpham.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "KhachHang/layout.html"; 
    }

    @PostMapping("/timkiem")
    public String getTimKiem(Model model, 
    @RequestParam("tensp") String tensp ,
    HttpServletRequest request, HttpSession session) {
        
        request.getSession().setAttribute("Timkiem", tensp);
        List<SanPham> list_timkiem = dvlSanPham.timkiem_sp_theoten(tensp);
        model.addAttribute("ds", list_timkiem);

        model.addAttribute("content", "KhachHang/timkiem.html");
        return "KhachHang/layout.html";
    }

    @GetMapping("/danhmuc/{id}")
    public String getDanhMuc(Model model, @PathVariable(value = "id") int id)
    {
        System.out.println("id danh muc : " + id);
        List<SanPham> list_sp_thuoc_danh_muc = dvlSanPham.ds_sp_thuoc_MaDanhMuc(id);

        model.addAttribute("ds", list_sp_thuoc_danh_muc);
        model.addAttribute("content", "KhachHang/danhmuc.html");
        return "KhachHang/layout.html";
    }

    @GetMapping({"/chitietsanpham/{id}"})
    public String getChiTietSanPham(Model model, @PathVariable(value = "id") int id, HttpServletRequest request, RedirectAttributes redirectAttributes) 
    {
        HttpSession session = request.getSession();
        Integer UserId = (Integer) session.getAttribute("USER_ID");
        
        if(UserId != null){
            KhachHang kh = dvlKhachHang.tìmKhachHangTheoId(UserId);
            boolean wishlist = dvlYeuThich.kiemtra(id, kh.getId());
            model.addAttribute("wishlist", wishlist);
        }
        else{
            boolean wishlist = false;
            model.addAttribute("wishlist", wishlist);
        }
        
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        SanPham dl = dvlSanPham.xemSanPham(id);

        List<BinhLuan> binhluan = dvlBinhLuan.dsBinhLuan_thuoc_SanPham(id);
        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", dl);
        
        // hiện danh sách các bình luận thuộc về sản phẩm đó
        model.addAttribute("bl", binhluan);

        // Nội dung riêng của trang...
        model.addAttribute("content", "KhachHang/chitietsanpham.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "KhachHang/layout.html"; 
    }
    
    @GetMapping("/gioithieu")
    public String getGioiThieu(Model model) {

        List<NhanVien> list = dvlNhanVien.duyệtNhanVien();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);
        
        model.addAttribute("content", "KhachHang/gioithieu.html");
        // ...được đặt vào bố cục chung của toàn website
        return "KhachHang/layout.html"; // layout.html
    }


//  forgot password
    @GetMapping("/khachang/forgotPassword")
    public String showForgotPasswordForm( Model model) {

        model.addAttribute("content", "KhachHang/quenmk.html");
        return "KhachHang/layout.html"; // layout.html
    }

    @GetMapping("/khachang/announcement")
    public String Announcement(Model model) {
        model.addAttribute("content", "KhachHang/thongbao.html");
        return "KhachHang/layout.html"; 
    }

    @PostMapping("/khachang/forgotPassword")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        
        try {
            dvl.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
            
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        
        return "redirect:/khachang/announcement";
    }

    
    public void sendEmail(String recipientEmail, String link)
        throws MessagingException, UnsupportedEncodingException, jakarta.mail.MessagingException {
        MimeMessage message = javamailsender.createMimeMessage();              
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setFrom("contact@shopme.com", "Shopme Support");
        helper.setTo(recipientEmail);
        
        String subject = "Đây là liên kết để đặt lại mật khẩu của bạn";
        
        // <p><a href=\"" + link + "\">Change my password</a></p>
        String content = "<p>Xin chào quý khách,</p>"
        + "<p>Bạn đã yêu cầu đổi mật khẩu thông qua email.</p>"
        + "<p>Click vào link bên dưới để đổi mật khẩu:</p>"
        + "<p><a href=\"" + link + "\">Đổi mật khẩu của tôi</a></p>"
        + "<br>"
        + "<p>Nếu bạn không phải người yêu cầu thiết lập lại mật khẩu,"
        + " thì bạn hãy bỏ qua email này.</p>"
        + "<p>Cảm ơn quý khách,</p>"
        + "<p>Ban quản trị diễn đàn.</p>"
        ;

        helper.setSubject(subject);
        
        helper.setText(content, true);
        
        javamailsender.send(message);
    }
    
    
    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, 
    Model model){
        KhachHang khachhang = dvl.getByResetPasswordToken(token);
        // để chuyển token sang post processResetPassword
        model.addAttribute("token", token);
        if(khachhang == null)
        {
            model.addAttribute("message", "INvalid token");
            return "message";
        }

        model.addAttribute("content", "KhachHang/reset_password_form.html");

            // ...được đặt vào bố cục chung của toàn website
            return "KhachHang/layout.html"; // layout.html
        
    }
    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        System.out.println(password + " new password ");

        KhachHang khachhang = dvl.getByResetPasswordToken(token);

        model.addAttribute("title", "reset your password");
        if(khachhang == null)
        {
            model.addAttribute("message", "Invalid Token");
            return "KhachHang/layout.html";
        }
        else
        {
            dvl.updatePassword(khachhang, password);
            model.addAttribute("content", "KhachHang/thongbao_thanhcong.html");
            // model.addAttribute("message", "You have successfully changed your password.");
        }
        return "KhachHang/layout.html";

    }
}// end class

