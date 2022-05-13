package com.example.foodorderapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.R;
import com.example.foodorderapp.models.QuanAnModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterRecycleOdau extends RecyclerView.Adapter<AdapterRecycleOdau.ViewHolder>{

    List<QuanAnModel> listQuanAn;
    int resource;
    public AdapterRecycleOdau(List<QuanAnModel> quanAnModelList,int resource){
        this.listQuanAn = quanAnModelList;
        this.resource = resource;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  txtTenQuan;
        Button btnDatmon;
        ImageView ivDoan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenQuan = itemView.findViewById(R.id.txtTenQuanOdau);
            btnDatmon = itemView.findViewById(R.id.btnDatMonOdau);
            ivDoan = itemView.findViewById(R.id.ivQuananOdau);
        }
    }
    @NonNull
    @Override
    public AdapterRecycleOdau.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleOdau.ViewHolder holder, int position) {
        QuanAnModel quanAnModel = listQuanAn.get(position);
        holder.txtTenQuan.setText(quanAnModel.getTenquanan());
        if (quanAnModel.isGiaoHang())
            holder.btnDatmon.setVisibility(View.VISIBLE);
        if (quanAnModel.getHinhanh().size() > 0){
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhanh().get(0));
            long MEGA_BYTE = 1024*1024;
            storageReference.getBytes(MEGA_BYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    holder.ivDoan.setImageBitmap(bitmap);
                }
            })
        }
    }

    @Override
    public int getItemCount() {
        return listQuanAn.size();
    }


}
