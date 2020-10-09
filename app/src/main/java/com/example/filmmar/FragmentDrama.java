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

public class FragmentDrama extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_drama, container, false);

        ImageView lucier = (ImageView)view.findViewById(R.id.luciernagas);
        ImageView mec = (ImageView)view.findViewById(R.id.conmigo);
        ImageView song = (ImageView)view.findViewById(R.id.theSong);
        ImageView pajaros = (ImageView)view.findViewById(R.id.pajaros);

        ImageView volv = (ImageView)view.findViewById(R.id.btnvolv);
        volv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FrameCategorias());
                fr.commit();
            }
        });

        lucier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Drama1());
                fr.commit();
            }
        });

        mec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Drama2());
                fr.commit();
            }
        });

        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Drama3());
                fr.commit();
            }
        });

        pajaros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Drama4());
                fr.commit();
            }
        });

        return view;
    }
}
