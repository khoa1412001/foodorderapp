package com.example.foodorderapp.controllers;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.R;
import com.example.foodorderapp.adapters.AdapterRecycleOdau;
import com.example.foodorderapp.controllers.interfaces.OdauInterface;
import com.example.foodorderapp.models.QuanAnModel;

import java.util.ArrayList;
import java.util.List;

public class OdauController {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecycleOdau adapterRecycleOdau;
    public OdauController(Context context) {
        this.context =context;
        quanAnModel = new QuanAnModel();
    }
    public  void getDanhSachQuanAnController(RecyclerView recyclerView){
        List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapterRecycleOdau = new AdapterRecycleOdau(quanAnModelList, R.layout.custom_layout_recycleview_odau);
        recyclerView.setAdapter(adapterRecycleOdau );
        OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
                quanAnModelList.add(quanAnModel);
                adapterRecycleOdau.notifyDataSetChanged();
            }
        };
    }
}
