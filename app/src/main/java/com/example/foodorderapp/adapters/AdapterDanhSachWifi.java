package com.example.foodorderapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodorderapp.R;
import com.example.foodorderapp.models.WifiQuanAnModel;

import java.util.List;


public class AdapterDanhSachWifi extends RecyclerView.Adapter<AdapterDanhSachWifi.ViewHolderWifi> {
    Context context;
    int resource;
    List<WifiQuanAnModel> wifiQuanAnModelList;

    public AdapterDanhSachWifi(Context context, int resource, List<WifiQuanAnModel> wifiQuanAnModelList){
        this.context = context;
        this.resource = resource;
        this.wifiQuanAnModelList = wifiQuanAnModelList;
    }

    public class ViewHolderWifi extends RecyclerView.ViewHolder {

        TextView txtTenWifi,txtMatKhauWifi,txtNgayDang;

        public ViewHolderWifi(View itemView) {
            super(itemView);

            txtTenWifi = (TextView) itemView.findViewById(R.id.txtTenWifi);
            txtMatKhauWifi = (TextView) itemView.findViewById(R.id.txtMatKhauWifi);
            txtNgayDang = (TextView) itemView.findViewById(R.id.txtNgayDangWifi);
        }
    }

    @Override
    public ViewHolderWifi onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource,parent,false);
        ViewHolderWifi viewHolderWifi = new ViewHolderWifi(view);

        return viewHolderWifi;
    }

    @Override
    public void onBindViewHolder(ViewHolderWifi holder, int position) {
        WifiQuanAnModel wifiQuanAnModel = wifiQuanAnModelList.get(position);

        holder.txtTenWifi.setText(wifiQuanAnModel.getTen());
        holder.txtMatKhauWifi.setText(wifiQuanAnModel.getMatkhau());
        holder.txtNgayDang.setText(wifiQuanAnModel.getNgaydang());

    }

    @Override
    public int getItemCount() {
        return wifiQuanAnModelList.size();
    }


}
