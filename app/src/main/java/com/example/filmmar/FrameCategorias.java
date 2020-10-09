package com.example.filmmar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.filmmar.R;

public class FrameCategorias extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frame_categorias,container,false);

        Button btnAc = (Button)view.findViewById(R.id.btnAccion);
        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FragmentAccion());
                fr.commit();
            }
        });

        Button btnFic = (Button)view.findViewById(R.id.btnFiccion);
        btnFic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FragmentFiccion());
                fr.commit();
            }
        });

        Button btnComedy = (Button)view.findViewById(R.id.btnComedia);
        btnComedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FragmentComedia());
                fr.commit();
            }
        });

        Button btnAven = (Button)view.findViewById(R.id.btnAventura);
        btnAven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FragmentAventura());
                fr.commit();
            }
        });

        Button btnDrama = (Button)view.findViewById(R.id.btnDrama);
        btnDrama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FragmentDrama());
                fr.commit();
            }
        });

        Button btnHorror = (Button)view.findViewById(R.id.btnMiedo);
        btnHorror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FragmetHorror());
                fr.commit();
            }
        });

        Button btnAnim = (Button)view.findViewById(R.id.btnAnimacion);
        btnAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FragmentAnimacion());
                fr.commit();
            }
        });

        Button btnRoman = (Button)view.findViewById(R.id.btnRomantica);
        btnRoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FragmentRomantica());
                fr.commit();
            }
        });



        return view;
    }

}
