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

public class FragmentRomantica extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_romantico, container, false);

        ImageView emma = (ImageView)view.findViewById(R.id.emma);
        ImageView barrio = (ImageView)view.findViewById(R.id.nuevaYprk);
        ImageView belle = (ImageView)view.findViewById(R.id.belle);

        ImageView volv = (ImageView)view.findViewById(R.id.btnvolv);
        volv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FrameCategorias());
                fr.commit();
            }
        });

        emma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Romantica1());
                fr.commit();
            }
        });

        barrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Romantica2());
                fr.commit();
            }
        });

        belle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Romantica3());
                fr.commit();
            }
        });

        return view;
    }
}
