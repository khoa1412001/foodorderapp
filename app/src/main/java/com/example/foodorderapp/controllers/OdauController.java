package com.example.foodorderapp.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.view.View;
import android.widget.ProgressBar;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.R;
import com.example.foodorderapp.adapters.AdapterRecycleOdau;
import com.example.foodorderapp.controllers.interfaces.OdauInterface;
import com.example.foodorderapp.models.QuanAnModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class OdauController {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecycleOdau adapterRecyclerOdau;
    int itemdaco = 3;

    public OdauController(Context context){
        this.context = context;
        quanAnModel = new QuanAnModel();
    }

    public void getDanhSachQuanAnController(Context context, NestedScrollView nestedScrollView, RecyclerView recyclerOdau, final ProgressBar progressBar, final Location vitrihientai){

        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        adapterRecyclerOdau = new AdapterRecycleOdau(context,quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecyclerOdau);

        progressBar.setVisibility(View.VISIBLE);

        final OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(final QuanAnModel quanAnModel) {
                final List<Bitmap> bitmaps = new ArrayList<>();
                for(String linkhinh : quanAnModel.getHinhanhquanan()){

                    StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);
                    long ONE_MEGABYTE = 1024 * 1024;
                    storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            bitmaps.add(bitmap);
                            quanAnModel.setBitmapList(bitmaps);

                            if(quanAnModel.getBitmapList().size() == quanAnModel.getHinhanhquanan().size()){
                                quanAnModelList.add(quanAnModel);
                                adapterRecyclerOdau.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });
                }


            }
        };

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(v.getChildAt(v.getChildCount() - 1) !=null){
                    if(scrollY >= (v.getChildAt(v.getChildCount() - 1)).getMeasuredHeight() - v.getMeasuredHeight()){
                        itemdaco += 3;
                        quanAnModel.getDanhSachQuanAn(odauInterface,vitrihientai,itemdaco,itemdaco-3);
                    }
                }
            }
        });

        quanAnModel.getDanhSachQuanAn(odauInterface,vitrihientai,itemdaco,0);

    }
}
