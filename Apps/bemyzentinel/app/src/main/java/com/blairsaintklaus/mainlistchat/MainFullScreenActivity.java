package com.blairsaintklaus.mainlistchat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ImageButton;
import android.widget.ListView;
import com.blairsaintklaus.adapters.ChatAdapter;
import com.blairsaintklaus.daos.Mensaje;
import com.blairsaintklaus.mainlistchat.databinding.MainActivityfullscreenBinding;
import com.blairsaintklaus.sendthezentinel.OpenChatWithUser;
import java.util.ArrayList;
import java.util.List;

/**
 * Una activity full-screen que muestra y oculta la UI de sistema, como
 * la barra de herramientas o la de navegacion mediante la interaccion con el usuario.
 */
public class MainFullScreenActivity extends AppCompatActivity {

    private static final boolean AUTO_HIDE = true;

    /**
     * Si {@link #AUTO_HIDE} está configurado, el numero de millisegundos espera
     * a que el usuario interaccione antes de ocultarse la UI de sistema.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Algunos dispositivos más antiguos necesitan un pequeño retraso
     * entre las actualizaciones del widget de la interfaz de usuario
     * y un cambio de la barra de estado y navegación.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler hideHandler = new Handler();
    private MainActivityfullscreenBinding binding;
    private View contentView;
    private ListView chat_list;
    private ChatAdapter adapter_chat;
    private List<Mensaje> list_chat;

    //Con esto recuperamos chats abiertos recientemente
    private ArrayList<String> listado_chats;
    private View controlsView;
    private boolean visible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MainActivityfullscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Comprobamos si hay algun dato enviado de otras activities
        if (getIntent().getStringArrayListExtra("Marco Aurelio") != null) {
            listado_chats = getIntent().getStringArrayListExtra("Marco Aurelio");
        }

        visible = true;
        controlsView = binding.fullScreenButton;
        contentView = binding.fullScreenList;
        list_chat = new ArrayList<>();
        adapter_chat = new ChatAdapter(
                this, R.layout.item_message_received, list_chat);

        chat_list = (ListView) findViewById(R.id.chat_list);
        chat_list.setAdapter((ChatAdapter) adapter_chat);

        // Se hace para que el usuario interaccione manualmente y muestre
        // la UI de sistema o no.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        ImageButton sendButton = findViewById(R.id.new_chat);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OpenChatWithUser.class);
                startActivity(intent);
            }
        });

        binding.newChat.setOnTouchListener(delayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        delayedHide(100);
    }

    private void toggle() {
        if (visible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Esconde la UI primero
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        controlsView.setVisibility(View.GONE);
        visible = false;

        hideHandler.removeCallbacks(showPart2Runnable);
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void show() {
        // Show the system bar
        if (Build.VERSION.SDK_INT >= 30) {
            contentView.getWindowInsetsController().show(
                    WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
        }else{
            contentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }
        visible = true;
        hideHandler.removeCallbacks(hidePart2Runnable);
        hideHandler.postDelayed(showPart2Runnable, UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        hideHandler.removeCallbacks(hideRunnable);
        hideHandler.postDelayed(hideRunnable, delayMillis);
    }

    private final Runnable hidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Retrasamos la eliminación del estado y la navegacion
            if (Build.VERSION.SDK_INT >= 30) {
                contentView.getWindowInsetsController()
                        .hide(WindowInsets.Type.statusBars()
                                | WindowInsets.Type.navigationBars());
            } else {
                // Hay que tener en cuenta que algunas de estas constantes son nuevas a partir
                // de API 16 (Jelly Bean) y la 19 (KitKat). Son seguras; están en línea
                // en tiempo de compilación y no hacen nada en dispositivos anteriores.
                contentView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LOW_PROFILE
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        }
    };

    private final Runnable showPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Retraso de visualizacion de los elementos de la UI
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            controlsView.setVisibility(View.VISIBLE);
        }
    };

    private final View.OnTouchListener delayHideTouchListener =
            new View.OnTouchListener() {
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

    private final Runnable hideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
}