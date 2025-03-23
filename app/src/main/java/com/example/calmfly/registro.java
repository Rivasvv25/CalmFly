package com.example.calmfly;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity {

    private static final String TAG = "registro";

    private EditText editTextNombre, editTextEmail, editTextPassword, editTextPasswordRepeat, editTextFechaNacimiento;
    private Button buttonRegister;
    private TextView textViewVer, textViewVerRepeat;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar Firebase Auth y Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Referencias a los elementos de la interfaz
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordRepeat = findViewById(R.id.editTextPasswordRepeat);
        editTextFechaNacimiento = findViewById(R.id.editTextFechaNacimiento);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewVer = findViewById(R.id.textViewVer);
        textViewVerRepeat = findViewById(R.id.textViewVerRepeat);

        // Listener para el botón de registro
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        // Listener para mostrar el DatePicker en fecha de nacimiento
        editTextFechaNacimiento.setOnClickListener(v -> mostrarDatePicker());

        // Listener para mostrar/ocultar contraseña en Contraseña
        textViewVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(editTextPassword, textViewVer);
            }
        });

        // Listener para mostrar/ocultar contraseña en Repetir Contraseña
        textViewVerRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(editTextPasswordRepeat, textViewVerRepeat);
            }
        });
    }

    private void togglePasswordVisibility(EditText editText, TextView textView) {
        if (editText.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            // Mostrar contraseña
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            textView.setText("Ocultar");
        } else {
            // Ocultar contraseña
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            textView.setText("Mostrar");
        }
        editText.setSelection(editText.getText().length()); // Cursor al final
    }

    private void mostrarDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            editTextFechaNacimiento.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }, year, month, day);
        datePickerDialog.show();
    }

    private void registrarUsuario() {
        final String nombre = editTextNombre.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String passwordRepeat = editTextPasswordRepeat.getText().toString().trim();
        final String fechaNacimiento = editTextFechaNacimiento.getText().toString().trim();

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty() || fechaNacimiento.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        } else if (!password.equals(passwordRepeat)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        } else if (!validarPassword(password)) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres, un número, una letra mayúscula y un símbolo especial.", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            int edad = calcularEdad(fechaNacimiento);

                            Map<String, Object> userData = new HashMap<>();
                            userData.put("nombre", nombre);
                            userData.put("email", email);
                            userData.put("fechaNacimiento", fechaNacimiento);
                            userData.put("edad", edad);
                            userData.put("fechaRegistro", System.currentTimeMillis());

                            db.collection("usuarios").document(user.getUid())
                                    .set(userData)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(registro.this, menu.class);
                                        startActivity(intent);
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.w(TAG, "Error al guardar los datos del usuario", e);
                                        Toast.makeText(registro.this, "Error al registrar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(registro.this, "Error al registrar: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validarPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
        return password.matches(regex);
    }

    private int calcularEdad(String fechaNacimiento) {
        String[] partesFecha = fechaNacimiento.split("/");
        int dia = Integer.parseInt(partesFecha[0]);
        int mes = Integer.parseInt(partesFecha[1]);
        int anio = Integer.parseInt(partesFecha[2]);

        Calendar fechaNacimientoCalendar = Calendar.getInstance();
        fechaNacimientoCalendar.set(anio, mes - 1, dia);

        Calendar hoy = Calendar.getInstance();
        int edad = hoy.get(Calendar.YEAR) - fechaNacimientoCalendar.get(Calendar.YEAR);
        if (hoy.get(Calendar.MONTH) < fechaNacimientoCalendar.get(Calendar.MONTH) ||
                (hoy.get(Calendar.MONTH) == fechaNacimientoCalendar.get(Calendar.MONTH) &&
                        hoy.get(Calendar.DAY_OF_MONTH) < fechaNacimientoCalendar.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        return edad;
    }
}
