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

public class FrameRecomendaciones extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frame_recomendaciones,container,false);

        ImageView f9 = (ImageView)view.findViewById(R.id.f9);
        ImageView bob = (ImageView)view.findViewById(R.id.bobEsponja);
        ImageView mulan = (ImageView)view.findViewById(R.id.mulan);
        ImageView jungle = (ImageView)view.findViewById(R.id.jungle);
        ImageView wonder = (ImageView)view.findViewById(R.id.wonderWoman);
        ImageView spiral = (ImageView)view.findViewById(R.id.spiral);
        ImageView venom = (ImageView)view.findViewById(R.id.venom);
        ImageView camaron = (ImageView)view.findViewById(R.id.operacionCamaron);

        f9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Mostrar2());
                fr.commit();
            }
        });

        bob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Animacion22());
                fr.commit();
            }
        });

        mulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Aventura44());
                fr.commit();
            }
        });

        jungle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Aventura33());
                fr.commit();
            }
        });

        wonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Accion55());
                fr.commit();
            }
        });

        spiral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Terror33());
                fr.commit();
            }
        });

        venom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Cf33());
                fr.commit();
            }
        });

        camaron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Comedia33());
                fr.commit();
            }
        });

        return view;
    }
}
