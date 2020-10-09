package com.example.filmmar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentAccion extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frame_accion,container,false);

        ImageView f9 = (ImageView)view.findViewById(R.id.f9);
        ImageView freeGuy = (ImageView)view.findViewById(R.id.freeGuy);
        ImageView topGun = (ImageView)view.findViewById(R.id.topGun);
        ImageView viudanegra = (ImageView)view.findViewById(R.id.viudaNegra);
        ImageView wonder = (ImageView)view.findViewById(R.id.wonderWoman);

        ImageView volv = (ImageView)view.findViewById(R.id.btnvolv);
        volv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FrameCategorias());
                fr.commit();
            }
        });

        f9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Mostrar());
                fr.commit();
            }
        });

        freeGuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Accion2());
                fr.commit();
            }
        });

        topGun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Accion3());
                fr.commit();
            }
        });

        viudanegra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Accion4());
                fr.commit();
            }
        });

        wonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Accion5());
                fr.commit();
            }
        });

        return view;
    }
}
