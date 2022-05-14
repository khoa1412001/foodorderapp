package com.example.foodorderapp.models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodorderapp.controllers.interfaces.OdauInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanAnModel {
    boolean giaohang;
    String giomocua,giodongcua,tenquanan,videogioithieu,maquanan;
    List<String> tienich;
    List<String> hinhanh;
    List<BinhLuanModel> binhLuanModelList;
    long luotthich;
    DatabaseReference db;
    public QuanAnModel(){
        db = FirebaseDatabase.getInstance().getReference();
    }
    public List<BinhLuanModel> getBinhLuanModelList() {
        return binhLuanModelList;
    }

    public void setBinhLuanModelList(List<BinhLuanModel> binhLuanModelList) {
        this.binhLuanModelList = binhLuanModelList;
    }

    public List<String> getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(List<String> hinhanh) {
        this.hinhanh = hinhanh;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public List<String> getTienich() {
        return tienich;
    }

    public void setTienich(List<String> tienich) {
        this.tienich = tienich;
    }

    public boolean isGiaoHang() {
        return giaohang;
    }

    public void setGiaoHang(boolean giaoHang) {
        this.giaohang = giaoHang;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public String getMaquanan() {
        return maquanan;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }


    public void getDanhSachQuanAn(OdauInterface odauInterface){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataQuanAn = snapshot.child("quanans");
                for (DataSnapshot value:dataQuanAn.getChildren()){
                    QuanAnModel quanAnModel = value.getValue(QuanAnModel.class);
                    quanAnModel.setMaquanan(value.getKey());
                    DataSnapshot hinhanhQuanAn = snapshot.child("hinhanhquanans").child(value.getKey());
                    List<String> hinhanhs = new ArrayList<>();
                    //lay hinh anh
                    for (DataSnapshot valueHinhanh: hinhanhQuanAn.getChildren())
                        hinhanhs.add(valueHinhanh.getValue(String.class));
                    quanAnModel.setHinhanh(hinhanhs);
                    DataSnapshot dataBinhLuan = snapshot.child("binhluans").child(value.getKey());
                    List<BinhLuanModel> binhLuanModels = new ArrayList<>();
                    //lay binh luan
                    for (DataSnapshot valueBinhLuan: dataBinhLuan.getChildren()){
                        BinhLuanModel binhLuanModel = valueBinhLuan.getValue(BinhLuanModel.class);
                        binhLuanModels.add(binhLuanModel);
                    }
                    quanAnModel.setBinhLuanModelList(binhLuanModels);
                    odauInterface.getDanhSachQuanAnModel(quanAnModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        db.addListenerForSingleValueEvent(valueEventListener);
    }
}
