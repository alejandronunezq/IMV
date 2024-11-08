package com.example.appimv;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuración de borde a borde para que el contenido ocupe toda la pantalla, incluida la barra de estado
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }

        // Conexión de los elementos UI
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        // Configura el listener del botón de login
        loginButton.setOnClickListener(v -> performLogin());
    }

    private void performLogin() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validación de campos vacíos
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Llamada a la API para el login
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<LoginResponse> call = apiService.loginUser(username, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.isSuccess()) {
                        // Redirección solo después del login exitoso
                        Toast.makeText(MainActivity.this, "Inicio de sesioòn exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, GateActivity.class);
                        startActivity(intent);
                        finish(); // Finaliza MainActivity para evitar volver atrás sin autenticación
                    } else {
                        // Muestra el mensaje de error devuelto por la API si el login no es exitoso
                        Toast.makeText(MainActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Muestra un mensaje si la respuesta no es exitosa o no contiene cuerpo
                    Toast.makeText(MainActivity.this, "Error en la respuesta del servidor. Código: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Muestra un mensaje de error en caso de fallo de conexión
                Toast.makeText(MainActivity.this, "Error de conexión: Verifique su conexión a Internet.", Toast.LENGTH_SHORT).show();
                t.printStackTrace(); // Imprime el error en Logcat para depuración
            }
        });
    }
}
