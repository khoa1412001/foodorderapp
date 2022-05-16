package com.example.foodorderapp.controllers;

import com.example.foodorderapp.models.DownloadPolyLineModel;
import com.example.foodorderapp.models.ParserPolylineModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


import java.util.List;
import java.util.concurrent.ExecutionException;

public class DanDuongToiQuanAnController {
    ParserPolylineModel parserPolyline;
    DownloadPolyLineModel downloadPolyLine;

    public DanDuongToiQuanAnController(){

    }

    public void HienThiDanDuongToiQuanAn(GoogleMap googleMap,String duongdan){
        parserPolyline = new ParserPolylineModel();
        downloadPolyLine = new DownloadPolyLineModel();
        downloadPolyLine.execute(duongdan);

        try {
            String dataJSON = downloadPolyLine.get();
            List<LatLng> latLngList = parserPolyline.LayDanhSachToaDo(dataJSON);

            PolylineOptions polylineOptions = new PolylineOptions();
            for (LatLng toado : latLngList){
                polylineOptions.add(toado);
            }

            Polyline polyline = googleMap.addPolyline(polylineOptions);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
