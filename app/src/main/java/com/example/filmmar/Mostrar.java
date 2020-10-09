package com.example.filmmar;

import android.content.Context;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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

public class Mostrar extends Fragment {

    private DatabaseReference mDataBase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mostrar,container,false);

        ImageView volver = (ImageView)view.findViewById(R.id.imgVolver);
        final TextView titulo = (TextView)view.findViewById(R.id.txtTitulo);
        final TextView sino = (TextView)view.findViewById(R.id.txtSinopsis);
        final TextView fecha = (TextView)view.findViewById(R.id.txtEstreno);
        final TextView dir = (TextView)view.findViewById(R.id.txtDirector);


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new FragmentAccion());
                fr.commit();
            }
        });

        //MediaController mediaController = new MediaController(this);

        VideoView videoView = (VideoView)view.findViewById(R.id.vid);
        videoView.setMediaController(new MediaController(getActivity()));
        //mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/filmmar-f81e8.appspot.com/o/accion%2FFast%20%26%20Furious%209%20%E2%80%93%20Tra%CC%81iler%20Oficial%20(Universal%20Pictures)%20%20%20HD.mp4?alt=media&token=e3bac91f-d3de-4459-8fd9-dda4ae23f38e");
        videoView.setVideoURI(uri);
        videoView.start();

        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child("0").addValueEventListener(new ValueEventListener() {
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
