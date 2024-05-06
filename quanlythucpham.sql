-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 06, 2024 lúc 02:56 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlythucpham`
--
CREATE DATABASE IF NOT EXISTS `quanlythucpham` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `quanlythucpham`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `binh_luan`
--

DROP TABLE IF EXISTS `binh_luan`;
CREATE TABLE IF NOT EXISTS `binh_luan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ma_khach_hang` int(11) NOT NULL,
  `ma_san_pham` int(11) NOT NULL,
  `noi_dung` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp6cnyspen4g7vpik62fg9j2t0` (`ma_khach_hang`),
  KEY `FKbnd7p9cndann9ktefkl1qdfmw` (`ma_san_pham`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `binh_luan`
--

INSERT INTO `binh_luan` (`id`, `ma_khach_hang`, `ma_san_pham`, `noi_dung`) VALUES
(1, 3, 2, 'Chất lượng sản phẩm tuyệt vời, đóng gói sản phẩm rất chắc chắn'),
(5, 12, 12, 'Cá rất tươi mới và sạch sẽ. Nhân viên rất thân thiện và nhiệt tình.'),
(6, 12, 16, 'Ếch làm sạch sẽ ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cai_dat`
--

DROP TABLE IF EXISTS `cai_dat`;
CREATE TABLE IF NOT EXISTS `cai_dat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gia_tri` varchar(255) DEFAULT NULL,
  `khoa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `cai_dat`
--

INSERT INTO `cai_dat` (`id`, `gia_tri`, `khoa`) VALUES
(1, 'ninhanh155@gmail.com', 'email'),
(2, '128A Hồ Tùng Mậu, Cầu Giấy', 'diachi'),
(3, '0855171146', 'phone');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danh_muc`
--

DROP TABLE IF EXISTS `danh_muc`;
CREATE TABLE IF NOT EXISTS `danh_muc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten_danh_muc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `danh_muc`
--

INSERT INTO `danh_muc` (`id`, `ten_danh_muc`) VALUES
(1, 'Rau Củ'),
(2, 'Thủy Sản'),
(3, 'Thịt'),
(4, 'Trái Cây'),
(5, 'Trứng'),
(6, 'Đồ Uống'),
(7, 'Gạo'),
(8, 'Sữa');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `don_hang`
--

DROP TABLE IF EXISTS `don_hang`;
CREATE TABLE IF NOT EXISTS `don_hang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(255) DEFAULT NULL,
  `ma_khach_hang` int(11) NOT NULL,
  `ngay_dat` datetime(6) DEFAULT NULL,
  `so_dien_thoai` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `tong_tien` bigint(20) NOT NULL,
  `trang_thai` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhimbr4rcx5fwmced2i28svhak` (`ma_khach_hang`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `don_hang`
--

INSERT INTO `don_hang` (`id`, `dia_chi`, `ma_khach_hang`, `ngay_dat`, `so_dien_thoai`, `ten`, `tong_tien`, `trang_thai`) VALUES
(3, 'xóm 1, Xã Lưu Phương, Huyện Kim Sơn, Tỉnh Ninh Bình', 12, '2024-05-03 09:27:41.000000', '0855171146', 'bảo', 28000, 'Đang xử lý'),
(4, 'xóm 1, Phường Trúc Bạch, Quận Ba Đình, Thành phố Hà Nội', 12, '2024-05-03 09:30:27.000000', '0123456789', 'bảo', 124000, 'Đang xử lý'),
(5, '128A, Phường Mai Dịch, Quận Cầu Giấy, Thành phố Hà Nội', 3, '2024-05-05 12:11:55.000000', '0565127992', 'Ninh Anh', 414000, 'Đang xử lý');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `don_hang_ds_gio_hang`
--

DROP TABLE IF EXISTS `don_hang_ds_gio_hang`;
CREATE TABLE IF NOT EXISTS `don_hang_ds_gio_hang` (
  `don_hang_id` int(11) NOT NULL,
  `ds_gio_hang_id` int(11) NOT NULL,
  UNIQUE KEY `UK_3lmd65v2aw0a9w6q1snyr49ox` (`ds_gio_hang_id`),
  KEY `FKkf5yx7vdr8ugwic6jfh5ww4y0` (`don_hang_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `don_hang_ds_gio_hang`
--

INSERT INTO `don_hang_ds_gio_hang` (`don_hang_id`, `ds_gio_hang_id`) VALUES
(3, 152),
(4, 153),
(4, 154),
(5, 202),
(5, 252),
(5, 253);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `gio_hang`
--

DROP TABLE IF EXISTS `gio_hang`;
CREATE TABLE IF NOT EXISTS `gio_hang` (
  `id` int(11) NOT NULL,
  `ma_khach_hang` int(11) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `status` bit(1) NOT NULL,
  `ma_san_pham` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3wnvjl1ttup74jl17uyrcyuov` (`ma_khach_hang`),
  KEY `FK3j3osx4bt8nfgk5yxsrj3gdjt` (`ma_san_pham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `gio_hang`
--

INSERT INTO `gio_hang` (`id`, `ma_khach_hang`, `so_luong`, `status`, `ma_san_pham`) VALUES
(102, 3, 1, b'0', 2),
(103, 3, 1, b'0', 8),
(104, 3, 1, b'0', 19),
(152, 12, 1, b'0', 8),
(153, 12, 3, b'0', 7),
(154, 12, 1, b'0', 37),
(155, 12, 1, b'1', 4),
(202, 3, 3, b'0', 4),
(252, 3, 1, b'0', 27),
(253, 3, 1, b'0', 31),
(302, 13, 1, b'1', 8);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `gio_hang_seq`
--

DROP TABLE IF EXISTS `gio_hang_seq`;
CREATE TABLE IF NOT EXISTS `gio_hang_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `gio_hang_seq`
--

INSERT INTO `gio_hang_seq` (`next_val`) VALUES
(401);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khach_hang`
--

DROP TABLE IF EXISTS `khach_hang`;
CREATE TABLE IF NOT EXISTS `khach_hang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `mat_khau` varchar(255) DEFAULT NULL,
  `ten_day_du` varchar(255) DEFAULT NULL,
  `xac_nhan_mat_khau` varchar(255) DEFAULT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khach_hang`
--

INSERT INTO `khach_hang` (`id`, `email`, `mat_khau`, `ten_day_du`, `xac_nhan_mat_khau`, `reset_password_token`) VALUES
(3, 'ninhanh155@gmail.com', '$2a$12$.n82zNTxF2ALada9/UmtKeRvE5awpEcavx9jzH.tHA2euFDp9DzN.', 'Ninh Anh', '$2a$10$KVCEkZQT8aUXa0JwLhpkFOOcB3xQniMV3p3AdUnrfDtdpqc5ZI6.S', NULL),
(12, 'bphung08@gmail.com', '$2a$10$N0hiXpm6RP5J4eMKJSIdOOCfY1EaG59DLqnJlz8KA/EKnUsteBrYy', 'Phùng Ngọc Bảo', '$2a$10$0an5iZyYmfVsEb7ExPA49eNInVXn14K1vgs/JvFJy/KKOyY4mqNL2', NULL),
(13, 'markson2268@gmail.com', '$2a$10$ZH5xpCwqH0VaBRppuOCzr.8tW5jO4l0AetKuRHCy5C0BaTRM2mHcy', 'Nguyễn Văn Sơn', '$2a$10$1fsw79OnzGR5O.9KHwTU1.3yYBAlwJP2lhJDU.B8Rhy2VQ43qs3q6', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lien_he`
--

DROP TABLE IF EXISTS `lien_he`;
CREATE TABLE IF NOT EXISTS `lien_he` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `ngay_phan_hoi` date DEFAULT NULL,
  `phan_hoi` varchar(255) DEFAULT NULL,
  `so_dien_thoai` varchar(255) DEFAULT NULL,
  `ten_khach` varchar(255) DEFAULT NULL,
  `tieu_de` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `lien_he`
--

INSERT INTO `lien_he` (`id`, `email`, `ngay_phan_hoi`, `phan_hoi`, `so_dien_thoai`, `ten_khach`, `tieu_de`) VALUES
(1, 'bao234@gmail.com', '2024-04-15', 'Dị Vật Trong Rau', '8347347763', 'Mai My', 'Có sâu ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhan_vien`
--

DROP TABLE IF EXISTS `nhan_vien`;
CREATE TABLE IF NOT EXISTS `nhan_vien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anh_nhan_vien` varchar(255) DEFAULT NULL,
  `dien_thoai` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mat_khau` varchar(255) DEFAULT NULL,
  `mo_ta_nhan_vien` varchar(255) DEFAULT NULL,
  `ten_day_du` varchar(255) DEFAULT NULL,
  `ngay_taonv` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhan_vien`
--

INSERT INTO `nhan_vien` (`id`, `anh_nhan_vien`, `dien_thoai`, `email`, `mat_khau`, `mo_ta_nhan_vien`, `ten_day_du`, `ngay_taonv`) VALUES
(3, '/images/custumer/6619805f-ab5d-448e-af1c-6d0fbf5c54b4_admin.jpg', '0123456789', 'ninhanh155@gmail.com', '$2a$12$HSGVe6A361J317qSTgQnW.OGML3FU.hoSQ3x7W1XgQjp6mbtPxa8y', 'Admin', 'Ninh Anh', '2024-04-07'),
(6, '/images/custumer/f688e7c5-d895-46dd-afb0-6836364bdf7e_nhanvien1.jpg', '0912833407', 'markson2268@gmail.com', '$2a$12$AiiveQ0icusjWsHsl2mKCugGiSPa7oV/oQ8RTNp/b.kYdOX6/NCy6', 'Nhân Viên 1', 'Nguyễn Văn Sơn', '2024-04-09'),
(7, '/images/custumer/6bd1aeb4-6047-436f-abe7-c1c4dc02b742_nhanvien2.jpg', '039999999', 'bphung08@gmail.com', '$2a$12$Er9XmZAN3PBdpQZYVyGYMuPDXF8yzf1Xd0QuEnH16vfkEDyR89pbS', 'Chủ Tịch Bảo', 'Phùng Ngọc Bảo', '2024-04-07'),
(8, '/images/custumer/e280a1a8-541d-4764-8d8a-2f007561926b_nhanvien3.jpg', '0364684245', 'mikehuyen29@gmail.com', '$2a$12$pMZjQNeiPlZKnklyHkSaZ.kliOB.3Vj4SLrA6V3T5Fm.XE3x9JtKy', 'Thư Ký ', 'Trần Thị Thanh Huyền', '2024-04-07');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nha_san_xuat`
--

DROP TABLE IF EXISTS `nha_san_xuat`;
CREATE TABLE IF NOT EXISTS `nha_san_xuat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anh_nha_san_xuat` varchar(255) DEFAULT NULL,
  `ten_nha_san_xuat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nha_san_xuat`
--

INSERT INTO `nha_san_xuat` (`id`, `anh_nha_san_xuat`, `ten_nha_san_xuat`) VALUES
(3, '/images/4474beb1-1028-4616-9b5b-34c72da094e0_saoTa.png', 'Sao Ta'),
(4, '/images/bed34f19-b97b-4ae0-93b8-7a3a36074989_Nestle.png', 'Nestle Việt Nam'),
(5, '/images/be7cdcdd-4062-48bd-94a7-3e6220e710e4_kido.png', 'KiDo'),
(6, '/images/707d2e37-0197-47b4-b3d3-9ce1f8937a21_HươngVietSinh.jpg', 'Hương Việt Sinh'),
(7, '/images/314205be-0b9b-4096-9661-d77413e2d7cb_HaiHa.png', 'Hải Hà'),
(8, '/images/01aa1929-e924-49c6-a6c3-a438826debf2_danon.png', 'Dân Ôn'),
(9, '/images/34fbe6ef-f2bf-4d8d-a202-8c191dd937c1_CP_Bibica.png', 'BiBiCa'),
(10, '/images/cde9ba64-3402-4173-ae46-eac5e9d14270_BinhVinh.png', 'Bình Vinh'),
(11, '/images/ec070427-dbd1-43ea-a0a0-c51b316b972e_Asiafood.png', 'AsiaFood'),
(12, '/images/d2ceeb67-175b-4a8f-bb75-47ca971df20c_AnKhang.jpg', 'An Khang'),
(13, '/images/779bb0f1-7656-4fca-bc23-fdfb2f94f682_Anco.jpg', 'An Cô');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quang_cao`
--

DROP TABLE IF EXISTS `quang_cao`;
CREATE TABLE IF NOT EXISTS `quang_cao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anh_quang_cao` varchar(255) DEFAULT NULL,
  `ten_anh` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `quang_cao`
--

INSERT INTO `quang_cao` (`id`, `anh_quang_cao`, `ten_anh`) VALUES
(1, '/images/banner/bb790808-76e1-451c-b031-f76d3686ffb6_banner2.png', 'Trái Cây'),
(2, '/images/banner/bc50a72a-af97-40c0-a7df-625e525cafc4_banner 1.png', 'Rau Củ'),
(3, '/images/banner/5f90089c-5302-4378-992f-240da314c237_OIP.jpg', 'Thịt'),
(7, '/images/banner/ebb112d3-18ea-48e5-9581-b95a466a0bd1_vetget.jpg', 'Củ Quả');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `san_pham`
--

DROP TABLE IF EXISTS `san_pham`;
CREATE TABLE IF NOT EXISTS `san_pham` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anh_san_pham` varchar(255) DEFAULT NULL,
  `ban_chay` bit(1) NOT NULL,
  `don_gia` int(11) NOT NULL,
  `ma_danh_muc` varchar(255) DEFAULT NULL,
  `ma_nha_san_xuat` varchar(255) DEFAULT NULL,
  `mo_ta` text DEFAULT NULL,
  `noi_bat` bit(1) NOT NULL,
  `ten_san_pham` varchar(255) DEFAULT NULL,
  `trang_thai` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `san_pham`
--

INSERT INTO `san_pham` (`id`, `anh_san_pham`, `ban_chay`, `don_gia`, `ma_danh_muc`, `ma_nha_san_xuat`, `mo_ta`, `noi_bat`, `ten_san_pham`, `trang_thai`) VALUES
(1, '/images/product/4c37f9d8-978f-4a68-8c72-62f383ff03d7_CaiBap.png', b'1', 19000, '1', '3', 'Bắp cải mộc châu là một loại rau phổ biến trong mùa thu – đông và quen thuộc trong các bữa ăn gia đình, bắp cải không chỉ có giá trị dinh dưỡng cao mà còn chữa được nhiều bệnh.', b'0', 'Cải Bắp', b'1'),
(2, '/images/product/663c32ff-5b70-4579-955c-02642db116b4_caithia.jpg', b'0', 14000, '1', '3', 'Cải Thìa là một loại rau dễ ăn, có hình dáng và màu sắc đẹp, giàu dinh dưỡng và xuất hiện trong nhiều công thức nấu ăn ', b'1', 'Cải Thìa', b'1'),
(4, '/images/product/9d178797-4a53-4acd-8a46-84524c6b24ce_carot.png', b'1', 12000, '1', '10', 'Cà Rốt là loại cây ăn củ, thực chất là rễ cây. Cà rốt là nguồn dồi dào các vitamin và dinh dưỡng có lợi cho cơ thể con người.', b'0', 'Cà Rốt', b'0'),
(5, '/images/product/0b865a9b-f6ae-40a8-9e9b-d6e19bd29309_namhuong.jpg', b'0', 36000, '1', '6', 'Nấm hương còn có tên gọi khác là nấm Đông cô, là một loại nấm sạch quá quen thuộc với ẩm thực Việt Nam. Nấm hương còn là một vị thuốc quý có nhiều tác dụng bất ngờ trong y học', b'1', 'Nấm Hương', b'0'),
(6, '/images/product/7d9c246b-1f08-4750-86a2-4bde667d9a59_caithao.jpg', b'0', 24000, '1', '7', 'Cải thảo có màu sắc khá giống với cải bắp, phần lá bao ngoài của màu xanh đậm, còn lá cuộn ở bên trong (gọi là lá non) có màu xanh nhạt, trong khi phần cuống lá có màu trắng.', b'1', 'Cải Thảo', b'1'),
(7, '/images/product/59e0a2d5-3994-4e83-9487-2bce8e0a4dca_supno.jpg', b'1', 31000, '1', '10', 'Bông cải xanh Bắc là một loại thực phẩm có giá trị, chứa nhiều chất chống oxy hóa, chất xơ và nhiều vitamin, khoáng chất, tăng cường sức khỏe và tuổi thọ.', b'1', 'Bông Cải Xanh', b'1'),
(8, '/images/product/f3a27d3e-4c85-4082-b2fe-86d70a98bc47_cachua.jpg', b'0', 28400, '1', '5', 'Cà chua chứa nhiều chất dinh dưỡng có lợi cho cơ thể như carotene, lycopene, vitamin và kali. Đặc biệt vitamin B, vitamin C', b'1', 'Cà Chua', b'1'),
(9, '/images/product/a2f59410-ee55-4fae-9234-aa31a3cf615a_khoaitay.jpg', b'1', 22600, '1', '9', 'Khoai tây bắc ruột vàng, khi luộc dễ chín, ăn vừa miệng. Khoai có tính bột cao, ngọt, có thể chế biến thành nhiều món, trong đó có làm bánh.', b'0', 'Khoai Tây', b'1'),
(10, '/images/product/661c381f-7037-4d74-8d21-adca7399c7f6_caiboxoi.jpg', b'1', 33000, '1', '9', 'Cải Bó Xôi hay còn gọi là rau chân vịt, cải bó xôi thường có cuống nhỏ và lá xanh đậm, lá mọc chụm lại ở một gốc bé xíu.', b'1', 'Cải Bó Xôi', b'0'),
(11, '/images/product/4902259d-13f0-4eff-b1de-9939eb68dbb8_xalach.png', b'0', 49000, '1', '7', 'Xà lách Romaine so với xà lách Lolo thì lá giòn và chắc hơn. Cũng như các loại xà lách khác, xà lách Romaine chứa rất nhiều nước, chất xơ, vitamin A, C và axit folic bổ sung cho cơ thể', b'1', 'Xà Lách', b'1'),
(12, '/images/product/82d82147-7553-4147-9d51-041c503f9c47_cahoi.jpg', b'1', 259000, '2', '11', 'Cá hồi nổi tiếng là loại cá có thể ăn sống cùng mù tạt, thịt cá vị ngọt đậm đà, khác biệt hẳn so với các loại cá khác, khi ăn không cảm thấy tanh và khiến cho người ăn cảm thấy mát lạnh tan trong lưỡi.', b'0', 'Cá Hồi Phi Lê', b'1'),
(13, '/images/product/1c0e3b0d-6255-474c-b026-0bea4694d81d_casaba.jpg', b'0', 57000, '2', '11', 'Cá saba có nhiều chất dinh dưỡng tốt cho sức khỏe, mang lại nhiều lợi ích cho sức khỏe như: Cung cấp protein, cung cấp axit béo Omega-3, DHA và EPA giúp giảm rủi ro các bệnh về tim và các bệnh mãn tính khác.', b'1', 'Cá SaBa', b'1'),
(14, '/images/product/924e1698-ca5d-49ee-9302-2d07343c9d74_tomthe.png', b'1', 82000, '2', '11', 'Tôm thẻ là thực phẩm được các bà nội trợ tin dùng hiện nay. Bởi nó chứa ít calo nhưng lại cung cấp rất nhiều chất dinh dưỡng cần thiết cho cơ thể như Protein, Vitamin B12, sắt, canxi, Omega 3', b'0', 'Tôm Thẻ', b'1'),
(15, '/images/product/940b4b1e-e08e-4806-a35d-a91481823466_cabasa.jpg', b'1', 47000, '2', '11', 'Basa là một nguồn protein chất lượng cao. Protein đóng vai trò quan trọng trong cơ thể người, bao gồm sự phát triển của các mô của cơ thể và sản xuất các enzyme chủ chốt.', b'0', 'Cá Ba Sa', b'1'),
(16, '/images/product/2f338dc0-0b00-42d2-9c05-f785d04c6d96_ech.png', b'1', 52000, '2', '11', 'Ếch là một trong những thực phẩm bổ dưỡng được nhiều người ưa thích. Thịt ếch có màu trắng, nạc, ngon như thịt gà với thành phần dinh dưỡng phong phú như protein, chất béo, đường, canxi, phốt pho, kali, natri, sắt, đồng', b'0', 'Ếch', b'1'),
(17, '/images/product/2e1f39ff-7541-4bd1-88ec-e83741233165_mucong.jpg', b'0', 219000, '2', '11', 'Mực ống làm sạch luôn là thực phẩm được nhiều chị em nội trợ chọn lựa bởi nó có hương vị, độ ngon riêng đồng thời có thể chế biến được nhiều món ăn.', b'0', 'Mực Ống', b'1'),
(18, '/images/product/9313e563-da37-4ba1-ba39-2a19628c5ebe_catrung.jpg', b'1', 29000, '2', '11', 'á trứng có da màu ánh bạc, kích thước nhỏ chỉ bằng 2 ngón tay. Phần xương nhỏ, thịt cá mềm, da cá mỏng. Bụng cá luôn chứa đầy trứng dù không phải là mùa sinh sản.', b'0', 'Cá Trứng', b'1'),
(19, '/images/product/43681b62-9977-4207-beb0-37e650b6a510_cachim.jpg', b'1', 209000, '2', '11', 'Cá Chim Trắng là loài cá đặc sản có giá trị kinh tế cao, thịt thơm ngon, giàu chất dinh dưỡng, giàu đạm và Omega 3 rất có lợi cho sức khỏe. Cá ăn được cả vây, ít xương, thơm và béo ngậy.', b'0', 'Cá Chim Trắng', b'1'),
(20, '/images/product/6be22913-4b39-4bcc-aace-b1f105d88318_baroi.jpg', b'1', 130000, '3', '10', 'Ba Rọi Heo hay còn gọi thịt ba chỉ có lớp thịt và lớp mỡ xen kẽ tạo nên ba đường chỉ đẹp mắt. Thịt ba rọi chứa rất nhiều protein, chất khoáng và vitamin, cung cấp nguồn năng lượng đáng kể cho cơ thể.', b'0', 'Thịt Heo Ba Rọi', b'1'),
(21, '/images/product/94c12a1e-a5eb-4069-a69f-2e384215b5e2_duiheo.jpg', b'0', 66000, '3', '10', 'Thịt đùi heo là phần thịt đặc trưng được lấy từ đùi sau của heo bao gồm nạc, mỡ và da. Đây là một trong những nguyên liệu quen thuộc giúp bà nội trợ chế biến các món ăn ngon.', b'0', 'Thịt Đùi Heo', b'1'),
(22, '/images/product/6a272dcb-4811-42b2-b520-250c55804580_canhga.jpg', b'0', 39000, '3', '6', 'Cánh gà là phần thịt được pha lóc từ những con gà đạt chuẩn sạch, chất thịt ngọt, dai, ngon, ít mỡ, xương mềm hơn các bộ phận khác nên được người tiêu dùng rất thích.', b'0', 'Cánh Gà', b'1'),
(23, '/images/product/6338ff65-41ad-4d7a-ab76-1309b12a5869_duiga.jpg', b'0', 45000, '3', '6', 'Đùi tỏi gà CP được nuôi dưỡng và chăm sóc trong chuồng kín với hệ thống tự động hiện đại. Trải qua quá trình giết mổ khép kín, đóng gói và bảo quản bằng công nghệ tiên tiến giúp sản phẩm được giao đến khách hàng trong tình trạng tươi ngon và an toàn.', b'0', 'Đùi Gà', b'1'),
(24, '/images/product/4c8173ef-9ab9-48ce-a16e-a9ab87d8a1fc_thitbo.jpg', b'0', 107000, '3', '6', 'Thịt thăn bò là phần thịt nằm từ giữa bụng bò cho đến gần mông. Thăn bò là phần thịt rất mềm và dễ chế biến. Được tuyển chọn kỹ càng từ những chú bò đạt chuẩn, thịt thăn bò sẽ là nguyên liệu bổ dưỡng tuyệt vời cho các món ăn.', b'0', 'Thăn Bò', b'1'),
(25, '/images/product/f58da0b2-b219-425e-8f2d-535ca46d9473_sunheo.jpg', b'0', 80000, '3', '6', 'Sườn sụn non của heo là phần thịt được lọc ra ở đầu của miếng sườn heo, thường có nhiều thịt mềm ở giữa là phần sụn giòn sần sật. Phần sụn này chính là phần bắt đầu của xương', b'0', 'Sườn Sụn Heo', b'1'),
(26, '/images/product/3c36ee67-dbdf-4546-b8f9-78711817c493_gaac.jpg', b'0', 54000, '3', '7', 'Gà ác nguyên con làm sạch CP 180-200g lấy từ nguồn gà được nuôi tại các trang trại có nguồn gốc xuất xứ rõ ràng, thịt gà ác đạt vệ sinh an toàn thực phẩm theo tiêu chuẩn chất lượng HACCP', b'0', 'Gà Ác', b'0'),
(27, '/images/product/3cc4e725-d8f1-4b98-af63-5b25914b5c1f_dautay.jpg', b'0', 299000, '4', '12', 'Dâu tây nhập khẩu Hàn Quốc được nhập khẩu trực tiếp từ Hàn Quốc. Dâu tây Hàn Quốc sở hữu màu đỏ tươi quyến rũ, quả rất to, thịt dày, vị ngọt thanh, mọng nước, chỉ cần cắn một miếng đủ nhớ mãi hương thơm dịu dàng của loại quả đỏ mọng này.', b'0', 'Dâu Tây ', b'1'),
(28, '/images/product/a97dd3fc-d63a-49a1-a9df-b538d141591e_dualuoi.jpg', b'1', 67300, '4', '13', 'Dưa lưới ruột cam là loại trái cây được rất nhiều người ưa thích vì màu sắc đẹp mắt và vị ngon ngọt. Dưa lưới ngon nhất khi phần cuống lõm tròn, có hình răng cưa.', b'1', 'Dưa Lưới', b'1'),
(29, '/images/product/f7b159d7-771b-4837-a911-e046ae983ba0_duahau.jpg', b'1', 84000, '4', '13', 'Dưa hấu ruột đỏ có vỏ màu xanh và sọc dưa đậm, ruột đỏ, hột đen. Quả có vỏ cứng, dày, ăn rất ngon, độ đường cao, hương vị thơm ngon, hấp dẫn.', b'0', 'Dưa Hấu', b'1'),
(30, '/images/product/d1ab11cd-ce4b-4845-adaa-cef14982e3d3_xoaicat.jpg', b'1', 30000, '4', '7', 'Xoài Cát Chu rất ít xơ, hương thơm nồng nàn quyến rũ, vị ngọt đậm đà, ăn mềm lại hơi dai, cảm giác miếng xoài tan dần trong miệng và vị ngọt dịu vẫn đọng lại trên đầu lưỡi thật khó chối từ.', b'0', 'Xoài Cát Chu', b'1'),
(31, '/images/product/9a2efdf0-5bc4-4f3f-aa18-334e7d9d6166_nhoxanh.jpg', b'1', 79000, '4', '7', 'Nho xanh không hạt nhập khẩu Úc (size trái 20-22mm) được xem là một trong những loại trái cây được yêu thích nhất, và cũng là một trong những giống cây ăn quả lâu đời nhất của xứ sở chuột túi.', b'0', 'Nho Xanh', b'0'),
(32, '/images/product/54b028d5-1bff-472b-889a-db442fadc91e_chomchom.jpg', b'0', 43000, '4', '9', 'Chôm chôm giống Thái có ưu điểm hạt dẹt nhỏ, cơm dày trắng mọng nước và ngọt thanh không quá gắt như chôm nhãn, cũng không chua như chôm thường.', b'1', 'Chôm Chôm', b'0'),
(33, '/images/product/4ed32386-e27a-4c8a-8cbc-69c6d3d0e9bc_man.jpg', b'0', 52000, '4', '8', 'Mận An Phước có hình dáng như một cái hồ lô thu nhỏ, màu đỏ đô nổi bật của mận luôn tạo cảm giác hấp dẫn vị giác người dùng rất hiệu quả.', b'0', 'Mận An Phước', b'1'),
(34, '/images/product/384d85f6-6b3f-4599-adfa-369a4c2a7fec_bosap.jpg', b'0', 49000, '4', '8', 'Bơ sáp nổi tiếng là loại bơ có phần cơm có màu vàng, độ dẻo cao và vị béo nhẹ. Vỏ quả bơ có màu xanh bóng láng, bơ sáp ăn rất béo, rất bùi.', b'0', 'Bơ Sáp', b'1'),
(35, '/images/product/23060ca9-8e4c-423d-a75d-1d578938228a_trungga.jpg', b'1', 32000, '5', '4', 'Trứng Gà Dabaco Hộp 10 Quả được chọn lọc kĩ càng từ những trang trại đạt chất lượng chăn nuôi cao. Sau đó trứng gà được làm sạch, xử lý ozone và tia cực tím.', b'0', 'Trứng Gà', b'1'),
(36, '/images/product/aa1928e6-cba6-4090-807b-dfb1d9acc0bc_trungvit.jpg', b'0', 31000, '5', '4', 'Trứng vịt thả đồng là loại trứng giàu dinh dưỡng và phổ biến trong bữa cơm của nhiều gia đình.  Trứng vịt thả đồng có lòng đỏ to, màu đậm hơn trứng vịt thường.', b'0', 'Trứng Vịt', b'1'),
(37, '/images/product/86c8c02c-66c5-4640-8de0-068042654cf3_trungcut.jpg', b'0', 31000, '5', '8', 'Trứng Chim Cút Ba Vì Hộp là loại thức ăn có giá trị dinh dưỡng đặc biệt cao. Trong trứng có đủ chất đạm, chất béo, vitamin, chất khoáng, các loại men và hormone.', b'0', 'Trứng Chim Cút', b'0'),
(38, '/images/product/38344ea3-bd35-4498-8166-f2dcb24e7df9_st25.png', b'1', 249000, '7', '5', 'Gạo Dẻo Thơm Đặc Sản ST25 Neptune được sản xuất từ giống lúa ST25 trứ danh của vùng đất Đồng Bằng Sông Cửu Long. Mang trong mình phẩm chất thượng hạng cùng hương vị tuyệt hảo', b'0', 'Gạo ST25 Neptune', b'1'),
(39, '/images/product/8d8a2fc6-c648-4335-81b2-27ab0f415855_st24.png', b'0', 216000, '7', '5', 'Giống lúa: Giống ST24 Đặc tính: Dẻo nhiều, cơm mềm Độ nở: Nở vừa Sản phẩm được đảm bảo 3 không: KHÔNG chất bảo quản, KHÔNG chất tạo mùi và KHÔNG dư lượng thuốc trừ sâu', b'0', 'Gạo Thơm ST24', b'1'),
(40, '/images/product/285ec422-183c-42af-900b-ec0e09dd361e_gaonhat.png', b'1', 185000, '7', '5', 'Gạo Neptune Japonica được sản xuất từ giống lúa Japonica nổi tiếng có nguồn gốc từ Nhật Bản, đất nước có nền văn hóa ẩm thực tinh tế. ', b'0', 'Gạo Nhật Neptune', b'0'),
(41, '/images/product/0d6a5bd4-f24f-42db-ae24-a0e79b80e4c7_baominhst25.png', b'0', 125000, '7', '12', 'Gạo Bảo Minh ST25 Lúa Ruộng Rươi Túi 3kg được sản xuất và canh tác bằng giống gạo ST25, vốn được xem là giống gạo ngon và phổ biến bậc nhất ở nước ta, lúa được trồng và canh tác bởi sự kết hợp đặc biệt giữa môi trường trồng lúa', b'0', 'Gạo Bảo Minh', b'0'),
(42, '/images/product/a16449a5-23cc-4733-b833-350a815d89e7_gaohan.png', b'0', 177000, '7', '12', 'Hạt dài thon, thơm đậm hương lài. Đặc tính cơm: dẻo, mềm, thơm, ngọt đậm. Cơm để nguội vẫn mềm, ngọt. Gợi ý món ngon: Ngoài nấu cơm, bạn cũng có thể sử dụng loại gạo này để chiên, nấu cháo, nghiền thành bột', b'0', 'Gạo Hàn Quốc', b'1'),
(43, '/images/product/aef1b5dc-05ab-4336-af8a-64a5364cee91_gaonut.png', b'1', 99000, '7', '3', 'Gạo Lức Huyết Rồng Phú Minh Tâm là giống gạo màu đỏ nâu, hạt gạo thon dài ruột phớt hồng, có chứa hàm lượng dưỡng chất cao, đặc biệt là lipit, gluxit, chất xơ, vitamin A, B1, Omega 3, 6, 9', b'0', 'Gạo Nứt', b'0'),
(44, '/images/product/a87317a3-d0fb-450c-ad08-c87cc0a20c3a_epcam.png', b'0', 18000, '6', '12', 'Nước Ép Cam TH True Juice với 99,94% nước cam ép từ nước cam cô đặc, sản phẩm giàu vitamin C và là nguồn cung cấp Kali, Magie, Folat giúp tăng cường khả năng miễn dịch và chống oxy hóa của cơ thể.', b'1', 'Nước Ép Cam', b'1'),
(45, '/images/product/b139c313-5741-4efd-afb8-9416a8d8b70a_epdao.jpg', b'0', 41000, '6', '12', 'Nước ép đào Juver được sản xuất trên dây chuyền công nghệ hiện đại tiêu chuẩn EU, không có chất bảo quản, không thêm đường, không chất tạo màu, đảm bảo an toàn thực phẩm.', b'1', 'Nước Ép Đào', b'0'),
(46, '/images/product/e1cde7c2-d6c2-4f1f-aff6-43f257752447_th.png', b'1', 12000, '6', '12', 'Nước Sữa Trái Cây TH True Juice Milk Vị Việt Quất Tự Nhiên Chai 300ml mang tới nguồn năng lượng, vitamin và khoáng chất hoàn toàn từ thiên nhiên cùng hương vị thơm ngon – sảng khoái – mới mẻ, phù hợp với tất cả mọi người', b'0', 'Sữa Trái Cây', b'0'),
(47, '/images/product/fb55c59b-14fa-4a9a-8945-884c0d5d52c8_twister.png', b'0', 49000, '6', '12', 'Nước Cam Ép Twister được chiết xuất từ những tép cam tươi nguyên chất tươi ngon và bổ dưỡng, bổ sung thêm nhiều vitamin A, C và năng lượng cho cơ thể.', b'0', 'Nước Cam Twister', b'1'),
(48, '/images/product/f2f03988-0fad-4658-ade1-b7b84be5ed81_vinamilk.png', b'0', 31000, '8', '7', 'Sữa Tươi Tiệt Trùng Vinamilk Không Đường Hộp 1L là thức uống có lợi cho sức khỏe nên được nhiều gia đình ưa chuộng, đặc biệt đây là sản phẩm có nguồn gốc hoàn toàn tự nhiên', b'1', 'Sữa Vinamilk', b'1'),
(49, '/images/product/8dffb1e9-0b50-4053-8081-28c889d8ef87_suachua.png', b'0', 21000, '8', '8', 'Sản phẩm được sản xuất bởi công nghệ lên men hiện đại từ châu Âu với chủng men thuần khiết Bulgaricus, hỗ trợ tiêu hóa và giúp hấp thu dưỡng chất tốt hơn. ', b'0', 'Sữa Chua Vinamilk', b'1'),
(50, '/images/product/73c195dd-eca3-4f12-b116-0f70eed23433_yakult.png', b'1', 49000, '8', '3', 'Sữa chua uống lên men Yakult dạng lốc gồm 10 chai nhỏ tiện lợi khi sử dụng. Sản phẩm thích hợp sử dụng trong gia đình cũng như mang theo trong các chuyến du lịch, dã ngoại.\r\n\r\nMỗi chai Yakult có chứa hơn 6.5 tỉ khuẩn L.casei Shirota. Có tác dụng hỗ trợ, cải thiện tiêu hóa, làm tăng vi khuẩn có lợi và làm giảm vi khuẩn gây hại, giúp cân bằng hệ vi sinh vật đường ruột, điều hòa hệ miễn dịch và ngăn ngừa các bệnh truyền nhiễm. Thích hợp với mọi lứa tuổi.\r\n\r\nThành phần: nước, sữa gầy, Xirô glucose-frucgyse, saccarose, hương cam quýt, và vi khuẩn sống Lacgybacillus paracasei Shirota.\r\n\r\nXuất xứ: Nhật Bản. Nơi sản xuất: Việt Nam. Hạn sử dụng: 40 ngày kể từ ngày sản xuất. Dùng cho trẻ từ 1 tuổi trở lên.\r\n\r\n', b'0', 'Sữa Yakult', b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `yeu_thich`
--

DROP TABLE IF EXISTS `yeu_thich`;
CREATE TABLE IF NOT EXISTS `yeu_thich` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ma_khach_hang` int(11) NOT NULL,
  `ma_san_pham` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc6fgal7xcnjt4skw9fki57x7f` (`ma_khach_hang`),
  KEY `FKp2m76wqg3h9tbjol54657tqc2` (`ma_san_pham`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `yeu_thich`
--

INSERT INTO `yeu_thich` (`id`, `ma_khach_hang`, `ma_san_pham`) VALUES
(1, 12, 2),
(2, 3, 11),
(3, 3, 12),
(4, 3, 27),
(5, 13, 2);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `binh_luan`
--
ALTER TABLE `binh_luan`
  ADD CONSTRAINT `FKbnd7p9cndann9ktefkl1qdfmw` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`id`),
  ADD CONSTRAINT `FKp6cnyspen4g7vpik62fg9j2t0` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`id`);

--
-- Các ràng buộc cho bảng `don_hang`
--
ALTER TABLE `don_hang`
  ADD CONSTRAINT `FKhimbr4rcx5fwmced2i28svhak` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`id`);

--
-- Các ràng buộc cho bảng `don_hang_ds_gio_hang`
--
ALTER TABLE `don_hang_ds_gio_hang`
  ADD CONSTRAINT `FKfskwj1uas7misdq3o6gl17doj` FOREIGN KEY (`ds_gio_hang_id`) REFERENCES `gio_hang` (`id`),
  ADD CONSTRAINT `FKkf5yx7vdr8ugwic6jfh5ww4y0` FOREIGN KEY (`don_hang_id`) REFERENCES `don_hang` (`id`);

--
-- Các ràng buộc cho bảng `gio_hang`
--
ALTER TABLE `gio_hang`
  ADD CONSTRAINT `FK3j3osx4bt8nfgk5yxsrj3gdjt` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`id`),
  ADD CONSTRAINT `FK3wnvjl1ttup74jl17uyrcyuov` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`id`);

--
-- Các ràng buộc cho bảng `yeu_thich`
--
ALTER TABLE `yeu_thich`
  ADD CONSTRAINT `FKc6fgal7xcnjt4skw9fki57x7f` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`id`),
  ADD CONSTRAINT `FKp2m76wqg3h9tbjol54657tqc2` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
