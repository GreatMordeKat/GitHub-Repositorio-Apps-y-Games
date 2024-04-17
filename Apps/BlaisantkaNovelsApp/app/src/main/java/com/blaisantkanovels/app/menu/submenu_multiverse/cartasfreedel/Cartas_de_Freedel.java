package com.blaisantkanovels.app.menu.submenu_multiverse.cartasfreedel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.blaisantkanovels.app.R;
import com.blaisantkanovels.app.menu.submenu_multiverse.narrative.cartas_freedel.stories_freedel.Capitulo_Siete_CartasFreedel;
import com.blaisantkanovels.app.piojos.Suscripcion;
import com.blaisantkanovels.app.menu.submenu_multiverse.narrative.cartas_freedel.stories_freedel.Capitulo_Cinco_CartasFreedel;
import com.blaisantkanovels.app.menu.submenu_multiverse.narrative.cartas_freedel.stories_freedel.Capitulo_Cuatro_CartasFreedel;
import com.blaisantkanovels.app.menu.submenu_multiverse.narrative.cartas_freedel.stories_freedel.Capitulo_Dos_CartasFreedel;
import com.blaisantkanovels.app.menu.submenu_multiverse.narrative.cartas_freedel.stories_freedel.Capitulo_Seis_CartasFreedel;
import com.blaisantkanovels.app.menu.submenu_multiverse.narrative.cartas_freedel.stories_freedel.Capitulo_Tres_CartasFreedel;
import com.blaisantkanovels.app.menu.submenu_multiverse.narrative.cartas_freedel.stories_freedel.Capitulo_Uno_CartasFreedel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import java.util.ArrayList;
import java.util.List;


public class Cartas_de_Freedel extends AppCompatActivity {

    private GridView gridCapitulos;
    private List<GridModalCaps> imageList;
    private Button suscripciones;
    private String[] token = {""};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_caps_freedel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cartas de Freedel");
        gridCapitulos = (GridView) findViewById(R.id.capsgrid);

        imageList = new ArrayList<>();
        imageList.add(new GridModalCaps(R.drawable.capitulo1, "He sobrevivido"));
        imageList.add(new GridModalCaps(R.drawable.capitulo2, "Inmóvil"));
        imageList.add(new GridModalCaps(R.drawable.capitulo3, "Debo socializar"));
        imageList.add(new GridModalCaps(R.drawable.capitulo4, "Mentalidad asesina"));
        imageList.add(new GridModalCaps(R.drawable.capitulo5, "Disturbios en la planta 15"));
        imageList.add(new GridModalCaps(R.drawable.capitulo6, "Conejito playboy"));
        imageList.add(new GridModalCaps(R.drawable.capitulo7, "Encerrado"));
        imageList.add(new GridModalCaps(R.drawable.capocho_prox, "Próximamente..."));
        //imageList.add(new GridModalCaps(R.drawable.capitulo7, "Publicación aproximada: \n2 octubre 2020"));

        gridCapitulos.setAdapter(new CartasFreedelAdapter(getApplicationContext(), imageList));

        //Sistema de suscripciones tipo topic de Firebase
        suscripciones = (Button) findViewById(R.id.suscription);

        //Instnciamos la base de datos
        final FirebaseDatabase read = FirebaseDatabase.getInstance();

        //Dentro del Usuario
        final FirebaseDatabase refuse = FirebaseDatabase.getInstance();
        final DatabaseReference con = read.getReference("/usuario");
        con.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (task.isComplete()) {
                            token[0] = task.getResult();
                            con.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    DatabaseReference consult = refuse.getReference("/usuario");
                                    consult.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (token[0].equals(snapshot.child(token[0]).getKey())) {
                                                DatabaseReference consulta = refuse.getReference("/usuario/" + token[0]);
                                                consulta.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if ("NO".equals(snapshot.child("suscrito").getValue().toString())) {
                                                            suscripciones.setText("Suscríbete para recibir notificaciones ");
                                                        } else if ("OK".equals(snapshot.child("suscrito").getValue().toString())) {
                                                            suscripciones.setText("Anular suscripción");
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        Suscripcion subserror = new Suscripcion();
                                                        subserror.setError(error.getMessage());
                                                        System.out.println(subserror.getError());
                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Suscripcion subserror = new Suscripcion();
                                            subserror.setError(error.getMessage());
                                            System.out.println(subserror.getError());
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Suscripcion subserror = new Suscripcion();
                                    subserror.setError(error.getMessage());
                                    System.out.println(subserror.getError());
                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Suscripcion subserror = new Suscripcion();
                subserror.setError(error.getMessage());
                System.out.println(subserror.getError());
            }
        });

        suscripciones.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final DatabaseReference consultaSuscripcion = refuse.getReference("/usuario");
                 consultaSuscripcion.addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                         final Suscripcion suscrip = new Suscripcion();
                         if (token[0].equals(snapshot.child(token[0]).getKey())) {
                             final DatabaseReference auxCon = refuse.getReference("/usuario/" + token[0]);
                             auxCon.addListenerForSingleValueEvent(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                     if ("NO".equals(snapshot.child("suscrito").getValue().toString())) {
                                         suscrip.setSuscrito("OK");
                                         auxCon.child("suscrito").setValue(suscrip.getSuscrito());
                                         FirebaseMessaging.getInstance().subscribeToTopic("viejodominio");
                                         Toast.makeText(getApplicationContext(),
                                                 "Te suscribiste al Viejo Dominio.",
                                                 Toast.LENGTH_LONG)
                                                 .show();
                                         suscripciones.setText("Anular suscripción");
                                     } else if ("OK".equals(snapshot.child("suscrito").getValue().toString())) {
                                         FirebaseMessaging.getInstance().unsubscribeFromTopic("viejodominio");
                                         suscrip.setSuscrito("NO");
                                         auxCon.child("suscrito").setValue(suscrip.getSuscrito());
                                         FirebaseMessaging.getInstance().unsubscribeFromTopic("viejodominio");
                                         Toast.makeText(getApplicationContext(),
                                                 "Suscripión anulada correctamente.",
                                                 Toast.LENGTH_LONG)
                                                 .show();
                                         suscripciones.setText("Suscríbete para recibir notificaciones");
                                     }
                                 }

                                 @Override
                                 public void onCancelled(@NonNull DatabaseError error) {
                                     Suscripcion subserror = new Suscripcion();
                                     subserror.setError(error.getMessage());
                                     System.out.println(subserror.getError());
                                 }
                             });
                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {
                         Suscripcion subserror = new Suscripcion();
                         subserror.setError(error.getMessage());
                         System.out.println(subserror.getError());
                     }
                 });
             }
        });
        //FIN DE SUSCRIPCIONES

        gridCapitulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                if (position == 0) {
                    intent = new Intent(getApplicationContext(), Capitulo_Uno_CartasFreedel.class);
                }else if (position == 1) {
                    intent = new Intent(getApplicationContext(), Capitulo_Dos_CartasFreedel.class);
                }else if (position == 2) {
                    intent = new Intent(getApplicationContext(), Capitulo_Tres_CartasFreedel.class);
                }else if (position == 3) {
                    intent = new Intent(getApplicationContext(), Capitulo_Cuatro_CartasFreedel.class);
                }else if (position == 4) {
                    intent = new Intent(getApplicationContext(), Capitulo_Cinco_CartasFreedel.class);
                }else if (position == 5) {
                    intent = new Intent(getApplicationContext(), Capitulo_Seis_CartasFreedel.class);
                }else if (position == 6) {
                    intent = new Intent(getApplicationContext(), Capitulo_Siete_CartasFreedel.class);
                }else if (position >= 7) {
                    Toast.makeText(getApplicationContext(),
                            "Capítulo no disponible en este momento",
                            Toast.LENGTH_LONG
                    ).show();
                }

                /*else if (position == 8) {
                    intent = new Intent(getApplicationContext(), Capitulo_Nueve_ViejoDominio.class);
                }else if (position == 9) {
                    intent = new Intent(getApplicationContext(), Capitulo_Diez_ViejoDominio.class);
                }*/
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

    /*private void enviarNotificaciones() {
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
                };
            };
        }catch(JSONException jsonerror) {
            Log.d("ERROR:", jsonerror.getMessage());
        }
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
