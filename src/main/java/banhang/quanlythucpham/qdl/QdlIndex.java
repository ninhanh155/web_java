package banhang.quanlythucpham.qdl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class QdlIndex {

    @GetMapping({"/", "/trangchu"})
    public String indexAction(Model model, HttpSession session, HttpServletRequest request) {
        
        model.addAttribute("content", "KhachHang/index.html");

        return "KhachHang/layout.html"; 
    }

    @GetMapping({"/admin"})
    public String adminAction(Model model, HttpSession session, HttpServletRequest request) {

        if(session.getAttribute("USER_LOGGED")==null)
        {
            request.getSession().setAttribute("LOCATION","/admin");
            return "redirect:/admin/dangnhap";
        }

        return "QuanTri/index.html"; 

    }

}
