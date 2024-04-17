package com.blaisantkanovels.app.menu.submenu_multiverse.shortstories.moderesuspalabras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blaisantkanovels.app.R;

import java.util.List;

public class ModereAdapter extends BaseAdapter {

    private Context context;
    private List<GridModalModere> listaRelatos;

    public ModereAdapter(Context c, List<GridModalModere> listaRelatos) {
        this.context = c;
        this.listaRelatos = listaRelatos;
    }

    @Override
    public int getCount() {
        return listaRelatos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaRelatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaRelatos.get(position).getImageView();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ImageView imageRelato;
        TextView textTitulo;
        TextView textAuthor;
        TextView textPublic;

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridlistserie, parent, false);
            view.setElevation(0);

            imageRelato = view.findViewById(R.id.gridImage);
            textTitulo = view.findViewById(R.id.titulo);
            textAuthor = view.findViewById(R.id.author);
            textPublic = view.findViewById(R.id.publicado);

            imageRelato.setImageResource(listaRelatos.get(position).getImageView());
            textTitulo.setText(listaRelatos.get(position).getText());
            textAuthor.setText(listaRelatos.get(position).getAuthor());
            textPublic.setText(listaRelatos.get(position).getPublicated());
        }else{
            view = convertView;
        }

        return view;
    }
}
