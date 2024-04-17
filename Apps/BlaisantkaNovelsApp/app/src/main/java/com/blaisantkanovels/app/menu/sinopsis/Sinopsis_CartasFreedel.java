package com.blaisantkanovels.app.menu.sinopsis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.menu.submenu_multiverse.cartasfreedel.Cartas_de_Freedel;
import com.blaisantkanovels.app.R;

public class Sinopsis_CartasFreedel extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinopsis_cartasdefreedel);

        getSupportActionBar().show();
        getSupportActionBar().setTitle("Cartas de Freedel");

        Button empezar = (Button) findViewById(R.id.botonPortada);

        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Cartas_de_Freedel.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
