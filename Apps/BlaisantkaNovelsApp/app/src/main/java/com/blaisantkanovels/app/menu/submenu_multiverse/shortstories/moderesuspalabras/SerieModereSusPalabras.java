package com.blaisantkanovels.app.menu.submenu_multiverse.shortstories.moderesuspalabras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.R;
import com.blaisantkanovels.app.menu.sinopsis.Sinopsis_ModeresusPalabras;


import java.util.ArrayList;
import java.util.List;

public class SerieModereSusPalabras extends AppCompatActivity {

    private GridView gridRelatos;
    private List<GridModalModere> listImages;

    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_relatos_modere);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Serie Modere sus Palabras");
        gridRelatos = (GridView) findViewById(R.id.relgrid);
        listImages = new ArrayList<>();

        listImages.add(new GridModalModere(
                R.drawable.pic_mspe,
                "Título: Modere sus Palabras, ¡exorcista!",
                "Autoría: Kan Zerbervm",
                "Año de publicación: 2021"
        ));

        gridRelatos.setAdapter(new ModereAdapter(getApplicationContext(), listImages));

        gridRelatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                if (position == 0) {
                    intent = new Intent(getApplicationContext(), Sinopsis_ModeresusPalabras.class);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Relato no disponible",
                            Toast.LENGTH_LONG
                    ).show();
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
