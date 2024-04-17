package com.blaisantkanovels.app.menu.otrosautores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blaisantkanovels.app.R;

import java.util.List;

public class MenuAdapterOtrosAutores extends BaseAdapter {

    private Context context;
    private List<GridModalOtrosAutores> imageList;
    private LayoutInflater inflater;

    public MenuAdapterOtrosAutores(Context c, List<GridModalOtrosAutores> imageList) {
        this.context = c;
        this.imageList = imageList;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return imageList.get(position).getImageView();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ImageView imageView;
        TextView textAutor;
        TextView anoPubli;
        TextView ultimaPubli;

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridlist, parent, false);
            view.setElevation(0);

            imageView = view.findViewById(R.id.imagegrid);
            textAutor = view.findViewById(R.id.autorpubli);
            anoPubli = view.findViewById(R.id.anopubli);
            ultimaPubli = view.findViewById(R.id.ultpubli);

            imageView.setImageResource(imageList.get(position).getImageView());
            textAutor.setText(imageList.get(position).getTextProyect());
            anoPubli.setText(imageList.get(position).getAnoPubli());
            ultimaPubli.setText(imageList.get(position).getAutoria());
        }else{
            view = convertView;
        }

        return view;
    }
}
