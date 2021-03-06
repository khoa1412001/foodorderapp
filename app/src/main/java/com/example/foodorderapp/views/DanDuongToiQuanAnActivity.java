package com.example.foodorderapp.views;


import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderapp.R;
import com.example.foodorderapp.controllers.DanDuongToiQuanAnController;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DanDuongToiQuanAnActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap googleMap;
    MapFragment mapFragment;
    double latitude =0;
    double longitude = 0;
    SharedPreferences sharedPreferences;
    Location vitrihientai;

    DanDuongToiQuanAnController danDuongToiQuanAnController;
    String duongdan = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danduong);

        danDuongToiQuanAnController = new DanDuongToiQuanAnController();


        latitude = getIntent().getDoubleExtra("latitude",0);
        longitude = getIntent().getDoubleExtra("longitude",0);

        sharedPreferences = getSharedPreferences("toado", Context.MODE_PRIVATE);
        vitrihientai = new Location("");
        vitrihientai.setLatitude(Double.parseDouble(sharedPreferences.getString("latitude","0")));
        vitrihientai.setLongitude(Double.parseDouble(sharedPreferences.getString("longitude","0")));

        duongdan = "https://maps.googleapis.com/maps/api/directions/json?origin=" + vitrihientai.getLatitude() + "," + vitrihientai.getLongitude() + "&destination=" +latitude+"," + longitude + "&language=vi&key=AIzaSyCSNQCX6UYnoiq-BSoaHRdQvmPovWRQeSY";
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.clear();

        LatLng latLng = new LatLng(vitrihientai.getLatitude(),vitrihientai.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        googleMap.addMarker(markerOptions);

        LatLng vitriquanan = new LatLng(latitude,longitude);
        MarkerOptions markervitriquanan = new MarkerOptions();
        markervitriquanan.position(vitriquanan);
        googleMap.addMarker(markervitriquanan);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,14);
        googleMap.moveCamera(cameraUpdate);

        danDuongToiQuanAnController.HienThiDanDuongToiQuanAn(googleMap,duongdan);
    }
}
