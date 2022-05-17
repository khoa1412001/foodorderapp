package com.example.foodorderapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.R;
import com.example.foodorderapp.models.BinhLuanModel;
import com.example.foodorderapp.models.QuanAnModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRecycleOdau extends RecyclerView.Adapter<AdapterRecycleOdau.ViewHolder>{

    List<QuanAnModel> listQuanAn;
    int resource;
    public AdapterRecycleOdau(List<QuanAnModel> quanAnModelList,int resource){
        this.listQuanAn = quanAnModelList;
        this.resource = resource;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  txtTenQuan,txtTieudebinhluan,txtTieudebinhluan2,txtNoidungbinhluan,txtNoidungbinhluan2,txtChamdiem,txtChamdiem2,txtTongbinhluan,txtDiemOdau;
        CircleImageView circleImageuser,circleImageuser2;
        LinearLayout containerBinhluan;
        LinearLayout containerBinhluan2;
        Button btnDatmon;
        ImageView ivDoan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenQuan = itemView.findViewById(R.id.txtTenQuanOdau);
            btnDatmon = itemView.findViewById(R.id.btnDatMonOdau);
            ivDoan = itemView.findViewById(R.id.ivQuananOdau);
            txtNoidungbinhluan = itemView.findViewById(R.id.txtNoidungbinhluan);
            txtNoidungbinhluan2 = itemView.findViewById(R.id.txtNoidungbinhluan2);
            txtTieudebinhluan = itemView.findViewById(R.id.txtTieudebinhluan);
            txtTieudebinhluan2 = itemView.findViewById(R.id.txtTieudebinhluan2);
            circleImageuser = itemView.findViewById(R.id.circleImageUser);
            circleImageuser2 = itemView.findViewById(R.id.circleImageUser2);
            containerBinhluan = itemView.findViewById(R.id.containerBinhluan);
            containerBinhluan2 = itemView.findViewById(R.id.containerBinhluan2);
            txtChamdiem = itemView.findViewById(R.id.txtChamdiem);
            txtChamdiem2 = itemView.findViewById(R.id.txtChamdiem2);
            txtTongbinhluan = itemView.findViewById(R.id.txtTongbinhluan);
            txtDiemOdau = itemView.findViewById(R.id.txtDiemOdau);
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
            });
        }
        if (quanAnModel.getBinhLuanModelList().size() > 0){
            holder.txtTongbinhluan.setText(quanAnModel.getBinhLuanModelList().size() + "");
            BinhLuanModel binhLuanModel = quanAnModel.getBinhLuanModelList().get(0);
            holder.txtTieudebinhluan.setText(binhLuanModel.getTieude());
            holder.txtNoidungbinhluan.setText(binhLuanModel.getNoidung());
            holder.txtChamdiem.setText(binhLuanModel.getChamdiem() + "");
            Log.d("kiemtra", binhLuanModel.getThanhVienModel().getHinhanh());
            setHinhAnhBinhLuan(holder.circleImageuser,binhLuanModel.getThanhVienModel().getHinhanh());
            if (quanAnModel.getBinhLuanModelList().size() > 1){
                BinhLuanModel binhLuanModel2 = quanAnModel.getBinhLuanModelList().get(2);
                holder.txtTieudebinhluan2.setText(binhLuanModel2.getTieude());
                holder.txtNoidungbinhluan2.setText(binhLuanModel2.getNoidung());
                holder.txtChamdiem2.setText(binhLuanModel2.getChamdiem() + "");
                setHinhAnhBinhLuan(holder.circleImageuser2,binhLuanModel2.getThanhVienModel().getHinhanh());
            }
            else holder.containerBinhluan2.setVisibility(View.GONE);
        }
        else {
            holder.containerBinhluan.setVisibility(View.INVISIBLE);
            holder.containerBinhluan2.setVisibility(View.GONE);
            holder.txtTongbinhluan.setVisibility(View.GONE);
        }
        int tongsobinhluan = 0;
        double tongdiem = 0;
        for (BinhLuanModel model: quanAnModel.getBinhLuanModelList()){
            tongsobinhluan++;
            tongdiem += model.getChamdiem();
        }
        holder.txtDiemOdau.setText(String.format("%.1f",tongdiem/tongdiem));
    }
    private void setHinhAnhBinhLuan(CircleImageView circleImageView,String linkhinh){
        StorageReference storeUser = FirebaseStorage.getInstance().getReference().child("thanhvien").child(linkhinh);
        long MEGA_BYTE = 1024*1024;
        storeUser.getBytes(MEGA_BYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listQuanAn.size();
    }


}
