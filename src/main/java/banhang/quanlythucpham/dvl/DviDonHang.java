package banhang.quanlythucpham.dvl;

import java.util.List;

import banhang.quanlythucpham.tdl.DonHang;

public interface DviDonHang {
    DonHang luu(DonHang donhang);

    List<DonHang> getDonHangByMaKhacHang(int id);

    DonHang xacNhanDonHang(int id);

    void huyDonHang(int id);

    List<DonHang> dsDonHang();

    List<DonHang> duyệtDonHang();

    // Đọc dữ liệu của một bản ghi
    DonHang tìmDonHangTheoId(int id);// getDonHangById( int id );

    DonHang xemDonHang(int id);

    // Lưu dữ liệu của 1 bản ghi vào db (thêm mới/cập nhật)
    void lưuDonHang(DonHang dl);

    // Xoá 1 dòng bản ghi dữ liệu
    void xóaDonHang(int id);

}
