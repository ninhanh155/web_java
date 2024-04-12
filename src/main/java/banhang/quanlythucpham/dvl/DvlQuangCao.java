package banhang.quanlythucpham.dvl;


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

import banhang.quanlythucpham.kdl.KdlQuangCao;
import banhang.quanlythucpham.tdl.QuangCao;

@Service
public class DvlQuangCao implements DviQuangCao
{
    @Autowired private KdlQuangCao kdl;// kho dữ liệu;

    @Override public List<QuangCao> dsQuangCao() // getAllThucThe()
    {
  
        // return null;

        // mã bởi lập trình viên:
        return kdl.findAll();
    }

    @Override public List<QuangCao>  duyệtQuangCao() 
    {
        return kdl.findAll();
    }

    @Override public QuangCao  tìmQuangCaoTheoId(int id)// 
    {
        // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        QuangCao dl = null;

        Optional<QuangCao> optional = kdl.findById(id);

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

    @Override public QuangCao xemQuangCao(int id)// 
    {

        QuangCao dl = null;

        Optional<QuangCao> optional = kdl.findById(id);

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
    public void lưuQuangCao(QuangCao dl)
    {
        MultipartFile file = dl.getFileAnh();
        if (file != null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                String uploadDir = "src/main/resources/static/images/banner/";
        
                if (!Files.exists(Paths.get(uploadDir))) {
                    Files.createDirectories(Paths.get(uploadDir));
                }
        
                String filePath = uploadDir + UUID.randomUUID().toString() + "_" + fileName;
                Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        
                String savedFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
                dl.setAnhQuangCao("/images/banner/" + savedFileName);
            } catch (IOException e) {
                // Xử lý lỗi nếu có
            }
        }
        this.kdl.save(dl);
    }

    @Override
    public void xóaQuangCao(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

}
