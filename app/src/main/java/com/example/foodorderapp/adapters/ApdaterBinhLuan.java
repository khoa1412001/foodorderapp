package com.example.foodorderapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.models.BinhLuanModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.example.foodorderapp.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ApdaterBinhLuan extends RecyclerView.Adapter<ApdaterBinhLuan.ViewHolder> {

    Context context;
    int layout;
    List<BinhLuanModel> binhLuanModelList;


    public ApdaterBinhLuan(Context context, int layout, List<BinhLuanModel> binhLuanModelList){
        this.context = context;
        this.layout = layout;
        this.binhLuanModelList = binhLuanModelList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView txtTieuDeBinhLuan,txtNoiDungBinhLuan,txtSoDiem;
        RecyclerView recyclerViewHinhBinhLuan;

        public ViewHolder(View itemView) {
            super(itemView);

            circleImageView = (CircleImageView) itemView.findViewById(R.id.cicleImageUser);
            txtTieuDeBinhLuan = (TextView) itemView.findViewById(R.id.txtTieudebinhluan);
            txtNoiDungBinhLuan = (TextView) itemView.findViewById(R.id.txtNodungbinhluan);
            txtSoDiem = (TextView) itemView.findViewById(R.id.txtChamDiemBinhLuan);
            recyclerViewHinhBinhLuan = (RecyclerView) itemView.findViewById(R.id.recyclerHinhBinhLuan);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final BinhLuanModel binhLuanModel = binhLuanModelList.get(position);
        holder.txtTieuDeBinhLuan.setText(binhLuanModel.getTieude());
        holder.txtNoiDungBinhLuan.setText(binhLuanModel.getNoidung());
        holder.txtSoDiem.setText(binhLuanModel.getChamdiem() + "");
        setHinhAnhBinhLuan(holder.circleImageView,binhLuanModel.getThanhVienModel().getHinhanh());
        final List<Bitmap>  bitmapList = new ArrayList<>();
        for (String linkhinh : binhLuanModel.getHinhanhBinhLuanList()){
            StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);
            long ONE_MEGABYTE = 1024 * 1024;
            storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    bitmapList.add(bitmap);
                    if(bitmapList.size() == binhLuanModel.getHinhanhBinhLuanList().size()){
                        AdapterRecyclerHinhBinhLuan adapterRecyclerHinhBinhLuan = new AdapterRecyclerHinhBinhLuan(context,R.layout.custom_layout_hinhbinhluan,bitmapList,binhLuanModel,false);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);
                        holder.recyclerViewHinhBinhLuan.setLayoutManager(layoutManager);
                        holder.recyclerViewHinhBinhLuan.setAdapter(adapterRecyclerHinhBinhLuan);
                        adapterRecyclerHinhBinhLuan.notifyDataSetChanged();
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        int soBinhLuan = binhLuanModelList.size();
        if(soBinhLuan > 5){
            return 5;
        }else{
            return binhLuanModelList.size();
        }

    }

    private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkhinh){
        StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("thanhvien").child(linkhinh);
        long ONE_MEGABYTE = 1024 * 1024;
        storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });
    }


}
