package com.example.calmfly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class recursos extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recursos);

        // Referencia al botón de volver al menú
        Button botonVolverMenu = findViewById(R.id.botonVolverMenu);
        botonVolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Regresar al menú principal
                Intent intent = new Intent(recursos.this, menu_principal.class);
                startActivity(intent);
                finish(); // Cierra la actividad actual para evitar acumulación en la pila de actividades
            }
        });
    }
}
