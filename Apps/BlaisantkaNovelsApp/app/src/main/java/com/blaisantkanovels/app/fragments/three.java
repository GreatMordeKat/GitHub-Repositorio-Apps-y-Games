package com.blaisantkanovels.app.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.blaisantkanovels.app.R;
import com.blaisantkanovels.app.launcher.MainActivity;
import com.blaisantkanovels.app.launcher.OnBackPressedListener;
import com.blaisantkanovels.app.piojos.Suscripcion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * A simple {@link Fragment} subclass.
 */
public class three extends Fragment implements IOBackPressed {

    private FragmentActivity activity;
    private String token;
    private boolean entendido;

    public three(@Nullable FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_three, container, false);
        final TextView info = (TextView) view.findViewById(R.id.infoText);
        final ImageView infov = (ImageView) view.findViewById(R.id.infoView);

        ImageView settings = (ImageView) view.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Ajustes no disponibles",
                        Toast.LENGTH_LONG).show();
            }
        });

        ImageView contact = (ImageView) view.findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setVisibility(View.INVISIBLE);
                infov.setVisibility(View.INVISIBLE);
                entendido = true;
                AlertDialog.Builder correo = new AlertDialog.Builder(getContext());
                correo.setTitle("¿Cuál es tu email?");
                final EditText email = new EditText(getContext());
                email.setInputType(InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                email.setHint("Email aquí...");
                correo.setView(email);
                correo.setPositiveButton("Listo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder asunto = new AlertDialog.Builder(getContext());
                        asunto.setTitle("¿Cuál es el asunto?");
                        final EditText quenecesitas = new EditText(getContext());
                        quenecesitas.setInputType(InputType.TYPE_CLASS_TEXT);
                        quenecesitas.setHint("Asunto aquí...");
                        asunto.setView(quenecesitas);
                        asunto.setPositiveButton("Listo", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringBuilder builder = new StringBuilder("mailto:" + Uri.encode(email.getText().toString()));
                                if (quenecesitas != null) {
                                    builder.append("?subject=" + quenecesitas.getText().toString());
                                }
                                String uri = builder.toString();
                                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(uri));
                                getContext().startActivity(intent);
                            }
                        })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });

        ImageView valors = (ImageView) view.findViewById(R.id.valorations);
        valors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                final String[] token = {""};
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
                                                        DatabaseReference consulta = refuse.getReference("/usuario/" + token);
                                                        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if ("NO".equals(snapshot.child("valorado").getValue().toString())) {
                                                                    OnBackPressedListener.abrirValoraciones(activity);
                                                                    suscriptor.setValorado("SI");
                                                                    consult.child(token[0]).child("valorado").setValue(suscriptor.getValorado());
                                                                } else if ("SI".equals(snapshot.child("valorado").getValue().toString())) {
                                                                    suscriptor.setValorado("SI");
                                                                    consult.child(token[0]).child("valorado").setValue(suscriptor.getValorado());
                                                                    Toast.makeText(getContext(), "¡Ya has valorado esta app!",
                                                                            Toast.LENGTH_SHORT).show();
                                                                }
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
            }
        });

        if (!entendido) {
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    info.setVisibility(View.INVISIBLE);
                    infov.setVisibility(View.INVISIBLE);
                    entendido = true;
                }
            });
        } else {
            info.setVisibility(View.INVISIBLE);
            infov.setVisibility(View.INVISIBLE);
        }

        ((MainActivity) activity).setOnBackPressedListener(new OnBackPressedListener(activity));

        return view;
    }

    @Override
    public void onBackPressed() {
        activity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
