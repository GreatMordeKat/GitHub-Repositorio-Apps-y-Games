package com.smithcastlepictures.smithcastlefilmmedia.shortfilm.three;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.smithcastlepictures.smithcastlefilmmedia.R;
import com.smithcastlepictures.smithcastlefilmmedia.databinding.ActivitySamsaddiaryFilmBinding;

public class SAMSADDiaryFilmActivity extends AppCompatActivity
        /* DESCOMENTAR PARA USARLO CON MEDIAPLAYER Y SURFACEHOLDER
            implements SurfaceHolder.Callback,
                    MediaPlayer.OnPreparedListener,
                    MediaPlayer.OnBufferingUpdateListener*/ {

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler(Looper.myLooper());
    private View mContentView;
    //private SurfaceView sView;
    private VideoView vView;
    //private SurfaceHolder holder;
    //private MediaPlayer mplayer;
    //private TextView progreso;
    //private AlertDialog.Builder loading;
    //private AlertDialog dialog;
    private boolean mVisible;
    private ActivitySamsaddiaryFilmBinding binding;
    private String fileName;
    private String pathVideo;
    private int count = 0;

    //EXOPLAYER
    //private PlayerView player;
    //private ExoPlayer sePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySamsaddiaryFilmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mContentView = binding.fullscreenContent;
        mVisible = true;

        //VIDEO VIEW (interno)
        vView = binding.samsaddiary;
        String videoPath = "android.resource://"
                + getPackageName()
                + "/"
                + R.raw.samsaddiary;

        try{
            vView.setVideoPath(videoPath);
            vView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                }
            });
            vView.start();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        //MEDIAPLAYER
        //sView = binding.samsaddiary;

        //MEDIAPLAYER (con SURFACEVIEW & HOLDER)
        //sView = (SurfaceView) binding.samsaddiary;
        //holder = sView.getHolder();
        //holder.addCallback(this);

        //ALERDIALOG USADO PARA EL MEDIOPLAYER POR CULPA DEL BUFFER
        /*loading = new AlertDialog.Builder(this);
        loading.setTitle("\t\t\t\t\tLOADING...");
        loading.setMessage("\nThis story shows us the decision a young " +
                "girl makes in order to see the love of her life again. " +
                "Who, after all, she has never stopped missing.\n\n" +
                "It will play in a few moments.\nKeep calm and don't call Batman.\n\n" +
                "Enjoy the shortfilm ;)");
        loading.setCancelable(false);
        loading.create();*/

        //EXOPLAYER (Streaming)
        /*player = (PlayerView) binding.exoplayer;
        sePlayer = new ExoPlayer.Builder(this).build();
        player.setPlayer(sePlayer);
        MediaItem mItem = MediaItem.fromUri(pathVideo);
        sePlayer.addMediaItem(mItem);
        sePlayer.prepare();
        sePlayer.play();*/

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
    }

    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (AUTO_HIDE) {
                        delayedHide(AUTO_HIDE_DELAY_MILLIS);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view.performClick();
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //mControlsView.setVisibility(View.GONE);
        mVisible = false;
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void show() {
        if (Build.VERSION.SDK_INT >= 30) {
            mContentView.getWindowInsetsController().show(
                    WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
        } else {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }
        mVisible = true;
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            if (Build.VERSION.SDK_INT >= 30) {
                mContentView.getWindowInsetsController().hide(
                        WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
            } else {
                mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        }
    };
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            //mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    //ON PREPARED LISTENER VIDEO (MEDIAPLAYER)
    /*@Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        int buffer = i;
        if (buffer < MediaplayerStates.MPS_STARTED.getState()) mediaPlayer.pause();
        else if (buffer >= MediaplayerStates.MPS_STARTED.getState()) {
            mediaPlayer.start();
            dialog.dismiss();
        }
    }

    //SURFACEHOLDER (VISUALIZAR VIDEO)
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        try {
            mplayer = new MediaPlayer();
            mplayer.setDisplay(holder);
            mplayer.setDataSource(pathVideo);
            mplayer.setOnBufferingUpdateListener(this);
            mplayer.setOnPreparedListener(this);
            mplayer.prepareAsync();
            dialog = loading.show();
            mplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (Exception ex) {
            Log.d("", ex.getMessage());
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }*/
}