package com.blaisantkanovels.app.launcher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.blaisantkanovels.app.R;
import com.blaisantkanovels.app.piojos.Suscripcion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;


public class OnBackPressedListener extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private FragmentActivity activity;
    private String[] token = {""};

    public OnBackPressedListener(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onBackStackChanged() {
        activity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.valorationdialog);
        dialog.setCancelable(false);
        TextView no = (TextView) dialog.findViewById(R.id.no);
        TextView talvez = (TextView) dialog.findViewById(R.id.talvez);
        TextView claro = (TextView) dialog.findViewById(R.id.claro);

        //Instnciamos la base de datos
        final FirebaseDatabase read = FirebaseDatabase.getInstance();

        //Dentro del Usuario
        final Suscripcion suscriptor = new Suscripcion();
        final FirebaseDatabase refuse = FirebaseDatabase.getInstance();
        final DatabaseReference con = read.getReference("/usuario");
        con.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (task.isSuccessful()) {
                            token[0] = task.getResult();
                            suscriptor.setToken(token[0]);
                            con.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    final DatabaseReference consult = refuse.getReference("/usuario");
                                    consult.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (token[0].equals(snapshot.child(token[0]).getKey())) {
                                                DatabaseReference consulta = refuse.getReference("/usuario/" + token[0]);
                                                consulta.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if ("NO".equals(snapshot.child("valorado").getValue().toString())) {
                                                            suscriptor.setValorado("NO");
                                                        }else if ("SI".equals(snapshot.child("valorado").getValue().toString())) {
                                                            suscriptor.setValorado("SI");
                                                        }
                                                        comprobarSuscriptor(suscriptor, dialog);
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        suscriptor.setError(error.getMessage());
                                                        System.out.println(suscriptor.getError());
                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            suscriptor.setError(error.getMessage());
                                            System.out.println(suscriptor.getError());
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

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                activity.finish();
            }
        });

        talvez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (suscriptor.getValorado().equals("NO")) {
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
                                                final Suscripcion suscriptor = new Suscripcion(token[0]);
                                                final DatabaseReference consult = refuse.getReference("/usuario");
                                                consult.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if (token[0].equals(snapshot.child(token[0]).getKey())) {
                                                            DatabaseReference consulta = refuse.getReference("/usuario/" + token[0]);
                                                            consulta.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    suscriptor.setValorado("SI");
                                                                    consult.child(token[0]).child("valorado").setValue(suscriptor.getValorado());
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {
                                                                    suscriptor.setError(error.getMessage());
                                                                    System.out.println(suscriptor.getError());
                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        suscriptor.setError(error.getMessage());
                                                        System.out.println(suscriptor.getError());
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
                    abrirValoraciones(activity);
                    dialog.dismiss();
                }
            }
        });

        claro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (suscriptor.getValorado().equals("NO")) {
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
                                    if (task.isSuccessful()) {
                                        token[0] = task.getResult();
                                        con.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                final Suscripcion suscriptor = new Suscripcion(token[0]);
                                                final DatabaseReference consult = refuse.getReference("/usuario");
                                                consult.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if (token[0].equals(snapshot.child(token[0]).getKey())) {
                                                            DatabaseReference consulta = refuse.getReference("/usuario/" + token[0]);
                                                            consulta.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    suscriptor.setValorado("SI");
                                                                    consult.child(token[0]).child("valorado").setValue(suscriptor.getValorado());
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {
                                                                    suscriptor.setError(error.getMessage());
                                                                    System.out.println(suscriptor.getError());
                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        suscriptor.setError(error.getMessage());
                                                        System.out.println(suscriptor.getError());
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
                    abrirValoraciones(activity);
                    dialog.dismiss();
                }
            }
        });
    }

    private void comprobarSuscriptor(Suscripcion s, Dialog d) {
        if (s.getValorado().equals("NO")) {
            d.show();
        }else if (s.getValorado().equals("SI")) {
            AlertDialog.Builder build = new AlertDialog.Builder(activity);
            build.setTitle("¿Estás seguro...?");
            build.setMessage("¿...de que deseas salir de la aplicación?");
            build.setPositiveButton("Sí, lo necesito", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    activity.finish();
                    Toast.makeText(activity, "¡Esperamos volver a verte!", Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("Bueno, tal vez no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Toast.makeText(activity, "Se te echaba de menos", Toast.LENGTH_LONG).show();
                }
            }).show();
        }
    }

    public static void abrirValoraciones(Context ctx) {
        String id = ctx.getPackageName();
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + id));

        //Variable de control
        boolean market = false;

        // Buscamos todas las aplicaciones que pueden abrir el Intent
        final List<ResolveInfo> apps = ctx.getPackageManager()
                .queryIntentActivities(intent, 0);

        //Recorremos los resultados devueltos
        for (ResolveInfo app : apps) {

            //Si se encuentra el paquete "vending"
            if (app.activityInfo.applicationInfo.packageName
                    .equals("com.android.vending")) {


                ActivityInfo infoAct = app.activityInfo;
                ComponentName Compo = new ComponentName(
                        infoAct.applicationInfo.packageName,
                        infoAct.name
                );

                //Nos aseguramos que no se abre/añade a la pila de nuestra actividad
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Se reasigna la tarea si es necesario "TASK REPARENTING"
                intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);


                //Flag necesario para asegurar que, aunque GPlay ya esté abierto,
                // este se moverá a la página de nuestra app
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                //Solo permitimos a GPlay abrir este intent
                intent.setComponent(Compo);
                ctx.startActivity(intent);
                market = true;
            }
        }
        //Si llegamos aquí, no se ha encontrado el paquete de GPlay
        //Lanzamos intent con url normal
        if (!market) {
            Intent intentNavegador = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + id));
            ctx.startActivity(intentNavegador);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
