package com.example.foodorderapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.R;
import com.example.foodorderapp.controllers.CapNhatWifiController;


public class CapNhatDanhSachWifiActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnCapNhatWifi;
    RecyclerView recyclerDanhSachWifi;
    CapNhatWifiController capNhatWifiController;
    String maquanan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_capnhatdanhsachwifi);

        btnCapNhatWifi = (Button) findViewById(R.id.btnCapNhatWifi);
        recyclerDanhSachWifi = (RecyclerView) findViewById(R.id.recyclerDanhSachWifi);

        maquanan = getIntent().getStringExtra("maquanan");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
        recyclerDanhSachWifi.setLayoutManager(layoutManager);

        capNhatWifiController = new CapNhatWifiController(this);

        capNhatWifiController.HienThiDanhSachWifi(maquanan,recyclerDanhSachWifi);

        btnCapNhatWifi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent iPopup = new Intent(this, PopupCapNhatWifiActivity.class);
        iPopup.putExtra("maquanan",maquanan);
        startActivity(iPopup);
    }
}
