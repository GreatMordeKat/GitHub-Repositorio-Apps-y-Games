package com.blaisantkanovels.app.menu.submenu_multiverse.narrative.demo_blasfemias;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.R;

public class Blasfemias_LeyendasThenmorhianas extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_antologia_blasfemias);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Fragmento de ¡blasfemias!: Leyendas thenmorhianas");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
