package com.smithcastlepictures.smithcastlefilmmedia.shortfilm.three;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import com.smithcastlepictures.smithcastlefilmmedia.databinding.ActivitySamsaddiaryOfflineBinding;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class StreamOff extends AppCompatActivity {

    private static final long MB = 1024L * 1024L;

    private ActivitySamsaddiaryOfflineBinding binding;
    private TextView progreso;
    private ProgressBar cargandovideo;
    private VideoView vView;
    private FrameLayout fdownload;
    private FrameLayout fvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySamsaddiaryOfflineBinding.inflate(getLayoutInflater());
        vView = binding.samsaddiaryoffline;
        fdownload = binding.fullscreenContent;
        fvideo = binding.fVideo;

        //DOWNLOAD MODE
        String fileName = "samsaddiary.mp4";
        String pathUrl = "http://167.114.86.12/web/assets/shortfilms/"+fileName;
        progreso = binding.progreso;
        cargandovideo = binding.cargandovideo;

        download(pathUrl, fileName);
    }

    private void download(String pathVideo, String fileName) {
        //TRYCATCH PARA REALIZAR EL MODO OFFLINE
        try {
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(pathVideo);
                        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                        int responseCode = httpConn.getResponseCode();
                        int contentLength = httpConn.getContentLength();

                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            InputStream inputStream = httpConn.getInputStream();
                            String saveFilePath = Environment.getExternalStorageDirectory()
                                    + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + fileName;
                            cargandovideo.setMax(contentLength);

                            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
                            byte data[] = new byte[4096];
                            long total = 0;
                            int count;
                            while ((count = inputStream.read(data)) != -1) {
                                total += count;

                                /*
                                    Necesario para poder usar dentro del hilo las vistas
                                    creadas en el thread principal.
                                 */
                                synchronized (this) {
                                    long finalTotal = total;
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (finalTotal < contentLength) {
                                                cargandovideo.setProgress((int) (finalTotal));
                                                if (finalTotal < 1024) {
                                                    progreso.setText(kBToMB(finalTotal)
                                                            + " kB /" + kBToMB(contentLength)
                                                            + " MB");
                                                }else{
                                                    progreso.setText(kBToMB(finalTotal)
                                                            + " MB /" + kBToMB(contentLength)
                                                            + " MB");
                                                }
                                            }else {
                                                vView.setVideoPath(saveFilePath);
                                                vView.start();
                                                cargandovideo.setVisibility(View.GONE);
                                                vView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                    @Override
                                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                                        deleteFile(saveFilePath);
                                                    }
                                                });
                                            }
                                        }
                                    });

                                }
                                outputStream.write(data, 0, count);
                            }
                            outputStream.close();
                            inputStream.close();
                        }
                    }catch (Exception ex) {
                        Log.d("", ex.getMessage());
                    }
                }
            });
            hilo.start();
        }catch (Exception ex) {
            Log.d("", ex.getMessage());
        }
    }
    private static long kBToMB(long KB) {
        return KB / MB;
    }
}
