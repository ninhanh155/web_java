package banhang.quanlythucpham.kdl;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import banhang.quanlythucpham.tdl.LienHe;

public interface KdlLienHe extends JpaRepository<LienHe, Integer>
{
    @Query ("SELECT COUNT(*) FROM LienHe")
    Integer tongLienHe();
}
