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

import banhang.quanlythucpham.dvl.DvlKhachHang;
import banhang.quanlythucpham.dvl.DvlSanPham;
import banhang.quanlythucpham.dvl.DvlYeuThich;
import banhang.quanlythucpham.tdl.KhachHang;
import banhang.quanlythucpham.tdl.SanPham;
import banhang.quanlythucpham.tdl.YeuThich;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class QdlYeuThich 
{
    @Autowired
    private DvlYeuThich dvl; // cung cấp các dịch vụ thao tác dữ liệu
    
    @Autowired
    private DvlSanPham dvl_sp;

    @Autowired
    private DvlKhachHang dvl_kh;

    
    @GetMapping({
            "/danhsachyeuthich"
    })
    public String getDuyet(Model model ,
    HttpServletRequest request,
    HttpSession session,
    RedirectAttributes redirectAttributes
    ) 
    {
        if(session.getAttribute("USER_ID")==null)
        {
            request.getSession().setAttribute("LOCATION","/danhsachyeuthich");
            return "redirect:/dangnhap";
        }

        session = request.getSession();
        int UserId = (int) session.getAttribute("USER_ID");
        KhachHang kh = dvl_kh.tìmKhachHangTheoId(UserId);
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        System.out.println("id khach hang : " + kh.getId());
        List<SanPham> list = dvl.dsYeuThich(kh.getId());

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "khachHang/yeuthich.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "KhachHang/layout.html";
    }

    @GetMapping("/wishlist/add/{id}")
    public String getThem(Model model , @PathVariable(value = "id") int id, 
    HttpServletRequest request,
    HttpSession session,
    RedirectAttributes redirectAttributes
    ) {

        if (session.getAttribute("USER_ID") == null) {
            request.getSession().setAttribute("LOCATION", "/wishlist/add/" + id);
            return "redirect:/dangnhap";
        }


        session = request.getSession();
        int UserId = (int) session.getAttribute("USER_ID");
        KhachHang kh = dvl_kh.tìmKhachHangTheoId(UserId);
        SanPham sp = dvl_sp.tìmSanPhamTheoId(id);

        System.out.println("hello " + sp.getId());

        YeuThich dl = new YeuThich();

        dl.setMaKhachHang(kh.getId());
        dl.setMaSanPham(sp.getId());
        dvl.lưuYeuThich(dl);


        return "redirect:/danhsachyeuthich";
    }
    @GetMapping("/wishlist/delete/{id}")
    public String getXoa(Model model , @PathVariable(value = "id") int id, 
    HttpServletRequest request,
    HttpSession session,
    RedirectAttributes redirectAttributes
    ) {
        if (session.getAttribute("USER_ID") == null) {
            request.getSession().setAttribute("LOCATION", "/wishlist/delete/" + id);
            return "redirect:/dangnhap";
        }

        session = request.getSession();
        int UserId = (int) session.getAttribute("USER_ID");
        KhachHang kh = dvl_kh.tìmKhachHangTheoId(UserId);
        SanPham sp = dvl_sp.tìmSanPhamTheoId(id);

        System.out.println("id product : " + sp.getId() + " id user : " + kh.getId());

        dvl.xóaYeuThich(sp.getId(),kh.getId());
        
        return "redirect:/danhsachyeuthich";
    }

    @PostMapping("/danhsachyeuthich/them")
    public String postThem(Model model,
    @RequestParam("Id") int id,
    HttpServletRequest request,
    RedirectAttributes redirectAttributes) {


        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("USER_ID");

        KhachHang kh = dvl_kh.tìmKhachHangTheoId(userId);

        SanPham sp = dvl_sp.tìmSanPhamTheoId(id);

        System.out.println("id user" + kh.getId() + "id sanpham : "+ sp.getId());

        YeuThich item = new YeuThich();
        item.setMaKhachHang(kh.getId());
        item.setMaSanPham(sp.getId());
        dvl.lưuYeuThich(item);

        model.addAttribute("content", "khachHang/sanpham.html"); 

        return "redirect:/sanpham";
    }

    @PostMapping("/danhsachyeuthich/xoa")
    public String postXoa(Model model,
    @RequestParam("Id") int id,
    HttpServletRequest request,
    RedirectAttributes redirectAttributes) // request param phải khớp với name="Id" của thẻ html input
    {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("USER_ID");

        KhachHang kh = dvl_kh.tìmKhachHangTheoId(userId);

        SanPham sp = dvl_sp.tìmSanPhamTheoId(id);
        this.dvl.xóaYeuThich(sp.getId(),kh.getId());


        // Điều hướng sang trang giao diện
        return "redirect:/sanpham";
    }


}// end class

