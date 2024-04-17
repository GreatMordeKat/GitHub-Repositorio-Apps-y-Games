package com.blaisantkanovels.app.menu.submenu_multiverse.narrative.short_story_exorcist;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.R;

public class ModereSusPalabras_exorcista extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relato_moderesuspalabras_exorcista);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Modere sus Palabras, ¡exorcista!");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
