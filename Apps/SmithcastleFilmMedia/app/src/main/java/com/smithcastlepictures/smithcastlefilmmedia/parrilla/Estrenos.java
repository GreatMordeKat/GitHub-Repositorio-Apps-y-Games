package com.smithcastlepictures.smithcastlefilmmedia.parrilla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.smithcastlepictures.smithcastlefilmmedia.FullscreenMain;
import com.smithcastlepictures.smithcastlefilmmedia.R;
import com.smithcastlepictures.smithcastlefilmmedia.microfilms.FullscreenLadyActivity;
import com.smithcastlepictures.smithcastlefilmmedia.shortfilm.three.FullscreenSamActivity;

public class Estrenos extends AppCompatActivity {

    private ImageView imageSam;
    private ImageView imageLady;
    //private final int MILIS = 500;
    //private final int INTERVAL = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estrenos);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        imageSam = (ImageView) findViewById(R.id.imagesam);
        imageLady = (ImageView) findViewById(R.id.imagelady);

        imageSam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FullscreenSamActivity.class));
                //Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
                //imageSam.startAnimation(animZoomIn);

                /*new CountDownTimer(MILIS, INTERVAL) {

                    @Override
                    public void onTick(long l) {
                        int s = (int) l/1000;
                    }

                    @Override
                    public void onFinish() {
                        startActivity(new Intent(getApplicationContext(), FullscreenSamActivity.class));
                    }
                }.start();*/
            }
        });

        imageLady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FullscreenLadyActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), FullscreenMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
}
