package com.blaisantkanovels.app.menu.otrosautores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.R;
import com.blaisantkanovels.app.menu.sinopsis.Sinopsis_Psicopatia;

import java.util.ArrayList;
import java.util.List;

public class OtrosAutores extends AppCompatActivity {

    private GridView gridOtros;
    private List<GridModalOtrosAutores> listImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_otros_autores);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Otros Autores");
        gridOtros = (GridView) findViewById(R.id.otrosgrid);
        listImages = new ArrayList<>();

        listImages.add(new GridModalOtrosAutores(
                R.drawable.pic_psicopatia,
                "Pisopatía. Un slasher de 20 puñaladas",
                "Publicado en 2021",
                "De Cristina Bermejo Rey"
        ));

        gridOtros.setAdapter(new MenuAdapterOtrosAutores(getApplicationContext(), listImages));

        gridOtros.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                if (position == 0) {
                    intent = new Intent(getApplicationContext(), Sinopsis_Psicopatia.class);
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
