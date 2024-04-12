package banhang.quanlythucpham.kdl;

//package Tên_Công_ty.Tên_Dự_Án.Kdl;

//import Tên_Công_ty.Tên_Dự_Án.Tdl.QuangCao;

import org.springframework.data.jpa.repository.JpaRepository;

import banhang.quanlythucpham.tdl.QuangCao;

public interface KdlQuangCao extends JpaRepository<QuangCao, Integer>
{

}
