package com.example.filmmar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        Button boton = (Button)findViewById(R.id.hola);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(InicioSesion.this, Categorias.class);
                startActivity(intent);
            }
        });

        Button boton2 = (Button)findViewById(R.id.btnReg);

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(InicioSesion.this, Registro.class);
                startActivity(intent);
            }
        });
    }
}
