package com.blairsaintklaus.sendthezentinel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.blairsaintklaus.adapters.ChatAdapter;
import com.blairsaintklaus.daos.Mensaje;
import com.blairsaintklaus.mainlistchat.R;
import com.blairsaintklaus.mainlistchat.databinding.OpenNewChatfullscreenBinding;

import java.util.ArrayList;
import java.util.List;

public class OpenChatWithUser extends AppCompatActivity {

    private EditText messageSend;
    private ListView listadoChat;
    private Button send;
    private List<Mensaje> messages;
    private ChatAdapter adapter;
    private View contentView;
    private View controlsView;
    private android.support.v7.widget.Toolbar navegation;
    private OpenNewChatfullscreenBinding binding;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        binding = OpenNewChatfullscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        contentView = binding.chatList;
        controlsView = binding.newChat;

        messages = new ArrayList();
        adapter = new ChatAdapter(
                this, R.layout.item_message_sent,messages
        );

        listadoChat = (ListView) findViewById(contentView.getId());
        listadoChat.setAdapter(adapter);
        //navegation = findViewById(R.id.topnavegation);
        //setSupportActionBar(navegation);
        //getSupportActionBar().setTitle("Be My Zentinel");

        controlsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText messageText = (EditText) findViewById(R.id.send_message_text);
                if (messageText.getText().toString().equals("") ||
                    messageText.getText().toString().equals("Escribe algo")) {
                    Toast.makeText(OpenChatWithUser.this,
                            "¡Escribe algo, anormal!", Toast.LENGTH_LONG).show();
                }else {
                    Mensaje message = new Mensaje();
                    message.setText(messageText.getText().toString());
                    messages.add(message);
                    adapter.notifyDataSetChanged();
                    messageText.setText("");
                }
            }
        });

        /*navegation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        MainFullScreenActivity.class);
                intent.putExtra("Marco Aurelio", (CharSequence) messages);
                finish();
            }
        });*/
    }
}
