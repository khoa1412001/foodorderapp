package com.example.foodorderapp.views.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.R;
import com.example.foodorderapp.controllers.OdauController;
import com.example.foodorderapp.models.QuanAnModel;

public class odau_fragment extends Fragment {
    OdauController odauController;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_odau,container,false);
        recyclerView = view.findViewById(R.id.recycleOdau);
        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        odauController = new OdauController(getContext());
        odauController.getDanhSachQuanAnController(recyclerView);
    }
}
