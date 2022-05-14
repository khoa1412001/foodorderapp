package com.example.foodorderapp.controllers;

import android.util.Log;

import com.example.foodorderapp.models.ThanhVienModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DangKyController {
    ThanhVienModel thanhVienModel;
    DatabaseReference databaseReference;
    public DangKyController(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("thanhviens");
    }
    public void ThemThongTinThanhVienController(ThanhVienModel thanhVienModel,String uid){
        databaseReference.child(uid).setValue(thanhVienModel);
    }
}
