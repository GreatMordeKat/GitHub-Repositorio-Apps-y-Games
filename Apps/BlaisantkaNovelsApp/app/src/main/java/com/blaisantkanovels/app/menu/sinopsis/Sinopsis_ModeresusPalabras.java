package com.blaisantkanovels.app.menu.sinopsis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.R;
import com.blaisantkanovels.app.menu.submenu_multiverse.narrative.short_story_exorcist.ModereSusPalabras_exorcista;

public class Sinopsis_ModeresusPalabras extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinopsis_moderesuspalabras);

        getSupportActionBar().show();
        getSupportActionBar().setTitle("Modere sus Palabras, ¡exorcista!");

        Button empezar = (Button) findViewById(R.id.botonPortada);

        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ModereSusPalabras_exorcista.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
