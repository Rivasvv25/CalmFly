package com.example.calmfly;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;  // Importar para mostrar los logs
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;  // Importar FirebaseApp

public class MainActivity extends AppCompatActivity {

    private static final int TIEMPO_CARGA = 2000; // Tiempo de carga en milisegundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);

        // Comprobar si Firebase está conectado
        if (FirebaseApp.getApps(this).isEmpty()) {
            Log.e("Firebase", "❌ Firebase NO está conectado");
        } else {
            Log.d("Firebase", "✅ Firebase conectado correctamente");
        }

        // Después del tiempo de carga, iniciar la siguiente actividad
        new Handler().postDelayed(() -> {
            startActivity(new Intent(MainActivity.this, Inicio.class));
            finish();
        }, TIEMPO_CARGA);
    }
}
