package com.blaisantkanovels.app.menu.submenu_multiverse.narrative.cartas_freedel.stories_freedel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.R;
import com.blaisantkanovels.app.menu.submenu_multiverse.cartasfreedel.adaptersandview.VPagerAdapterFreedelCapSiete;
import com.blaisantkanovels.app.menu.submenu_multiverse.cartasfreedel.adaptersandview.VerticalCartasFreedelViewPager;

public class Capitulo_Siete_CartasFreedel extends AppCompatActivity {

    private VerticalCartasFreedelViewPager vPagerFreedel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_siete_freedel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cartas de Freedel");

        vPagerFreedel = findViewById(R.id.viewCartaSiete);
        VPagerAdapterFreedelCapSiete vPagerAdapterFreedelCapSiete = new VPagerAdapterFreedelCapSiete(this);
        vPagerFreedel.setAdapter(vPagerAdapterFreedelCapSiete);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
