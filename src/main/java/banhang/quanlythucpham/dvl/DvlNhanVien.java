package banhang.quanlythucpham.dvl;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import banhang.quanlythucpham.kdl.KdlNhanVien;
import banhang.quanlythucpham.tdl.NhanVien;

@Service
public class DvlNhanVien implements DviNhanVien
{
    @Autowired private KdlNhanVien kdl;// kho dữ liệu;

    @Override
    public Boolean tồnTạiEmail(String mail)
    {
        return kdl.existsByEmail(mail);
    }
    @Override
    public NhanVien tìmNhanVienTheoEmail(String mail)
    {
 // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        NhanVien dl = null;

        dl = kdl.findOneByEmail(mail);// OK

        return dl;
    }

    @Override public List<NhanVien> dsNhanVien() // getAllThucThe()
    {
  
        // return null;

        // mã bởi lập trình viên:
        return kdl.findAll();
    }

    @Override public List<NhanVien>  duyệtNhanVien() 
    {
        return kdl.findAll();
    }

    @Override public NhanVien  tìmNhanVienTheoId(int id)// 
    {
        // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        NhanVien dl = null;

        Optional<NhanVien> optional = kdl.findById(id);

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

    @Override public NhanVien xemNhanVien(int id)// 
    {

        NhanVien dl = null;

        Optional<NhanVien> optional = kdl.findById(id);

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
    public void lưuNhanVien(NhanVien dl)
    {
        MultipartFile file = dl.getFileNv();
        if (file != null && !file.isEmpty()){
            try {
                String fileName = file.getOriginalFilename();

                String uploadDir = "src/main/resources/static/images/custumer/";

                // String uploadDir = "src/main/resources/static/img/";

                if(!Files.exists(Paths.get(uploadDir)))
                {
                    new File(uploadDir).mkdirs();
                }
                String filePath = uploadDir + UUID.randomUUID().toString() + "_" +fileName;// target of upload
                // String filePath = uploadDir + fileName;// target of upload


                Files.createFile(Paths.get(filePath));
                Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                
                String savedFileName = filePath.substring(filePath.lastIndexOf("/") + 1); // Lấy tên file đã lưu
                dl.setAnhNhanVien("/images/custumer/" + savedFileName); // Lưu tên file vào trường "image" của đối tượng SamPham
            } catch (IOException e) {
                // Xử lý lỗi nếu có
            }
        }
        this.kdl.save(dl);
    }

    @Override
    public void xóaNhanVien(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

}

