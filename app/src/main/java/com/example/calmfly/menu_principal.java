package com.example.calmfly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class menu_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // Referencia al botón de cerrar sesión
        Button botonCerrarSesion = findViewById(R.id.botonCerrarSesion);
        botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_principal.this, iniciosesion.class);
                startActivity(intent);
                finish(); // Cierra la actividad actual
            }
        });

        // Referencia al botón de Foro
        Button botonForo = findViewById(R.id.botonForo);
        botonForo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_principal.this, foro.class);
                startActivity(intent);
            }
        });

        // Referencia al botón de Recursos
        Button botonRecursos = findViewById(R.id.botonRecursos);
        botonRecursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_principal.this, recursos.class);
                startActivity(intent);
            }
        });

        // Referencia al botón de Mostrar Registros
        Button botonMostrarRegistros = findViewById(R.id.botonMostrarRegistros);
        botonMostrarRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_principal.this, registros_usuario.class);
                startActivity(intent);
            }
        });
    }
}
