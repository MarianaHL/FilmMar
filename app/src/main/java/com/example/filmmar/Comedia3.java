package com.example.filmmar;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Comedia3 extends Fragment {

    private DatabaseReference mDataBase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.accion2,container,false);

        ImageView volver = (ImageView)view.findViewById(R.id.imgVolver1);
        final TextView titulo = (TextView)view.findViewById(R.id.txtTitulo1);
        final TextView sino = (TextView)view.findViewById(R.id.txtSinopsis1);
        final TextView fecha = (TextView)view.findViewById(R.id.txtEstreno1);
        final TextView dir = (TextView)view.findViewById(R.id.txtDirector1);


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FragmentComedia());
                fr.commit();
            }
        });

        //MediaController mediaController = new MediaController(this);

        VideoView videoView = (VideoView)view.findViewById(R.id.vid1);
        videoView.setMediaController(new MediaController(getActivity()));
        //mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/filmmar-f81e8.appspot.com/o/comedia%2FOPERACI%C3%93N%20CAMAR%C3%93N%20I%20Tr%C3%A1iler%20oficial%20I%2013%20de%20marzo%20en%20cines.mp4?alt=media&token=1914d78d-939b-4795-a41c-7fdd042e52dc");
        videoView.setVideoURI(uri);
        videoView.start();

        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child("comedy2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String nombre = dataSnapshot.child("nombre").getValue().toString();
                    titulo.setText(nombre);
                    String sinopsis = dataSnapshot.child("sinopsis").getValue().toString();
                    sino.setText(sinopsis);
                    String estreno = dataSnapshot.child("estreno").getValue().toString();
                    fecha.setText("Fecha de estreno: "+estreno);
                    String director = dataSnapshot.child("director").getValue().toString();
                    dir.setText("Director: "+director);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
