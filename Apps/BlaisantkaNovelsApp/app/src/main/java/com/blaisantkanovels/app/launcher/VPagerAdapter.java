package com.blaisantkanovels.app.launcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class VPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> lista;

    public VPagerAdapter (@NonNull FragmentManager fm, List<Fragment> lista) {
        super(fm);
        this.lista = lista;
    }

    @Override
    public Fragment getItem(int position) {
        return lista.get(position);
    }

    @Override
    public int getCount() {
        return lista.size();
    }
}
