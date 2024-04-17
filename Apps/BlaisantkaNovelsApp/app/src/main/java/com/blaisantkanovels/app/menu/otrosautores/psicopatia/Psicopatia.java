package com.blaisantkanovels.app.menu.otrosautores.psicopatia;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blaisantkanovels.app.R;

public class Psicopatia extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_psicopatia);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Psicopatía. Un slasher de 20 puñaladas");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
