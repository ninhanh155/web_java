package banhang.quanlythucpham.dvl;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banhang.quanlythucpham.kdl.KdlKhachHang;
import banhang.quanlythucpham.tdl.KhachHang;

@Service
public class DvlKhachHang implements DviKhachHang
{
    @Autowired private KdlKhachHang kdl;// kho dữ liệu;

    @Override
    public Boolean tồnTạiEmail(String mail)
    {
        return kdl.existsByEmail(mail);
    }
    @Override
    public KhachHang tìmKhachHangTheoEmail(String mail)
    {

        KhachHang dl = null;

        dl = kdl.findOneByEmail(mail);

        return dl;
    }


    @Override public List<KhachHang> dsKhachHang() // getAllThucThe()
    {
  
        // return null;

        // mã bởi lập trình viên:
        return kdl.findAll();
    }

    @Override public List<KhachHang>  duyệtKhachHang() 
    {
        return kdl.findAll();
    }

    @Override public KhachHang  tìmKhachHangTheoId(int id)// 
    {
        KhachHang dl = null;

        Optional<KhachHang> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            //throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung !");
        }

        return dl;

    }

    @Override public KhachHang xemKhachHang(int id)// 
    {

        KhachHang dl = null;

        Optional<KhachHang> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            //throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung !");
        }

        return dl;

    }

    @Override
    public void lưuKhachHang(KhachHang dl)
    {
        this.kdl.save(dl);
    }

    @Override
    public void xóaKhachHang(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

    // quên mật khẩu
    public void updateResetPasswordToken(String token, String email) {
        KhachHang khachhang = kdl.findEmail(email);
        if (khachhang != null) {
           khachhang.setResetPasswordToken(token);
            kdl.save(khachhang);
        } else {
          
        }
    }

    public KhachHang getByResetPasswordToken(String token) {
        return kdl.findByResetPasswordToken(token);
    }

    public void updatePassword(KhachHang khachhang, String newPassword) {

        // var inputPassword = dl.getMatKhau();
        var hash = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
        // dl.setMatKhau(hash);

        System.out.println(newPassword + " new password ");
        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // String encodedPassword = passwordEncoder.encode(newPassword);
        khachhang.setMatKhau(hash);

        khachhang.setResetPasswordToken(null);
        kdl.save(khachhang);
    }

    public Integer sLKhachHang(){
        return kdl.tongKhachHang();
    }
}

