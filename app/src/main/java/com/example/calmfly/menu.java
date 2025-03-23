package com.example.calmfly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {

    Button adelante, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        // Referencias a los botones
        adelante = findViewById(R.id.adelante);
        button2 = findViewById(R.id.button2);

        // Botón para ir a registro.java
        adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this, registro.class);
                startActivity(i);
            }
        });

        // Botón para ir a iniciosesion.java
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this, iniciosesion.class);
                startActivity(i);
            }
        });
    }
}
