package com.blaisantkanovels.app.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.blaisantkanovels.app.R;
import com.blaisantkanovels.app.menu.GridModal;
import com.blaisantkanovels.app.menu.MenuAdapter;
import com.blaisantkanovels.app.menu.otrosautores.OtrosAutores;
import com.blaisantkanovels.app.menu.submenu_multiverse.MultiverseClass;

import java.util.ArrayList;
import java.util.List;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

/**
 * A simple {@link Fragment} subclass.
 */
public class two extends Fragment {

    public two() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_two, container, false);

        List<GridModal> listImages = new ArrayList<>();
        listImages.add(new GridModal(
                R.drawable.pic_mutiverse,
                "A MultiVerse of Pain",
                "Publicación: regular",
                "Última publicación:\nCapítulo 7"
        ));
        listImages.add(new GridModal(
                R.drawable.pic_otrosautores,
                "Varios protectos",
                "Publicación: a petición",
                "Última publicación:\nPsicopatía de Cristina Bermejo Rey"
        ));

        final GridView gridView = (GridView) rootView.findViewById(R.id.grid1);
        gridView.setAdapter(new MenuAdapter(getActivity(), listImages));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getContext(), MultiverseClass.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(getContext(), OtrosAutores.class);
                    startActivity(intent);
                }
            }
        });

        FlipperLayout flipperLayout = rootView.findViewById(R.id.carrusel);
        int[] flipImages = new int[] {
            R.drawable.banner_brujas,
            R.drawable.banner_almudena,
            R.drawable.banner_asesinos,
            R.drawable.banner_nomoregod,
            R.drawable.banner_soloalicia
        };
        for (int i = 0; i < flipImages.length; i++) {
            FlipperView flipper = new FlipperView(getContext());
            flipper.setImageDrawable(flipImages[i]);
            flipperLayout.addFlipperView(flipper);
        }

        return rootView;
    }

}
