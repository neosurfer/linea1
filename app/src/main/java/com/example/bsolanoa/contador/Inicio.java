package com.example.bsolanoa.contador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {
    Button btn_torniquetes, btn_exonerados, btn_discapacitados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btn_torniquetes = (Button)findViewById(R.id.btn_torniquetes);
        btn_exonerados = (Button)findViewById(R.id.btn_exonerados);
        btn_discapacitados = (Button)findViewById(R.id.btn_discapacitados);

        btn_torniquetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        btn_exonerados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),segunda.class);
                startActivity(i);
            }
        });

        btn_discapacitados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),tercera.class);
                startActivity(i);
            }
        });
    }

}
