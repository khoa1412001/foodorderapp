package com.example.foodorderapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.foodorderapp.views.fragments.angi_fragment;
import com.example.foodorderapp.views.fragments.odau_fragment;

public class AdapterViewPagerTrangChu extends FragmentStatePagerAdapter {
    angi_fragment angiFragment;
    odau_fragment odauFragment;
    public AdapterViewPagerTrangChu(@NonNull FragmentManager fm) {
        super(fm);
        angiFragment = new angi_fragment();
        odauFragment = new odau_fragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return odauFragment;
            case 1:
                return angiFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
