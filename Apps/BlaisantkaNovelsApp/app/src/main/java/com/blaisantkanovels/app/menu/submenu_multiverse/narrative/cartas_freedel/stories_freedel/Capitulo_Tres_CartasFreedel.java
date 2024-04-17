package com.blaisantkanovels.app.menu.submenu_multiverse.narrative.cartas_freedel.stories_freedel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.R;
import com.blaisantkanovels.app.launcher.VPagerAdapter;
import com.blaisantkanovels.app.menu.submenu_multiverse.cartasfreedel.adaptersandview.VPagerAdapterFreedelCapTres;
import com.blaisantkanovels.app.menu.submenu_multiverse.cartasfreedel.adaptersandview.VerticalCartasFreedelViewPager;

public class Capitulo_Tres_CartasFreedel extends AppCompatActivity {

    private VerticalCartasFreedelViewPager vPagerFreedel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_tres_freedel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cartas de Freedel");

        vPagerFreedel = findViewById(R.id.viewCartaTres);
        VPagerAdapterFreedelCapTres vAdapterFreedelCapTres = new VPagerAdapterFreedelCapTres(this);
        vPagerFreedel.setAdapter(vAdapterFreedelCapTres);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
