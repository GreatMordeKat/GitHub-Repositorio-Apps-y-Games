package com.blaisantkanovels.app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.blaisantkanovels.app.fragments.one;
import com.blaisantkanovels.app.fragments.three;
import com.blaisantkanovels.app.fragments.two;


public class PagerAdapter extends FragmentStatePagerAdapter {


    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new one();

            case 1:
                return new two();

            case 2:
                return new three(null);
            default:
                return new one();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
