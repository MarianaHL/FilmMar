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

public class FragmentAventura extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_aventura, container, false);

        ImageView artemis = (ImageView)view.findViewById(R.id.artemis);
        ImageView afterlife = (ImageView)view.findViewById(R.id.afterlife);
        ImageView jungle = (ImageView)view.findViewById(R.id.jungle);
        ImageView mulan = (ImageView)view.findViewById(R.id.mulan);

        ImageView volv = (ImageView)view.findViewById(R.id.btnvolv);
        volv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FrameCategorias());
                fr.commit();
            }
        });

        artemis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Aventura1());
                fr.commit();
            }
        });

        afterlife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Aventura2());
                fr.commit();
            }
        });

        jungle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Aventura3());
                fr.commit();
            }
        });

        mulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new Aventura4());
                fr.commit();
            }
        });

        return view;
    }
}
