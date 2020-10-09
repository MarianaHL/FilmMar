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

public class FragmetHorror extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_horror, container, false);

        ImageView candyman = (ImageView)view.findViewById(R.id.candyman);
        ImageView tuerca = (ImageView)view.findViewById(R.id.turning);
        ImageView spiral = (ImageView)view.findViewById(R.id.spiral);
        ImageView tranquilo = (ImageView)view.findViewById(R.id.lugarTranquilo);

        ImageView volv = (ImageView)view.findViewById(R.id.btnvolv);
        volv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FrameCategorias());
                fr.commit();
            }
        });

        candyman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Terror1());
                fr.commit();
            }
        });

        tuerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Terror2());
                fr.commit();
            }
        });

        spiral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Terror3());
                fr.commit();
            }
        });

        tranquilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Terror4());
                fr.commit();
            }
        });

        return view;
    }
}
