package banhang.quanlythucpham.qdl;

import java.util.List;

// Thư viện web: Java Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import banhang.quanlythucpham.dvl.DvlDonHang;
import banhang.quanlythucpham.dvl.DvlGioHang;
import banhang.quanlythucpham.tdl.DonHang;
import banhang.quanlythucpham.tdl.GioHang;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class QdlDonHang 
{
    @Autowired
    private DvlDonHang dvl; // cung cấp các dịch vụ thao tác dữ liệu

    @Autowired
    private DvlGioHang dvlGioHang;

    @GetMapping({
            "/lichsumuahang"
    })
    public String getLichSuMuaHang(Model model, HttpSession session) 
    {
        Integer UserId = (Integer) session.getAttribute("USER_ID");
        if (UserId == null) {
            return "redirect:/dangnhap";
        }
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        model.addAttribute("dh", this.dvl.getDonHangByMaKhacHang(UserId));

        if(session.getAttribute("status_create_order_success") != null) {
            model.addAttribute("orderSuccess", true);
            session.removeAttribute("status_create_order_success");
        }

        // Nội dung riêng của trang...
        model.addAttribute("content", "KhachHang/lichsumuahang.html");// duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "KhachHang/layout.html";
    }

    @GetMapping({
        "/thanhtoan"
})
    public String getThanhToan(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) 
    {
        session = request.getSession();
        Integer UserId = (Integer) session.getAttribute("USER_ID");
        if (UserId == null) {
            return "redirect:/dangnhap";
        }

        List<GioHang> dsGioHang = dvlGioHang.dsGioHangByUser(UserId);

        long tongTien = dsGioHang.stream()
                         .mapToLong(gioHang -> (long) (gioHang.getSp().getDonGia() * gioHang.getSoLuong()))
                         .sum();


        model.addAttribute("tong", tongTien);

        model.addAttribute("dh", dsGioHang);

        // Nội dung riêng của trang...
        model.addAttribute("content", "KhachHang/thanhtoan.html");// duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "KhachHang/layout.html";
    }

    @PostMapping("/dathang")
    public String postThanhToan(
        @RequestParam("tendaydu") String tendaydu,
        @RequestParam("tinhthanhpho") String tinhthanhpho,
        @RequestParam("quanhuyen") String quanhuyen,
        @RequestParam("phuongxa") String phuongxa,
        @RequestParam("diachi") String diachi,
        @RequestParam("sodienthoai") String sodienthoai,

    Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) 
    {   
        Integer UserId = (Integer) session.getAttribute("USER_ID");

        DonHang donHang = new DonHang();

        List<GioHang> dsGioHang = dvlGioHang.dsGioHangByUser(UserId);
        
        for ( GioHang gioHang : dsGioHang)
        {
            gioHang.setStatus(false);
            dvlGioHang.luuGioHang(gioHang);
        }


        Long tongTien = dsGioHang.stream().mapToLong(gioHang -> (gioHang.getSp().getDonGia() * gioHang.getSoLuong())).sum();

        String diaChiCuThe = diachi + ", " + phuongxa + ", " + quanhuyen + ", " + tinhthanhpho;
        donHang.setTen(tendaydu);
        donHang.setDiaChi(diaChiCuThe);
        donHang.setSoDienThoai(sodienthoai);
        donHang.setDsGioHang(dsGioHang);
        donHang.setTongTien(tongTien);
        donHang.setMaKhachHang(UserId);
        dvl.lưuDonHang(donHang);

        session.setAttribute("status_create_order_success", true);

        return "redirect:/lichsumuahang";
    }
    


    @GetMapping("/admin/donhang/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id, HttpServletRequest request, HttpSession session) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/donhanh/xem/"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        DonHang dl = dvl.xemDonHang(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "Quantri/donhang/xem.html"); 

        return "QuanTri/layout.html";    // layout.html
    }

        
    @GetMapping({
            "/admin/donhang",
            "/admin/donhang/duyet"
    })
    public String getDuyet(Model model, HttpSession session, HttpServletRequest request) 
    {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/donhang/duyet");
            return "redirect:/admin/dangnhap";
        }

        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<DonHang> list = dvl.duyệtDonHang();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/donhang/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; 
    }

     @GetMapping("/admin/donhang/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id, HttpSession session, HttpServletRequest request) {
        if(session.getAttribute("ADMIN_USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin/donhang/xoa"+id);
            return "redirect:/admin/dangnhap";
        }
        // Lấy ra bản ghi theo id
        DonHang dl = dvl.tìmDonHangTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "QuanTri/donhang/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
        return "QuanTri/layout.html"; // QuanTri/layout.html
    }

    @PostMapping("/admin/donhang/xoa")
    public String postXoa(Model model, @RequestParam("Id") int id) // request param phải khớp với name="Id" của thẻ html input
    {
        // Xoá dữ liệu
        this.dvl.xóaDonHang(id);

        // Điều hướng sang trang giao diện
        return "redirect:/admin/donhang/duyet";
    }
}// end class

