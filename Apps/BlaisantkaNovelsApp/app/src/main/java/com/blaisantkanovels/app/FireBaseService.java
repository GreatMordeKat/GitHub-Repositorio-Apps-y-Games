package com.blaisantkanovels.app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.blaisantkanovels.app.menu.submenu_multiverse.cartasfreedel.Cartas_de_Freedel;
import com.blaisantkanovels.app.piojos.Suscripcion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class FireBaseService extends FirebaseMessagingService {

    private boolean existe;
    private Suscripcion usuario;

    public FireBaseService() {
        final String[] token = {""};
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isComplete()) {
                token[0] = task.getResult();
                almacenarToken(token[0]);
            }
        });
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        almacenarToken(s);
    }

    public void almacenarToken(final String s) {
        final FirebaseDatabase tokens = FirebaseDatabase.getInstance();
        final DatabaseReference refer = tokens.getReference("/usuario");
        refer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Comprobamos si existe un usuario igual
                for (DataSnapshot claves : snapshot.getChildren()) {
                    if (claves.getKey().equals(s)) {
                        usuario = new Suscripcion(s);
                        existe = true;
                        break;
                    }else{
                       existe = false;
                    }
                }
                if (!existe) {
                    refer.child(s).child("suscrito").setValue("NO");
                    refer.child(s).child("valorado").setValue("NO");
                    usuario = new Suscripcion(s, "NO", "NO");
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
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String titulo = remoteMessage.getData().get("Titulo");
        String cuerpo = remoteMessage.getData().get("Cuerpo");

        versionAndroid(titulo, cuerpo);
    }

    private void versionAndroid (String titulo, String cuerpo) {
        String id = "Mensaje";

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, "Nuevo", NotificationManager.IMPORTANCE_HIGH);
            channel.setShowBadge(true);
            assert nm != null;
            nm.createNotificationChannel(channel);
        }

        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(titulo)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(cuerpo)
                .setContentIntent(pushNotify())
                .setContentInfo("Nuevo");
        Random random = new Random();
        int notify = random.nextInt(100000);
        assert nm != null;
        nm.notify(notify, builder.build());
    }

    private PendingIntent pushNotify () {
        Intent notify = new Intent(getApplicationContext(), Cartas_de_Freedel.class);
        notify.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(getApplicationContext(), 0, notify, 0);
    }
}
