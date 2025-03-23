package com.example.calmfly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class iniciosesion extends AppCompatActivity {

    // Instancia de FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);

        // Inicializar FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Referencias a los elementos (CORREGIDOS)
        EditText editTextEmail = findViewById(R.id.editTextEmail); // Cambio aquí
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin); // Cambio aquí
        Button botonRegistro = findViewById(R.id.botonaregistro);

        // Evento click para iniciar sesión
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = editTextEmail.getText().toString().trim(); // Cambio aquí
                String password = editTextPassword.getText().toString().trim();

                // Validación de campos vacíos
                if (correo.isEmpty() || password.isEmpty()) {
                    Toast.makeText(iniciosesion.this, "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Iniciar sesión con Firebase Authentication
                mAuth.signInWithEmailAndPassword(correo, password)
                        .addOnCompleteListener(iniciosesion.this, task -> {
                            if (task.isSuccessful()) {
                                // Si el inicio de sesión es exitoso
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(iniciosesion.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                                // Redirigir a la actividad del menú
                                Intent intent = new Intent(iniciosesion.this, menu_principal.class);
                                startActivity(intent);
                                finish(); // Cierra la actividad actual
                            } else {
                                // Si el inicio de sesión falla
                                Toast.makeText(iniciosesion.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Evento click para ir a registro.java
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(iniciosesion.this, registro.class);
                startActivity(intent);
            }
        });
    }
}
