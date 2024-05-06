package banhang.quanlythucpham.dvl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import banhang.quanlythucpham.kdl.KdlSanPham;
import banhang.quanlythucpham.tdl.SanPham;

@Service
public class DvlSanPham implements DviSanPham
{
    @Autowired private KdlSanPham kdl;// kho dữ liệu;

    
    @Override public List<SanPham> dsSanPham() // getAllThucThe()
    {
        return kdl.findAll();
    }

    @Override public List<SanPham>  duyệtSanPham() 
    {
        return kdl.findAll();
    }

    @Override public List<SanPham> timkiem_sp_theoten(String ten_sp){

        return kdl.findbynameproduct(ten_sp);
    }
    
    
    @Override public List<SanPham> ds_sp_thuoc_MaDanhMuc(int id_danh_muc){

        return kdl.findByMaDanhMuc(id_danh_muc);
    }


    @Override public SanPham  tìmSanPhamTheoId(int id)// 
    {

        SanPham dl = null;

        Optional<SanPham> optional = kdl.findById(id);

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

    @Override public SanPham xemSanPham(int id)// 
    {

        SanPham dl = null;

        Optional<SanPham> optional = kdl.findById(id);

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
    public void lưuSanPham(SanPham dl)
    {
        MultipartFile file = dl.getFileSp();
        if (file != null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                String uploadDir = "src/main/resources/static/images/product/";
        
                if (!Files.exists(Paths.get(uploadDir))) {
                    Files.createDirectories(Paths.get(uploadDir));
                }
        
                String filePath = uploadDir + UUID.randomUUID().toString() + "_" + fileName;
                Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        
                String savedFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
                dl.setAnhSanPham("/images/product/" + savedFileName);
            } catch (IOException e) {
                // Xử lý lỗi nếu có
            }
        }
        this.kdl.save(dl);
    }
    public List<Object[]> thongKeSoLuongSanPhamTheoNhaSanXuat(){
        return kdl.thongKeSoLuongSanPhamTheoNhaSanXuat();
    }

    @Override
    public void xóaSanPham(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

    public Page<SanPham> findPaginated(
	     int pageNo, 
	     int pageSize
	) 
	{
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.kdl.findAll(pageable);
	}
}
