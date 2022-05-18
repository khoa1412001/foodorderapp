package com.example.foodorderapp.controllers;


import com.example.foodorderapp.models.ThanhVienModel;

public class DangKyController {
    ThanhVienModel thanhVienModel;

    public DangKyController(){
        thanhVienModel = new ThanhVienModel();
    }

    public void ThemThongTinThanhVienController(ThanhVienModel thanhVienModel,String uid){
        thanhVienModel.ThemThongTinThanhVien(thanhVienModel,uid);
    }
}
