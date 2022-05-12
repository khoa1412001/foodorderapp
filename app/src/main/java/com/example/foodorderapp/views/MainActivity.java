package com.example.foodorderapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.foodorderapp.R;
import com.example.foodorderapp.adapters.AdapterViewPagerTrangChu;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    ViewPager viewPager;
    RadioButton rbOdau,rbAngi;
    RadioGroup rgOdauAngi;
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
        viewPager.addOnPageChangeListener(this);
        rbOdau = findViewById(R.id.rbOdau);
        rbAngi = findViewById(R.id.rbAngi);
        rgOdauAngi = findViewById(R.id.rgOdauAngi);
    }

    private void addEvents() {
        rgOdauAngi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbAngi:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rbOdau:
                        viewPager.setCurrentItem(0);
                        break;
                }
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rbOdau.setChecked(true);
                break;
            case 1:
                rbAngi.setChecked(true);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}