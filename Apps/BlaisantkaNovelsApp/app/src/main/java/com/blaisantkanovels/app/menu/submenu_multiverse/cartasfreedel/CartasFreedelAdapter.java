package com.blaisantkanovels.app.menu.submenu_multiverse.cartasfreedel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blaisantkanovels.app.R;

import java.util.List;

public class CartasFreedelAdapter extends BaseAdapter {

    private Context context;
    private List<GridModalCaps> listaCapitulos;

    public CartasFreedelAdapter(Context c, List<GridModalCaps> listaCapitulos) {
        this.context = c;
        this.listaCapitulos = listaCapitulos;
    }

    @Override
    public int getCount() {
        return listaCapitulos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCapitulos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaCapitulos.get(position).getImageView();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ImageView imageCap;
        TextView textCap;

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridcapslist, parent, false);
            view.setElevation(0);

            imageCap = view.findViewById(R.id.imagecap);
            textCap = view.findViewById(R.id.titlecap);

            imageCap.setImageResource(listaCapitulos.get(position).getImageView());
            textCap.setText(listaCapitulos.get(position).getText());
        }else{
            view = convertView;
        }

        return view;
    }
}
