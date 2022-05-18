package com.example.foodorderapp.controllers;


import com.example.foodorderapp.models.BinhLuanModel;

import java.util.List;


public class BinhLuanController {
    BinhLuanModel binhLuanModel;

    public  BinhLuanController(){
        binhLuanModel = new BinhLuanModel();
    }

    public void ThemBinhLuan(String maQuanAn, BinhLuanModel binhLuanModel, List<String> listHinh){
        binhLuanModel.ThemBinhLuan(maQuanAn,binhLuanModel,listHinh);
    }
}
