package com.blairsaintklaus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.blairsaintklaus.mainlistchat.R;
import com.blairsaintklaus.daos.Mensaje;

import java.util.List;

public class ChatAdapter extends ArrayAdapter {
    private List<Mensaje> messages;
    private LayoutInflater inflater;

    public ChatAdapter(Context context, int resource, List<Mensaje> messages) {
        super(context, resource, messages);
        this.messages = messages;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Mensaje message = messages.get(position);

        View view;
        if (message.isSendByMe()) {
            view = inflater.inflate(R.layout.item_message_sent, parent, false);
            TextView messageTextView = view.findViewById(R.id.message_by_me);
            messageTextView.setText(message.getText());
        } else {
            view = inflater.inflate(R.layout.item_message_received, parent, false);
            TextView messageTextView = view.findViewById(R.id.message_for_user);
            messageTextView.setText(message.getText());
        }
        return view;
    }
}
