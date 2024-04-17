package com.blaisantkanovels.app.launcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.blaisantkanovels.app.fragments.one;
import com.blaisantkanovels.app.fragments.three;
import com.blaisantkanovels.app.fragments.two;
import com.blaisantkanovels.app.FireBaseService;
import com.blaisantkanovels.app.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VerticalViewPager viewPager;
    private VPagerAdapter pagerAdapter;
    protected OnBackPressedListener onBackPressedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //Slider horizontal (Usar PagerAdapter y en layout igual)
        //viewPager = findViewById(R.id.viewpager);
        viewPager = findViewById(R.id.pager);

        List<Fragment> lista = new ArrayList<>();
        lista.add(new one());
        lista.add(new two());
        lista.add(new three(this));

        new FireBaseService();

        //Para slider horizontal
        //viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        pagerAdapter = new VPagerAdapter(getSupportFragmentManager(), lista);
        viewPager.setAdapter(pagerAdapter);

        //Valoraciones
        this.setOnBackPressedListener(new OnBackPressedListener(this));
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener.onBackStackChanged();
        }else {
            super.onBackPressed();
        }
    }
}
