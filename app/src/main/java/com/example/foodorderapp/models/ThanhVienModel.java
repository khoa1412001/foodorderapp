package com.example.foodorderapp.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThanhVienModel {
    String hinhanh;
    String hoten;
    String mathanhvien;

    public String getMathanhvien() {
        return mathanhvien;
    }

    public void setMathanhvien(String mathanhvien) {
        this.mathanhvien = mathanhvien;
    }

    public  ThanhVienModel(){
    }
    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
}
