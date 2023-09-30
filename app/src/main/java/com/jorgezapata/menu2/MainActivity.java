package com.jorgezapata.menu2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    Button google,alarma,llamada,mapas,camara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        google = findViewById(R.id.google);
        alarma = findViewById(R.id.alarma);
        llamada = findViewById(R.id.llamar);
        mapas = findViewById(R.id.mapas);
        camara = findViewById(R.id.camara);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irAGoogle = new Intent(Intent.ACTION_VIEW);
                irAGoogle.setData(Uri.parse("http://www.google.com"));
                startActivity(irAGoogle);
            }
        });

        alarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            crearAlarma();
            }
        });

        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            crearLlamada();
            }
        });

        mapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ubicacion = Uri.parse("geo:37.7749,-122.4194"); // Ejemplo de una Uri con coordenadas de ubicación
                activarmapa(ubicacion); // Llama a activarmapa() con la Uri de ubicación
            }
        });

        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent,1);

                }
            }
        });
    }

    public void activarmapa(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    private void crearAlarma() {
        Intent alarma = new Intent(AlarmClock.ACTION_SET_ALARM);
        alarma.putExtra(AlarmClock.EXTRA_MESSAGE,"Gimnasio");
        alarma.putExtra(AlarmClock.EXTRA_HOUR,10);
        alarma.putExtra(AlarmClock.EXTRA_MINUTES,30);
        if(alarma.resolveActivity(getPackageManager())!=null){
            startActivity(alarma);
        }
    }

    public void crearLlamada() {
        String phoneNumber = "32261884493"; // Debes proporcionar el número de teléfono válido aquí
       Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + phoneNumber));
       startActivity(intent);
    }
}