package com.blaisantkanovels.app.menu.sinopsis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.R;
import com.blaisantkanovels.app.menu.otrosautores.psicopatia.Psicopatia;

public class Sinopsis_Psicopatia extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinopsis_psicopatia);

        getSupportActionBar().show();
        getSupportActionBar().setTitle("Psicopatía: un slasher de 20 puñaladas");

        Button empezar = (Button) findViewById(R.id.botonPortada);

        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Psicopatia.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
