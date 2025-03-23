package com.example.calmfly;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class registros_usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_usuario);

        // Referencia a un TextView para mostrar los registros
        TextView textViewRegistros = findViewById(R.id.textViewRegistros);

        // Simulación de datos (puedes reemplazar esto con datos reales de una base de datos)
        String registros = "Registro 1: Sesión de relajación\n" +
                "Registro 2: Ejercicio de respiración\n" +
                "Registro 3: Meditación guiada\n";

        // Mostrar los registros en el TextView
        textViewRegistros.setText(registros);
    }
}
