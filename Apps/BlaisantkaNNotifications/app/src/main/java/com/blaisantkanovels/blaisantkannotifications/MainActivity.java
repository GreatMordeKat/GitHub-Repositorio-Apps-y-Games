package com.blaisantkanovels.blaisantkannotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String titulo = "¡Hay una nueva publicación!";
    private String cuerpo = "Algo se cuece en el Viejo Dominio...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button topico = (Button) findViewById(R.id.topica);
        Button espe = (Button) findViewById(R.id.especifica);

        topico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarNotificaciones();
            }
        });
    }

    private void enviarNotificaciones() {
        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        JSONObject json = new JSONObject();
        try {
            String topic = "/topics/" + "viejodominio";
            json.put("to", topic);
            JSONObject notificaction = new JSONObject();
            notificaction.put("Titulo", titulo);
            notificaction.put("Cuerpo", cuerpo);
            json.put("data", notificaction);

            String url = "https://fcm.googleapis.com/fcm/send";

            JsonObjectRequest jsonRequest = new JsonObjectRequest(
                    Request.Method.POST, url, json, null, null) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=AAAAcP2oF8I:APA91bFHjFL9JIt_Q4RQJ2Ya74vuOR6lAL4vTdQgJA5AbIxdQPehsA24gVvbKgFCG60PqpDXIoGSAdPfwft-6q3vZQB7PJ42OpP0TmIcHgBpcR5s8kbmKyOr4Q5Sk9zSwuwzLIPMddxn");
                    return header;
                }
            };

            request.add(jsonRequest);
        } catch (JSONException jsonerror) {
            Log.d("ERROR:", jsonerror.getMessage());
        }
    }
}