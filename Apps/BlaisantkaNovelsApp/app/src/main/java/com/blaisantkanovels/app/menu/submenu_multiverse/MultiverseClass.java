package com.blaisantkanovels.app.menu.submenu_multiverse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.R;
import com.blaisantkanovels.app.menu.sinopsis.Sinopsis_Blasfemias;
import com.blaisantkanovels.app.menu.sinopsis.Sinopsis_CartasFreedel;
import com.blaisantkanovels.app.menu.submenu_multiverse.shortstories.moderesuspalabras.SerieModereSusPalabras;

import java.util.ArrayList;
import java.util.List;

public class MultiverseClass extends AppCompatActivity {

    private GridView gridProyects;
    private List<GridModalMultiverse> listImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_multiverse);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("A Multiverse of Pain");
        gridProyects = (GridView) findViewById(R.id.multiversegrid);
        listImages = new ArrayList<>();

        listImages.add(new GridModalMultiverse(
                R.drawable.pic_blasfemias,
                "¡blasfemias!: Leyendas thenmorhianas",
                "Publicación: 2021",
                "Kan Zerbervm (principal)"
        ));

        listImages.add(new GridModalMultiverse(
                R.drawable.pic_serie_modere,
                "Serie Modere sus Palabras",
                "Publicación: 2021",
                "Última publicación: \nModere sus Palabras, ¡exorcista!"
        ));

        listImages.add(new GridModalMultiverse(
                R.drawable.pic_freedel,
                "Cartas de Freedel",
                "Publicación: regular",
                "Última publicación: \nCapítulo 7"
        ));

        listImages.add(new GridModalMultiverse(
                R.drawable.keep_calm,
                "",
                "",
                ""
        ));

        gridProyects.setAdapter(new MenuAdapterMultiverse(getApplicationContext(), listImages));

        gridProyects.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                if (position == 0) {
                    intent = new Intent(getApplicationContext(), Sinopsis_Blasfemias.class);
                }else if (position == 1) {
                    intent = new Intent(getApplicationContext(), SerieModereSusPalabras.class);
                }else if (position == 2) {
                    intent = new Intent(getApplicationContext(), Sinopsis_CartasFreedel.class);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Obra no disponible",
                            Toast.LENGTH_LONG
                    ).show();
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
