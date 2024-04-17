package com.blaisantkanovels.app.menu.submenu_multiverse.cartasfreedel.adaptersandview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.blaisantkanovels.app.R;

public class VPagerAdapterFreedelCapSeis extends PagerAdapter {

    private Context context;
    private int[] images = {
            R.drawable.sextacarta_1,
            R.drawable.sextacarta_2,
            R.drawable.sextacarta_3,
            R.drawable.sextacarta_4
    };

    public VPagerAdapterFreedelCapSeis(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView view = new ImageView(context);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setImageResource(images[position]);
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
