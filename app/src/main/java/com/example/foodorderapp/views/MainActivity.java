package com.example.foodorderapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.foodorderapp.R;
import com.example.foodorderapp.adapters.AdapterViewPagerTrangChu;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    AdapterViewPagerTrangChu adapterViewPagerTrangChu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        viewPager = findViewById(R.id.vpTrangChu);
        adapterViewPagerTrangChu = new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPagerTrangChu);
    }

    private void addEvents() {

    }
}