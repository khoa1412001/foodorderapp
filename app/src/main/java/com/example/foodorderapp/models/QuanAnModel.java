package com.example.foodorderapp.models;

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
    boolean giaoHang;
    String giomocua,giodongcua,tenquanan,videogioithieu,maquanan;
    List<String> tienich;
    List<String> hinhanh;
    long luotthich;
    DatabaseReference db;
    public QuanAnModel(){
        db = FirebaseDatabase.getInstance().getReference();
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
        return giaoHang;
    }

    public void setGiaoHang(boolean giaoHang) {
        this.giaoHang = giaoHang;
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
                    hinhanh = new ArrayList<>();
                    for (DataSnapshot valueHinhanh: hinhanhQuanAn.getChildren())
                        hinhanh.add(valueHinhanh.getValue(String.class));
                    quanAnModel.setHinhanh(hinhanh);
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
