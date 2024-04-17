package com.blaisantkanovels.app.menu.sinopsis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.menu.submenu_multiverse.narrative.demo_blasfemias.Blasfemias_LeyendasThenmorhianas;
import com.blaisantkanovels.app.R;

public class Sinopsis_Blasfemias extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinopsis_blasfemias);

        getSupportActionBar().show();
        getSupportActionBar().setTitle("¡blasfemias!: Leyendas thenmorhianas");

        Button empezar = (Button) findViewById(R.id.botonPortada);

        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Blasfemias_LeyendasThenmorhianas.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
