package com.example.calmfly;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class foro extends AppCompatActivity {

    private EditText barraBusqueda;
    private Button botonCrearForo, botonLeerForos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        // Referencias a los botones y barra de búsqueda
        barraBusqueda = findViewById(R.id.barraBusqueda);
        botonCrearForo = findViewById(R.id.botonCrearForo);
        botonLeerForos = findViewById(R.id.botonLeerForos);

        // Acción de "Crear foro nuevo"
        botonCrearForo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para crear foro nuevo
                // Aquí puedes añadir la funcionalidad de crear un foro
            }
        });

        // Acción de "Leer foros"
        botonLeerForos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para leer foros
                // Aquí puedes agregar la funcionalidad de leer foros
            }
        });
    }
}
